package com.udacity.jdnd.course3.critter.DAO;

import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PetDAO extends JpaRepository<PetEnt, Long> {

    // Access to customer in PetEnt
    List<PetEnt> findPetByCustomerId(Long customerId);
}

