package ithema.com.hichats.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import ithema.com.hichats.R;
import ithema.com.hichats.base.BaseFragment;

/**
 * Created by X-uan on 2017/3/13.
 */

public class ContactFragment extends BaseFragment {

    @Override
    public void setDefaultTitle(TextView tv) {
        tv.setText("通讯录");
    }

    @Override
    public void setEmptyViewInfo(ImageView iv, TextView tv) {
        iv.setImageResource(R.drawable.ic_guest_contact_empty);
        tv.setText("可以让附近的人互动");
    }
}
