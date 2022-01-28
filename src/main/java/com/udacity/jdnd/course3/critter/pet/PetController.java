package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import com.udacity.jdnd.course3.critter.pet.PetServices.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    // Convert PetEnt to PetDTO
    private PetDTO PetEntToPetDTO(PetEnt pet) {

        PetDTO petDTO;
        petDTO = new PetDTO(pet.getId(),
                pet.getType(),
                pet.getName(),
                pet.getCustomer().getId(),
                pet.getBirthDate(),
                pet.getNotes());
        return petDTO;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        PetEnt petEnt = new PetEnt(petDTO.getType(),petDTO.getName(),petDTO.getBirthDate(),petDTO.getNotes());

        try {
            return PetEntToPetDTO(petService.petSave( petDTO.getOwnerId(), petEnt));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet not saved", exception);
        }

        /*throw new UnsupportedOperationException();*/
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        try {

            return PetEntToPetDTO(petService.petById(petId));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no Pet", exception);
        }

       /* throw new UnsupportedOperationException();*/
    }

    @GetMapping
    public List<PetDTO> getPets(){
       /* throw new UnsupportedOperationException();*/
        List<PetEnt> pets = petService.allPets();
        List<PetDTO> list = new ArrayList<>();
        for (PetEnt pet : pets) {
            PetDTO petDTO = PetEntToPetDTO(pet);
            list.add(petDTO);
        }
        return list;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        try {

            return petService.petsByCustomer(ownerId).stream().map(pet -> {
                return PetEntToPetDTO(pet);
            }).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no owner", exception);
        }

        /*throw new UnsupportedOperationException();*/
    }
}
