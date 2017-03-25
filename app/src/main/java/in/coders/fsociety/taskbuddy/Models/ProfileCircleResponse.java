package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sahil on 25/3/17.
 */

public class ProfileCircleResponse {

    @SerializedName("circles")
    private ArrayList<Circle> circleArrayList;

    public ProfileCircleResponse(ArrayList<Circle> circleArrayList) {
        this.circleArrayList = circleArrayList;
    }

    public ArrayList<Circle> getCircleArrayList() {
        return circleArrayList;
    }

    public void setCircleArrayList(ArrayList<Circle> circleArrayList) {
        this.circleArrayList = circleArrayList;
    }
}
