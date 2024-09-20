package com.siete.rehapp.mapper;
import com.siete.rehapp.dto.PatientUserRegisterDTO;
import com.siete.rehapp.dto.PhysioUserRegisterDTO;
import com.siete.rehapp.dto.UserDTO;
import com.siete.rehapp.entity.PhysiotherapistEntity;
import com.siete.rehapp.entity.UserEntity;
import com.siete.rehapp.enums.UserType;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "professionalCardNumber", source = "userEntity", qualifiedByName = "mapProfessionalCardNumber")
    })
    UserDTO toUserDTO(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "userName", source = "userName"),
            @Mapping(target = "identificationNumber", ignore = true),
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "age", ignore = true),
            @Mapping(target = "sex", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "city", ignore = true)
    })
    UserEntity toUserEntity(UserDTO userDTO);

    UserEntity toUserEntityRegister(PatientUserRegisterDTO patientUserRegisterDTO);

    PatientUserRegisterDTO toUserRegisterDTO(UserEntity userEntity);

    void updateUserFromDto(UserDTO userDTO, @MappingTarget UserEntity userEntity);

    @Mappings({
            @Mapping(target = "userType", constant = "PHYSIOTHERAPIST"),
            @Mapping(target = "userId", ignore = true)
    })
    UserEntity toUserEntityRegister(PhysioUserRegisterDTO physioUserRegisterDTO);

    @Mapping(target = "id", ignore = true)
    PhysiotherapistEntity toPhysiotherapistEntity(PhysioUserRegisterDTO physioUserRegisterDTO);

    @Named("mapProfessionalCardNumber")
    default String mapProfessionalCardNumber(UserEntity userEntity) {
        if (userEntity.getUserType() == UserType.PHYSIOTHERAPIST && userEntity.getPhysiotherapists() != null) {
            for (PhysiotherapistEntity physiotherapist : userEntity.getPhysiotherapists()) {
                return physiotherapist.getProfessionalCardNumber();
            }
        }
        return null;
    }
}
