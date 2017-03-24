package in.coders.fsociety.taskbuddy.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 24/3/17.
 */

public class SingleProfilePost implements Parcelable{
    /*
      {
       "authorName": "Rahul",
      "authorPicUrl": "http://wallpaper-gallery.net/images/profile-pics/profile-pics-20.jpg",
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
    */

    @SerializedName("credit")
    private int credit;
    @SerializedName("description")
    private String description;
    @SerializedName("authorName")
    private String authorName;
    @SerializedName("authorPicUrl")
    private String authorPicUrl;
    @SerializedName("id")
    private int id;
    @SerializedName("noOfParticipant")
    private int noOfParticipant;

    @SerializedName("postPicUrl")
    private String postPicUrl;
    @SerializedName("status")
    private boolean status;
    @SerializedName("tags")
    private ArrayList<String> tags;
    @SerializedName("title")
    private String title;

    @SerializedName("userProgress")
    private int userProgress;

    protected SingleProfilePost(Parcel in) {
        credit = in.readInt();
        description = in.readString();
        authorName = in.readString();
        authorPicUrl = in.readString();
        id = in.readInt();
        noOfParticipant = in.readInt();
        postPicUrl = in.readString();
        status = in.readByte() != 0;
        tags = in.createStringArrayList();
        title = in.readString();
        userProgress = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(credit);
        dest.writeString(description);
        dest.writeString(authorName);
        dest.writeString(authorPicUrl);
        dest.writeInt(id);
        dest.writeInt(noOfParticipant);
        dest.writeString(postPicUrl);
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeStringList(tags);
        dest.writeString(title);
        dest.writeInt(userProgress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SingleProfilePost> CREATOR = new Creator<SingleProfilePost>() {
        @Override
        public SingleProfilePost createFromParcel(Parcel in) {
            return new SingleProfilePost(in);
        }

        @Override
        public SingleProfilePost[] newArray(int size) {
            return new SingleProfilePost[size];
        }
    };

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

    public SingleProfilePost(int credit, String description, String authorName, String authorPicUrl, int id, int noOfParticipant, String postPicUrl, boolean status, ArrayList<String> tags, String title) {
        this.credit = credit;
        this.description = description;
        this.authorName = authorName;
        this.authorPicUrl = authorPicUrl;
        this.id = id;
        this.noOfParticipant = noOfParticipant;
        this.postPicUrl = postPicUrl;
        this.status = status;
        this.tags = tags;
        this.title = title;
    }

    public SingleProfilePost(int credit, String description, String authorName, String authorPicUrl, int id, int noOfParticipant, String postPicUrl, ArrayList<String> tags, String title, int userProgress) {
        this.credit = credit;
        this.description = description;
        this.authorName = authorName;
        this.authorPicUrl = authorPicUrl;
        this.id = id;
        this.noOfParticipant = noOfParticipant;
        this.postPicUrl = postPicUrl;
        this.tags = tags;
        this.title = title;
        this.userProgress = userProgress;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPicUrl() {
        return authorPicUrl;
    }

    public void setAuthorPicUrl(String authorPicUrl) {
        this.authorPicUrl = authorPicUrl;
    }

    public int getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(int userProgress) {
        this.userProgress = userProgress;
    }
}
