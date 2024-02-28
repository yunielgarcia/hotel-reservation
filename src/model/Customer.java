package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String firstName, String lastName, String email) {
        this.checkEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private void checkEmail(String email) {
        final String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, invalid email");
        }
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.email;
    }

    public String getEmail() {
        return this.email;
    }
}
