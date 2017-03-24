package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProfilePostModel {
    /*

    "posts": [
    {
      "credit": 20,
      "description": "testing",
      "id": 5,
      "noOfParticipant": 0,
      "postPicUrl": "http://hd-wall-papers.com/images/wallpapers/profile-pics/profile-pics-17.png",
      "status": false,
      "tags": [
        "test",
        "hi",
        "credit",
        "hint"
      ],
      "title": "Post for testing"
    }
  ],
  "success": true

    */

    public ProfilePostModel() {
    }

    @SerializedName("success")
    private boolean success;

    @SerializedName("posts")
    private ArrayList<SingleProfilePost> posts;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<SingleProfilePost> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<SingleProfilePost> posts) {
        this.posts = posts;
    }
}
