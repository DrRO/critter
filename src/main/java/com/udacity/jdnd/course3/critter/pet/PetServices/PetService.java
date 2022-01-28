package com.udacity.jdnd.course3.critter.pet.PetServices;

import com.udacity.jdnd.course3.critter.DAO.CustomerDAO;
import com.udacity.jdnd.course3.critter.DAO.PetDAO;
import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import com.udacity.jdnd.course3.critter.user.UserEntities.CustomerEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {


    @Autowired
    PetDAO petDAO;

    @Autowired
    CustomerDAO customerDAO;



// get pet by id
    public PetEnt petById(Long petId) {
        PetEnt pet = petDAO.getOne(petId);
        return pet;
    }


// Get pets by customer id
    public List<PetEnt> petsByCustomer(long customerId) {
        return petDAO.findPetByCustomerId(customerId);
    }

    // get all pets list
    public List<PetEnt> allPets() {
        return petDAO.findAll();
    }


//================ Save pet method===============//
public PetEnt petSave( Long customerId, PetEnt petEnt) {
    CustomerEnt customer = customerDAO.getOne(customerId);
    petEnt.setCustomer(customer);
    petEnt = petDAO.<PetEnt>save(petEnt);

    List<PetEnt> pets = new ArrayList<>();
    pets.add(petEnt);

    customer.setPets(pets);
    customerDAO.<CustomerEnt>save(customer);

    return petEnt;
}
//==================================================>>



}
