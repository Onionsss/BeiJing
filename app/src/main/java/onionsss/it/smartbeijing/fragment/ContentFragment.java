package onionsss.it.smartbeijing.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.adapter.ViewPagerAdapter;
import onionsss.it.smartbeijing.base.BasePager;
import onionsss.it.smartbeijing.base.impl.HomePager;
import onionsss.it.smartbeijing.base.impl.MessagePager;
import onionsss.it.smartbeijing.base.impl.MyPager;
import onionsss.it.smartbeijing.base.impl.RamblePager;
import onionsss.it.smartbeijing.view.MyViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends BaseFragment {
    private MyViewPager mViewpager_loading;
    private RadioGroup mMain_radiogroup;

    private List<BasePager> mList;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mViewpager_loading = (MyViewPager) view.findViewById(R.id.content_viewpager);
        mMain_radiogroup = (RadioGroup) view.findViewById(R.id.main_radiogroup);
        return view;
    }

    @Override
    public void initData() {
        if(mList == null){
            mList = new ArrayList<>();
        }
        mList.add(new HomePager(mActivity));
        mList.add(new RamblePager(mActivity));
        mList.add(new MessagePager(mActivity));
        mList.add(new MyPager(mActivity));

        mViewpager_loading.setAdapter(new ViewPagerAdapter(mList,mActivity));
        
        initRadioButton();
        initPageChange();
    }

    /**
     * 用Button来切换页面
     * 并且初始化首页
     */
    private void initRadioButton() {
        /**
         * 初始化的界面
         */
        mMain_radiogroup.check(R.id.main_rb_home);
        mList.get(0).initData();
        mViewpager_loading.setCurrentItem(0);
        SetSlidingMenuEnable(false);

        mMain_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.main_rb_home:
                        mViewpager_loading.setCurrentItem(0);
                    break;
                    case R.id.main_rb_ramble:
                        mViewpager_loading.setCurrentItem(1);
                        break;
                    case R.id.main_rb_dialog:
                        mViewpager_loading.setCurrentItem(2);
                        break;
                    case R.id.main_rb_my:
                        mViewpager_loading.setCurrentItem(3);
                        break;
                }
            }
        });
    }

    private void initPageChange() {
        mViewpager_loading.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mList.get(position).initData();
                /**
                 * 禁用侧边栏
                 */
                if(position == 0 || position == mList.size()-1){
                    SetSlidingMenuEnable(false);
                }else{
                    SetSlidingMenuEnable(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 开启或者禁止侧边栏
     * @param enable true开启  false禁用
     */
    public void SetSlidingMenuEnable(boolean enable) {
        if(enable){
            mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏触摸
        }else{
            mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//全屏触摸
        }
    }

    /**
     * 拿到Ramble页面
     */
    public RamblePager getRamblePager(){
        RamblePager ramblePager = (RamblePager) mList.get(1);
        return ramblePager;
    }
}
