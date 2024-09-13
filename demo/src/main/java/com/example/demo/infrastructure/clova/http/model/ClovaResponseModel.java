package com.example.demo.infrastructure.clova.http.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClovaResponseModel {
    private Status status;
    private Result result;

    public ClovaResponseModel(Status status, Result result) {
        this.status = status;
        this.result = result;
    }

    public String getContent() {
        return this.result.getMessage().getContent();
    }
}
