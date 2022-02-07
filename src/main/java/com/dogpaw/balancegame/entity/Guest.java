package com.dogpaw.balancegame.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "new_collections")
public class Guest {

    @Id
    @NotNull
    private ObjectId id;

    @NotNull
    private String name;

    @NotNull
    private List<Byte> answers;

    private Integer score;

    @Builder
    public Guest(String name, List<Byte> answers) {
        this.name = name;
        this.answers = answers;
    }
}
