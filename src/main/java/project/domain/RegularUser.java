package project.domain;

import java.io.Serializable;

public class RegularUser extends User implements Serializable {
    private String firstName;
    private String lastName;

    public RegularUser() {
        super();
    }

    public RegularUser(String firstName, String lastName, String username, String password) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + ", Last Name: " + lastName + super.toString();
    }
}