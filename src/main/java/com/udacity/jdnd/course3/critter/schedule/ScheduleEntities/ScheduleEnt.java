package com.udacity.jdnd.course3.critter.schedule.ScheduleEntities;

import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.UserEntities.EmployeeEnt;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Table
@Entity

public class ScheduleEnt {

    @Id   //the primary key of current entity
    @GeneratedValue  //auto increment of column
    private long id;


    // Use ManyToMany relationship annotation

    @ManyToMany(targetEntity = PetEnt.class)
    private List<PetEnt> pets;

    @ManyToMany(targetEntity = EmployeeEnt.class)
    private List<EmployeeEnt> employee;


    @ElementCollection  // use annotation   @ElementCollection  to implement one-to-many relationship with simple way
    private Set<EmployeeSkill> activities;


    private LocalDate date;


// Generate constructors to ScheduleEnt


    public ScheduleEnt(long id, List<EmployeeEnt> employee, List<PetEnt> pets, Set<EmployeeSkill> activities, LocalDate date) {
        this.id = id;
        this.employee = employee;
        this.pets = pets;
        this.activities = activities;
        this.date = date;
    }


    public ScheduleEnt(LocalDate date, Set<EmployeeSkill> activities) {
        this.date = date;
        this.activities = activities;
    }

    public ScheduleEnt() {
    }

    // Generate Getter and Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<EmployeeEnt> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeEnt> employee) {
        this.employee = employee;
    }

    public List<PetEnt> getPets() {
        return pets;
    }

    public void setPets(List<PetEnt> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

}
