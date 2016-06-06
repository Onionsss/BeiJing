package onionsss.it.smartbeijing.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.adapter.LeftMenuAdapter;
import onionsss.it.smartbeijing.bean.Categories;

/**
 * 作者：张琦 on 2016/5/29 20:05
 */
public class LeftMenuFragment extends BaseFragment{
    public int mEnablePosition;
    /**
     * json解析的新闻数据
     */
    private Categories mCategories;

    private ListView mLeftmenu_lv;

    private LeftMenuAdapter mLeftMenuAdapter;


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.left_fragment, null);
        mLeftmenu_lv = (ListView) view.findViewById(R.id.leftmenu_lv);
        return view;
    }

    @Override
    public void initData() {

    }
    /**
     * 拿到侧边栏的新闻数据
     */
    public void setData(Categories categories){
        this.mCategories = categories;
        mLeftMenuAdapter = new LeftMenuAdapter(mActivity, mCategories,this);
        initAdapter();
    }
    /**
     * 给ListView设置数据
     */
    private void initAdapter() {
        mLeftmenu_lv.setAdapter(mLeftMenuAdapter);
        initListener();
    }

    private void initListener() {
        mLeftmenu_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateListView(position);
                openOrCloseSliding();
                setClickPager(position);
            }
        });
    }

    /**
     * 设置点击跳转的页面
     * @param position
     */
    private void setClickPager(int position) {
        mContentFragment.getRamblePager().setLeftMenuClickPager(position);
    }

    /**
     * 更新listView
     * @param position
     */
    public void updateListView(int position){
        mEnablePosition = position;
        mLeftMenuAdapter.notifyDataSetChanged();
    }
}
