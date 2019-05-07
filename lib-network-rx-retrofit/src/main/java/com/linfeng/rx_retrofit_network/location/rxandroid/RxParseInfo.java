package com.linfeng.rx_retrofit_network.location.rxandroid;

import com.google.gson.JsonObject;


public class RxParseInfo {
    public final static RxParseInfo DEFAULT =
            new RxParseInfo("code", "data", "msg");

    private final String codeKey;
    private final String dataKey;


    private final String msgKey;

    public RxParseInfo(String codeKey, String dataKey, String msgKey) {
        this.codeKey = codeKey;
        this.dataKey = dataKey;
        this.msgKey = msgKey;
    }

    public boolean hasKey(JsonObject asJsonObject) throws Exception {
        boolean hasCode = asJsonObject.has(codeKey);
        boolean hasData = asJsonObject.has(dataKey);
        boolean hasMsg = asJsonObject.has(msgKey);
        return hasCode && hasData && hasMsg;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public String getDataKey() {
        return dataKey;
    }

    public String getMsgKey() {
        return msgKey;
    }
}
