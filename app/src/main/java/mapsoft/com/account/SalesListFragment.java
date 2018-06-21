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

public class SalesListFragment extends SupportFragment{
    private static SalesListFragment mFragment;

    private static final Object ojb = new Object();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pView = inflater.inflate(R.layout.fragment_layout_saleslist,container,false);
        return pView;
    }

    public static SalesListFragment newInstance() {

        if (mFragment == null) {
            synchronized (ojb) {
                if (mFragment == null) {
                    mFragment = new SalesListFragment();
                }
            }
        }
        return mFragment;
    }

}
