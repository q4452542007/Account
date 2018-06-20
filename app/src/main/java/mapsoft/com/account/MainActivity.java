package mapsoft.com.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {


    @BindView(R.id.fr_container)
    FrameLayout mFrContainer;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (findFragment(AccountFragment.class) == null){
                        start(AccountFragment.newInstance());
                    }else {
                        showHideFragment(AccountFragment.newInstance());
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (findFragment(ChartFragment.class) == null){
                        start(ChartFragment.newInstance());
                    }else {
                        showHideFragment(ChartFragment.newInstance());
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (findFragment(MineFragment.class) == null){
                        start(MineFragment.newInstance());
                    }else {
                        showHideFragment(MineFragment.newInstance());
                    }

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (findFragment(AccountFragment.class) == null) {
            loadRootFragment(R.id.fr_container, AccountFragment.newInstance());
        }
    }

}
