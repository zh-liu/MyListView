package text.com.mylistview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EFragment;

import text.com.mylistview.R;
import text.com.mylistview.customviews.TitleBar;

/**
 * Created by liuzh on 2016/6/2.
 */
@EFragment(R.layout.tab_chat_fragment)
public class TabChatFragment extends BaseTitleBarFragment {
    @Override
    protected void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        getTitleBar().setMode(TitleBar.MODE_TITLE_NAME);
        getTitleBar().setTitleName("消息");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.tab_chat_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
