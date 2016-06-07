package text.com.mylistview.activity;


import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

import text.com.mylistview.R;

import text.com.mylistview.customviews.CustomDialog;
import text.com.mylistview.customviews.TitleBar;

/**
 * Created by liuzh on 2016/5/31.
 */
@EActivity(R.layout.msi_screen_wrapper)
public class BaseActivity extends FragmentActivity implements TitleBar.OnActionChangedListener{
    public  TitleBar mTitleBar;
    protected  ViewGroup mDecor;
    protected ViewGroup  mContentParent;
    private void initTitleBar(View view){
        TitleBar  titleBar = (TitleBar)view.findViewById(R.id.msi_titlebar);
        initTitleBar(titleBar);
    }
    protected void initTitleBar(TitleBar titleBar){
        mTitleBar = titleBar;
        titleBar.setOnActionChangedListener(this);
    }

    protected  final void initTitleBar(){
        if(mDecor!=null){
            initTitleBar(mDecor);
        }
    }



    public TitleBar getmTitleBar(){
        return mTitleBar;
    }

    @Override
    public void setContentView(int layoutResID) {
        if(null==mContentParent){
            installDecor();
        }else{
            mContentParent.removeAllViews();
        }
        getLayoutInflater().inflate(layoutResID,mContentParent);
        android.view.Window.Callback callback = getWindow().getCallback();
        if(callback!=null){
            callback.onContentChanged();
        }
        initTitleBar(mDecor);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (mContentParent == null) {
            installDecor();
        }
        mContentParent.addView(view, params);
        initTitleBar(mDecor);
    }

    @Override
    public void setContentView(View view) {
        if (mContentParent == null) {
            installDecor();
        }
        mContentParent.addView(view);
        initTitleBar(mDecor);
    }

    protected void installDecor(){
        if(mDecor==null){
            mDecor =(ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        }

        if(mContentParent ==null){
            List<View> views = null;
            if (mDecor.getChildCount() > 0) {
                views = new ArrayList<View>(1); // Usually there's only one
                // child
                for (int i = 0, children = mDecor.getChildCount(); i < children; i++) {
                    View child = mDecor.getChildAt(0);
                    mDecor.removeView(child);
                    views.add(child);
                }
            }

            mContentParent = generateLayout();

            // Copy over the old children. See above for explanation.
            if (views != null) {
                for (View child : views) {
                    mContentParent.addView(child);
                }
            }
        }
    }

    private ViewGroup generateLayout() {
        int layoutResource;
        layoutResource = R.layout.msi_screen_wrapper;
        View in = getLayoutInflater().inflate(layoutResource, null);
        mDecor.addView(in, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        ViewGroup contentParent = (ViewGroup) mDecor.findViewById(R.id.msi_content);
        if (contentParent == null) {
            throw new RuntimeException("Couldn't find content container view");
        }

        // Make our new child the true content view (for fragments). VERY
        // VOLATILE!
        mDecor.setId(View.NO_ID);
        contentParent.setId(android.R.id.content);
        return contentParent;
    }


    protected Dialog mDialog;

    protected void showSncStyleDialog(final int id, String title, String message,
                                      int positiveBtnRes, int negativeBtnRes) {
        /**
         * bug fix:
         * android.view.WindowManager$BadTokenException: Unable to add window
         */
        if (isFinishing()) return;
        mDialog = new CustomDialog.Builder(this).setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveBtnRes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismissSncStyleDialog();
                        onClickDialogPositiveButton(id);
                    }
                })
                .setNegativeButton(negativeBtnRes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismissSncStyleDialog();
                        onClickDialogNegativeButton(id);
                    }
                })
                .create();
        mDialog.show();
    }

    protected void showSncStyleDialog(final int id, int titleRes, int msgRes, int positiveBtnRes,
                                      int negativeBtnRes) {
        showSncStyleDialog(id, getText(titleRes).toString(), getResources().getString(msgRes),
                positiveBtnRes, negativeBtnRes);
    }

    protected void showSncStyleDialog(final int id, int msgRes, int positiveBtnRes,
                                      int negativeBtnRes) {
        showSncStyleDialog(id, null, getResources().getString(msgRes), positiveBtnRes,
                negativeBtnRes);
    }

    protected void showSncStyleDialog(final int id, String msgRes, int positiveBtnRes,
                                      int negativeBtnRes) {
        showSncStyleDialog(id, null, msgRes, positiveBtnRes, negativeBtnRes);
    }

    protected void onClickDialogPositiveButton(int id) {
    }

    protected void onClickDialogNegativeButton(int id) {
    }

    protected void dismissSncStyleDialog() {
        try {
            if (mDialog != null) {
                mDialog.dismiss();
                mDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void onLeftActionChanged() {
        onBackPressed();
    }

    @Override
    public void onRightActionChanged() {

    }

    @Override
    public void onLeftToggleDomainAction() {

    }

    @Override
    public void onRightSecondryAction() {

    }

    @Override
    public void onTitleSearchAction() {

    }
}
