package onionsss.it.smartbeijing.base.leftmenuimpl.childpager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.base.BaseLeftMenuPage;
import onionsss.it.smartbeijing.bean.Categories;
import onionsss.it.smartbeijing.bean.NewsPagerList;
import onionsss.it.smartbeijing.constant.HttpConstant;
import onionsss.it.smartbeijing.utils.JsonCache;
import onionsss.it.smartbeijing.utils.LogUtil;
import onionsss.it.smartbeijing.utils.OkUtils;
import onionsss.it.smartbeijing.view.TopViewPager;

/**
 * 作者：张琦 on 2016/6/2 08:24
 */
public class NewsChildPager extends BaseLeftMenuPage {
    private BitmapUtils mBitmapUtils;
    private String mUrl;
    private int mPosition;
    private TopViewPager mNews_childpager_vp;
    private List<Categories.DataBean.ChildrenBean> mChildren;
    private NewsPagerList mNewspager;
    private List<NewsPagerList.DataBean.TopicBean> mTopic;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HttpConstant.JSON_OK) {
                formatGson((String) msg.obj);
            } else if (msg.what == HttpConstant.NET_ERROR) {
            }
        }
    };
    private TextView mNews_childpager_title;
    private CirclePageIndicator mNews_childpager_circle;

    public NewsChildPager(Activity activity, List<Categories.DataBean.ChildrenBean> children, int position) {
        super(activity);
        mChildren = children;
        this.mPosition = position;
        /**
         * 拿到URL
         */
        mUrl = HttpConstant.SERVER_ADDRESS + mChildren.get(mPosition).getUrl();
        LogUtil.ShowLog(mUrl);
        mBitmapUtils = new BitmapUtils(mActivity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.news_childpager, null);
        mNews_childpager_vp = (TopViewPager) view.findViewById(R.id.news_childpager_vp);
        mNews_childpager_title = (TextView) view.findViewById(R.id.news_childpager_title);
        mNews_childpager_circle = (CirclePageIndicator) view.findViewById(R.id.news_childpager_circle);
        return view;
    }

    @Override
    public void initData() {
        String json = JsonCache.readCache(mUrl, mActivity);
        if (!TextUtils.isEmpty(json)) {
            formatGson(json);
        }

        initServerData();
        initListener();
    }

    private void initListener() {
        mNews_childpager_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                String title = mTopic.get(position).getTitle();
                mNews_childpager_title.setText(title);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initServerData() {
        new Thread() {
            @Override
            public void run() {
                Message mMsg = handler.obtainMessage();
                try {
                    Response response = OkUtils.getResponse(mUrl);
                    String json = response.body().string();
                    JsonCache.CacheJson(mUrl, json, mActivity);
                    mMsg.what = HttpConstant.JSON_OK;
                    mMsg.obj = json;
                } catch (IOException e) {
                    mMsg.what = HttpConstant.NET_ERROR;
                    e.printStackTrace();
                } finally {
                    handler.sendMessage(mMsg);
                }
            }
        }.start();
    }

    /**
     * 使用GSON解析Json
     *
     * @param json
     */
    private void formatGson(String json) {
        Gson gson = new Gson();
        mNewspager = gson.fromJson(json, NewsPagerList.class);

        initViewPager();
    }

    private void initViewPager() {
        mNews_childpager_vp.setAdapter(new ListPagerAdapter());
        /**
         * 第一次加载的默认
         */
        mNews_childpager_title.setText(mTopic.get(0).getTitle());
        mNews_childpager_circle.setViewPager(mNews_childpager_vp);
//        mNews_childpager_circle.setSnap(true);
    }

    /**
     * ViewPager适配器
     */
    class ListPagerAdapter extends PagerAdapter {

        public ListPagerAdapter() {
            mTopic = mNewspager.getData().getTopic();
        }

        @Override
        public int getCount() {
            return mTopic.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(mActivity);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            mBitmapUtils.display(iv, mTopic.get(position).getListimage());
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
