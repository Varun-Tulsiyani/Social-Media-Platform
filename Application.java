package project;

import java.util.*;

public class Application {
    static Scanner in = new Scanner(System.in);
    static String firstName, lastName, username, password, currentUser, postName, postTitle, postContent, savedUserType;
    static int choice1, adminID;
    static UserManagement um = new UserManagement();
    static PostManagement pm = new PostManagement();
    static MessageManagement mm = new MessageManagement();
    static PostType postType;
    private static final int LOGIN = 1, SIGNUP = 2, EXIT = 3;
    private static final int REGULAR_USER = 1, ADMIN_USER = 2;
    private static final int TEXT = 1, AUDIO = 2, IMAGE = 3, VIDEO = 4;
    private static final int POST = 1, MESSAGE = 2;
    private static final int DELETE_POST = 1, GET_USER = 2, BAN_USER = 3, SIGNOUT = 4;
    private static final int ADD = 1, DELETE = 2, GET = 3;
    private static final int INVALID_CHOICE = 1, AUTHENTICATION = 2, USERNAME_TAKEN = 3;

    public static void main(String[] args) throws ApplicationException {
        // ClearScreen();
        System.out.println("WELCOME TO SOCIAL MEDIA PLATFORM");

        System.out.println("1. Log In" + "\n2. Sign Up" + "\n3. Exit");
        choice1 = in.nextInt();

        switch (choice1) {
            case LOGIN:
                login();
                break;
            case SIGNUP:
                signup();
                break;
            case EXIT:
                System.out.println("Exiting!");
                return;
            default:
                throw new ApplicationException(INVALID_CHOICE);
        }

        if (currentUser != null) {
            System.out.println("Welcome " + currentUser);
            User currentUserObj = um.getUser(currentUser);
            if (currentUserObj instanceof RegularUser) {
                RegularUserMenu();
            } else if (currentUserObj instanceof AdminUser) {
                AdminUserMenu();
            }
        } else {
            throw new ApplicationException(AUTHENTICATION);
        }
    }

    public static void login() throws ApplicationException {
        // ClearScreen();

        System.out.println("---Log In---");
        System.out.print("Username: ");
        username = in.next();
        System.out.print("Password: ");
        password = in.next();

        ArrayList<User> userList = um.readFromFile();
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = username;
                break;
            }
        }
    }

    public static void signup() throws ApplicationException {
        ClearScreen();

        System.out.println("---Sign Up---");
        System.out.println("1. Regular User" + "\n2. Admin User");
        choice1 = in.nextInt();

        System.out.print("Username: ");
        username = in.next();
        ArrayList<User> userList = um.readFromFile();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                throw new ApplicationException(USERNAME_TAKEN);
            }
        }

        System.out.print("Password: ");
        password = in.next();

        if (choice1 == REGULAR_USER) {
            System.out.print("First Name: ");
            firstName = in.next();
            System.out.print("Last Name: ");
            lastName = in.next();
            um.registerRegularUser(firstName, lastName, username, password);
        } else if (choice1 == ADMIN_USER) {
            System.out.print("Admin ID: ");
            adminID = in.nextInt();
            um.registerAdminUser(adminID, username, password);
        }
        System.out.println("Sign Up successful");
        login();
    }

    public static void add() throws ApplicationException {
        ClearScreen();

        System.out.println("1. Post" + "\n2. Message");
        choice1 = in.nextInt();

        if (choice1 == POST) {
            System.out.println("---ADD POST---");
            System.out.println("1. Text Post" + "\n2. Audio Post" + "\n3. Image Post" + "\n4. Video Post");
            choice1 = in.nextInt();
            switch (choice1) {
                case TEXT:
                    System.out.println("---TEXT---");
                    postType = PostType.TEXT;
                    postName = currentUser + "text" + TextPost.TextPostCount();

                    System.out.println("Text Title: ");
                    postTitle = in.next();
                    System.out.println("Text Content: ");
                    postContent = in.next();

                    pm.createPost(currentUser, postType, postName, postTitle, postContent);
                    break;
                case AUDIO:
                    System.out.println("---AUDIO---");
                    postType = PostType.AUDIO;
                    postName = currentUser + "audio" + AudioPost.AudioPostCount();

                    System.out.println("Audio Title: ");
                    postTitle = in.next();
                    System.out.println("Audio Description: ");
                    postContent = in.next();

                    pm.createPost(currentUser, postType, postName, postTitle, postContent);
                    break;
                case IMAGE:
                    System.out.println("---IMAGE---");
                    postType = PostType.IMAGE;
                    postName = currentUser + "image" + ImagePost.ImagePostCount();

                    System.out.println("Image Title: ");
                    postTitle = in.next();
                    System.out.println("Image Description: ");
                    postContent = in.next();

                    pm.createPost(currentUser, postType, postName, postTitle, postContent);
                    break;
                case VIDEO:
                    System.out.println("---VIDEO---");
                    postType = PostType.VIDEO;
                    postName = currentUser + "video" + ImagePost.ImagePostCount();

                    System.out.println("Video Title: ");
                    postTitle = in.next();
                    System.out.println("Video Description: ");
                    postContent = in.next();

                    pm.createPost(currentUser, postType, postName, postTitle, postContent);
                    break;
                default:
                    throw new ApplicationException(INVALID_CHOICE);
            }
        } else if (choice1 == MESSAGE) {
            System.out.println("---MESSAGE---");
            String messageURL = currentUser + Message.getCount();

            System.out.println("Receiver: ");
            String receiver = in.next();
            System.out.println("Message: ");
            String content = in.next();

            mm.sendMessage(messageURL, currentUser, receiver, content);
        } else {
            throw new ApplicationException(INVALID_CHOICE);
        }
    }

    public static void delete() throws ApplicationException {
        ClearScreen();

        System.out.println("1. Post" + "\n2. Message");
        choice1 = in.nextInt();

        if (choice1 == POST) {
            System.out.println("---POST---");
            System.out.print("Post Name: ");
            String postName = in.next();
            pm.deletePost(pm.PostByPostName(postName));
        } else if (choice1 == MESSAGE) {
            System.out.println("---MESSAGE---");
            System.out.println("Message URL: ");
            String messageURL = in.next();
            mm.deleteMessage(mm.MessageByURL(messageURL));
        } else {
            throw new ApplicationException(INVALID_CHOICE);
        }
    }

    public static void get() throws ApplicationException {
        ClearScreen();

        System.out.println("1. Post" + "\n2. Message");
        choice1 = in.nextInt();

        if (choice1 == POST) {
            System.out.println("---POST---");
            System.out.println(pm.getPost(currentUser));
        } else if (choice1 == MESSAGE) {
            System.out.println("---MESSAGE---");
            System.out.println(mm.getMessage(currentUser));
        } else {
            throw new ApplicationException(INVALID_CHOICE);
        }
    }

    public static void RegularUserMenu() throws ApplicationException {
        while (savedUserType != "" || savedUserType != null) {
            System.out.println("---REGULAR USER---");
            System.out.println("1. Add" + "\n2. Delete" + "\n3. Get" + "\n4. Sign Out");
            choice1 = in.nextInt();

            switch (choice1) {
                case ADD:
                    add();
                    break;
                case DELETE:
                    delete();
                    break;
                case GET:
                    get();
                    break;
                case SIGNOUT:
                    savedUserType = "";
                    main(null);
                    break;
                default:
                    throw new ApplicationException(INVALID_CHOICE);
            }
        }
    }

    public static void AdminUserMenu() throws ApplicationException {
        while (savedUserType != "" || savedUserType != null) {
            System.out.println("---ADMIN USER---");
            System.out.println("1. Delete Post" + "\n2. Get User Details" + "\n3. Ban User" + "\n4. Sign Out");
            choice1 = in.nextInt();

            switch (choice1) {
                case DELETE_POST:
                    System.out.print("Post Name: ");
                    postName = in.next();
                    pm.deletePost(pm.PostByPostName(postName));
                    break;
                case GET_USER:
                    System.out.print("Username: ");
                    username = in.next();
                    System.out.println(um.getUser(username));
                    break;
                case BAN_USER:
                    System.out.print("Username: ");
                    username = in.next();
                    um.banUser(username);
                    break;
                case SIGNOUT:
                    savedUserType = "";
                    main(null);
                    break;
                default:
                    throw new ApplicationException(INVALID_CHOICE);
            }
        }
    }

    public static void ClearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}