package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import com.udacity.jdnd.course3.critter.schedule.ScheduleEntities.ScheduleEnt;
import com.udacity.jdnd.course3.critter.schedule.scheduleServices.ServiceOfSchedule;
import com.udacity.jdnd.course3.critter.user.UserEntities.EmployeeEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ServiceOfSchedule serviceOfSchedule;

// convert ScheduleEnt to ScheduleDTO
    private ScheduleDTO scheduleEntToScheduleDTO(ScheduleEnt scheduleEnt) {

        List<Long> employeeIds;
        List<Long> petIds;
// Collect employee in list by id
        List<Long> emplist = new ArrayList<>();
        for (EmployeeEnt employeeEnt : scheduleEnt.getEmployee()) {
            Long id = employeeEnt.getId();
            emplist.add(id);
        }
        employeeIds = emplist;


 // collect pets into list by id
        List<Long> petList = new ArrayList<>();
        for (PetEnt petEnt : scheduleEnt.getPets()) {
            Long id = petEnt.getId();
            petList.add(id);
        }
        petIds = petList;

      // get ScheduleDTO data
        ScheduleDTO scheduleDTO = new ScheduleDTO(scheduleEnt.getId(),
                employeeIds,
                petIds,
                scheduleEnt.getDate(),
                scheduleEnt.getActivities());

        return scheduleDTO;

    }



    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

/*throw new UnsupportedOperationException();*/


        try {
            ScheduleEnt schedule = new ScheduleEnt( scheduleDTO.getDate(),
                    scheduleDTO.getActivities());
            return scheduleEntToScheduleDTO(serviceOfSchedule.saveSchedule(schedule,
                    scheduleDTO.getEmployeeIds(),
                    scheduleDTO.getPetIds()));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedule not saved", exception);
        }

    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

/*throw new UnsupportedOperationException();*/

        List<ScheduleEnt> schedules = serviceOfSchedule.allSchedules();
        List<ScheduleDTO> schedulelist = new ArrayList<>();

        // for loop to add scheduleDTO to  schedulelist
        for (ScheduleEnt schedule : schedules) {
            ScheduleDTO scheduleDTO = scheduleEntToScheduleDTO(schedule);
            schedulelist.add(scheduleDTO);
        }
        return schedulelist;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

/*throw new UnsupportedOperationException();*/

        List<ScheduleEnt> schedules;
            schedules = serviceOfSchedule.petSchedule(petId);
        List<ScheduleDTO> schedulelist = new ArrayList<>();

        // for loop to add scheduleDTO to  schedulelist
        for (ScheduleEnt schedule : schedules) {
            ScheduleDTO scheduleDTO = scheduleEntToScheduleDTO(schedule);
            schedulelist.add(scheduleDTO);
        }
        return schedulelist;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {

/* throw new UnsupportedOperationException();*/


        List<ScheduleEnt> schedules;

         schedules = serviceOfSchedule.employeeSchedule(employeeId);
        List<ScheduleDTO> schedulelist = new ArrayList<>();

        // for loop to add scheduleDTO to  schedulelist
        for (ScheduleEnt schedule : schedules) {
            ScheduleDTO scheduleDTO = scheduleEntToScheduleDTO(schedule);
            schedulelist.add(scheduleDTO);
        }
        return schedulelist;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {

/* throw new UnsupportedOperationException();*/

        List<ScheduleEnt> schedules = serviceOfSchedule.customerSchedule(customerId);

        List<ScheduleDTO> schedulelist = new ArrayList<>();

        // for loop to add scheduleDTO to  schedulelist
        for (ScheduleEnt schedule : schedules) {
            ScheduleDTO scheduleDTO = scheduleEntToScheduleDTO(schedule);
            schedulelist.add(scheduleDTO);
        }
        return schedulelist;

    }
}


