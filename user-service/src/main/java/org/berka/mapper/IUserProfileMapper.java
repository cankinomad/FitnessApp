package org.berka.mapper;

import org.berka.dto.request.UpdateAuthRequestDto;
import org.berka.dto.request.UserRegisterRequestDto;
import org.berka.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfile toUserProfile(final UserRegisterRequestDto dto);

    @Mapping(source = "authId",target = "id")
    UpdateAuthRequestDto toUpdateAuthRequestDto(final UserProfile userProfile);
}
