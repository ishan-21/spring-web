package com.ishan.spring.web.controller;

import com.ishan.spring.web.exception.OwnerNotFoundException;
import com.ishan.spring.web.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/owners")
@RestController // @Controller + @ResponseBody => All methods automatically serialize responses to JSON/XML
public class OwnerController {

    private final OwnerService ownerService;


    @PostMapping
    public String saveOwner() {
        return ownerService.saveOwner();
    }


    @GetMapping
    public String findOwner() {
        try {
            return ownerService.findOwner();
        } catch (OwnerNotFoundException e) {
            return e.getMessage();
        }
    }


    @PutMapping
    public String updateOwner() {
        try {
            return ownerService.updateOwner();
        } catch (OwnerNotFoundException e) {
            return e.getMessage();
        }
    }


    @PatchMapping
    public String updatePetDetails() {
        try {
            return ownerService.updatePetDetails();
        } catch (OwnerNotFoundException e) {
            return e.getMessage();
        }
    }


    @DeleteMapping
    public String deleteOwner() {
        try {
            return ownerService.deleteOwner();
        } catch (OwnerNotFoundException e) {
            return e.getMessage();
        }
    }


    @GetMapping(value = "/all")
    public String findAllOwners() {
        return ownerService.findAllOwners();
    }

}
