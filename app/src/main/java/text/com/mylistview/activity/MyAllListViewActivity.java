package text.com.mylistview.activity;

import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import text.com.mylistview.R;
import text.com.mylistview.customviews.TitleBar;
import text.com.mylistview.listview.listhelper.SimpleModelAdapter;
import text.com.mylistview.listview.service.ListDataService;

/**
 * Created by liuzh on 2016/6/1.
 */
@EActivity(R.layout.mylistview)
public class MyAllListViewActivity extends BaseActivity {

    @Bean
    ListDataService service;
    @ViewById(R.id.listView)
    ListView listView;

    private SimpleModelAdapter adapter;

    @Override
    protected void initTitleBar(TitleBar titleBar) {
        titleBar.setMode(TitleBar.MODE_ALL);
        titleBar.setTitleName("ListView适配");
        titleBar.setLeftActionImage(R.drawable.com_bt_ttb_back);
    }

    @AfterViews void afterViews(){
        adapter = new SimpleModelAdapter(this,service.getModelFactory());
        listView.setAdapter(adapter);

        adapter.addList(service.getDataList());
        adapter.notifyDataSetChanged();
    }
}
