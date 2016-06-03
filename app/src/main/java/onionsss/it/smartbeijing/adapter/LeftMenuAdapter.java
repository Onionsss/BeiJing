package onionsss.it.smartbeijing.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.bean.Categories;
import onionsss.it.smartbeijing.fragment.LeftMenuFragment;

/**
 * 作者：张琦 on 2016/5/31 13:33
 */
public class LeftMenuAdapter extends BaseAdapter{
    private Activity mActivity;
    private Categories mCategories;
    private LeftMenuFragment mLmf;

    public LeftMenuAdapter(Activity activity, Categories categories,LeftMenuFragment lmf) {
        this.mActivity = activity;
        this.mCategories = categories;
        this.mLmf =lmf;
    }

    @Override
    public int getCount() {
        return mCategories.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return mCategories.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mActivity, R.layout.listview_leftmenu,null);
        TextView tv = (TextView) convertView.findViewById(R.id.leftmenu_item_tv_title);
        tv.setText(mCategories.getData().get(position).getTitle());
        if(mLmf.mEnablePosition == position){
            tv.setEnabled(true);
        }else{
            tv.setEnabled(false);
        }
        return convertView;
    }
}
