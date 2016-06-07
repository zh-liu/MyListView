package text.com.mylistview.listview.view;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import text.com.mylistview.R;
import text.com.mylistview.activity.MyTitleActivity_;
import text.com.mylistview.bean.ListItemBean;
import text.com.mylistview.imageload.DisplayOption;
import text.com.mylistview.listview.listhelper.BaseItemModel;

/**
 * Created by liuzh on 2016/6/1.
 */
@EViewGroup(R.layout.mylistview_item_a)
public class MyItemViewA extends BaseItemModel<ListItemBean> {

    @ViewById(R.id.textView2)
    TextView textView;
    @ViewById(R.id.imageView)
    ImageView imageView;

    @Bean
    DisplayOption displayOption;

    private Context cn;
    private ListItemBean bean;
    public MyItemViewA(Context context){
     super(context);
        cn = context;
    }

    @Override
    public void bindView() {
        bean = model.getContent();
        textView.setText(bean.getName());
        ImageLoader.getInstance().displayImage(bean.getUrl(),imageView,displayOption.mMsgDispalyImageOptions);
    }

    @Click void container(){
        MyTitleActivity_.intent(getContext()).myBean(model.getContent()).start();

    }
}
