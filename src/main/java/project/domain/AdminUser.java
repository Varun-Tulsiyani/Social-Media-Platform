package project.domain;

import java.io.Serializable;

public class AdminUser extends User implements Serializable {
    private int adminID;

    public AdminUser() {
        super();
    }

    public AdminUser(int adminID, String username, String password) {
        super(username, password);
        this.adminID = adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getAdminID() {
        return adminID;
    }

    @Override
    public String toString() {
        return "Admin ID: " + adminID + super.toString();
    }
}