package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 24/3/17.
 */

public class MainPostModel {

    @SerializedName("post")
    private ArrayList<SingleMainPost> posts;


    public ArrayList<SingleMainPost> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<SingleMainPost> posts) {
        this.posts = posts;
    }

}
