package pekversicherung.Model;

import java.util.Date;

public class Account{
    private boolean isLoggedIn = false;
    private AdministrativeStaff admin;

    private String username;
    private String password;

    public Account() {
    }

    public void anMelden(String username, String password){
        isLoggedIn = true;
        this.username = username;
        this.password = password;
    }

    public void abMelden(){
        isLoggedIn = false;
    }

    public void setAdmin(AdministrativeStaff admin) {
        this.admin = admin;
    }

    public AdministrativeStaff getAdmin(){ return admin; }

}
