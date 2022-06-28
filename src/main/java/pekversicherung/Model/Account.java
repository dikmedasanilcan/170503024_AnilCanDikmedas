package pekversicherung.model;

import java.util.Date;

public class Account{
    private boolean isLoggedIn = false;
    private AdministrativeStaff person;

    private String username;
    private String password;

    public Account() {
    }

    public void login(String username, String password){
        isLoggedIn = true;
        this.username = username;
        this.password = password;
    }

    public void logout(){
        isLoggedIn = false;
    }

    public void setPerson(AdministrativeStaff person) {
        this.person = person;
    }

    public AdministrativeStaff getPerson(){
        return person;
    }

}
