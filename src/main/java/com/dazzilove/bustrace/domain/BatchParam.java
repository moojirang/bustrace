package com.dazzilove.bustrace.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BatchParam {

    public static final String INPUT_TYPE_TEXT = "text";

    private String name;
    private String inputType;
    private String defaultValue;
}
