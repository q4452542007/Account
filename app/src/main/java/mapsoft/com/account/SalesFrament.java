package mapsoft.com.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import org.litepal.crud.DataSupport;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function5;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author djl
 * @function
 */

public class SalesFrament extends SupportFragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "time";

    private static final Object ojb = new Object();
    @BindView(R.id.sales_name)
    EditText mSalesName;
    @BindView(R.id.sales_phone)
    EditText mSalesPhone;
    @BindView(R.id.sales_money)
    EditText mSalesMoney;
    @BindView(R.id.sales_details)
    EditText mSalesDetails;
    @BindView(R.id.sales_date)
    Button mSalesDate;
    @BindView(R.id.sales_time)
    Button mSalesTime;
    @BindView(R.id.bill_payed)
    CheckBox mBillPayed;
    @BindView(R.id.sales_call)
    Button mSalesCall;
    @BindView(R.id.sales_save)
    Button mSalesSave;

    private SalesBill mBill;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        if (crimeId == null) {
            mBill = DataSupport.findLast(SalesBill.class);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pView = inflater.inflate(R.layout.fragment_layout_sales, container, false);
        unbinder = ButterKnife.bind(this, pView);
        init();
        updateDate();
        return pView;
    }

    private void init() {
        Observable<CharSequence> nameOb = RxTextView.textChanges(mSalesName).skip(1);
        Observable<CharSequence> moneyOb = RxTextView.textChanges(mSalesMoney).skip(1);
        Observable<CharSequence> detailsOb = RxTextView.textChanges(mSalesDetails).skip(1);
        Observable<CharSequence> phoneOb = RxTextView.textChanges(mSalesPhone).skip(1);
        Observable<CharSequence> payOb = RxTextView.textChanges(mBillPayed).skip(1);
        Observable.combineLatest(nameOb, moneyOb, detailsOb, phoneOb, payOb, new Function5<CharSequence, CharSequence, CharSequence, CharSequence, CharSequence, Boolean>() {

            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, CharSequence charSequence5) throws Exception {
                String name = mSalesName.getText().toString();
                String money = mSalesMoney.getText().toString();
                String details = mSalesDetails.getText().toString();
                String phone = mSalesPhone.getText().toString();
                boolean isPayed = mBillPayed.isChecked();
                mBill.setName(name);
                mBill.setMoney(Double.valueOf(money));
                mBill.setComment(details);
                mBill.setPhoneNum(phone);
                if (!TextUtils.isEmpty(money)) {
                    if (isPayed) {
                        return true;
                    } else if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(phone)) {
                        return true;
                    }
                } else {
                    return false;
                }
                return false;
            }
        }).subscribe(new Consumer<Boolean>() {

            @Override
            public void accept(Boolean s) throws Exception {
                mSalesSave.setEnabled(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("test", throwable.toString());
            }
        });
    }

    public static SalesFrament newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        SalesFrament pFrament = new SalesFrament();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        pFrament.setArguments(args);
        return pFrament;
    }

    public static SalesFrament newInstance() {
        SalesFrament pFrament = new SalesFrament();
        return pFrament;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void updateDate() {
        String date = (String) DateFormat.format("EEEE, MMMM dd, yyyy", mBill.getDate());
        mSalesDate.setText(date);
        String time = (String) DateFormat.format("kk:mm", mBill.getDate());
        mSalesTime.setText(time);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
