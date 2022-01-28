package com.udacity.jdnd.course3.critter.DAO;

import com.udacity.jdnd.course3.critter.user.UserEntities.CustomerEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerDAO extends JpaRepository<CustomerEnt, Long> {

}
