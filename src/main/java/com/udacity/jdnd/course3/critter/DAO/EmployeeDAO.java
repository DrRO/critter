package com.udacity.jdnd.course3.critter.DAO;

import com.udacity.jdnd.course3.critter.user.UserEntities.CustomerEnt;
import com.udacity.jdnd.course3.critter.user.UserEntities.EmployeeEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.DayOfWeek;
import java.util.List;



@Repository

public interface EmployeeDAO extends JpaRepository<EmployeeEnt, Long> {

    /**
     * @param dayOfWeek
     * @return
     */
    // Access to daysAvailable in EmployeeEnt
    List<EmployeeEnt> findByDaysAvailable(DayOfWeek dayOfWeek);
}




