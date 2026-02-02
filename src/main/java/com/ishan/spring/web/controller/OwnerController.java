package com.ishan.spring.web.controller;

import com.ishan.spring.web.dto.OwnerDto;
import com.ishan.spring.web.exception.OwnerNotFoundException;
import com.ishan.spring.web.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

import java.util.List;

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
    public ResponseEntity<Void> saveOwner(@RequestBody OwnerDto ownerDto) {
        ownerService.saveOwner(ownerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<OwnerDto> updateOwner(@RequestBody OwnerDto ownerDto) {
        try{
            OwnerDto updatedOwner = ownerService.updateOwner(ownerDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedOwner);
        }catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<OwnerDto> findOwner(@RequestParam("ownerId") int ownerId) {
        try {
            OwnerDto ownerDto = ownerService.findOwner(ownerId);
            return ResponseEntity.status(HttpStatus.OK).body(ownerDto);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PatchMapping("/{ownerId}/{petName}")
    public ResponseEntity<OwnerDto> updatePetDetails(@PathVariable("ownerId") int ownerId, @PathVariable("petName") String petName) {
        try {
            ownerService.updatePetDetails(ownerId, petName);
            OwnerDto updatedOwner = ownerService.findOwner(ownerId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedOwner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteOwner(@RequestParam("ownerId") int ownerId) {
        try {
            ownerService.deleteOwner(ownerId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<OwnerDto>> findAllOwners() {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwners());
    }

}
