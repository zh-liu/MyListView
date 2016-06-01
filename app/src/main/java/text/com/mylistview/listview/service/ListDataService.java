package text.com.mylistview.listview.service;

import android.graphics.AvoidXfermode;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import text.com.mylistview.bean.ListItemBean;
import text.com.mylistview.listview.listhelper.ModelFactory;
import text.com.mylistview.listview.listhelper.SimpleItemEntity;
import text.com.mylistview.listview.view.MyItemViewA_;
import text.com.mylistview.listview.view.MyItemViewB_;
import text.com.mylistview.listview.view.MyItemViewC_;

/**
 * Created by liuzh on 2016/6/1.
 */
@EBean
public class ListDataService {

    public ModelFactory getModelFactory() {
        ModelFactory modelFactory = new ModelFactory.Builder()
                .addModel(MyItemViewA_.class)
                .addModel(MyItemViewB_.class)
                .addModel(MyItemViewC_.class).build();
        return modelFactory;
    }


    //写死数据，数据可以通过此方法传进来适配 getDataList(T t)
    public List<SimpleItemEntity> getDataList() {
        List<SimpleItemEntity> list = new ArrayList<SimpleItemEntity>();

        for (int i = 0; i < 20; i++) {
            SimpleItemEntity<ListItemBean> entity = new SimpleItemEntity<ListItemBean>();

            ListItemBean bean = new ListItemBean();

            if (i % 2 == 0) {
                bean.setName("itemA");
                entity.setContent(bean);
                entity.setModelView(MyItemViewA_.class);
            } else if (i % 3 == 0) {
                bean.setName("itemB");
                entity.setContent(bean);
                entity.setModelView(MyItemViewB_.class);
            } else {
                bean.setName("itemC");
                entity.setContent(bean);
                entity.setModelView(MyItemViewC_.class);
            }
            list.add(entity);

        }


        return list;
    }
}
