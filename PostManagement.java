package project;

import java.io.*;
import java.util.*;

public final class PostManagement implements InterfacePostMgmt {
    private ArrayList<Post> postList;
    private String filename = "posts.txt";

    public PostManagement() {
        postList = readFromFile();
    }

    public void createPost(String username, PostType postType, String postName, String postTitle, String postContent) {
        switch (postType) {
            case TEXT:
                postList.add(new TextPost(username, postType, postName, postTitle, postContent));
                break;

            case IMAGE:
                postList.add(new ImagePost(username, postType, postName, postTitle, postContent));
                break;

            case AUDIO:
                postList.add(new AudioPost(username, postType, postName, postTitle, postContent));
                break;

            case VIDEO:
                postList.add(new VideoPost(username, postType, postName, postTitle, postContent));
                break;

            default:
                System.out.println("Invalid choice");
                break;
        }

        writeToFile(postList);

        System.out.println("Contents of the ArrayList:");
        for (Post post : postList) {
            System.out.println(post.toString());
        }
    }

    public void deletePost(Post post) {
        if (postList.contains(post)) {
            postList.remove(post);
            writeToFile(postList);
            System.out.println("Post removed successfully!");
        } else {
            System.out.println("Post not found!");
        }
    }

    public ArrayList<Post> getPost(String username) {
        ArrayList<Post> userPosts = new ArrayList<>();
        postList = readFromFile();

        for (Post post : postList) {
            if (post.getUsername().equals(username)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }

    public Post PostByPostName(String postName) {
        Post post = new Post();
        for (Post p : postList) {
            if (p.getPostName().equals(postName)) {
                post = p;
            }
        }
        return post;
    }

    public void writeToFile(ArrayList<Post> postList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Post post : postList) {
                switch (post.getPostType()) {
                    case TEXT:
                        TextPost textPost = (TextPost) post;
                        writer.write(textPost.getUsername() + "," + textPost.getPostType() + ","
                                + textPost.getPostName() + "," + textPost.getPostTitle() + ","
                                + textPost.getPostContent() + "," + textPost.getTimestamp());
                        break;
                    case AUDIO:
                        AudioPost audioPost = (AudioPost) post;
                        writer.write(audioPost.getUsername() + "," + audioPost.getPostType() + ","
                                + audioPost.getPostName() + "," + audioPost.getAudioTitle() + ","
                                + audioPost.getAudioDescription() + "," + audioPost.getTimestamp());
                        break;
                    case IMAGE:
                        ImagePost imagePost = (ImagePost) post;
                        writer.write(imagePost.getUsername() + "," + imagePost.getPostType() + ","
                                + imagePost.getPostName() + "," + imagePost.getImageTitle() + ","
                                + imagePost.getImageDescription() + "," + imagePost.getTimestamp());
                        break;
                    case VIDEO:
                        VideoPost videoPost = (VideoPost) post;
                        writer.write(videoPost.getUsername() + "," + videoPost.getPostType() + ","
                                + videoPost.getPostName() + "," + videoPost.getVideoTitle() + ","
                                + videoPost.getVideoDescription() + "," + videoPost.getTimestamp());
                        break;
                }
                writer.newLine();
            }

            System.out.println("Post successful!");
        } catch (IOException e) {
            System.out.println("Error writing to posts file: " + e.getMessage());
        }
    }

    public ArrayList<Post> readFromFile() {
        ArrayList<Post> readPostList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                String savedUsername = accountData[0],
                        savedPostName = accountData[2],
                        savedPostTitle = accountData[3],
                        savedPostContent = accountData[4];

                PostType postType;
                switch (accountData[1]) {
                    case "TEXT":
                        postType = PostType.TEXT;
                        readPostList.add(new TextPost(savedUsername, postType, savedPostName, savedPostTitle,
                                savedPostContent));
                        break;
                    case "AUDIO":
                        postType = PostType.AUDIO;
                        readPostList.add(new AudioPost(savedUsername, postType, savedPostName, savedPostTitle,
                                savedPostContent));
                        break;
                    case "IMAGE":
                        postType = PostType.IMAGE;
                        readPostList.add(new ImagePost(savedUsername, postType, savedPostName, savedPostTitle,
                                savedPostContent));
                        break;
                    case "VIDEO":
                        postType = PostType.VIDEO;
                        readPostList.add(new VideoPost(savedUsername, postType, savedPostName, savedPostTitle,
                                savedPostContent));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading posts file: " + e.getMessage());
        }
        return readPostList;
    }
}