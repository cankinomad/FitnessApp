package org.berka.controller;

import lombok.RequiredArgsConstructor;
import org.berka.dto.request.CardUpdateRequestDto;
import org.berka.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.berka.constant.EndPoints.*;

@RestController
@RequestMapping(CARD)
@RequiredArgsConstructor
public class CardController {


    private final CardService service;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> saveCard(@RequestBody Long userId) {
        return ResponseEntity.ok(service.saveCard(userId));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> cardUpdate(CardUpdateRequestDto dto) {
        return ResponseEntity.ok(service.cardUpdate(dto));
    }
}
