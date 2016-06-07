package text.com.mylistview.fragment;

import android.os.Bundle;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


import text.com.mylistview.R;
import text.com.mylistview.customviews.TitleBar;
import text.com.mylistview.listview.listhelper.SimpleModelAdapter;
import text.com.mylistview.listview.service.ListDataService;
import text.com.mylistview.utils.ListScrollHelper;

/**
 * Created by liuzh on 2016/6/2.
 */
@EFragment(R.layout.tab_job_fragment)
public class TabJobFragment extends BaseTitleBarFragment {

    @ViewById(R.id.list_view)
    PullToRefreshListView listView;

    private SimpleModelAdapter adapter;

    @Bean
    ListDataService service;

    private int tag = 1;

    @Override
    protected void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);


        titleBar.setMode(TitleBar.MODE_TITLE_NAME);
        titleBar.setTitleName("工作");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.tab_job_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @AfterViews void afterViews(){
        adapter = new SimpleModelAdapter(getActivity(),service.getModelFactory());
        listView.setAdapter(adapter);

        adapter.addList(service.getDataList());
        adapter.notifyDataSetChanged();


        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                adapter.clearList();
                adapter.addList(service.getDataList());

                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }
                }, 1000);
            }


            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                if(tag==3){
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(),"没有更多数据",Toast.LENGTH_SHORT).show();
                            listView.onRefreshComplete();
                        }
                    }, 1000);
                }else{
                    listView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tag++;
                            adapter.addList(service.getDataList());
                            adapter.notifyDataSetChanged();
                            listView.onRefreshComplete();
                        }
                    }, 1000);
                }

            }
        });
    }



}

