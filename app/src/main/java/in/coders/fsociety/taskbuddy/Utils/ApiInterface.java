package in.coders.fsociety.taskbuddy.Utils;

import in.coders.fsociety.taskbuddy.Fragments.LoginFragment;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("user")
    @FormUrlEncoded
    Call<LoginFragment.UserSentResponse> sendUserData(@Field("name")String name,
                                                      @Field("id")String id,
                                                      @Field("picUrl")String picUrl,
                                                      @Field("email")String email);
}
