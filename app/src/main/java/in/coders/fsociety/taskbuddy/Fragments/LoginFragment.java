package in.coders.fsociety.taskbuddy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.MainActivity;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaykay12 on 24/3/17.
 */

public class LoginFragment extends Fragment {

    private LoginButton btnLogin;
    private TextView tvSkip;
    private SharedPref sharedPref;
    private ArrayList<String> permissions;
    private CallbackManager callbackManager;
    private ProgressBar pbLogin;

    public LoginFragment() {
        //Required
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        btnLogin = (LoginButton)view.findViewById(R.id.btnLogin);
        pbLogin = (ProgressBar)view.findViewById(R.id.pbLogin);
        btnLogin.setFragment(this);

        tvSkip = (TextView)view.findViewById(R.id.tvSkip);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.setSkipStatus(true);
                getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        permissions = new ArrayList<>();
        permissions.add("email");
        btnLogin.setReadPermissions(permissions);

        callbackManager = CallbackManager.Factory.create();

        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        String name = "", email = "", picUrl = "",id = "";
                        if (object.has("name")) {
                            try {
                                name = object.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (object.has("email")) {
                            try {
                                email = object.getString("email");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (object.has("id")) {
                            try {
                                id = object.getString("id");
                                picUrl = "https://graph.facebook.com/" + id + "/picture?width=200&height=200";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        btnLogin.setVisibility(View.GONE);
                        saveUserData(name, email, id, picUrl);
                        pbLogin.setVisibility(View.VISIBLE);
                    }
                });

                Bundle bundle = new Bundle();
                bundle.putString("fields","name,email,id");
                graphRequest.setParameters(bundle);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                sharedPref.setSkipStatus(true);
                sharedPref.setLoginStatus(false);
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), "Error Occur While Processing your Facebook Login, Please Try Again!!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void saveUserData(String name, String email,final String id, String picUrl)
    {
        Call<LoginFragment.UserSentResponse> userSentResponseCall = Util.getRetrofitService().sendUserData(name,id,picUrl,email);
        userSentResponseCall.enqueue(new Callback<UserSentResponse>() {
            @Override
            public void onResponse(Call<UserSentResponse> call, Response<UserSentResponse> response) {
                UserSentResponse userSentResponse = response.body();
                if(userSentResponse!=null && response.isSuccess())
                {
                    Log.v("ID", id+"");
                    Log.v("Message",userSentResponse.getMessage()+"");
                    Log.v("Success",userSentResponse.getSuccess()+"");
                    sharedPref.setLoginStatus(true);
                    sharedPref.setSkipStatus(false);// as user has login succesfully and we make sure  that screen does not come again
                    sharedPref.setUserId(id);

                    pbLogin.setVisibility(View.GONE);

                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    getActivity().startActivity(intent);
                    getActivity().finish();
                }
                else
                {
                    Toast.makeText(getActivity(),"Check Internet connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserSentResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Check Internet Connection",Toast.LENGTH_LONG).show();
            }
        });
    }

    public class UserSentResponse{
        @SerializedName("message")
        private String message;

        @SerializedName("success")
        private String success;

        public UserSentResponse(String message, String success){
            this.message = message; this.success = success;
        }

        public void setMessage(String message){ this.message = message; }

        public String getMessage() {    return message; }

        public void setSuccess(String success){ this.success = success; }

        public String getSuccess() {    return success; }
    }

}
