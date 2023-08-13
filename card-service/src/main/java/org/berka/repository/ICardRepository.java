package org.berka.repository;

import org.berka.repository.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {


    boolean existsByUserId(Long userId);

    Optional<Card> findByUserId(Long userId);
}
