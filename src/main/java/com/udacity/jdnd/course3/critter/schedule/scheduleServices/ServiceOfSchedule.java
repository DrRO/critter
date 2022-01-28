package com.udacity.jdnd.course3.critter.schedule.scheduleServices;

import com.udacity.jdnd.course3.critter.DAO.CustomerDAO;
import com.udacity.jdnd.course3.critter.DAO.EmployeeDAO;
import com.udacity.jdnd.course3.critter.DAO.PetDAO;
import com.udacity.jdnd.course3.critter.DAO.ScheduleDAO;
import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import com.udacity.jdnd.course3.critter.schedule.ScheduleEntities.ScheduleEnt;
import com.udacity.jdnd.course3.critter.user.UserEntities.CustomerEnt;
import com.udacity.jdnd.course3.critter.user.UserEntities.EmployeeEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ServiceOfSchedule {

    @Autowired
    ScheduleDAO scheduleDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    PetDAO petDAO;

// get all Schedule list
    public List<ScheduleEnt> allSchedules() {
        List<ScheduleEnt> allSchedules;
        allSchedules = scheduleDAO.findAll();
        return allSchedules;
    }

    // get Schedule by Employee id
    public List<ScheduleEnt> employeeSchedule(Long employeeId) {
        EmployeeEnt employee;
        List<ScheduleEnt> schedules;
        employee = employeeDAO.getOne(employeeId);
        schedules = Collections.unmodifiableList(scheduleDAO.findByEmployee(employee));
        return schedules;
    }

    // get Schedule by pet id

    public List<ScheduleEnt> petSchedule(Long petId) {
        PetEnt pet;
        List<ScheduleEnt> schedules;

        pet = petDAO.getOne(petId);

        schedules = Collections.unmodifiableList(scheduleDAO.findByPets(pet));
        return schedules;
    }


    // get Schedule by customer id
    public List<ScheduleEnt> customerSchedule(Long custId) {
        CustomerEnt customer;
        List<ScheduleEnt> schedules;

        customer = customerDAO.getOne(custId);

        schedules = Collections.unmodifiableList(scheduleDAO.findByPetsIn(customer.getPets()));
        return schedules;
    }

//===========Save Schedule==============//
    public ScheduleEnt saveSchedule(ScheduleEnt scheduleEnt, List<Long> employeeIds, List<Long> petId) {

        // Set pets at scheduleEnt
        List<PetEnt> pets;
        pets = Collections.unmodifiableList(petDAO.findAllById(petId));
        scheduleEnt.setPets(pets);

        // Set employees at scheduleEnt
        List<EmployeeEnt> employee;
        employee = Collections.unmodifiableList(employeeDAO.findAllById(employeeIds));
        scheduleEnt.setEmployee(employee);

        ScheduleEnt saveSchedule = scheduleDAO.save(scheduleEnt);
        return saveSchedule;
    }
//==========================================//
}
