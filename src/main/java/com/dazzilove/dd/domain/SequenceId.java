package com.dazzilove.dd.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seq")
@Data
@ToString
public class SequenceId {
    @Id private String id;
    private int seq;
}
