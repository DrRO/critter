package com.udacity.jdnd.course3.critter.user.UserEntities;


import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class CustomerEnt {

    @Id  //the primary key of current entity
    @GeneratedValue  //auto increment of column
    private long id;

    private String name;
    private String phoneNumber;
    private String notes;

    // Generate CustomerEnt Constructor
    public CustomerEnt(long id, String name, String phoneNumber, String notes) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    @OneToMany(targetEntity = PetEnt.class)  // Relationship between CustomerEnt and PetEnt
    private List<PetEnt> pets;



    // Generate Getter and Setter methods


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PetEnt> getPets() {
        return pets;
    }

    public void setPets(List<PetEnt> pets) {
        this.pets = pets;
    }
}
