package com.example.demo.infrastructure.clova.http.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClovaRequestModel {
    private String topP;
    private String topK;
    private String maxTokens;
    private String temperature;
    private String repeatPenalty;
    private List<String> stopBefore;
    private boolean includeAiFilters;
    private String seed;

    private List<MessageModel> messages;

    public ClovaRequestModel(String topP, String topK, String maxTokens, String temperature, String repeatPenalty, List<String> stopBefore, boolean includeAiFilters, String seed, List<MessageModel> messages) {
        this.topP = topP;
        this.topK = topK;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.repeatPenalty = repeatPenalty;
        this.stopBefore = stopBefore;
        this.includeAiFilters = includeAiFilters;
        this.seed = seed;
        this.messages = messages;
    }
}
