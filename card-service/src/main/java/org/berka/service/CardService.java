package org.berka.service;

import org.berka.dto.request.CardUpdateRequestDto;
import org.berka.exception.CardManagerException;
import org.berka.exception.ErrorType;
import org.berka.repository.ICardRepository;
import org.berka.repository.entity.Card;
import org.berka.repository.enums.EMemberType;
import org.berka.repository.enums.EUserType;
import org.berka.utility.JwtTokenManager;
import org.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class CardService extends ServiceManager<Card, Long> {

    private final ICardRepository repository;

    private final JwtTokenManager jwtTokenManager;

    public CardService(ICardRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public Boolean saveCard(Long userId) {
        boolean b = repository.existsByUserId(userId);
        if (b) {
            throw new CardManagerException(ErrorType.USER_ALREADY_EXIST);
        }

        try {
            save(Card.builder().userId(userId).build());
            return true;
        } catch (Exception e) {
            throw new CardManagerException(ErrorType.CARD_NOT_CREATED);
        }
    }

    public String cardUpdate(CardUpdateRequestDto dto) {
        Optional<Long> optionalUserId = jwtTokenManager.getIdFromToken(dto.getToken());
        Optional<String> optionalRole = jwtTokenManager.getRoleFromToken(dto.getToken());

        if (optionalUserId.isEmpty() || optionalRole.isEmpty()) {
            throw new CardManagerException(ErrorType.INVALID_TOKEN);
        }

        Optional<Card> optionalCard = repository.findByUserId(optionalUserId.get());
        if (optionalCard.isEmpty()) {
            throw new CardManagerException(ErrorType.CARD_NOT_FOUND);
        }

        if (optionalRole.get().equals(EUserType.ADMIN)) {
            optionalCard.get().setAccessToAnything(true);
        }

         Long expiresAt= expireDateUpdate(dto.getHowManyMonths());
        EMemberType eMemberType = memberTypeControl(dto.getMemberType());

        optionalCard.get().setExpiresAt(expiresAt);
        optionalCard.get().setMemberType(eMemberType);
        update(optionalCard.get());
        return "Kart bilgileriniz guncellenmistir";
    }

    private EMemberType memberTypeControl(EMemberType memberType) {
        switch (memberType) {
            case PLATINUM -> {
                return EMemberType.PLATINUM;
            }
            case SILVER -> {
                return EMemberType.SILVER;
            }
            case GOLD -> {
                return EMemberType.GOLD;
            }
            default -> {
                throw new CardManagerException(ErrorType.INVALID_EXPIRE_DATE);
            }
        }
    }

    private Long expireDateUpdate(Integer howManyMonths) {
        switch (howManyMonths) {
            case 1 -> {
                return LocalDate.now().plusMonths(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC);
            }
            case 3 -> {
                return LocalDate.now().plusMonths(3).atStartOfDay().toEpochSecond(ZoneOffset.UTC);
            }
            case 6 -> {
               return   LocalDate.now().plusMonths(6).atStartOfDay().toEpochSecond(ZoneOffset.UTC);
            }
            case 12 -> {
              return   LocalDate.now().plusYears(1).atStartOfDay().toEpochSecond(ZoneOffset.UTC);
            }
            default -> {
                throw new CardManagerException(ErrorType.INVALID_EXPIRE_DATE);
            }
        }
    }
}
