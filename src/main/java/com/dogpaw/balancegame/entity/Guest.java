package com.dogpaw.balancegame.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "new_collections")
public class Guest {

    @NotNull
    private String name;

    @NotNull
    private List<Byte> answers;

    private Integer score;

    private Integer percentage;

    @Builder
    public Guest(String name, List<Byte> answers, Integer score, Integer percentage) {
        this.name = name;
        this.answers = answers;
        this.score = score;
        this.percentage = percentage;
    }
}
