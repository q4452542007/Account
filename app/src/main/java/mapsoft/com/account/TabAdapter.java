package mapsoft.com.account;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;


    public TabAdapter(FragmentManager fm) {
        super(fm);
        //this.mTitles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        //初始化Fragment数据
        ContentFragment fragment = new ContentFragment();
        //String[] title = mTitles.get(position);
        //fragment.setType(Integer.parseInt(title[1]));
        fragment.setTitle(AccountFragment.tabTitle[position]);
        return fragment;
    }

    @Override
    public int getCount() {
        return AccountFragment.tabTitle.length;
    }

    //设置tablayout标题
    @Override
    public CharSequence getPageTitle(int position) {
        return AccountFragment.tabTitle[position];

    }
}