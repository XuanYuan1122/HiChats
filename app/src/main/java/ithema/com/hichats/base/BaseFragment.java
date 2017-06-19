package ithema.com.hichats.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ithema.com.hichats.R;
import ithema.com.hichats.activity.EnterNickNameActivity;

/**
 * Created by X-uan on 2017/3/13.
 */

public abstract class BaseFragment extends Fragment {


    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.header_left)
    LinearLayout headerLeft;
    @BindView(R.id.header_right)
    LinearLayout headerRight;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.bt_register)
    Button btRegister;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.empty_ll)
    LinearLayout emptyLl;
    @BindView(R.id.content)
    FrameLayout content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        //隐藏回退键
        ibBack.setVisibility(ImageButton.GONE);
        setDefaultTitle(tvTitle);
        setEmptyViewInfo(ivEmpty, tvInfo);
    }

    //设置缺省的标题
    public abstract void setDefaultTitle(TextView tv);

    //设置空布局的图片和文字
    public abstract void setEmptyViewInfo(ImageView iv, TextView tv);

    @OnClick({R.id.ib_back, R.id.bt_register, R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                break;
            case R.id.bt_register:
                startActivity(new Intent(getContext(), EnterNickNameActivity.class));
                break;
            case R.id.bt_login:
                break;
        }
    }
}
