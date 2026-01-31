package com.ishan.spring.web.service;


import com.ishan.spring.web.exception.OwnerNotFoundException;

public interface OwnerService {

    String saveOwner();

    String findOwner() throws OwnerNotFoundException;

    String updateOwner() throws OwnerNotFoundException;

    String updatePetDetails() throws OwnerNotFoundException;

    String deleteOwner() throws OwnerNotFoundException;

    String findAllOwners();

}
