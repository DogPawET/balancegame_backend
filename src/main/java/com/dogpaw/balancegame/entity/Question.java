package com.dogpaw.balancegame.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "new_collections")
public class Question {

    @Id
    @NotNull
    private ObjectId id;

    @NotNull
    private String firstOption;

    @NotNull
    private String secondOption;

    @Builder
    public Question(String firstOption, String secondOption) {
        this.firstOption = firstOption;
        this.secondOption = secondOption;
    }
}
