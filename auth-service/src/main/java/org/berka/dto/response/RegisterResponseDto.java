package org.berka.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {

    private Long id;
    private String username;
    private String activationCode;
}
