package ru.maxmorev.android.fixedtabs.retrofit2;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    long id;

    @SerializedName("name")
    String name;

    @SerializedName("username")
    String userName;

    @SerializedName("email")
    String email;

    /*public User(long id, String name, String userName, String email) {
        id = id;
        name = name;
        userName = userName;
        email = email;
    }*/
    public User(){
        super();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
