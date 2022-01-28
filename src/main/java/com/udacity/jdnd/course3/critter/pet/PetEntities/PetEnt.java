package com.udacity.jdnd.course3.critter.pet.PetEntities;

import com.udacity.jdnd.course3.critter.pet.PetType;
import com.udacity.jdnd.course3.critter.user.UserEntities.CustomerEnt;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class PetEnt {


    @Id   //the primary key of current entity
    @GeneratedValue  //auto increment of column
    private long id;


    private PetType type;
    private String name;


    private long ownerId;
    private LocalDate birthDate;

    private String notes;

    @ManyToOne(targetEntity = CustomerEnt.class)
    // Relationship between PetEnt and CustomerEnt
    private CustomerEnt customer;



    // Generate constructors for PetEnt


    public PetEnt(PetType type, String name, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    // Generate Getter and Setter methods
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public CustomerEnt getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEnt customer) {
        this.customer = customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PetEnt() {
    }
}
