package com.ishan.spring.web.service;

import com.ishan.spring.web.dto.OwnerDto;
import com.ishan.spring.web.exception.OwnerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    void saveOwner(OwnerDto ownerDto);

    void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

    void deleteOwner(int ownerId) throws OwnerNotFoundException;

    OwnerDto findOwner(int ownerId) throws OwnerNotFoundException;

    List<OwnerDto> findAllOwners();
}
