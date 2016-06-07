package text.com.mylistview.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import text.com.mylistview.R;
import text.com.mylistview.customviews.TitleBar;

/**
 * Created by liuzh on 2016/6/2.
 */

public class BaseTitleBarFragment extends BaseFragment {

    public static final String TAG = "BaseTitleBarFragment";
    public static final String ACTION_BROADCAST_DATA_CHANGE_NEED_REFRESH= "text.com.mylistview.action.broadcase.datachange.needrefresh";

    public static final String ACTION_CLEAR_CACHE_DATA= "action_clear_cache_view";

    private ViewFeature viewFeature = ViewFeature.NORMAL;
    protected enum ViewFeature{
        OVERLAY,NORMAL;
    }
    protected View cacheView;

    private ViewGroup contentContainer;
    private TitleBar titleBar;

    private boolean shouldRefresh;

    private boolean hasLoaded;

    protected DataChangeNeedRefreshReceiver dataChangeNeedRefreshReceiver = new DataChangeNeedRefreshReceiver();


    private int childViewRes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BROADCAST_DATA_CHANGE_NEED_REFRESH);
        intentFilter.addAction(ACTION_CLEAR_CACHE_DATA);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(dataChangeNeedRefreshReceiver, intentFilter);
        cacheView = null;
        hasLoaded = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(cacheView==null){
            View view = inflater.inflate(R.layout.fragment_base,container,false);
            int index = 0;
            if(viewFeature == ViewFeature.NORMAL){
                contentContainer = (ViewGroup)view.findViewById(R.id.linear_container);
                titleBar = (TitleBar)view.findViewById(R.id.linear_title_bar);
                index = 1;
            }else if(viewFeature == ViewFeature.OVERLAY){
                contentContainer = (ViewGroup)view.findViewById(R.id.frame_container);
                titleBar = (TitleBar)view.findViewById(R.id.frame_title_bar);
                index = 0;
            }

            contentContainer.setVisibility(View.VISIBLE);
            View childView = LayoutInflater.from(getActivity()).inflate(childViewRes,contentContainer,false);
            contentContainer.addView(childView,index);
            cacheView = view;
            initTitleBar(titleBar);


        }
        ViewGroup parent = (ViewGroup)cacheView.getParent();
        if(parent!=null){
            parent.removeView(cacheView);
        }
        shouldRefresh = true;//重新切回当前页面


        return cacheView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shouldRefresh = false;
        hasLoaded = false;
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(dataChangeNeedRefreshReceiver);
    }

    protected void dataChangeNeedRefresh(){

    }

    protected void setContentView(int layoutId){
        childViewRes = layoutId;
    }

    protected void setViewFeature(ViewFeature viewFeature){
        this.viewFeature = viewFeature;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(cacheView!=null){
            hasLoaded = true;
        }
    }

    public void clearCacheView(){
        cacheView = null;
    }
    public boolean hasLoaded(){
        return hasLoaded;
    }
    public void reLoad(){
        hasLoaded = false;
    }

    public boolean pollRefresh(){
        if(shouldRefresh){
            shouldRefresh = false;
            return true;
        }
        return  false;
    }

    public void offerShouldRefresh(){
        this.shouldRefresh = true;
    }

    public void setTitleBarVisibility(int visibility){
        getTitleBar().setVisibility(visibility);
    }
    private class DataChangeNeedRefreshReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ACTION_BROADCAST_DATA_CHANGE_NEED_REFRESH)){
                dataChangeNeedRefresh();
            }else if(intent.getAction().equals(ACTION_CLEAR_CACHE_DATA)){
                cacheView = null;
                reLoad();
            }
        }
    }
    protected  void initTitleBar(){
        if(titleBar!=null){
            initTitleBar(titleBar);
        }
    }
    protected  void initTitleBar(TitleBar titleBar){
        this.titleBar = titleBar;
    }
    protected TitleBar getTitleBar(){
        return this.titleBar;
    }


}
