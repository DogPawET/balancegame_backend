package com.dogpaw.balancegame.dto;

import com.dogpaw.balancegame.entity.Guest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class GuestDTO {

    @Getter
    public static class Create {
        private String name;
        private List<Byte> answers;

        @Builder
        public Create(String name, List<Byte> answers) {
            this.name = name;
            this.answers = answers;
        }

        public Guest toEntity(Integer score, Integer percentage) {
            return Guest.builder()
                    .name(name)
                    .answers(answers)
                    .score(score)
                    .percentage(percentage)
                    .build();
        }

    }
}
