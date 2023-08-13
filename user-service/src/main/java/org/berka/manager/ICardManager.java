package org.berka.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.berka.constant.EndPoints.SAVE;

@FeignClient(url = "http://localhost:9092/api/v1/card",decode404 = true,name = "user-cardmanager")
public interface ICardManager {

    @PostMapping(SAVE)
    ResponseEntity<Boolean> saveCard(@RequestBody Long userId);
}
