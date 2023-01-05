package com.example.smartagenda.model;

import com.example.smartagenda.enums.Gender;

public class Person {
    //protected int personId;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String email;
    protected int age;
    protected Gender gender;

    public Person() {
    }

    public Person(String firstName, String lastName, String phone, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
