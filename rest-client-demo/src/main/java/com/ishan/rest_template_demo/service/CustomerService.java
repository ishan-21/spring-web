package com.ishan.rest_template_demo.service;


import com.ishan.rest_template_demo.dto.CompanionDTO;
import com.ishan.rest_template_demo.dto.CustomerDTO;
import com.ishan.rest_template_demo.dto.CustomerDtoForSave;
import com.ishan.rest_template_demo.exception.CustomerNotFoundException;


public interface CustomerService {

    void saveCustomer(CustomerDtoForSave customerDtoForSave);

    CustomerDTO findCustomer(int customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomerDetails(CustomerDTO customerDTO) throws CustomerNotFoundException;

    void deleteCustomer(int customerId) throws CustomerNotFoundException;

}
