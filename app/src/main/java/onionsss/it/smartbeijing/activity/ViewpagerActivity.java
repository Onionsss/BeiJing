package onionsss.it.smartbeijing.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.utils.StringUtil;

public class ViewpagerActivity extends AppCompatActivity {

    private ViewPager mViewpager_loading;
    List<ImageView> list;
    private LinearLayout mViewpager_ll;
    /**
     * 圆点的宽度
     */
    private int mDotWidth;
    private View mViewpager_reddot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
        initDot();
        initDotWidth();
        initViewPager();
    }

    private void initDot() {
        for(int i = 0;i<StringUtil.viewPagerImage.length;i++){
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.viewpager_dot);
            LinearLayout.LayoutParams ll_params = new LinearLayout.LayoutParams(10,10);
            if (i>0){
                ll_params.leftMargin = 10;
            }
            iv.setLayoutParams(ll_params);
            mViewpager_ll.addView(iv);
        }
    }

    /**
     * 拿到圆点的宽度
     */
    private void initDotWidth() {
        mViewpager_ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mViewpager_ll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mDotWidth = mViewpager_ll.getChildAt(1).getLeft() - mViewpager_ll.getChildAt(0).getLeft();
            }
        });
    }

    /**
     * ViewPager图片轮播
     */
    private void initViewPager() {
        mViewpager_loading.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return StringUtil.viewPagerImage.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = new ImageView(ViewpagerActivity.this);
                iv.setImageResource(StringUtil.viewPagerImage[position]);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                list.add(iv);
                container.addView(iv);
                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }
        });

        mViewpager_loading.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int width = (int) (mDotWidth * positionOffset) + (position * mDotWidth);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mViewpager_reddot.getLayoutParams();
                lp.leftMargin = width;
                mViewpager_reddot.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mViewpager_loading = (ViewPager) findViewById(R.id.viewpager_loading);
        mViewpager_ll = (LinearLayout) findViewById(R.id.viewpager_ll);
        mViewpager_reddot = findViewById(R.id.viewpager_reddot);
        list = new ArrayList<>();
    }
}
