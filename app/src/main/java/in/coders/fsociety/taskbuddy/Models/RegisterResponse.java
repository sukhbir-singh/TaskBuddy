package in.coders.fsociety.taskbuddy.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sahil on 25/3/17.
 */

public class RegisterResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean status;

    public RegisterResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
