package com.practiceproject.exceltodatabase.service;

import com.practiceproject.exceltodatabase.domain.Customer;
import com.practiceproject.exceltodatabase.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
/*
When creating data transfer objects (DTOs) to transfer data between layers of your application (e.g., between the controller and service layer), you often need constructors to initialize all fields.
Using @AllArgsConstructor on DTO classes helps in creating constructors quickly without the need to write boilerplate code for initializing each field.
 */
/*
When you annotate a class with @Service, Spring Boot recognizes it as a service component and automatically registers it as a Spring bean during application startup.
Once a class is registered as a Spring bean, other components in the application, such as controllers or other services, can request instances of that bean through dependency injection.
 */
public class CustomerService {

    private CustomerRepository customerRepository;

    public void saveCustomerToDB(MultipartFile file){
        if(ExcelUploadService.isValidExcelFormat(file)){
            try {
                List<Customer> customers = ExcelUploadService.getCustomerDataFromExcel(file.getInputStream());
                this.customerRepository.saveAll(customers);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
}
