package org.berka.controller;

import lombok.RequiredArgsConstructor;
import org.berka.dto.request.ActivateAccountRequestDto;
import org.berka.dto.request.LoginRequestDto;
import org.berka.dto.request.RegisterRequestDto;
import org.berka.dto.request.UpdateAuthRequestDto;
import org.berka.dto.response.RegisterResponseDto;
import org.berka.service.AuthService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.berka.constant.EndPoints.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }

    @PostMapping(ACTIVATION)
    public ResponseEntity<String> activateAccount(@RequestBody @Valid ActivateAccountRequestDto dto) {
        return ResponseEntity.ok(service.activateAccount(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(service.login(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateDetails(@RequestBody UpdateAuthRequestDto dto) {
        return ResponseEntity.ok(service.updateDetails(dto));
    }

}
