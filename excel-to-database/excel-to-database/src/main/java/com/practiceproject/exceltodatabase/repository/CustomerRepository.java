package com.practiceproject.exceltodatabase.repository;

import com.practiceproject.exceltodatabase.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CustomerRepository extends JpaRepository<Customer, Integer> {

    /*
Class to Class:
Class can extend another class (single inheritance).
Class cannot extend an interface.
Class to Interface:
Class can implement multiple interfaces.
Class cannot implement a class.
Interface to Interface:
Interface can extend multiple interfaces (interface inheritance).
Interface cannot extend a class.
Interface cannot implement another interface.
Interface to Class:
Interface cannot extend a class.
Interface cannot implement a class.
     */



}
