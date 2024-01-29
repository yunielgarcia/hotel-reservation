package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) {
        this.checkEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private void checkEmail(String email) {
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, invalid email");
        }
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
