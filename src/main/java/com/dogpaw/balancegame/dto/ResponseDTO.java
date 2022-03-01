package com.dogpaw.balancegame.dto;

import com.dogpaw.balancegame.entity.BalanceGame;
import com.dogpaw.balancegame.entity.Guest;
import com.dogpaw.balancegame.entity.Question;
import java.util.UUID;
import lombok.Getter;

import java.util.List;

public class ResponseDTO {

    @Getter
    public static class QuestionsResponse {

        private final String hostName;
        private final List<Question> questions;

        public QuestionsResponse(BalanceGame balanceGame) {
            this.hostName = balanceGame.getName();
            this.questions = balanceGame.getQuestions();
        }
    }

    @Getter
    public static class ResultResponse {

        private final String hostName;
        private final String guestName;
        private final List<Question> questions;
        private final List<Byte> hostAnswers;
        private final List<Byte> guestAnswers;
        private final Integer score;
        private final Integer percentage;

        public ResultResponse(BalanceGame balanceGame, Guest guest) {
            this.hostName = balanceGame.getName();
            this.guestName = guest.getName();
            this.questions = balanceGame.getQuestions();
            this.hostAnswers = balanceGame.getAnswers();
            this.guestAnswers = guest.getAnswers();
            this.score = guest.getScore();
            this.percentage = guest.getPercentage();
        }
    }

    @Getter
    public static class LeaderBoard {

        private final String hostName;
        private final List<GuestResponse> guest;

        public LeaderBoard(String hostName, List<GuestResponse> guest) {
            this.hostName = hostName;
            this.guest = guest;
        }
    }

    @Getter
    public static class GuestResponse {

        private final String name;
        private final Integer percentage;

        public GuestResponse(Guest guest) {
            this.name = guest.getName();
            this.percentage = guest.getPercentage();
        }
    }


    @Getter
    public static class hostResponse {

        private final UUID uuid;

        public hostResponse(UUID uuid) {
            this.uuid = uuid;
        }
    }
}
