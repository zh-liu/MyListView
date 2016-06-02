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
@EFragment(R.layout.tab_job_fragment)
public class TabJobFragment extends BaseTitleBarFragment {
    @Override
    protected void initTitleBar(TitleBar titleBar) {
        super.initTitleBar(titleBar);
        titleBar.setTitleName("工作");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setContentView(R.layout.tab_job_fragment);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

