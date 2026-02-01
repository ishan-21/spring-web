package com.ishan.spring.web.dto;

import com.ishan.spring.web.enums.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode( onlyExplicitlyIncluded = true )
@Getter
@Setter
@ToString
public class OwnerDto {
    @EqualsAndHashCode.Include
    private int id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String city;
    private String state;
    @EqualsAndHashCode.Include
    private String mobileNumber;
    @EqualsAndHashCode.Include
    private String emailId;
    @EqualsAndHashCode.Include
    private PetDto petDto;
}
