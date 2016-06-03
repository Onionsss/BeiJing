package onionsss.it.smartbeijing.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import onionsss.it.smartbeijing.base.BasePager;

/**
 * 作者：张琦 on 2016/5/29 21:56
 */
public class ViewPagerAdapter extends PagerAdapter{
    private List<BasePager> mList;
    private Context mContext;

    public ViewPagerAdapter(List<BasePager> list, Context context) {
        mList = list;
        mContext = context;
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
        View rootView = mList.get(position).mRootView;
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
