package com.dogpaw.balancegame.service;

import com.dogpaw.balancegame.dto.GuestDTO;
import com.dogpaw.balancegame.dto.ResponseDTO;
import com.dogpaw.balancegame.entity.BalanceGame;
import com.dogpaw.balancegame.entity.Guest;
import com.dogpaw.balancegame.repository.BalanceGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GuestService {

    private final BalanceGameRepository balanceGameRepository;

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO.ResultResponse saveGuest(UUID uuid, GuestDTO.Create dto) {
        BalanceGame balanceGame = balanceGameRepository.findBalanceGameByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("balancegame with uuid : " + uuid + " is not valid"));

        int score = 0;
        List<Byte> balanceGameAnswers = balanceGame.getAnswers();
        for (int i = 0; i < dto.getAnswers().size(); i++) {
            if (Objects.equals(dto.getAnswers().get(i), balanceGameAnswers.get(i))) {
                score++;
            }
        }
        int percentage = score / balanceGameAnswers.size();

        Guest newGuest = dto.toEntity(score, percentage);

        balanceGame.getGuests().add(newGuest);
        balanceGameRepository.save(balanceGame);

        return new ResponseDTO.ResultResponse(balanceGame, newGuest);
    }

    @Transactional(readOnly = true)
    public ResponseDTO.LeaderBoard getLeaderBoard(UUID uuid) {
        BalanceGame balanceGame = balanceGameRepository.findBalanceGameByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("balancegame with uuid : " + uuid + " is not valid"));

        List<Guest> guests = balanceGame.getGuests();
        guests.sort(Comparator.comparing(Guest::getPercentage));

        List<ResponseDTO.GuestResponse> guestResponses = guests.stream()
                .map(ResponseDTO.GuestResponse::new).collect(Collectors.toList());

        return new ResponseDTO.LeaderBoard(balanceGame.getName(), guestResponses);

    }
}
