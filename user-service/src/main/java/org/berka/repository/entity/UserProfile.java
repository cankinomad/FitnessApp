package org.berka.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.berka.repository.enums.EMemberType;
import org.berka.repository.enums.EStatus;
import org.berka.repository.enums.EUserType;

import javax.persistence.*;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserProfile extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long authId;
    private String name;
    private String surname;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String phone;
    private String avatar;
    private String address;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EStatus status=EStatus.PENDING;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    EMemberType memberType = EMemberType.SILVER;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    EUserType userType = EUserType.USER;
}
