package ithema.com.hichats.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import ithema.com.hichats.base.BaseFragment;

/**
 * Created by X-uan on 2017/3/13.
 */

public class LiveFragment extends BaseFragment {
    @Override
    public void setDefaultTitle(TextView tv) {
        tv.setText("直播");
    }

    @Override
    public void setEmptyViewInfo(ImageView iv, TextView tv) {

    }
}
