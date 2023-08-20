package org.berka.controller;

import lombok.RequiredArgsConstructor;
import org.berka.dto.request.UserRegisterRequestDto;
import org.berka.dto.request.UserUpdateRequestDto;
import org.berka.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.berka.constant.EndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService service;


    @PostMapping(SAVE)
    public ResponseEntity<Boolean> register(@RequestBody UserRegisterRequestDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping(SAVEWITHRABBITMQ)
    public ResponseEntity<Boolean> registerWithRabbitMq(@RequestBody UserRegisterRequestDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping(ACTIVATION)
    public ResponseEntity<String> activateAccount(@RequestParam Long authId) {
        return ResponseEntity.ok(service.activateAccount(authId));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateDetails(@RequestBody @Valid UserUpdateRequestDto dto) {
        return ResponseEntity.ok(service.updateDetails(dto));
    }

    @PostMapping(CREATEUSERTOKEN)
    public ResponseEntity<String> convertToken(String authToken) {
        return ResponseEntity.ok(service.convertToken(authToken));
    }
}
