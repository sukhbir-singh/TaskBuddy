package in.coders.fsociety.taskbuddy;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity{

    private ImageView img;
    private ViewPager pager;
    private TabLayout tab;
    private int id;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tab=(TabLayout)findViewById(R.id.profile_tab);
        pager=(ViewPager)findViewById(R.id.profile_viewpager);
        pager.setOffscreenPageLimit(3);
        img=(ImageView)findViewById(R.id.profilePic);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Glide.with(this).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(img);

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
                case 1: str="Worked On";
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
                case 0: fragment=ProfileFragment.getInstance(ProfileActivity.this,1);
                    break;
                case 1: fragment=ProfileFragment.getInstance(ProfileActivity.this,2);
                    break;
                case 2: fragment=ProfileFragment.getInstance(ProfileActivity.this,3);
                    break;
                default:fragment=ProfileFragment.getInstance(ProfileActivity.this,1);
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}

