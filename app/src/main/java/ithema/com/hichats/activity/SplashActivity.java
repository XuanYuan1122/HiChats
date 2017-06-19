package ithema.com.hichats.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ithema.com.hichats.R;

public class SplashActivity extends AppCompatActivity {

    private static final int ENTER_MAIN_UI = 100;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ENTER_MAIN_UI:
                    //进去主界面
                    enterUI();
                    break;
            }
        }
    };

    private void enterUI() {
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //过2s后进入主界面(消息机制)
        mHandler.sendEmptyMessageDelayed(ENTER_MAIN_UI, 2000);
    }
}
