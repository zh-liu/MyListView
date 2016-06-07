package text.com.mylistview.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentTabHost;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import de.greenrobot.event.EventBus;
import text.com.mylistview.R;
import text.com.mylistview.customviews.TitleBar;
import text.com.mylistview.fragment.TabChatFragment_;
import text.com.mylistview.fragment.TabJobFragment_;
import text.com.mylistview.fragment.TabMeFragment_;

/**
 * Created by liuzh on 2016/6/2.
 */
@EActivity(R.layout.activity_tab)
public class TabActivity extends  BaseActivity {
    @ViewById(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    View btnJob,btnChat,btnMe;
  //  @StringRes(R.string.tab_widget_string_job)
    String tab_widget_string_job = "工作";
   // @StringRes
   String tab_widget_string_chat="消息";
  //  @StringRes
    String tab_widget_string_me="我";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         btnJob = View.inflate(getApplicationContext(),R.layout.tab_button_main,null);
         btnChat = View.inflate(getApplicationContext(),R.layout.tab_button_chat,null);
        btnMe = View.inflate(getApplicationContext(),R.layout.tab_button_me,null);

    }

    @Override
    protected void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        mTitleBar.setVisibility(View.GONE);
    }



    @AfterViews void afterViews(){
         mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        mTabHost.addTab(mTabHost.newTabSpec(tab_widget_string_job).setIndicator(btnJob), TabJobFragment_.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(tab_widget_string_chat).setIndicator(btnChat), TabChatFragment_.class,null);
        mTabHost.addTab(mTabHost.newTabSpec(tab_widget_string_me).setIndicator(btnMe), TabMeFragment_.class,null);
        mTabHost.setClickable(true);
        setUpTabViewClick();
    }

    private void setUpTabViewClick(){
        btnJob.setOnClickListener(onClickListener);
        btnChat.setOnClickListener(onClickListener);
        btnMe.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener  =  new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(view.equals(btnJob)){
                tabClick(view, tab_widget_string_job);

            }else if(view.equals(btnChat)){
                tabClick(view,tab_widget_string_chat);
            }else if(view.equals(btnMe)){
                tabClick(view,tab_widget_string_me);
            }
        }
    };

    private void tabClick(View view,String tabTag){
        if(mTabHost.getCurrentTabTag().equals(tabTag)){
            EventBus.getDefault().post(view);
        }else{
            if(!isFinishing()){
                mTabHost.setCurrentTabByTag(tabTag);
            }
        }
    }
}
