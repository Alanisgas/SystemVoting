package ar.edu.unaj.login.controller;

public class ErrorValidation {

    private String message;

    public ErrorValidation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
