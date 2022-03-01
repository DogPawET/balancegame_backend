package com.dogpaw.balancegame.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "질문")
public class Question {

    @NotNull
    @Schema(description = "옵션 1")
    private String firstOption;

    @NotNull
    @Schema(description = "옵션 2")
    private String secondOption;

    @Builder
    public Question(String firstOption, String secondOption) {
        this.firstOption = firstOption;
        this.secondOption = secondOption;
    }
}
