package org.berka.service;

import org.berka.dto.request.ActivateAccountRequestDto;
import org.berka.dto.request.LoginRequestDto;
import org.berka.dto.request.RegisterRequestDto;
import org.berka.dto.request.UpdateAuthRequestDto;
import org.berka.dto.response.RegisterResponseDto;
import org.berka.exception.AuthManagerException;
import org.berka.exception.ErrorType;
import org.berka.manager.IUserProfileManager;
import org.berka.mapper.IAuthMapper;
import org.berka.repository.IAuthRepository;
import org.berka.repository.entity.Auth;
import org.berka.repository.enums.EStatus;
import org.berka.utility.CodeGenerator;
import org.berka.utility.JwtTokenManager;
import org.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final IUserProfileManager userProfileManager;

    public AuthService(IAuthRepository repository, JwtTokenManager jwtTokenManager, IUserProfileManager userProfileManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.userProfileManager = userProfileManager;
    }

    @Transactional
    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);

        if (repository.existsByEmail(dto.getEmail())) {
            throw new AuthManagerException(ErrorType.EMAIL_TAKEN);
        }
        if (repository.existsByUsername(dto.getUsername())) {
            throw new AuthManagerException(ErrorType.USERNAME_EXIST);
        }

        try {
            auth.setActivationCode(CodeGenerator.generateCode());
            save(auth);
            userProfileManager.register(IAuthMapper.INSTANCE.toUserRegisterRequestDto(auth));
            return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        } catch (Exception e) {
            throw new AuthManagerException(ErrorType.USER_NOT_CREATED);
        }
    }

    @Transactional
    public String activateAccount(ActivateAccountRequestDto dto) {
        Optional<Auth> optionalAuth = repository.findByUsernameAndActivationCode(dto.getUsername(), dto.getActivationCode());

        if (optionalAuth.isEmpty()) {
            throw new AuthManagerException(ErrorType.ACTIVATION_CODE_MISMATCH);
        }

        return accountStatusControl(optionalAuth.get());
    }


    public String accountStatusControl(Auth auth) {

        switch (auth.getStatus()) {
            case PENDING,INACTIVE -> {
                auth.setStatus(EStatus.ACTIVE);
                update(auth);
                userProfileManager.activateAccount(auth.getId());
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

    public String login(LoginRequestDto dto) {
        Optional<Auth> optionalAuth = repository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

        if (optionalAuth.isEmpty()) {
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }

        if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)) {
            return "Login olmaniz icin oncelikle hesabinizi aktif etmeniz gerekmektedir";
        }

        Optional<String> token = jwtTokenManager.createToken(optionalAuth.get().getId(), optionalAuth.get().getUserType());
        if (token.isEmpty()) {
            throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);
        }
        return token.get();
    }

    public String updateDetails(UpdateAuthRequestDto dto) {
        Optional<Auth> optionalAuth = findById(dto.getId());
        if (optionalAuth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }

        optionalAuth.get().setEmail(dto.getEmail());
        optionalAuth.get().setUsername(dto.getUsername());
        optionalAuth.get().setPassword(dto.getPassword());
        update(optionalAuth.get());
        return "Hesabiniz basariyla guncellenmistir";
    }
}
