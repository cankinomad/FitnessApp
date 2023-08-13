package org.berka.repository;

import org.berka.repository.entity.UserProfile;
import org.mapstruct.control.MappingControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserProfileRepository extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findByAuthId(Long authId);

    Boolean existsByUsername(String username);
}
