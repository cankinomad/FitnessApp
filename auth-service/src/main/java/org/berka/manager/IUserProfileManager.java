package org.berka.manager;

import org.berka.dto.request.RegisterRequestDto;
import org.berka.dto.request.UserRegisterRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static org.berka.constant.EndPoints.ACTIVATION;
import static org.berka.constant.EndPoints.SAVE;

@FeignClient(url ="http://localhost:9091/api/v1/user",decode404 = true,name = "auth-userprofile")
public interface IUserProfileManager {

    @PostMapping(SAVE)
    ResponseEntity<Boolean> register(@RequestBody UserRegisterRequestDto dto);

    @PostMapping(ACTIVATION)
    ResponseEntity<String> activateAccount(@RequestParam Long authId);
}
