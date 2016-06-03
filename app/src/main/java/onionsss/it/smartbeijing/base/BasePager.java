package onionsss.it.smartbeijing.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.activity.HomeActivity;
import onionsss.it.smartbeijing.fragment.ContentFragment;
import onionsss.it.smartbeijing.fragment.LeftMenuFragment;

/**
 * 作者：张琦 on 2016/5/29 21:33
 */
public class BasePager {
    public Activity mActivity;
    public View mRootView;
    public FrameLayout mBase_frame;
    public ImageView mBase_menu;
    public TextView mBase_title;
    public HomeActivity mHomeAct;
    public LeftMenuFragment mLeftMenuFragment;
    public ContentFragment mContentFragment;
    public SlidingMenu mSlidingMenu;

    public BasePager(Activity activity){
        /**
         * 在base页面中直接拿到侧边栏 主页 Home的对象
         */
        this.mActivity = activity;
        mHomeAct = (HomeActivity) mActivity;
        mLeftMenuFragment = mHomeAct.getLeftFragment();
        mContentFragment = mHomeAct.getContentFragment();
        mSlidingMenu = mHomeAct.getSlidingMenu();
        mRootView = initView();
    }

    /**
     * 初始化布局
     * @return
     */
    public View initView(){
        View view = View.inflate(mActivity, R.layout.base_pager, null);
        mBase_frame = (FrameLayout) view.findViewById(R.id.base_frame);
        mBase_menu = (ImageView) view.findViewById(R.id.base_menu);
        mBase_title = (TextView) view.findViewById(R.id.base_title);
        return view;
    }

    /**
     * 初始化数据
     */
    public void initData(){};

    /**
     * 打开或者关闭sliding
     */
    public void openOrCloseSliding() {
        mSlidingMenu.toggle();
    }
}
