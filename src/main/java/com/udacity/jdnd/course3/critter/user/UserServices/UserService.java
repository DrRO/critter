package com.udacity.jdnd.course3.critter.user.UserServices;

import com.udacity.jdnd.course3.critter.DAO.CustomerDAO;
import com.udacity.jdnd.course3.critter.DAO.EmployeeDAO;
import com.udacity.jdnd.course3.critter.DAO.PetDAO;
import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.UserEntities.CustomerEnt;
import com.udacity.jdnd.course3.critter.user.UserEntities.EmployeeEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {
    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    PetDAO petDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    private CustomerEnt customerEnt;
    private EmployeeEnt employeeEnt;

    // get Customer by pet id
    public CustomerEnt getCustomer (Long petId) {
        customerEnt = petDAO.getOne(petId).getCustomer();
        return customerEnt;
    }


    // get Employee by id
    public EmployeeEnt employeeById(Long empId) {
        employeeEnt = employeeDAO.getOne(empId);
        return employeeEnt;
    }

    // Get all cusomers list
    public List<CustomerEnt> getCustomersList() {

        return customerDAO.findAll();
    }


    // Get employee by skills and date
    public List<EmployeeEnt> employeesBySkills(Set<EmployeeSkill> employeeSkills, LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<EmployeeEnt> list = new ArrayList<>();

        // Find EmployeeEnt by days avaliable
        List<EmployeeEnt> findByDaysAvailable = employeeDAO.findByDaysAvailable(dayOfWeek);

        // use for loop to add employee to list if EmployeeEnt contain employeeSkills in days Avaliable
        for (int i = 0, findByDaysAvailableSize = findByDaysAvailable.size(); i < findByDaysAvailableSize; i++) {
            EmployeeEnt employee = findByDaysAvailable.get(i);
            if (employee.getSkills().containsAll(employeeSkills)) {
                list.add(employee);
            }
        }
        return list;
    }


    // set Employee by days and id
    public void setEmployee(Set<DayOfWeek> days, Long empid) {
// get employee by id
        EmployeeEnt employee = employeeById(empid);
// set dayavaliable
        employee.setDaysAvailable(days);
// save employee
        employeeDAO.save(employee);

    }


    //==============Save Methods======================//

    // save customer method
    public CustomerEnt saveCustomer(CustomerEnt customer, List<Long> petIds) {
        List<PetEnt> customerPets = new ArrayList<>();
        if (petIds == null || petIds.isEmpty()) {
        } else {
            List<PetEnt> list = new ArrayList<>();
            for (Long petId : petIds) {
                PetEnt one = petDAO.getOne(petId);
                list.add(one);
            }
            customerPets = list;
        }
        customer.setPets(customerPets);
        return customerDAO.save(customer);
    }


    // Save Employee method
    public EmployeeEnt employeeSave(EmployeeEnt employee) {
        employeeEnt = employeeDAO.save(employee);
        return employeeEnt;
    }

    //==================================================//


}
