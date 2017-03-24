package in.coders.fsociety.taskbuddy.Utils;

import com.facebook.login.LoginFragment;

import in.coders.fsociety.taskbuddy.UploadPost;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/post")
    @FormUrlEncoded
    Call<UploadPost.Response1> sendPostData(@Field("title") String title, @Field("description") String description,
                                            @Field("credit") int credit
            , @Field("tags") String tag, @Field("picPostUrl") String picPostUrl,@Field("isPublic") String isPublic  ,@Field("authorId") String authorid ,
                                            @Field("circlesId") String circlesId);

}
