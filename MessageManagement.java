package project;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public final class MessageManagement implements InterfaceMessageMgmt {
    private ArrayList<Message> messageList;
    private String filename = "messages.txt";

    public MessageManagement() {
        messageList = readFromFile();
    }

    public void sendMessage(String messageURL, String sender, String receiver, String content) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
        Message newMessage = new Message(messageURL, sender, receiver, content, timeStamp);
        messageList.add(newMessage);

        writeToFile(messageList);

        System.out.println("Contents of the ArrayList:");
        for (Message message : messageList) {
            System.out.println(message.toString());
        }
    }

    public void deleteMessage(Message message) {
        if (messageList.contains(message)) {
            messageList.remove(message);
            writeToFile(messageList);
            System.out.println("Message removed successfully!");
        } else {
            System.out.println("Message not found!");
        }
    }

    public ArrayList<Message> getMessage(String username) {
        ArrayList<Message> userMessages = new ArrayList<>();
        messageList = readFromFile();

        for (Message m : messageList) {
            if (m.getSender().equals(username)) {
                userMessages.add(m);
            }
        }
        return userMessages;
    }

    public Message MessageByURL(String messageURL) {
        Message message = new Message();
        for (Message u : messageList) {
            if (u.getMessageURL().equals(messageURL)) {
                message = u;
            }
        }
        return message;
    }

    public void writeToFile(ArrayList<Message> messageList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Message message : messageList) {
                writer.write(message.getMessageURL() + "," + message.getSender() + "," + message.getReceiver() + ","
                        + message.getContent() + "," + message.getTimestamp());
                writer.newLine();
            }

            System.out.println("Message sent!");
        } catch (IOException e) {
            System.out.println("Error writing to messages file: " + e.getMessage());
        }
    }

    public ArrayList<Message> readFromFile() {
        ArrayList<Message> readMessageList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                String savedMessageURL = accountData[0],
                        savedSender = accountData[1],
                        savedReceiver = accountData[2],
                        savedContent = accountData[3],
                        savedTimeStamp = accountData[4];
                Message message = new Message(savedMessageURL, savedSender, savedReceiver, savedContent,
                        savedTimeStamp);
                readMessageList.add(message);
            }
        } catch (IOException e) {
            System.out.println("Error reading messages file: " + e.getMessage());
        }
        return readMessageList;
    }
}