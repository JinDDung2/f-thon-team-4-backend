package com.example.demo.infrastructure.clova.http.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AiFilter {
    private String groupName;
    private String name;
    private long score;
    private String result;

    public AiFilter(String groupName, String name, long score, String result) {
        this.groupName = groupName;
        this.name = name;
        this.score = score;
        this.result = result;
    }
}
