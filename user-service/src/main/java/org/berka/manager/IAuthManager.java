package org.berka.manager;

import org.berka.dto.request.UpdateAuthRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.berka.constant.EndPoints.UPDATE;

@FeignClient(url ="http://localhost:9090/api/v1/auth", decode404 = true, name = "user-authmanager")
public interface IAuthManager {

    @PutMapping(UPDATE)
     ResponseEntity<String> updateDetails(@RequestBody UpdateAuthRequestDto dto);
}
