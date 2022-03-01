package com.dogpaw.balancegame.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "new_collections")
@Schema(description = "밸런스게임")
public class BalanceGame {

    @Id
    @NotNull
    private ObjectId id;

    @NotNull
    private String name;

    @NotNull
    private UUID uuid;

    @NotNull
    @Schema(description = "질문 개수")
    private Integer questionNumber;

    private List<Question> questions;

    private List<Byte> answers;

    private List<Guest> guests;

    @Builder
    private BalanceGame(ObjectId id, String name, UUID uuid, Integer questionNumber,
        List<Question> questions,
        List<Byte> answers) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
        this.questionNumber = questionNumber;
        this.questions = questions;
        this.guests = new ArrayList<>();
        this.answers = answers;
    }

    public static BalanceGame of(String name, UUID uuid, Integer questionNumber) {
        validateQuestionNumber(questionNumber);
        return BalanceGame.builder()
            .name(name)
            .uuid(uuid)
            .questionNumber(questionNumber)
            .build();
    }

    public static BalanceGame of(BalanceGame balanceGame, List<Question> questions,
        List<Byte> answers) {
        validateQuestions(questions.size(), balanceGame.getQuestionNumber());
        validateAnswers(questions.size(), balanceGame.getQuestionNumber());
        return BalanceGame.builder()
            .id(balanceGame.getId())
            .name(balanceGame.getName())
            .uuid(balanceGame.getUuid())
            .questionNumber(balanceGame.getQuestionNumber())
            .questions(questions)
            .answers(answers)
            .build();
    }

    private static void validateQuestionNumber(int questionNumber) {
        if (questionNumber < 3 || questionNumber > 10) {
            throw new IllegalArgumentException(
                "The number of questions should be between 3 and 10.");
        }
    }

    private static void validateQuestions(int questionsSize, int questionNumber) {
        if (questionNumber != questionsSize) {
            throw new IllegalArgumentException(
                "size of question must be same with question numbers.");
        }
    }

    private static void validateAnswers(int answerSize, int questionSize) {
        if (answerSize != questionSize) {
            throw new IllegalArgumentException(
                "size of question must be same with size of answer.");
        }

    }
  
}
