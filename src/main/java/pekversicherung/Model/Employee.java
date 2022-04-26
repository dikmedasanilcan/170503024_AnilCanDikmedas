package pekversicherung.Model;

import java.io.*;
import java.lang.*;
import java.util.*;

public class Employee extends Person{

    private int employeeId;
    private String position;
    private int socialSecurityNumber;

    public Employee(String firstName, String lastName, int phoneNumber, int id) {
        super(firstName, lastName, phoneNumber, id);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
}
