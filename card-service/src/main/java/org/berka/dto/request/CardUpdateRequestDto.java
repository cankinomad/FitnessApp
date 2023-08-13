package org.berka.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.berka.repository.enums.EMemberType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardUpdateRequestDto {

    private String token;
    EMemberType memberType;
    private Integer howManyMonths;


}
