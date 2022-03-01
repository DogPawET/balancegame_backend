package com.dogpaw.balancegame.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "new_collections")
@Schema(description = "게스트")
public class Guest {

    @NotNull
    private String name;

    @NotNull
    private List<Byte> answers;

    @Schema(description = "점수")
    private Integer score;

    @Schema(description = "정답률")
    private Integer percentage;

    @Builder
    private Guest(String name, List<Byte> answers, Integer score, Integer percentage) {
        this.name = name;
        this.answers = answers;
        this.score = score;
        this.percentage = percentage;
    }

    public static Guest of(String name, List<Byte> guestAnswers, List<Byte> hostAnswers, Integer questionNumber) {
        validateAnswer(questionNumber, guestAnswers.size());
        Integer score = scoring(guestAnswers, hostAnswers);
        Double percentage = (double)score / (double)questionNumber * 100;
        return Guest.builder()
                .name(name)
                .answers(guestAnswers)
                .score(score)
                .percentage(percentage.intValue())
                .build();
    }

    public static void validateAnswer(int questionNumber, int answerSize) {
        if (questionNumber != answerSize) {
            throw new IllegalArgumentException("size of guest answer must be same with host question numbers.");
        }
    }

    public static Integer scoring(List<Byte> guestAnswers, List<Byte> hostAnswers) {
        int score = 0;
        for (int i = 0; i < hostAnswers.size(); i++) {
            if (Objects.equals(hostAnswers.get(i), guestAnswers.get(i))) {
                score++;
            }
        }

        return score;
    }
}
