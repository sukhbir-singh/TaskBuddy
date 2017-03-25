package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sahil on 25/3/17.
 */

public class Circle  {
     @SerializedName("title")
    private String title;
     @SerializedName("friendCount")
    private int numberOfMember;
    @SerializedName("id")
    private int id;
     @SerializedName("isAdmin")
    private boolean isAdmin;

    public Circle(String title, int numberOfMember, int id, boolean isAdmin) {
        this.title = title;
        this.numberOfMember = numberOfMember;
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfMember() {
        return numberOfMember;
    }

    public void setNumberOfMember(int numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
