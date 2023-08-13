package org.berka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivateAccountRequestDto {

    @NotBlank
    private String username;
    @NotBlank
    private String activationCode;
}
