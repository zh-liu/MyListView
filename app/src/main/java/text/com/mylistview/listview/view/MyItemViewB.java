package text.com.mylistview.listview.view;

import android.content.Context;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import text.com.mylistview.R;
import text.com.mylistview.bean.ListItemBean;
import text.com.mylistview.listview.listhelper.BaseItemModel;

/**
 * Created by liuzh on 2016/6/1.
 */
@EViewGroup(R.layout.mylistview_item_b)
public class MyItemViewB extends BaseItemModel<ListItemBean> {

    @ViewById(R.id.textViewb)
    TextView textView;
    private Context cn;
    private ListItemBean bean;
    public MyItemViewB(Context context){
     super(context);
        cn = context;
    }

    @Override
    public void bindView() {
        bean = model.getContent();
        textView.setText(bean.getName());
    }
}
