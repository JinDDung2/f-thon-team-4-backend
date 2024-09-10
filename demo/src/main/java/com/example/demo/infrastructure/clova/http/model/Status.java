package com.example.demo.infrastructure.clova.http.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Status {
    private String code;
    private String message;

    public Status(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
