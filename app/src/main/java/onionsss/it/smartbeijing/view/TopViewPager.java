package onionsss.it.smartbeijing.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者：张琦 on 2016/6/5 13:09
 */
public class TopViewPager extends ViewPager {

    private int mStartX;
    private int mStartY;

    public TopViewPager(Context context) {
        super(context);
    }

    public TopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = (int) ev.getX();
                mStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int) ev.getX();
                int currentY = (int) ev.getY();

                int dx = currentX - mStartX;
                int dy = currentY - mStartY;

                if(Math.abs(dx) > Math.abs(dy)){
                    int currentItem = getCurrentItem();
                    /**
                     * 左右滑动
                     */
                    if(dx > 0){
                        /**
                         * 像左滑动
                         */
                        if(currentItem == 0){
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }else{
                        /**
                         * 像右滑动
                         */
                        int count = getAdapter().getCount();
                        if(currentItem == count - 1){
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                    }
                }else{
                    /**
                     * 上下滑动
                     */
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
