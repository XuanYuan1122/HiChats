package ithema.com.hichats.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ithema.com.hichats.R;
import ithema.com.hichats.fragment.ContactFragment;
import ithema.com.hichats.fragment.ConversationFragment;
import ithema.com.hichats.fragment.LiveFragment;
import ithema.com.hichats.fragment.NearByFragment;
import ithema.com.hichats.fragment.PersonalFragment;
import ithema.com.hichats.utils.FragmentFactory;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.activity_home)
    LinearLayout activityHome;
    private BadgeItem badgeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initFragment();
        initBottomNavigationBar();
    }

    private Class[] fragments = {
            NearByFragment.class,
            LiveFragment.class,
            ConversationFragment.class,
            ContactFragment.class,
            PersonalFragment.class
    };

    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.contaier, FragmentFactory.getInstance(fragments[0]), "0");
        ft.commit();
    }

    //底部导航条图片资源
    int[] barIcons = {
            R.drawable.ic_nav_nearby_active,
            R.drawable.ic_nav_live_active,
            R.drawable.ic_nav_conversation_active,
            R.drawable.ic_nav_contacts_active,
            R.drawable.ic_nav_personal_active
    };

    //初始化导航条
    private void initBottomNavigationBar() {
        //底部导航的标签
        String[] barLabels = getResources().getStringArray(R.array.bottombarlabel);

        //未读消息提醒
        /*BadgeItem badgeItem = new BadgeItem();
        badgeItem.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
        badgeItem.setText("10");
        badgeItem.show();*/
        //设置位置（靠顶部|水平居中）
        badgeItem = new BadgeItem()
                .setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);

        bottomNavigationBar
                .setBarBackgroundColor(R.color.bottombarcolor)//设置背景色
                .addItem(new BottomNavigationItem(barIcons[0], barLabels[0]))//设置条目的图片和标签
                .addItem(new BottomNavigationItem(barIcons[1], barLabels[1]))
                .addItem(new BottomNavigationItem(barIcons[2], barLabels[2]).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(barIcons[3], barLabels[3]))
                .addItem(new BottomNavigationItem(barIcons[4], barLabels[4]))
                .setActiveColor(R.color.activecolor)//设置条目选中的颜色
                .setInActiveColor(R.color.inactivecolor)//设置条目未选中的颜色
                .setFirstSelectedPosition(0)//设置第一个条目
                .setMode(BottomNavigationBar.MODE_FIXED)//设置为混合模式(重要)
                .initialise();//初始化
        bottomNavigationBar.setTabSelectedListener(new MyTabSelectedListener());
    }

    private class MyTabSelectedListener implements BottomNavigationBar.OnTabSelectedListener {
        /**
         * tab选中的时候调用(显示Fragment)
         *
         * @param position
         */
        @Override
        public void onTabSelected(int position) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            //1 通过FragmentManager获取Fragment 2 如果不存在，就通过FragmentFactory创建 3 如果存在，显示
            Fragment fragment = fragmentManager.findFragmentByTag(position + "");//看管理器里面有没有这个fragment
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (fragment == null) {
                //添加
                ft.add(R.id.contaier, FragmentFactory.getInstance(fragments[position]), position + "");
            } else {
                //显示
                ft.show(fragment);
            }
            ft.commit();
        }

        /**
         * 之前选中的tab未被选中调用(隐藏Fragment)
         *
         * @param position
         */
        @Override
        public void onTabUnselected(int position) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(position + "");
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.hide(fragment);
            ft.commit();
        }

        @Override
        public void onTabReselected(int position) {

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //把当前的Activity移至到后台
            //是否为任务栈的根   false:只能够处理点击App图标进入的Activity(Splash)
            moveTaskToBack(false);
            return true;//自己处理后退键的行为
        }
        return super.onKeyDown(keyCode, event);
    }
}
