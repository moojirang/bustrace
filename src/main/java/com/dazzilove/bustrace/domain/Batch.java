package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Batch {

    public static final String BASIC_URL = "http://bustrace.11st.co.kr";

    private String name;
    private String method;
    private String schedule;
    private String url;

    private List<BatchParam> batchParams = new ArrayList<>();

    public String getFullUrl() {
        String fullUrl = BASIC_URL + url;

        if (batchParams == null || batchParams.size() == 0) {
            return fullUrl;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("?");
        boolean hasParams = false;
        for (BatchParam batchParam: batchParams) {
            if (hasParams) sb.append("&");
            sb.append(batchParam.getName());
            sb.append("=");
            sb.append(batchParam.getDefaultValue());
            hasParams = true;
        }

        return fullUrl + sb.toString();
    }

}
