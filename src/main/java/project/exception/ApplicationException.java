package project.exception;

public class ApplicationException extends Exception {
    private int code;

    ApplicationException(int code) {
        this.code = code;
    }

    public String toString() {
        String message = "";
        if (code == 1) {
            message = "Invalid choice!";
        } else if (code == 2) {
            message = "Invalid username or password!";
        } else if (code == 3) {
            message = "Username already exists!";
        }

        return message;
    }
}

// signup()