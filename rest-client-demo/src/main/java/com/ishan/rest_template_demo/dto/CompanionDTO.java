package com.ishan.rest_template_demo.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Setter
@Getter
public class CompanionDTO {
    /*
        The name of these fields exactly match the name of the fields in PetDto
        Note that the data-type might not match and that's fine
        For example, the data type for gender is string here while in the
        other service, it is an enum, same for Pet-Type
        category field tells us the type of pet it is
     */
    private String name;
    private String gender;
    private String petType;
    private LocalDate birthDate;
    private String birthPlace;
    private String category;

}
