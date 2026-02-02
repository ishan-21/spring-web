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

    /*
        please note that in the save owner method
        we are NOT required to send the id field as it is auto generated (see entity class base)
        all fields, which are marked as Nullable = false in the entity class are required to be sent
        even if you send an id field it will be ignored => the next sequential id will be saved
        we could have also created a separate dto for saving owners, but we are not doing that
     */

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
