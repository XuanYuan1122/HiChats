package ithema.com.hichats.activity;

import android.security.keystore.KeyInfo;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import ithema.com.hichats.R;
import ithema.com.hichats.base.BaseActivity;
import ithema.com.hichats.base.BaseSimpleTextWatcher;
import ithema.com.hichats.utils.MyToast;
import ithema.com.hichats.utils.ValidateUtils;

/**
 * Created by X-uan on 2017/3/16.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout relativeLayout;
    private TextView textView;

    @Override
    public int addView() {
        return R.layout.activity_register;
    }

    TextInputEditText etPwd;
    TextInputEditText etConfirmPwd;
    Button btRegister;
    TextInputEditText etUsername;

    @Override
    public void initContent(View view) {
        etPwd = ButterKnife.findById(view, R.id.et_pwd);
        etConfirmPwd = ButterKnife.findById(view, R.id.et_confirm_pwd);
        btRegister = ButterKnife.findById(view, R.id.bt_register);
        etUsername = ButterKnife.findById(view, R.id.et_username);

        btRegister.setOnClickListener(this);
        //设置对username,pwd,confirmpwd输入框的监听
        BaseSimpleTextWatcher textWatcher = new BaseSimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                //判断三个输入框是否为空
                String name = etUsername.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                String confirmPwd = etConfirmPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirmPwd)) {
                    btRegister.setEnabled(false);
                } else {
                    btRegister.setEnabled(true);
                }
            }
        };
        etUsername.addTextChangedListener(textWatcher);
        etPwd.addTextChangedListener(textWatcher);
        etConfirmPwd.addTextChangedListener(textWatcher);
        //监听软件的操作
        etUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_NEXT) {
                    if (TextUtils.isEmpty(etUsername.getText().toString().trim())) {
                        MyToast.show(RegisterActivity.this, "用户名输入为空");
                    }
                }
                return false;
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                register();

                break;
            case R.id.ib_back:
                if (relativeLayout != null && relativeLayout.getVisibility() == View.VISIBLE) {
                    //点击后退图标和按钮都应该不执行任何操作
                } else {
                    super.onClick(view);
                }
                break;
        }
    }

    /**
     * 注册按钮
     */
    private void register() {
        boolean data = validateData();
        if (data) {
            //显示加载界面
            addLoadingUI();
        }
    }

    /**
     * 显示加载界面
     */
    private void addLoadingUI() {
        View view = getLayoutInflater().inflate(R.layout.loading, getRlRoot(), true);
        relativeLayout = ButterKnife.findById(view, R.id.rl_loading);
        textView = ButterKnife.findById(view, R.id.tv_info);
        textView.setText("正在注册");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (relativeLayout != null && relativeLayout.getVisibility() == View.VISIBLE) {
                return true;//中断事件
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 校验用户名、密码是否合法，密码和确认密码是否一致
     */
    private boolean validateData() {
        //校验用户名
        String name = etUsername.getText().toString().trim();
        boolean userName = ValidateUtils.validateUserName(name);
        if (!userName) {
            MyToast.show(RegisterActivity.this, "用户名不合法");
            etUsername.setFocusable(true);
            return false;
        }
        //校验密码
        String pwd = etPwd.getText().toString().trim();
        boolean password = ValidateUtils.validatePassword(pwd);
        if (!password) {
            MyToast.show(RegisterActivity.this, "密码不合法");
            etPwd.getText().clear();
            etConfirmPwd.getText().clear();
            etPwd.setFocusable(true);
            return false;
        }
        //校验确认密码
        String confirmPwd = etConfirmPwd.getText().toString().trim();
        if (!confirmPwd.equals(pwd)) {
            MyToast.show(RegisterActivity.this, "密码和确认密码不一致");
            etConfirmPwd.getText().clear();
            etConfirmPwd.setFocusable(true);
            return false;
        }
        return true;
    }
}
