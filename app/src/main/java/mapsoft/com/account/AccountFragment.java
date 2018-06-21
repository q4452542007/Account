package mapsoft.com.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author djl
 * @function
 */

public class AccountFragment extends SupportFragment {

    private static AccountFragment mFragment;

    private static final Object ojb = new Object();

    private TabAdapter mTabAdapter;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;

    public static final String[] tabTitle = new String[]{ "销售账单", "进货账单"};
    private SupportFragment[] mFragments = new SupportFragment[2];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pView = inflater.inflate(R.layout.fragment_layout_account, container, false);
        unbinder = ButterKnife.bind(this, pView);
        init();
        return pView;
    }

    private void init() {

        mTabAdapter = new TabAdapter(getFragmentManager());
        //给ViewPager设置适配器
        mViewpager.setAdapter(mTabAdapter);
        //将TabLayout和ViewPager关联起来。
        mTab.setupWithViewPager(mViewpager);
//      mTabLayout.setLayoutParams();
        //设置可以滑动
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //均分
        mTab.setTabMode(TabLayout.MODE_FIXED);
    }

    public static AccountFragment newInstance() {

        if (mFragment == null) {
            synchronized (ojb) {
                if (mFragment == null) {
                    mFragment = new AccountFragment();
                }
            }
        }
        return mFragment;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
