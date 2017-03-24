package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sahil on 24/3/17.
 */

public class SearchResponse {

    @SerializedName("peoples")
    private ArrayList<UserModel> getAllUsers;

    @SerializedName("posts")
    private ArrayList<SingleProfilePost> getAllPosts;

    @SerializedName("tags")
    private ArrayList<SingleProfilePost> getAllTagsPost;

    public SearchResponse(ArrayList<UserModel> getAllUsers, ArrayList<SingleProfilePost> getAllPosts, ArrayList<SingleProfilePost> getAllTagsPost) {
        this.getAllUsers = getAllUsers;
        this.getAllPosts = getAllPosts;
        this.getAllTagsPost = getAllTagsPost;
    }

    public ArrayList<UserModel> getGetAllUsers() {
        return getAllUsers;
    }

    public void setGetAllUsers(ArrayList<UserModel> getAllUsers) {
        this.getAllUsers = getAllUsers;
    }

    public ArrayList<SingleProfilePost> getGetAllPosts() {
        return getAllPosts;
    }

    public void setGetAllPosts(ArrayList<SingleProfilePost> getAllPosts) {
        this.getAllPosts = getAllPosts;
    }

    public ArrayList<SingleProfilePost> getGetAllTagsPost() {
        return getAllTagsPost;
    }

    public void setGetAllTagsPost(ArrayList<SingleProfilePost> getAllTagsPost) {
        this.getAllTagsPost = getAllTagsPost;
    }
}
