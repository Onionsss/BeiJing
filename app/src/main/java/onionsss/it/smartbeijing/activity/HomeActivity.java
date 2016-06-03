package onionsss.it.smartbeijing.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.fragment.ContentFragment;
import onionsss.it.smartbeijing.fragment.LeftMenuFragment;

public class HomeActivity extends SlidingFragmentActivity {
    public static final String LEFT_MENU = "leftMenu";
    public static final String CONTENT = "content";
    private FragmentManager mFm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initSliding();
        initView();
    }

    /**
     * 填充Content布局和LeftMenu布局
     */
    private void initView() {
        mFm = getSupportFragmentManager();
        FragmentTransaction ft = mFm.beginTransaction();
        ft.replace(R.id.fl_left_content,new ContentFragment(),CONTENT);
        ft.replace(R.id.fl_left_menu,new LeftMenuFragment(),LEFT_MENU);
        ft.commit();
    }

    private void initSliding() {
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏触摸
        slidingMenu.setBehindOffset(200);//屏幕预留200像素宽度
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
    }

    /**
     * 拿到侧边栏fragment
     */
    public LeftMenuFragment getLeftFragment(){
        LeftMenuFragment leftFragment = (LeftMenuFragment) mFm.findFragmentByTag(LEFT_MENU);
        return leftFragment;
    }

    /**
     * 拿到主页fragment
     */
    public ContentFragment getContentFragment(){
        ContentFragment ContentFragment = (ContentFragment) mFm.findFragmentByTag(CONTENT);
        return ContentFragment;
    }
}
