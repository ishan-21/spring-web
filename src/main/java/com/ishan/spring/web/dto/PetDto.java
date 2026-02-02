package com.ishan.spring.web.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ishan.spring.web.enums.Gender;
import com.ishan.spring.web.enums.PetType;
import lombok.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes({ @JsonSubTypes.Type(value = DomesticPetDto.class, name = "Domestic"),
        @JsonSubTypes.Type(value = WildPetDto.class, name = "Wild") })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"ownerDto"}) // excluding this as it was causing circular reference
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class PetDto {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private Gender gender;
    private PetType petType;
    @EqualsAndHashCode.Include
    private OwnerDto ownerDto;
}
