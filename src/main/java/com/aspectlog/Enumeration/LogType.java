package com.aspectlog.Enumeration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum LogType {
    INSERT("INSERT", "保存"),
    UPDATE("UPDATE", "修改"),
    DELETEMORE("DELETEMORE", "批删"),
    DELETE("DELETE", "删除"),
    QUERY("QUERY", "查询"),
    LOGIN("LOGIN", "登录");

    public String value;
    public String displayCode;

    LogType(String value, String displayCode) {
        this.value = value;
        this.displayCode = displayCode;
    }

    public static List<Map<String,Object>> getListMap(){
        List<Map<String,Object>> maplist = new ArrayList<Map<String,Object>>();
        for (LogType s : LogType.values()) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",s.value);
            map.put("name", s.displayCode);
            maplist.add(map);
        }
        return maplist;
    }

}
