package com.ishan.spring.web.controller;

import com.ishan.spring.web.exception.OwnerNotFoundException;
import com.ishan.spring.web.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/owners")
@RestController // @Controller + @ResponseBody => All methods automatically serialize responses to JSON/XML
public class OwnerController {
    /*
     * In all the below methods we are using ResponseEntity to return the response.
     * => so that we can manipulate the status code ( for OwnerNotFoundException we should send 404 for example )
     * ResponseEntity is a class that represents the HTTP response.
     * It contains the status code, headers, and body.
     */

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<String> saveOwner() {
        String responseBody = ownerService.saveOwner();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }


    @GetMapping
    public ResponseEntity<String> findOwner() {
        try {
            String responseBody = ownerService.findOwner();
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PutMapping
    public ResponseEntity<String> updateOwner() {
        try {
            String responseBody = ownerService.updateOwner();
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PatchMapping
    public ResponseEntity<String> updatePetDetails() {
        try {
            String responseBody = ownerService.updatePetDetails();
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping
    public ResponseEntity<String> deleteOwner() {
        try {
            String responseBody = ownerService.deleteOwner();
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping(value = "/all")
    public ResponseEntity<String> findAllOwners() {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwners());
    }

}
