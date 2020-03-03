package com.dazzilove.dd.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@ToString
public class WordUnitExam {
    @Id private int wordUnitExamSeq;
    private UUID wordUnitId;
    private LocalDateTime createdAt;
    private List<Word> words = new ArrayList<>();

    public List<Word> getWords() {
        if (words == null) {
            return new ArrayList<>();
        }
        return words;
    }
}
