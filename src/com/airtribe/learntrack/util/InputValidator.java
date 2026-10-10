package com.airtribe.learntrack.util;

public class InputValidator {

    public boolean validEmail(String email){
        return email.contains("@") && email.contains(".");
    }
}
