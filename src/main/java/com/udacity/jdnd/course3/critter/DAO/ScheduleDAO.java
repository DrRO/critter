package com.udacity.jdnd.course3.critter.DAO;

import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import com.udacity.jdnd.course3.critter.schedule.ScheduleEntities.ScheduleEnt;
import com.udacity.jdnd.course3.critter.user.UserEntities.EmployeeEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDAO extends JpaRepository<ScheduleEnt, Long> {

    // Access to pet at ScheduleEnt
    List<ScheduleEnt> findByPets(PetEnt pet);



    // Access to pets list at ScheduleEnt
    List<ScheduleEnt> findByPetsIn (List<PetEnt> pets);

    // Access to employee at ScheduleEnt
    List<ScheduleEnt> findByEmployee(EmployeeEnt employee);
}


