package com.ishan.spring.web.repository;


public interface OwnerRepository {

    String save();

    String find();

    String updateOwner();

    String updatePetDetails();

    String delete();

    String findAll();

}
