package onionsss.it.smartbeijing.base.leftmenuimpl.childpager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import onionsss.it.smartbeijing.base.BaseLeftMenuPage;
import onionsss.it.smartbeijing.bean.Categories;

/**
 * 作者：张琦 on 2016/6/2 08:24
 */
public class NewsChildPager extends BaseLeftMenuPage{
    private Categories mCategories;
    private int mPosition;
    private TextView mTv;

    public NewsChildPager(Activity activity,Categories categories,int position) {
        super(activity);
        mCategories = categories;
        this.mPosition = position;
    }

    @Override
    public View initView() {
        mTv = new TextView(mActivity);
        mTv.setTextColor(Color.BLACK);
        mTv.setTextSize(35);
        mTv.setGravity(Gravity.CENTER);
        return mTv;
    }

    @Override
    public void initData() {
        mTv.setText(mCategories.getData().get(0).getChildren().get(mPosition).getTitle());
    }
}
