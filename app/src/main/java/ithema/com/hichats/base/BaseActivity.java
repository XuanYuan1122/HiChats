package ithema.com.hichats.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ithema.com.hichats.R;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.header_left)
    LinearLayout headerLeft;
    @BindView(R.id.header_right)
    LinearLayout headerRight;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        ButterKnife.bind(this);
        View view = LayoutInflater.from(this).inflate(addView(), content, true);
        initContent(view);
    }

    //设置标题内容
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    //返回根布局
    public RelativeLayout getRlRoot() {
        return rlRoot;
    }

    public abstract int addView();
    //添加内容
    //public abstract View addContent(Inflater mInflater, FrameLayout content);

    public abstract void initContent(View view);

    @OnClick(R.id.ib_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
        }
    }
}
