package org.berka.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.berka.repository.enums.EStatus;
import org.berka.repository.enums.EUserType;

import javax.persistence.*;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Auth extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String activationCode;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    EUserType userType = EUserType.USER;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    EStatus status = EStatus.PENDING;

}
