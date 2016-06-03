package onionsss.it.smartbeijing.base;

import android.app.Activity;
import android.view.View;

import onionsss.it.smartbeijing.activity.HomeActivity;

/**
 * 作者：张琦 on 2016/5/31 14:38
 */
public abstract class BaseLeftMenuPage {
    public Activity mActivity;
    public HomeActivity mHomeAct;
    public View mRootView;

    public BaseLeftMenuPage(Activity activity){
        mActivity = activity;
        mHomeAct = (HomeActivity) mActivity;
        mRootView = initView();
    }
    public abstract View initView();

    public void initData(){};
}
