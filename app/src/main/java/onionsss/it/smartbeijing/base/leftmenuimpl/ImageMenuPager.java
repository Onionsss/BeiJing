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
public class ImageMenuPager extends BaseLeftMenuPage{

    public ImageMenuPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView tv = new TextView(mActivity);
        tv.setText("组图");
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(35);
        tv.setGravity(Gravity.CENTER);
        
        return tv;
    }
}
