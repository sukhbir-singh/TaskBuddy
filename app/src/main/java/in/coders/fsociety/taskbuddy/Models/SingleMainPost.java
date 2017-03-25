package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 24/3/17.
 */

public class SingleMainPost {
     /*
    {
      "categoryTitle": [],
      "credit": 20,
      "description": "testing",
      "id": 5,
      "noOfParticipant": 0,
      "postPicUrl": "http://hd-wall-papers.com/images/wallpapers/profile-pics/profile-pics-17.png",
      "public": true,
      "status": false,
      "tags": [
        "test",
        "hi",
        "credit",
        "hint"
      ],
      "title": "Post for testing"
    }
    */

    @SerializedName("categoryTitle")
    private ArrayList<String> categoryTitle;
    @SerializedName("credit")
    private int credit;
    @SerializedName("description")
    private String description;
    @SerializedName("id")
    private int id;
    @SerializedName("noOfParticipant")
    private int noOfParticipant;
    @SerializedName("postPicUrl")
    private String postPicUrl;
    @SerializedName("public")
    private boolean isPublic;
    @SerializedName("status")
    private boolean status;
    @SerializedName("tags")
    private ArrayList<String> tags;
    @SerializedName("title")
    private String title;

    @SerializedName("userProgress")
    private int userProgress;

    public ArrayList<String> getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(ArrayList<String> categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfParticipant() {
        return noOfParticipant;
    }

    public void setNoOfParticipant(int noOfParticipant) {
        this.noOfParticipant = noOfParticipant;
    }

    public String getPostPicUrl() {
        return postPicUrl;
    }

    public void setPostPicUrl(String postPicUrl) {
        this.postPicUrl = postPicUrl;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(int userProgress) {
        this.userProgress = userProgress;
    }
}
