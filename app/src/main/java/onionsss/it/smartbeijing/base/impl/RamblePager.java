package onionsss.it.smartbeijing.base.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import onionsss.it.smartbeijing.R;
import onionsss.it.smartbeijing.base.BaseLeftMenuPage;
import onionsss.it.smartbeijing.base.BasePager;
import onionsss.it.smartbeijing.base.leftmenuimpl.ImageMenuPager;
import onionsss.it.smartbeijing.base.leftmenuimpl.InteractMenuPager;
import onionsss.it.smartbeijing.base.leftmenuimpl.NewsMenuPager;
import onionsss.it.smartbeijing.base.leftmenuimpl.SeminarMenuPager;
import onionsss.it.smartbeijing.bean.Categories;
import onionsss.it.smartbeijing.constant.HttpConstant;
import onionsss.it.smartbeijing.utils.JsonCache;
import onionsss.it.smartbeijing.utils.OkUtils;

/**
 * 作者：张琦 on 2016/5/29 21:47
 */
public class RamblePager extends BasePager implements View.OnClickListener {
    public static final int JSON_OK = 101;
    public static final int NET_ERROR = 102;
    public static final String JSON_LOCAL = HttpConstant.SERVER_ADDRESS + HttpConstant.CATEGORIES;
    private List<BaseLeftMenuPage> mList;
    /**
     * 分类的网络数据
     */
    private Categories mCategories;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case JSON_OK:
                    formatGson((String)msg.obj);
                break;
                case NET_ERROR:
                    break;
            }
        }
    };
    public RamblePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        mBase_menu.setVisibility(View.VISIBLE);
        String cache = JsonCache.readCache(JSON_LOCAL, mActivity);
        if(!TextUtils.isEmpty(cache)){
            /**
             * 说明有缓存
             */
            formatGson(cache);
        }
        /**
         * 网络加载 万一会有更新
         */
        initJson();
        /**
         * 加载页面
         */
        initPager();
        /**
         * 点击事件
         */
        initListener();
    }

    /**
     * 加载页面以便切换
     */
    private void initPager() {
        if(mList == null){
            mList = new ArrayList<>();
        }
        mList.add(new NewsMenuPager(mActivity,mCategories));
        mList.add(new SeminarMenuPager(mActivity));
        mList.add(new ImageMenuPager(mActivity));
        mList.add(new InteractMenuPager(mActivity));

        /**
         * 初始化fl页和侧边栏
         */
        setLeftMenuClickPager(0);
        mHomeAct.getLeftFragment().updateListView(0);
        mList.get(0).initData();
    }

    private void initListener() {
        mBase_menu.setOnClickListener(this);
    }

    /**
     * 解析服务器的Json数据
     */
    private void initJson() {
        new Thread(){
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                try {
                    /**
                     *  访问服务器
                     */
                    Response response = OkUtils.getResponse(JSON_LOCAL);
                    String json = response.body().string();
                    /**
                     * 读取到json 放入缓存中
                     * url当作文件名 json作为内容
                     */
                    JsonCache.CacheJson(HttpConstant.SERVER_ADDRESS + HttpConstant.CATEGORIES,json,mActivity);

                    msg.what = JSON_OK;
                    msg.obj = json;
                } catch (IOException e) {
                    msg.what = NET_ERROR;
                    e.printStackTrace();
                }finally {
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    /**
     * 使用GSON解析Json
     * @param json
     */
    private void formatGson(String json){
        Gson gson = new Gson();
        mCategories = gson.fromJson(json, Categories.class);
        /**
         * 解析完Json即可给侧边栏设置数据
         */
        setLeftMenuData();
    }

    /**
     * 给侧边栏数据  需要拿到侧边栏
     */
    private void setLeftMenuData(){
        mLeftMenuFragment.setData(mCategories);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.base_menu:
                openOrCloseSliding();
            break;
        }
    }

    /**
     * 给侧边栏设置页面使用
     * @param position
     */
    public void setLeftMenuClickPager(int position){
        BaseLeftMenuPage baseLeftMenuPage = mList.get(position);
        View rootView = baseLeftMenuPage.mRootView;
        baseLeftMenuPage.initData();
        /**
         * 设置页面之前  清除所有View
         */
        mBase_frame.removeAllViews();
        mBase_frame.addView(rootView);
        mBase_title.setText(mCategories.getData().get(position).getTitle());
    }
}
