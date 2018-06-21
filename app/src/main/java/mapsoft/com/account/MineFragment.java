package mapsoft.com.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author djl
 * @function
 */

public class MineFragment extends SupportFragment {

    private static MineFragment mFragment;

    private static final Object ojb = new Object();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pView = inflater.inflate(R.layout.fragmen_layout_mine,container,false);
        return pView;
    }

    public static MineFragment newInstance() {

        if (mFragment == null) {
            synchronized (ojb) {
                if (mFragment == null) {
                    mFragment = new MineFragment();
                }
            }
        }
        return mFragment;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
