package com.ishan.spring.web.controller;

import com.ishan.spring.web.dto.OwnerDto;
import com.ishan.spring.web.exception.OwnerNotFoundException;
import com.ishan.spring.web.service.OwnerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

import java.util.List;


/*
    @CrossOrigin(origins = "http://localhost:3000")  // Allow specific origin
    @CrossOrigin(origins = "*", maxAge = 3600)       // All origins with 1hr cache
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST})  // Specific methods
*/
@CrossOrigin // used to prevent CORS errors => browser blocks requests from different origins (different domains (xyz.com), ports(8080,9090,etc), or protocols(http/https,etc))
@Validated // for input validation
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
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody OwnerDto ownerDto) {
        ownerService.saveOwner(ownerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<OwnerDto> updateOwner(@Valid @RequestBody OwnerDto ownerDto) throws OwnerNotFoundException {
        OwnerDto updatedOwner = ownerService.updateOwner(ownerDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOwner);
    }

    @GetMapping
    public ResponseEntity<OwnerDto> findOwner(@Min(value = 1, message = "{owner.id.constraint}") @RequestParam("ownerId") int ownerId) throws OwnerNotFoundException {
        OwnerDto ownerDto = ownerService.findOwner(ownerId);
        return ResponseEntity.status(HttpStatus.OK).body(ownerDto);
    }

    @PatchMapping("/{ownerId}/{petName}")
    public ResponseEntity<OwnerDto> updatePetDetails(@Min(value = 1, message = "{owner.id.constraint}") @PathVariable("ownerId") int ownerId, @PathVariable("petName") String petName) throws OwnerNotFoundException {
        ownerService.updatePetDetails(ownerId, petName);
        OwnerDto updatedOwner = ownerService.findOwner(ownerId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedOwner);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOwner(@Min(value = 1, message = "{owner.id.constraint}") @RequestParam("ownerId") int ownerId) throws OwnerNotFoundException {
        ownerService.deleteOwner(ownerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<OwnerDto>> findAllOwners() {
        return ResponseEntity.status(HttpStatus.OK).body(ownerService.findAllOwners());
    }

    @GetMapping(value = "/all/paginated")
    public ResponseEntity<List<OwnerDto>> findAllOwnersPaginated(@Min(value = 1, message = "{page.number.constraint}") @RequestParam("page-number") int pageNumber,@Min(value = 1, message = "{page.size.constraint}") @RequestParam("page-size") int pageSize, @RequestParam("sort-by") String sortBy, @RequestParam("descending") boolean descending) {
        List<OwnerDto> ownersPaginated = ownerService.findAllOwnersPaginated(pageNumber, pageSize, sortBy, descending);
        return ResponseEntity.status(HttpStatus.OK).body(ownersPaginated);
    }

}
