package org.berka.repository;

import org.berka.repository.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {

    Optional<Auth> findByUsernameAndActivationCode(String username, String activationCode);

    Optional<Auth> findByUsernameAndPassword(String username, String password);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}
