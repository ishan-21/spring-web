package com.ishan.rest_template_demo.dto;

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
public class CustomerDTO {

    /*
        The name of these fields exactly match the name of the fields in OwnerDto
        Note that the data-type might not match and that's fine
        For example, the data type for gender is string here while in the
        other service, it is an enum
     */
    @EqualsAndHashCode.Include
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String state;
    @EqualsAndHashCode.Include
    private String mobileNumber;
    @EqualsAndHashCode.Include
    private String emailId;
    private CompanionDTO petDto;

}
