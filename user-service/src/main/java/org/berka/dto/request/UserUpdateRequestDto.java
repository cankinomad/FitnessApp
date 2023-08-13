package org.berka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {

    private String token;
    private String name;
    private String surname;
    @NotBlank(message = "Username cannot be null")
    @Size(min = 2,max = 32,message = "Username must contain min 2 and max 32 character")
    private String username;
    @NotBlank(message = "Password cannot be null")
    @Size(min = 5,max = 32,message = "Password must contain min 5 and max 32 character")
    private String password;
    @NotBlank
    @Email
    private String email;
    private String phone;
    private String avatar;
    private String address;

}
