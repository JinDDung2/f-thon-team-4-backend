package com.example.demo.infrastructure.clova.http.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageModel {
    private String role;
    private String content;

    public MessageModel(String role, String content) {
        this.role = role;
        this.content = content;
    }
}

