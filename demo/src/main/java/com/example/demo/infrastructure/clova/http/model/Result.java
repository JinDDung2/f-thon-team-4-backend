package com.example.demo.infrastructure.clova.http.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Result {
    private MessageModel message;
    private int inputLength;
    private int outputLength;
    private String stopReason;
    private long seed;
    private List<AiFilter> aiFilter;

    public Result(MessageModel message, int inputLength, int outputLength, String stopReason, long seed, List<AiFilter> aiFilter) {
        this.message = message;
        this.inputLength = inputLength;
        this.outputLength = outputLength;
        this.stopReason = stopReason;
        this.seed = seed;
        this.aiFilter = aiFilter;
    }
}
