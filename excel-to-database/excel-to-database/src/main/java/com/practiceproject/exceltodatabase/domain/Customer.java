package com.practiceproject.exceltodatabase.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name="customers")
@Getter
@Setter
//these getetr and setter are there to generate getters
//and setters themselves using lombok

//to tell that this Cutomer class will act as Table in database
/*
@AllArgsConstructor: This annotation generates a constructor for all fields of the class. It saves you from writing boilerplate code for constructors with multiple parameters by automatically generating one that accepts values for all fields in the class.
@NoArgsConstructor: This annotation generates a constructor with no arguments. It's useful when you want to create objects without providing initial values for fields, such as when you're using frameworks like Hibernate or when you're deserializing objects from JSON.
 */
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    @Id
    private Integer customerId;
    //means this customerId is our primary key here.
    private String firstName;
    private String lastName;
    private String country;
    private Integer contact;
}
