package project;

import java.io.Serializable;

public class Message implements Serializable {
    private String messageURL;
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;
    private static int count;

    public Message() {

    }

    public Message(String messageURL, String sender, String receiver, String content, String timestamp) {
        this.messageURL = messageURL;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = timestamp;
        count++;
    }

    public void setMessageURL(String messageURL) {
        this.messageURL = messageURL;
    }

    public String getMessageURL() {
        return messageURL;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public static int getCount() {
        return count;
    }

    public String toString() {
        return "Message URL: " + messageURL + ", Sender: " + sender + ", Receiver: " + receiver +
                ", Content: " + content + ", Timestamp: " + timestamp;
    }
}