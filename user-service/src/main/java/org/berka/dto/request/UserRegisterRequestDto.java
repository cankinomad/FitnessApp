package org.berka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {

    private Long authId;
    private String username;
    private String password;
    private String email;
}
