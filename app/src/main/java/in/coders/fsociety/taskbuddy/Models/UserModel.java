package in.coders.fsociety.taskbuddy.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 24/3/17.
 */

public class UserModel implements Parcelable {
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

    @SerializedName("credit")
    private int credit;

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

    public UserModel(String bio, int credit, String name, String picUrl, String id) {
        this.bio = bio;
        this.credit = credit;
        this.name = name;
        this.picUrl = picUrl;
        this.id = id;
    }

    protected UserModel(Parcel in) {
        bio = in.readString();
        circleCount = in.readInt();
        email = in.readString();
        friendCount = in.readInt();
        id = in.readString();
        name = in.readString();
        picUrl = in.readString();
        success = in.readByte() != 0;
        credit = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bio);
        dest.writeInt(circleCount);
        dest.writeString(email);
        dest.writeInt(friendCount);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(picUrl);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeInt(credit);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

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
