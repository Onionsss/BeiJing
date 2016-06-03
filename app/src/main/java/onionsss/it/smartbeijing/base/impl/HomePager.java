package onionsss.it.smartbeijing.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import onionsss.it.smartbeijing.base.BasePager;

/**
 * 作者：张琦 on 2016/5/29 21:47
 */
public class HomePager extends BasePager{

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        mBase_title.setText("首页");

        TextView tv = new TextView(mActivity);
        tv.setText("首页");
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(35);
        tv.setGravity(Gravity.CENTER);

        mBase_frame.addView(tv);
        mBase_menu.setVisibility(View.GONE);
    }
}
