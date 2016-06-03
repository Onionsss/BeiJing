package onionsss.it.smartbeijing.base.leftmenuimpl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import onionsss.it.smartbeijing.base.BaseLeftMenuPage;

/**
 * 作者：张琦 on 2016/5/31 14:41
 */
public class InteractMenuPager extends BaseLeftMenuPage{

    public InteractMenuPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mActivity);
        tv.setText("互动");
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(35);
        tv.setGravity(Gravity.CENTER);
        
        return tv;
    }
}
