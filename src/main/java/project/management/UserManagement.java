package project.management;

import java.io.*;
import java.util.ArrayList;

public final class UserManagement implements InterfaceUserMgmt {
    private ArrayList<User> userList;
    String filename = "users.txt";

    public UserManagement() {
        userList = new ArrayList<>();
    }

    public void registerRegularUser(String firstName, String lastName, String username, String password) {
        userList = readFromFile();
        RegularUser newUser = new RegularUser(firstName, lastName, username, password);
        userList.add(newUser);
        writeToFile(userList);
    }

    public void registerAdminUser(int adminID, String username, String password) {
        userList = readFromFile();
        AdminUser newUser = new AdminUser(adminID, username, password);
        userList.add(newUser);
        writeToFile(userList);
    }

    public User getUser(String username) {
        ArrayList<User> userList = readFromFile();

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void banUser(String username) {
        userList = readFromFile();
        User userToRemove = null;

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                userToRemove = user;
                break;
            }
        }

        if (userToRemove != null) {
            userList.remove(userToRemove);
            writeToFile(userList);
            System.out.println("User banned successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    public void writeToFile(ArrayList<User> userList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : userList) {
                if (user instanceof RegularUser) {
                    RegularUser regularUser = (RegularUser) user;
                    writer.write(regularUser.getFirstName() + "," + regularUser.getLastName() + ","
                            + regularUser.getUsername() + "," + regularUser.getPassword() + ",Regular");
                } else if (user instanceof AdminUser) {
                    AdminUser adminUser = (AdminUser) user;
                    writer.write(adminUser.getAdminID() + "," + adminUser.getUsername() + ","
                            + adminUser.getPassword() + ",Admin");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to users file: " + e.getMessage());
        }
    }

    public ArrayList<User> readFromFile() {
        ArrayList<User> readUserList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                if (accountData.length == 4) {
                    int savedAdminID = Integer.parseInt(accountData[0]);
                    String savedUsername = accountData[1],
                            savedPassword = accountData[2];

                    readUserList.add(new AdminUser(savedAdminID, savedUsername, savedPassword));
                } else if (accountData.length == 5) {
                    String savedFirstName = accountData[0],
                            savedLastName = accountData[1],
                            savedUsername = accountData[2],
                            savedPassword = accountData[3];

                    readUserList.add(new RegularUser(savedFirstName, savedLastName, savedUsername, savedPassword));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        return readUserList;
    }
}