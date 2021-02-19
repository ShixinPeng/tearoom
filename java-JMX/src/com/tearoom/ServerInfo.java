package com.tearoom;

public class ServerInfo implements ServerInfoMBean {


    private Integer code;

    @Override
    public int getServerInfoCount() {
        return code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
