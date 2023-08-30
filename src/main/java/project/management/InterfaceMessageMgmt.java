package project.management;

import java.util.ArrayList;

public interface InterfaceMessageMgmt {
    void sendMessage(String messageURL, String sender, String receiver, String content);

    void deleteMessage(Message message);

    ArrayList<Message> getMessage(String username);

    Message MessageByURL(String messageURL);

    void writeToFile(ArrayList<Message> messageList);

    ArrayList<Message> readFromFile();
}