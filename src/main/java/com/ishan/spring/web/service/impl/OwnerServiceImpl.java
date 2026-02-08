package com.ishan.spring.web.service.impl;

import com.ishan.spring.web.dto.OwnerDto;
import com.ishan.spring.web.entity.Owner;
import com.ishan.spring.web.entity.Pet;
import com.ishan.spring.web.exception.OwnerNotFoundException;
import com.ishan.spring.web.repository.OwnerRepository;
import com.ishan.spring.web.service.OwnerService;
import com.ishan.spring.web.util.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Value( "${owner.not.found}")
    private String ownerNotFound;

    private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Override
    public void saveOwner(OwnerDto ownerDto) {
        ownerRepository.save(ownerMapper.ownerDTOToOwner(ownerDto));
    }

    @Override
    public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
        
        Pet pet = owner.getPet();
        if (pet == null) {
            LOGGER.info("No pet found for owner with ID {}", ownerId);
            return;
        }
        pet.setName(petName);
        ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(int ownerId) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(ownerId)) {
            throw new OwnerNotFoundException(String.format(ownerNotFound, ownerId));
        }
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public OwnerDto updateOwner(OwnerDto ownerDto) throws OwnerNotFoundException {
        Owner existingOwner = ownerRepository.findById(ownerDto.getId())
                .orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerDto.getId())));

        Owner updatedOwner = ownerMapper.ownerDTOToOwner(ownerDto);
        // Update fields manually
        existingOwner.setFirstName(updatedOwner.getFirstName());
        existingOwner.setLastName(updatedOwner.getLastName());
        existingOwner.setGender(updatedOwner.getGender());
        existingOwner.setCity(updatedOwner.getCity());
        existingOwner.setState(updatedOwner.getState());
        existingOwner.setMobileNumber(updatedOwner.getMobileNumber());
        existingOwner.setEmailId(updatedOwner.getEmailId());

        // Update pet if needed
        if (ownerDto.getPetDto() != null) {
            existingOwner.setPet(updatedOwner.getPet());
        }

        Owner newUpdatedOwner = ownerRepository.save(existingOwner);
        return ownerMapper.ownerToOwnerDto(newUpdatedOwner);
    }

    @Override
    public OwnerDto findOwner(int ownerId) throws OwnerNotFoundException {
            return ownerRepository.findById(ownerId)
                    .map(ownerMapper::ownerToOwnerDto)
                    .orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
    }

    @Override
    public List<OwnerDto> findAllOwners() {
        return ownerRepository.findAll().stream().map(ownerMapper::ownerToOwnerDto).toList();
    }

}