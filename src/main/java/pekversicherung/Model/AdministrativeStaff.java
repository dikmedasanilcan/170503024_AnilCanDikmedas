package pekversicherung.Model;

import java.io.*;
import java.lang.*;
import java.util.*;

public class AdministrativeStaff extends Person{

    private int administrativeStaffId;
    private String position;
    private int socialSecurityNumber;

    public AdministrativeStaff(String firstName, String lastName, int phoneNumber, int id) {
        super(firstName, lastName, phoneNumber, id);
    }

    public int getAdministrativeStaffId() {
        return administrativeStaffId;
    }

    public void setAdministrativeStaffId(int administrativeStaffId) {
        this.administrativeStaffId = administrativeStaffId;
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

    public void addContract(){
        //...
    }

    public void deleteContract(){
        //...
    }

    public void createUser(){
        //...
    }

    public void deleteUser(){
        //...
    }

    public void updateUser(){
        //...
    }

}
