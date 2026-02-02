package com.ishan.spring.web.util;

import com.ishan.spring.web.dto.DomesticPetDto;
import com.ishan.spring.web.dto.OwnerDto;
import com.ishan.spring.web.dto.PetDto;
import com.ishan.spring.web.dto.WildPetDto;
import com.ishan.spring.web.entity.DomesticPet;
import com.ishan.spring.web.entity.Owner;
import com.ishan.spring.web.entity.Pet;
import com.ishan.spring.web.entity.WildPet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {

    String UNSUPPORTED_PET_INSTANCE = "Unsupported pet instance: %s";

    @Mapping(source = "petDto", target = "pet")
    Owner ownerDTOToOwner(OwnerDto ownerDTO);

    default Pet petDTOToPet(PetDto petDTO) {
        return switch (petDTO) {
            case DomesticPetDto domesticPetDto -> domesticPetDtoToDomesticPet(domesticPetDto);
            case WildPetDto wildPetDto -> wildPetDtoToWildPet(wildPetDto);
            default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, petDTO.getClass()));
        };
    }

    @Mapping(target = "owner", ignore = true)
    DomesticPet domesticPetDtoToDomesticPet(DomesticPetDto domesticPetDto);

    @Mapping(target = "owner", ignore = true)
    WildPet wildPetDtoToWildPet(WildPetDto wildPetDto);

    @Mapping(source = "pet", target = "petDto")
    OwnerDto ownerToOwnerDto(Owner owner);

    default PetDto petToPetDto(Pet pet) {
        return switch (pet) {
            case DomesticPet domesticPet -> domesticPetToDomesticPetDto(domesticPet);
            case WildPet wildPet -> wildPetToWildPetDto(wildPet);
            default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, pet.getClass()));
        };
    }

    @Mapping(target = "ownerDto", ignore = true)
    DomesticPetDto domesticPetToDomesticPetDto(DomesticPet domesticPet);

    @Mapping(target = "ownerDto", ignore = true)
    WildPetDto wildPetToWildPetDto(WildPet wildPet);
}
