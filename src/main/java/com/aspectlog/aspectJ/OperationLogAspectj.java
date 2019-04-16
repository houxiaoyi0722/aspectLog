package com.aspectlog.aspectJ;

import com.aspectlog.Enumeration.Successed;
import com.aspectlog.annotation.Log;
import com.aspectlog.entity.OperationLog;
import com.aspectlog.entity.User;
import com.aspectlog.service.OperationLogService;
import com.aspectlog.util.RequestIpUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.Method;

/**
 * 记录被自定义注解标记的方法，所产生的操作日志
 * 其中在执行方法前获取相应的注解值和方法日志
 * 被标记方法执行完成后，保存所产生的日志
 * 如果标记方法在执行过程中失败或异常，则记录失败的日志
 */
@Aspect
@Component
public class OperationLogAspectj {

    private static Logger logger = Logger.getLogger(OperationLogAspectj.class);

    @Autowired
    private OperationLogService operationLogService;


    private Log log;

    private OperationLog opg = new OperationLog();

    public OperationLogAspectj() {
    }

    @Pointcut("@annotation(com.aspectlog.annotation.Log)")//-------@Pointcut("@annotation(com.aspectlog.annotation.Log)")
    public void executeLog(){};

    @Before(value = "executeLog()")
    public void initOperationLog(JoinPoint jp){//---------------------------------jp
        //获取切点上的方法名
        String signatureName  = jp.getSignature().getName();
        //获取定义切点的类的class
        Class targetClass = jp.getTarget().getClass();
        //获取请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//holder
        //获取客户端信息
        opg.setOpTerminal(request.getHeader("User-Agent"));
        logger.debug("---------------request----------------"+request);

        //获取ip
        String remoteIp = RequestIpUtil.getRemoteHost(request);
        opg.setOpIp(remoteIp);

        //获取并set 服务id
        String opServiceId = request.getAttribute("opServiceId") == null? request.getParameter("opServiceId") : (String) request.getAttribute("opServiceId");
        opg.setOpServerId(opServiceId);

        //通过反射获取目标类全部方法
        Method[] methods = targetClass.getDeclaredMethods();

        //遍历找到目标方法并获取方法注解
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(signatureName)){
                log = method.getAnnotation(Log.class);
            }
        }
        //将获取到的注解中的值 set给对象
        if (log!=null){
            opg.setOpExplain(log.contect());
            opg.setTypeOfService(log.type().name());
        }
    }

    @AfterReturning(value = "executeLog()",returning = "result")
    public void executeResultMethod(Object result){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//holder
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            opg.setUsername(user.getUsername());
        }else{
            opg.setUsername("未登录用户-"+opg.getOpIp());
        }
        logger.debug(opg);
        logger.debug("后置通知");
        this.createLog(Successed.SUCCESSED,"");
    }

    @AfterThrowing(value = "executeLog()" ,argNames = "exception" ,throwing = "exception")
    public void throwProcess(Exception exception){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//holder
        User user = (User) request.getSession().getAttribute("user");
        if (user!=null){
            opg.setUsername(user.getUsername());
        }else{
            opg.setUsername("未登录用户-"+opg.getOpIp());
        }
        logger.debug("出错了"+exception.toString());
        opg.setOpServerId("-1");
        this.createLog(Successed.FAILED,exception.toString());
    }

    private void createLog(Successed successed, String desc) {

        opg.setOpStatus(successed.value);
        //格式化日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date.getTime());

        opg.setCreateTime(format);
        //失败详情
        opg.setFailDetail(desc);
        logger.debug(opg);

        operationLogService.insertUserOptionLog(opg);
    }

}
