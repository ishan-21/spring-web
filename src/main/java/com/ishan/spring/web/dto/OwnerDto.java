package com.ishan.spring.web.dto;

import com.ishan.spring.web.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "{first.name.constraint}")
    private String firstName;
    @NotBlank(message = "{last.name.constraint}")
    private String lastName;
    private Gender gender;
    private String city;
    private String state;
    @Size(min = 10, max = 10, message = "{mobile.number.constraint}")
    @EqualsAndHashCode.Include
    private String mobileNumber;
    @Email(message = "{email.constraint}")
    @EqualsAndHashCode.Include
    private String emailId;
    @EqualsAndHashCode.Include
    private PetDto petDto;
}
