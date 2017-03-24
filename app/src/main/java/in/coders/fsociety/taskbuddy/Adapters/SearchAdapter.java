package in.coders.fsociety.taskbuddy.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by sahil on 24/3/17.
 */

public class SearchAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragmentArrayList =new ArrayList<>();
    private ArrayList<String>   titleArrayList = new ArrayList<>();
    public SearchAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return titleArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArrayList.get(position);
    }

    public void addSlide(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        titleArrayList.add(title);
    }

    public void reset(){
        fragmentArrayList.clear();
        titleArrayList.clear();
    }
}
