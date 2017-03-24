package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

public class UserModel {
   /*
      "bio": null,
      "circleCount": 0,
      "credit": 100,
      "email": "apc.ss@gmail.com",
      "friendCount": 0,
      "id": "847596855g3",
      "name": "Rahul",
      "picUrl": "http://wallpaper-gallery.net/images/profile-pics/profile-pics-20.jpg",
      "success": true
   */

    @SerializedName("credit")
    private int credit;
    @SerializedName("bio")
    private String bio;
    @SerializedName("circleCount")
    private int circleCount;
    @SerializedName("email")
    private String email;
    @SerializedName("friendCount")
    private int friendCount;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("picUrl")
    private String picUrl;
    @SerializedName("success")
    private boolean success;

    public UserModel() {
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getCircleCount() {
        return circleCount;
    }

    public void setCircleCount(int circleCount) {
        this.circleCount = circleCount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
