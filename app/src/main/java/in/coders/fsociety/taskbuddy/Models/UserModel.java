package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

public class UserModel {
   /*
      "bio": null,
      "circleCount": 0,
      "email": "apc.ss@gmail.com",
      "friendCount": 0,
      "id": "847596855g3",
      "name": "Rahul",
      "picUrl": "http://wallpaper-gallery.net/images/profile-pics/profile-pics-20.jpg",
      "success": true
   */

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

    public UserModel(String bio, int circleCount, String email, int friendCount, String id, String name, String picUrl, boolean success) {
        this.bio = bio;
        this.circleCount = circleCount;
        this.email = email;
        this.friendCount = friendCount;
        this.id = id;
        this.name = name;
        this.picUrl = picUrl;
        this.success = success;
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
