package mapsoft.com.account;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends SupportActivity implements View.OnClickListener{

    private static final int FIRST = 0;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.fl_menu)
    FloatingActionsMenu mFlMenu;
    @BindView(R.id.btn_stock_return)
    FloatingActionButton mBtnStockReturn;
    @BindView(R.id.btn_sell_return)
    FloatingActionButton mBtnSellReturn;
    @BindView(R.id.btn_stock)
    FloatingActionButton mBtnStock;
    @BindView(R.id.btn_pay)
    FloatingActionButton mBtnPay;
    @BindView(R.id.btn_sell)
    FloatingActionButton mBtnSell;
    @BindView(R.id.container)
    RelativeLayout mContainer;

    SupportFragment[] mFragments = new SupportFragment[3];

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showHideFragment(AccountFragment.newInstance());
                    return true;
                case R.id.navigation_dashboard:
                    showHideFragment(ChartFragment.newInstance());
                    return true;
                case R.id.navigation_notifications:
                    showHideFragment(MineFragment.newInstance());
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

        initView();
    }

    private void initView() {
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (findFragment(AccountFragment.class) == null) {
            mFragments[0] = AccountFragment.newInstance();
            mFragments[1] = ChartFragment.newInstance();
            mFragments[2] = MineFragment.newInstance();
            loadMultipleRootFragment(R.id.fr_container, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2]);
        } else {
            // 这里库已经做了Fragment恢复工作，不需要额外的处理
            // 这里我们需要拿到mFragments的引用，用下面的方法查找更方便些，也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些)
            mFragments[0] = findFragment(AccountFragment.class);
            mFragments[1] = findFragment(ChartFragment.class);
            mFragments[2] = findFragment(MineFragment.class);
        }
        mBtnStockReturn.setOnClickListener(this);
        mBtnPay.setOnClickListener(this);
        mBtnSell.setOnClickListener(this);
        mBtnSellReturn.setOnClickListener(this);
        mBtnStock.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //销售单
            case R.id.btn_sell:
                SalesBill pBill = new SalesBill();
                pBill.save();
                if (findFragment(SalesFrament.class) == null) {
                    start(SalesFrament.newInstance());
                } else {
                    showHideFragment(SalesFrament.newInstance());
                }
                mFlMenu.collapse();
                break;
            case R.id.btn_sell_return:
                mFlMenu.collapse();
                break;
            case R.id.btn_stock:
                mFlMenu.collapse();
                break;
            case R.id.btn_stock_return:
                mFlMenu.collapse();
                break;
            case R.id.btn_pay:
                mFlMenu.collapse();
                break;
        }
    }
}
