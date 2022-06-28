package pekversicherung.model;

import java.io.*;
import java.lang.*;
import java.util.*;

public class Employee extends Person{

    public String role;

    public Employee(int id, String firstName, String lastName, double phoneNumber, String address, String role) {
        super(id, firstName, lastName, phoneNumber, address);
        setRole(role);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
