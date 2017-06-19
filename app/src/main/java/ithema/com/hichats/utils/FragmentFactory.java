package ithema.com.hichats.utils;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by X-uan on 2017/3/13.
 * Fragment的工厂类
 */

public class FragmentFactory {
    public FragmentFactory() {
    }
    private static Map<Class,Fragment> map=new HashMap<>();

    /**
     * 获取fragment的实例
     * @param clazzs
     * @return
     */
    public static synchronized Fragment getInstance(Class<? extends Fragment> clazzs){
        //从集合中获取到fragment
        Fragment fragment = map.get(clazzs);
        if (fragment==null){
            try {
                fragment=clazzs.newInstance();
                //将fragment保存到集合中
                map.put(clazzs,fragment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }
}
