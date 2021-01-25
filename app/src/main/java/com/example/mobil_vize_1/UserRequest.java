package com.example.mobil_vize_1;


import android.os.Build;
import android.text.Editable;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@RequiresApi(api = Build.VERSION_CODES.N)
public class UserRequest<datetime> {
    private String username;
    private String first_name;
    private String last_name;
    private String email;

    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

    private String date_joined = date;
    private String password = "13a1dwa3d1wd32a1@";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }
}
