package com.aspectlog.entity;

public class OperationLog {
    private String id;
    private String username;//用户名
    private String createTime;//操作时间
    private String opIp;//操作ip
    private String opTerminal;//操作终端
    private String opServerId;//操作的业务id
    private String opExplain;//操作说明
    private String opStatus;//操作状态
    private String failDetail;//失败详情
    private String typeOfService;//业务类型

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public String getOpTerminal() {
        return opTerminal;
    }

    public void setOpTerminal(String opTerminal) {
        this.opTerminal = opTerminal;
    }

    public String getOpServerId() {
        return opServerId;
    }

    public void setOpServerId(String opServerId) {
        this.opServerId = opServerId;
    }

    public String getOpExplain() {
        return opExplain;
    }

    public void setOpExplain(String opExplain) {
        this.opExplain = opExplain;
    }

    public String getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(String opStatus) {
        this.opStatus = opStatus;
    }

    public String getFailDetail() {
        return failDetail;
    }

    public void setFailDetail(String failDetail) {
        this.failDetail = failDetail;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public OperationLog(String id, String username, String createTime, String opIp, String opTerminal, String opServerId, String opExplain, String opStatus, String failDetail, String typeOfService) {
        this.id = id;
        this.username = username;
        this.createTime = createTime;
        this.opIp = opIp;
        this.opTerminal = opTerminal;
        this.opServerId = opServerId;
        this.opExplain = opExplain;
        this.opStatus = opStatus;
        this.failDetail = failDetail;
        this.typeOfService = typeOfService;
    }

    public OperationLog() {
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", createTime='" + createTime + '\'' +
                ", opIp='" + opIp + '\'' +
                ", opTerminal='" + opTerminal + '\'' +
                ", opServerId='" + opServerId + '\'' +
                ", opExplain='" + opExplain + '\'' +
                ", opStatus='" + opStatus + '\'' +
                ", failDetail='" + failDetail + '\'' +
                ", typeOfService='" + typeOfService + '\'' +
                '}';
    }
}
