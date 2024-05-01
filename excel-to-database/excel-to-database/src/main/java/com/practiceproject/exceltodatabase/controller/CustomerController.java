package com.practiceproject.exceltodatabase.controller;

import com.practiceproject.exceltodatabase.domain.Customer;
import com.practiceproject.exceltodatabase.service.CustomerService;
import lombok.AllArgsConstructor;
import org.apache.xmlbeans.impl.jam.provider.ResourcePath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    //to autowire this field to contructor we use allargsconstructor



    /*
    The main advantage of versioning APIs with version numbers (v1, v2, etc.) lies in its ability to maintain backward compatibility and provide a clear and predictable way for developers to manage changes and updates to the API. Here are the key advantages:

Backward Compatibility:
Versioning allows API providers to introduce changes, enhancements, or even major redesigns without breaking existing client applications.
By specifying a version number in the URI, clients can continue using the older version of the API until they are ready to upgrade to the new version.
     */

    /*
    API endpoints are specific URLs or URIs (Uniform Resource Identifiers) within an API that clients can use to interact with the API and perform various operations. Each endpoint typically represents a specific resource or functionality provided by the API. When a client makes an HTTP request to a specific endpoint, the API server processes the request and returns a response.
     */

    //we will create endpoint to accept file and send it to service and service will send to repositpry then to db

    @PostMapping("/upload-customers-data")
    public ResponseEntity<?> uploadCustomerData(@RequestParam("file") MultipartFile file){
        this.customerService.saveCustomerToDB(file);
        return ResponseEntity.ok(Map.of("Message", "Customers data uploaded and saved to DB successfully"));
    }

    /*
    The <?> part indicates that the body of the ResponseEntity can be of any type.
By using <?>, the method is declaring that it can return a response with a body of any type.


     */

    //now we will create endpoints to retrive data
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.FOUND);
    }





}
