package com.ishan.spring.web.repository;

import com.ishan.spring.web.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}
