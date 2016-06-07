package text.com.mylistview.imageload;

import com.nostra13.universalimageloader.core.DisplayImageOptions;


import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import org.androidannotations.annotations.EBean.Scope;
import text.com.mylistview.R;

/**
 * Created by liuzh on 2016/6/7.
 */
@EBean(scope = Scope.Singleton)
public class DisplayOption {
//    @Bean
//    BitmapProcessorImp bitmapProcessorImp;


    public DisplayImageOptions mMsgDispalyImageOptions =
            new DisplayImageOptions.Builder().cacheOnDisc(true)
                .cacheInMemory(true)
                .showImageOnFail(R.mipmap.com_bt_tab_home_menu_message_select)
                .showImageOnLoading(R.mipmap.com_bt_tab_home_menu_message_normal)
                .showImageForEmptyUri(R.mipmap.com_bt_tab_home_menu_message_normal)
                .build();

}
