package com.ishan.rest_template_demo.controller;

import com.ishan.rest_template_demo.dto.CustomerDTO;
import com.ishan.rest_template_demo.dto.CustomerDtoForSave;
import com.ishan.rest_template_demo.exception.CustomerNotFoundException;
import com.ishan.rest_template_demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDtoForSave customerDtoForSave) {
        customerService.saveCustomer(customerDtoForSave);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
        CustomerDTO customerDTO = customerService.findCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomerDetails(@RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException {
        customerService.updateCustomerDetails(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
