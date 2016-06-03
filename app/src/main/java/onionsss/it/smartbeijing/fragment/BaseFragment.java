package onionsss.it.smartbeijing.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import onionsss.it.smartbeijing.activity.HomeActivity;

/**
 * 作者：张琦 on 2016/5/29 19:58
 */
public abstract class BaseFragment extends Fragment{

    public Activity mActivity;
    public HomeActivity mHomeAct;
    public SlidingMenu mSlidingMenu;
    public LeftMenuFragment mLeftMenuFragment;
    public ContentFragment mContentFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mHomeAct = (HomeActivity) mActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    public abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSlidingMenu = mHomeAct.getSlidingMenu();
        mLeftMenuFragment = mHomeAct.getLeftFragment();
        mContentFragment = mHomeAct.getContentFragment();
        initData();
    }

    public void initData(){};
    /**
     * 打开或者关闭sliding
     * 定义在父类中 供子类使用
     */
    public void openOrCloseSliding() {
        mSlidingMenu.toggle();
    }
}
