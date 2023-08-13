package org.berka.service;

import org.berka.dto.request.UserRegisterRequestDto;
import org.berka.dto.request.UserUpdateRequestDto;
import org.berka.exception.ErrorType;
import org.berka.exception.UserManagerException;
import org.berka.manager.IAuthManager;
import org.berka.manager.ICardManager;
import org.berka.mapper.IUserProfileMapper;
import org.berka.repository.IUserProfileRepository;
import org.berka.repository.entity.UserProfile;
import org.berka.repository.enums.EStatus;
import org.berka.utility.JwtTokenManager;
import org.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository repository;

    private final JwtTokenManager jwtTokenManager;

    private final ICardManager cardManager;


    private final IAuthManager authManager;

    public UserProfileService(IUserProfileRepository repository, JwtTokenManager jwtTokenManager, ICardManager cardManager, IAuthManager authManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.cardManager = cardManager;
        this.authManager = authManager;
    }
    @Transactional
    public Boolean register(UserRegisterRequestDto dto) {
        UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfile(dto);

        try {
            save(userProfile);
            cardManager.saveCard(userProfile.getId());
            return true;
        } catch (Exception e) {
            throw new UserManagerException(ErrorType.USER_NOT_CREATED);
        }
    }

    public String activateAccount(Long authId) {
        Optional<UserProfile> optionalUserProfile = repository.findByAuthId(authId);
        if (optionalUserProfile.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }

        return accountStatusControl(optionalUserProfile.get());

    }

    private String accountStatusControl(UserProfile userProfile) {
        switch (userProfile.getStatus()) {
            case PENDING,INACTIVE -> {
                userProfile.setStatus(EStatus.ACTIVE);
                update(userProfile);
                return "Hesabiniz aktif edilmistir";
            }
            case DELETED,BANNED -> {
                return "Bu islemi yapmaya yetkiniz yok, lutfen yetkli birisiyle iletisime geciniz";
            }
            case ACTIVE -> {
                return "Hesabiniz zaten aktif";
            }
            default -> {
                return "herhangi bir islem yapilamadi";
            }
        }
    }

    @Transactional
    public String updateDetails(UserUpdateRequestDto dto) {
        Optional<Long> optionalLong = jwtTokenManager.getIdFromToken(dto.getToken());
        if (optionalLong.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> optionalUserProfile = repository.findByAuthId(optionalLong.get());

        if (optionalUserProfile.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }

        if (repository.existsByUsername(dto.getUsername()) && !optionalUserProfile.get().getUsername().equals(dto.getUsername())) {
            throw new UserManagerException(ErrorType.USERNAME_DUPLICATE);
        }

        if (!optionalUserProfile.get().getUsername().equals(dto.getUsername()) || !optionalUserProfile.get().getEmail().equals(dto.getEmail()) ||
                !optionalUserProfile.get().getPassword().equals(dto.getPassword())) {
            optionalUserProfile.get().setUsername(dto.getUsername());
            optionalUserProfile.get().setEmail(dto.getEmail());
            optionalUserProfile.get().setPassword(dto.getPassword());
            authManager.updateDetails(IUserProfileMapper.INSTANCE.toUpdateAuthRequestDto(optionalUserProfile.get()));
        }

        optionalUserProfile.get().setAddress(dto.getAddress());
        optionalUserProfile.get().setName(dto.getName());
        optionalUserProfile.get().setSurname(dto.getSurname());
        optionalUserProfile.get().setAvatar(dto.getAvatar());
        optionalUserProfile.get().setPhone(dto.getPhone());

        update(optionalUserProfile.get());

        return "Bilgiler guncellenmistir";

    }

    public String convertToken(String authToken) {
        Optional<Long> optionalLong = jwtTokenManager.getIdFromToken(authToken);
        if (optionalLong.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> optionalUserProfile = repository.findByAuthId(optionalLong.get());
        if (optionalUserProfile.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        Optional<String> token = jwtTokenManager.createToken(optionalUserProfile.get().getId(), optionalUserProfile.get().getUserType());
        if (token.isEmpty()) {
            throw new UserManagerException(ErrorType.TOKEN_NOT_CREATED);
        }
        return token.get();
    }
}
