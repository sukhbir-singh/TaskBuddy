package in.coders.fsociety.taskbuddy.Activities;

import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.google.gson.Gson;

import in.coders.fsociety.taskbuddy.Fragments.ProfileFragment;
import in.coders.fsociety.taskbuddy.Models.UserModel;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity{

    private ImageView img;
    private ViewPager pager;
    private TabLayout tab;
    private int id;
    private Toolbar toolbar;
    private TextView circles,bio,friends;
    private LinearLayout user_details;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tab=(TabLayout)findViewById(R.id.profile_tab);
        pager=(ViewPager)findViewById(R.id.profile_viewpager);
        pager.setOffscreenPageLimit(3);
        img=(ImageView)findViewById(R.id.profilePic);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        bar=(ProgressBar)findViewById(R.id.progress_bar);

        bio=(TextView)findViewById(R.id.profile_bio);
        circles=(TextView)findViewById(R.id.profile_circles);
        friends=(TextView)findViewById(R.id.profile_friends);
        user_details=(LinearLayout) findViewById(R.id.user_details);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Profile");

        getResponse(new SharedPref(this).getUserId());

        Log.v("profile","ok");

        MyPagerAdapter adapter=new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tab.setupWithViewPager(pager);

    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            String str="Tab";
            switch (position){
                case 0: str="Posts";
                    break;
                case 1: str="Works";
                    break;
                case 2: str="Circles";
                    break;
            }

            return str;
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;

            switch (position){
                case 0: fragment= ProfileFragment.getInstance(ProfileActivity.this,1);
                    break;
                case 1: fragment= ProfileFragment.getInstance(ProfileActivity.this,2);
                    break;
                case 2: fragment= ProfileFragment.getInstance(ProfileActivity.this,3);
                    break;
                default:fragment= ProfileFragment.getInstance(ProfileActivity.this,1);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private void getResponse(String id){
        Log.v("profile","getting response for "+id);

        Call<UserModel> UserModelCall= Util.getRetrofitService().getProfile(id);

        UserModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel model=response.body();
                bar.setVisibility(View.GONE);

                Log.v("profile-response",  new Gson().toJson(response.body()));

                if(model!=null&&response.isSuccess()){
                    user_details.setVisibility(View.VISIBLE);

                    Glide.with(getApplicationContext()).load(model.getPicUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.person_icon)
                            .error(R.drawable.person_icon).into(new ImageViewTarget<Bitmap>(img) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(),resource);
                            drawable.setCircular(true);
                            img.setImageDrawable(drawable);
                        }
                    });

                    if(model.getBio()!=null){
                        bio.setText("Email: "+model.getEmail()+"\n"+model.getBio());
                    }else{
                        bio.setText("Email: "+model.getEmail());
                    }

                    getSupportActionBar().setTitle(model.getName());

                    if(model.getFriendCount()==0){
                        friends.setText("No friends yet");
                    }else{
                        friends.setText("Friends: "+model.getFriendCount()+"");
                    }

                    if(model.getCircleCount()==0){
                        circles.setText("Not a part of any circle");
                    }else{
                        circles.setText("Circles: "+model.getCircleCount()+"");
                    }

                }else{
                    Toast.makeText(ProfileActivity.this,"Some error occurs",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                t.printStackTrace();
                bar.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this,"Please Check Internet Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }


}

