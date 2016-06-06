package onionsss.it.smartbeijing.base.leftmenuimpl;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.base.BaseLeftMenuPage;
import onionsss.it.smartbeijing.base.leftmenuimpl.childpager.NewsChildPager;
import onionsss.it.smartbeijing.bean.Categories;

/**
 * 作者：张琦 on 2016/5/31 14:41
 */
public class NewsMenuPager extends BaseLeftMenuPage{
    private Categories mCategories;
    private ViewPager mNewsmenu_viewpager;
    private MyPagerAdapter mPagerAdapterdapter;
    private List<NewsChildPager> mList;
    private TabPageIndicator mTitleIndicator;
    private ImageView mIndicator_iv_next;
    private List<Categories.DataBean.ChildrenBean> mChildren;


    public NewsMenuPager(Activity activity,List<Categories.DataBean.ChildrenBean> children) {
        super(activity);
        mChildren = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.news_menu_pager, null);
        mNewsmenu_viewpager = (ViewPager) view.findViewById(R.id.newsmenu_viewpager);
        mTitleIndicator = (TabPageIndicator)view.findViewById(R.id.titles);
        mIndicator_iv_next = (ImageView) view.findViewById(R.id.indicator_iv_next);
        return view;
    }

    @Override
    public void initData() {
        initPagers();

        mPagerAdapterdapter = new MyPagerAdapter();
        mNewsmenu_viewpager.setAdapter(mPagerAdapterdapter);
        mTitleIndicator.setViewPager(mNewsmenu_viewpager);

        initListener();
    }

    private void initListener() {
        mNewsmenu_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position > 0){
                    mHomeAct.getContentFragment().SetSlidingMenuEnable(false);
                }else if(position == 0){
                    mHomeAct.getContentFragment().SetSlidingMenuEnable(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mIndicator_iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = mNewsmenu_viewpager.getCurrentItem();
                if((current+1) < mList.size()){
                    mNewsmenu_viewpager.setCurrentItem(++current);
                }else{
                    current = 0;
                    mNewsmenu_viewpager.setCurrentItem(current);
                }
            }
        });
    }

    /**
     * 初始化页面
     */
    private void initPagers() {
        mList = new ArrayList<>();
        for(int i = 0;i<mChildren.size();i++){
            mList.add(new NewsChildPager(mActivity,mChildren,i));
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public CharSequence getPageTitle(int position) {
            return mChildren.get(position).getTitle();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            NewsChildPager newsChildPager = mList.get(position);
            View rootView = newsChildPager.mRootView;
            newsChildPager.initData();

            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
