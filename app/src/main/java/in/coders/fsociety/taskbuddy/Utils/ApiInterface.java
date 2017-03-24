package in.coders.fsociety.taskbuddy.Utils;

import in.coders.fsociety.taskbuddy.Models.UserModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/profile/{id}")
    Call<UserModel> getProfile(@Path("id") String id);

}
