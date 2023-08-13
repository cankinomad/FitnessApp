package org.berka.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR_SERVER(5100,"Sunucu Hatasi",INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100, "Parametre hatasi", HttpStatus.BAD_REQUEST),
    USERNAME_EXIST(4110,"Kullanici zaten mevcut",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4111,"Kullanici olusturulamadi",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4112,"Boyle bir kullanici bulunamadi",HttpStatus.BAD_REQUEST),
    ACTIVATION_CODE_MISMATCH(4113,"Aktivasyon kodunuz uyusmadi",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4114,"Kullanici adi veya sifre hatali",HttpStatus.NOT_FOUND),
    ACCOUNT_NOT_ACTIVE(4115,"Aktive Edilmemis hesap. Lutfen hesabinizi aktif hale getirin" ,HttpStatus.FORBIDDEN),
    INVALID_TOKEN(4116,"Gecersiz token" ,HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4117,"Token olusturulamadi" ,HttpStatus.BAD_REQUEST),
    EMAIL_TAKEN(4117,"Bu email sistemde kayitli" ,HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    HttpStatus status;
}
