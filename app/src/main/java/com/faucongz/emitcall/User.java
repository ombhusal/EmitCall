package com.faucongz.emitcall;
import org.jitsi.meet.sdk.JitsiMeetActivity;

public class User {
    private String name,email,pass;


    public User(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
