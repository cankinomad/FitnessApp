package org.berka.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR_SERVER(5200,"Sunucu Hatasi",INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100, "Parametre hatasi", HttpStatus.BAD_REQUEST),
    CARD_NOT_CREATED(4210,"Kart olusturulamadi",HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_ACTIVE(4211,"Aktive Edilmemis hesap. Lutfen hesabinizi aktif hale getirin" ,HttpStatus.FORBIDDEN),
    USER_NOT_FOUND(4212,"Boyle bir kullanici bulunamadi",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4213,"Gecersiz token" ,HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4114,"Token olusturulamadi" ,HttpStatus.BAD_REQUEST),
    ACTIVATION_CODE_MISMATCH(4113,"Aktivasyon kodunuz uyusmadi",HttpStatus.BAD_REQUEST),
    STATUS_NOT_FOUND(4114,"boyle bir kullanici durumu bulunamadi" ,HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4115,"bu kullanici adi uygun degildir." ,HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXIST(4116,"Bu kullanici zaten mevcut" ,HttpStatus.BAD_REQUEST),
    CARD_NOT_FOUND(4117,"Bu kullaniciya ait herhangi bir kart bulunamadi" ,HttpStatus.BAD_REQUEST),
    INVALID_EXPIRE_DATE(4118,"Kart sureleri 1,3,6,12 ay olarak belirlenmelidir" ,HttpStatus.BAD_REQUEST),
    INVALID_MEMBER_TYPE(4119,"Member type PLATINUM,SILVER,GOLD olarak belirlenmelidir" ,HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    HttpStatus status;
}
