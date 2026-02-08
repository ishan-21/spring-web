package com.ishan.spring.web.repository;

import com.ishan.spring.web.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    Page<Owner> findAll(Pageable pageable);

}
