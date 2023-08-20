package org.berka.mapper;

import org.berka.dto.request.RegisterRequestDto;
import org.berka.dto.request.UserRegisterRequestDto;
import org.berka.dto.response.RegisterResponseDto;
import org.berka.rabbitmq.model.RegisterModel;
import org.berka.rabbitmq.producer.RegisterProducer;
import org.berka.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);

    RegisterResponseDto toRegisterResponseDto(final Auth auth);

    @Mapping(source = "id",target = "authId")
    UserRegisterRequestDto toUserRegisterRequestDto(final Auth auth);

    RegisterModel toRegisterModel(final Auth auth);
}
