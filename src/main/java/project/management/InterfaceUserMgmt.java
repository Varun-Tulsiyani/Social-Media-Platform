package project.management;

import java.util.ArrayList;

public interface InterfaceUserMgmt {
    void registerRegularUser(String firstName, String lastName, String username, String password);

    void registerAdminUser(int adminID, String username, String password);

    User getUser(String username);

    void banUser(String username);

    void writeToFile(ArrayList<User> userList);

    ArrayList<User> readFromFile();
}