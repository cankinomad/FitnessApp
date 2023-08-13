package org.berka.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.berka.repository.enums.EMemberType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    EMemberType memberType = EMemberType.SILVER;

    @Builder.Default
    private Long expiresAt = LocalDate.now().plusMonths(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC);

    @Builder.Default
    boolean accessToAnything = false;
}
