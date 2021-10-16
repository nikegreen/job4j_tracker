package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public Error() {
    }

    public void info() {
        System.out.println("active=" + this.active
                + " status=" + this.status
                + " message=" + this.message);
    }

    public static void main(String[] args) {
        Error errDefault = new Error();
        Error err1 = new Error(true, 1, "err 1");
        Error err2 = new Error(false, 2, "err 2");
        Error err3 = new Error(true, 3, "err 3");
        errDefault.info();
        err1.info();
        err2.info();
        err3.info();
    }
}
