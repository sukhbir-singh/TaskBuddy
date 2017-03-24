package in.coders.fsociety.taskbuddy.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

import in.coders.fsociety.taskbuddy.Adapters.MainAdapter;
import in.coders.fsociety.taskbuddy.Models.MainPostModel;
import in.coders.fsociety.taskbuddy.Models.UserModel;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearProfile;
    private SharedPref sharedPref;
    private RecyclerView recyclerView;
    private ProgressBar bar;

    private ImageView profile;
    TextView name,credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = new SharedPref(this);

        Log.v("Login Status:",sharedPref.getLoginStatus()+"");
        Log.v("Skip Status:",sharedPref.getSkipStatus()+"");
        Log.v("UserId:",sharedPref.getUserId()+"");

        if (!sharedPref.getLoginStatus() && !sharedPref.getSkipStatus()) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        bar=(ProgressBar) findViewById(R.id.bar);

        linearProfile=(LinearLayout)findViewById(R.id.main_linear_profile);

        profile=(ImageView)findViewById(R.id.main_image);
        name=(TextView)findViewById(R.id.main_name);
        credits=(TextView)findViewById(R.id.main_credits);

        findViewById(R.id.main_linear_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this, UploadPostActivity.class);
                startActivity(i);
            }
        });

        getProfile("847596855g3");
        getAllPosts("847596855g3");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(sharedPref.getSkipStatus())
            getMenuInflater().inflate(R.menu.main_rightskipmenu,menu);
        else
            getMenuInflater().inflate(R.menu.main_rightloginmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_logout) {
            sharedPref.setUserId("");
            sharedPref.setLoginStatus(false);
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        else if(id==R.id.action_search){
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void  getAllPosts(String id){
        Call<MainPostModel> call= Util.getRetrofitService().getMainPosts(id);
        call.enqueue(new Callback<MainPostModel>() {
            @Override
            public void onResponse(Call<MainPostModel> call, Response<MainPostModel> response) {
                MainPostModel r=response.body();

                bar.setVisibility(View.GONE);

                Log.v("main posts-response",  new Gson().toJson(response.body()));

                if(r!=null&&response.isSuccess()){
                    MainAdapter adapter1=new MainAdapter(MainActivity.this, r.getPosts());
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                    recyclerView.setAdapter(adapter1);

                    Log.d("main size",""+r.getPosts().size());

                }

            }

            @Override
            public void onFailure(Call<MainPostModel> call, Throwable t) {

            }
        });
    }

    private void getProfile(String id){
        Log.v("profile","getting response for "+id);

        Call<UserModel> UserModelCall= Util.getRetrofitService().getProfile(id);

        UserModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel model=response.body();

               // Log.v("profile-response",  new Gson().toJson(response.body()));

                if(model!=null&&response.isSuccess()){
                    linearProfile.setVisibility(View.VISIBLE);
                    //Glide.with(MainActivity.this).load(model.getPicUrl()).into(profile);

                    Glide.with(MainActivity.this).load(model.getPicUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.person_icon)
                            .error(R.drawable.person_icon).into(new ImageViewTarget<Bitmap>(profile) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(),resource);
                            drawable.setCircular(true);
                            profile.setImageDrawable(drawable);
                        }
                    });

                    name.setText(model.getName()+"");
                    credits.setText("Credits: "+model.getCredit()+"");

                }else{
                    Toast.makeText(MainActivity.this,"Some error occurs",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this,"Please Check Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
