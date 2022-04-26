package pekversicherung.Model;

import java.io.*;
import java.lang.*;
import java.util.*;

public class Customer extends Person{

    private int customerId;
    private String insuranceType;
    private String address;
    private String userAccount;
    private String password;

    public Customer(String firstName, String lastName, int phoneNumber, int id) {
        super(firstName, lastName, phoneNumber, id);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
