package ithema.com.hichats.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import ithema.com.hichats.R;
import ithema.com.hichats.base.BaseActivity;
import ithema.com.hichats.base.BaseSimpleTextWatcher;

/**
 * Created by X-uan on 2017/3/15.
 */

public class EnterNickNameActivity extends BaseActivity implements View.OnClickListener {


    private TextInputEditText textInputEditText;
    private Button button;

    @Override
    public int addView() {
        return R.layout.activity_enter_nick_name;
    }

    @Override
    public void initContent(View view) {
        textInputEditText = ButterKnife.findById(view, R.id.et_nickname);
        button = ButterKnife.findById(view, R.id.bt_next);
        //设置文本输入框的监听
        textInputEditText.addTextChangedListener(new BaseSimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                if (TextUtils.isEmpty(editable.toString())) {
                    button.setEnabled(false);
                } else {
                    button.setEnabled(true);
                }
            }
        });

        //设置按钮的点击监听
        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                Intent intent = new Intent(this, PersonalInfoActivity.class);
                intent.putExtra("username", textInputEditText.getText().toString().trim());
                startActivity(intent);
                break;
            case R.id.ib_back:
                showAlertExitDialog();
                break;
        }
    }

    private void showAlertExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定要放弃注册么？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }
}
