package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetEntities.PetEnt;
import com.udacity.jdnd.course3.critter.user.UserEntities.CustomerEnt;
import com.udacity.jdnd.course3.critter.user.UserEntities.EmployeeEnt;
import com.udacity.jdnd.course3.critter.user.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;



    // Convert Customer entity to DTO
    private CustomerDTO customerEntityToCustomerDTO(CustomerEnt customerEnt){
        List<Long> petIds = new ArrayList<>();
        for (PetEnt petEnt : customerEnt.getPets()) {
            Long id = petEnt.getId();
            petIds.add(id);
        }

        CustomerDTO customerDTO =  new CustomerDTO(customerEnt.getId(),
                customerEnt.getName(),
                customerEnt.getPhoneNumber(),
                customerEnt.getNotes(),
                petIds);
        return customerDTO;
    }



    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){


        CustomerEnt customer = new CustomerEnt(customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getPhoneNumber(),
                customerDTO.getNotes());

        try {
            return customerEntityToCustomerDTO(userService.saveCustomer(customer, customerDTO.getPetIds()));
        } catch (Exception exception) {
            throw new ResponseStatusException(Objects.requireNonNull(HttpStatus.BAD_REQUEST),
                    "Customer not saved", exception);
        }
/*throw new UnsupportedOperationException();*/

    }




    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){


        List<CustomerDTO> list = new ArrayList<>();
        List<CustomerEnt> customers = userService.getCustomersList();

        // loop method to add customerDTO to list
        for (CustomerEnt customer : customers) {
            CustomerDTO customerDTO = customerEntityToCustomerDTO(customer);

            list.add(customerDTO);
        }
        return list;
/* throw new UnsupportedOperationException();*/

    }



    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

        try {
            return customerEntityToCustomerDTO(userService.getCustomer(petId));
        } catch (Exception exception) {
            throw new ResponseStatusException(Objects.requireNonNull(HttpStatus.BAD_REQUEST),
                    "Owner not found", exception);
        }

/*throw new UnsupportedOperationException();*/

    }


    // Convert Employee entity to DTO
    private EmployeeDTO employeeEntityToEmployeeDTO(EmployeeEnt employee) {
        EmployeeDTO employeeDTO =   new EmployeeDTO(employee.getId(),
                employee.getName(),
                employee.getSkills(),
                employee.getDaysAvailable());
        return employeeDTO;
    }




    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {

        // get EmployeeEnt data
        EmployeeEnt employee = new EmployeeEnt(employeeDTO.getId(),
                employeeDTO.getName(),
                employeeDTO.getSkills(),
                employeeDTO.getDaysAvailable());

        try {

            // save data
            return employeeEntityToEmployeeDTO(userService.employeeSave(employee));
        } catch (Exception exception) {
            throw new ResponseStatusException(Objects.requireNonNull(HttpStatus.BAD_REQUEST),
                    "Employee  not saved", exception);
        }
/* throw new UnsupportedOperationException();*/

    }





    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        try {
   // Get Employee by  employeeId
            return employeeEntityToEmployeeDTO(userService.employeeById(employeeId));
        } catch (Exception exception) {
            throw new ResponseStatusException(Objects.requireNonNull(HttpStatus.BAD_REQUEST),
                    "Employee not found", exception);
        }

/* throw new UnsupportedOperationException();*/

    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {

        try {
            // Set employee avaliability
            userService.setEmployee(daysAvailable, employeeId);
        } catch (Exception exception) {
            throw new ResponseStatusException(Objects.requireNonNull(HttpStatus.BAD_REQUEST),
                    "Employee not found", exception);
        }
/*throw new UnsupportedOperationException();*/

    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<EmployeeEnt> employeeEntList;
        employeeEntList = userService.employeesBySkills( employeeDTO.getSkills(),employeeDTO.getDate());
        try {
            return employeeEntList.stream().map(employee -> employeeEntityToEmployeeDTO(employee)).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ResponseStatusException(Objects.requireNonNull(HttpStatus.BAD_REQUEST),
                    "Employee not found", exception);
        }
/* throw new UnsupportedOperationException();*/

    }

}

