package text.com.mylistview.activity;

import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import text.com.mylistview.R;
import text.com.mylistview.bean.ListItemBean;
import text.com.mylistview.customviews.TitleBar;

/**
 * Created by liuzh on 2016/6/1.
 */
@EActivity(R.layout.activity_title)
public class MyTitleActivity extends  BaseActivity {

    @Extra
    ListItemBean myBean;

    @Override
    protected void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        titleBar.setMode(TitleBar.MODE_ALL);
        titleBar.setTitleName(myBean.getName());

       // titleBar.setLeftActionImage(R.drawable.com_bt_ttb_back);

      //  titleBar.showLeftText("返回");

        titleBar.showRightText();
        titleBar.setRightButtonText("进入");
        titleBar.toggleRightButtonTextColor(true);


    }

    @Override
    public void onRightActionChanged() {
        Toast.makeText(this,"进入",Toast.LENGTH_SHORT).show();
    }
//
//    @Override
//    public void onLeftActionChanged() {
//
//    }
}
