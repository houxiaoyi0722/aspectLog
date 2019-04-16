package com.aspectlog.service;

import com.aspectlog.entity.OperationLog;
import com.aspectlog.mapper.OperationLogMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    public void insertUserOptionLog(OperationLog operationLog){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("opIp",operationLog.getOpIp());
        hashMap.put("createTime",operationLog.getCreateTime());
        hashMap.put("failDetail",operationLog.getFailDetail());
        hashMap.put("opExplain",operationLog.getOpExplain());
        hashMap.put("opServerId",operationLog.getOpServerId());
        hashMap.put("opStatus",operationLog.getOpStatus());
        hashMap.put("opTerminal",operationLog.getOpTerminal());
        hashMap.put("typeOfService",operationLog.getTypeOfService());
        hashMap.put("username",operationLog.getUsername());
        //获取当前日期生成表名
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date.getTime());
        String table = format.substring(0,format.lastIndexOf("-"));

        hashMap.put("table",table);
        operationLogMapper.insertUserOptionLog(hashMap);
    }
    // 秒 分 时 日 月 周 年 每月最后一天的 23：59：00 生成一张新表
    //@Scheduled(cron = "0 59 23 L * ?")
    @Scheduled(cron = "0 24 20 16 * ?")
    public void createTable(){
        //获取当前日期生成表名
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date.getTime());
        String table = format.substring(0,format.lastIndexOf("-"));
        System.out.println("生成新表"+table);
        operationLogMapper.createTable(table);
    }

}
