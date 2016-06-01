package text.com.mylistview.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import text.com.mylistview.R;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:lmyu@wisorg.com">David.Yu</a>
 * @version V1.0, 8/4/16
 */
@EViewGroup(R.layout.msi_titlebar)
public class TitleBar extends LinearLayout{

    private static final String TAG = "TitleBar";

    public static final int MODE_NONE = 0;
    public static final int MODE_TITLE_NAME = 1;
    public static final int MODE_LEFT_ACTION = 2;
    public static final int MODE_RIGHT_ACTION = 4;
    public static final int MODE_ALL = 7;

    @ViewById(R.id.action_back)
    ImageView mActionBack;

    @ViewById(R.id.action_go) ImageView mActionGo;

    @ViewById(R.id.action_word)
    TextView mActionWord;

    @ViewById ImageView action_second_right;

    @ViewById TextView left_text;

    @ViewById(R.id.title_name) TextView mTitleName;
    /**
     * 首页同学帮帮图标+美化的字体，如果设置了这个，要隐藏原来的文字title
     */
    @ViewById(R.id.title_delegate) ImageView titleDelegate;

    @ViewById(R.id.action_go_wrapper)
    View mActionGoWrapper;

    @ViewById(R.id.action_back_wrapper) View mActionBackWrapper;

    @ViewById
    ImageButton btn_toggle_domain;

    @ViewById(R.id.progressBar)
    ProgressBar progressBar;

    @ViewById(R.id.title_container) View container;

    @ViewById(R.id.bg_view) View bgView;

    @ViewById(R.id.custom_view)
    ViewGroup customView;

    private Context mContext;

    private OnActionChangedListener mActionChangedListener;

    private boolean blRefreshing = false;

    @AfterViews
    void afterViews() {
    }

    @Click
    void action_back_wrapper() {
        if (mActionChangedListener != null) {
            mActionChangedListener.onLeftActionChanged();
        }
    }

    @Click void action_go_wrapper() {
        if (mActionChangedListener != null) {
            mActionChangedListener.onRightActionChanged();
        }
    }

    @Click void action_second_right() {
        if (mActionChangedListener != null) {
            mActionChangedListener.onRightSecondryAction();
        }
    }

    @Click void btn_toggle_domain() {
        if (mActionChangedListener != null) {
            mActionChangedListener.onLeftToggleDomainAction();
        }
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setMode(int mode) {
        if ((mode & MODE_TITLE_NAME) == 0) {
            mTitleName.setVisibility(View.INVISIBLE);
        } else {
            mTitleName.setVisibility(View.VISIBLE);
        }
        if ((mode & MODE_LEFT_ACTION) == 0) {
            mActionBackWrapper.setVisibility(View.INVISIBLE);
        } else {
            mActionBackWrapper.setVisibility(View.VISIBLE);
        }
        if ((mode & MODE_RIGHT_ACTION) == 0) {
            mActionGoWrapper.setVisibility(View.INVISIBLE);
        } else {
            mActionGoWrapper.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleName(int titleNameRes) {
        setTitleName(mContext.getString(titleNameRes));
    }

    public void setTitleName(String titleName) {
        mTitleName.setText(titleName);
    }

    public void setTitleDelegate(int res) {
        mTitleName.setVisibility(View.INVISIBLE);
        titleDelegate.setVisibility(View.VISIBLE);
        titleDelegate.setImageResource(res);
    }

    public void setLeftActionImage(int imageRes) {
        mActionBack.setImageResource(imageRes);
    }

    public void setRightActionImage(int imageRes) {
        mActionGo.setImageResource(imageRes);
    }

    public void setRightButtonText(int StringRes) {
        mActionWord.setText(StringRes);
    }

    public void setRightButtonText(String text){
        mActionWord.setText(text);
    }

    public void updateRightButtonTextColor(int resId) {
        mActionWord.setTextColor(getContext().getResources().getColor(resId));
    }


    //显示右边按钮左侧的二级按钮
    public void showRightSecondryButton(int visibility) {
        action_second_right.setVisibility(visibility);
    }

    public void setRightSecondryRes(int resId){
        action_second_right.setImageResource(resId);
    }

    public void showLeftText(String text) {
        left_text.setVisibility(View.VISIBLE);
        left_text.setText(text);
    }

    public void showLeftToggleDomainButton() {
        btn_toggle_domain.setVisibility(View.VISIBLE);
        mActionBack.setVisibility(View.INVISIBLE);
    }

    public void hideLeftToggleDomainButton() {
        btn_toggle_domain.setVisibility(View.INVISIBLE);
    }

    public void setLeftToggleDomainRes(int resId) {
        btn_toggle_domain.setImageResource(resId);
    }

    public void setLeftActionImageExtraMarginLeft(int dimen) {
        RelativeLayout.LayoutParams lParams =
                (RelativeLayout.LayoutParams) this.btn_toggle_domain.getLayoutParams();
        lParams.leftMargin = getResources().getDimensionPixelSize(dimen);
        btn_toggle_domain.setLayoutParams(lParams);
    }

    public void showRightText() {
        mActionWord.setVisibility(View.VISIBLE);
        mActionGo.setVisibility(View.GONE);
    }

    public void hideRightText(){
        mActionWord.setVisibility(View.GONE);
        mActionGo.setVisibility(View.VISIBLE);
    }

    public void setCustomView(int resId) {
        customView.removeAllViews();
        LayoutInflater.from(getContext()).inflate(resId, customView, true);
        customView.setVisibility(View.VISIBLE);
    }

    public void setCustomView(View view) {
        customView.removeAllViews();
        customView.addView(view);
        customView.setVisibility(View.VISIBLE);
    }

    public View getCustomView() {
        if (customView.getChildCount() == 0) {
            throw new RuntimeException("Title bar has no custom view.");
        }
        return customView.getChildAt(0);
    }

    /**
     * 标题栏点击事件
     */
    public void setOnTitleClickListener(OnClickListener onTitleClickListener) {
        mTitleName.setOnClickListener(onTitleClickListener);
    }

    public void setLeftActionVisibility(int visible) {
        mActionBack.setVisibility(visible);
    }

    public void setRightActionVisibility(int visible) {
        mActionGoWrapper.setVisibility(visible);
    }

    public void setOnActionChangedListener(OnActionChangedListener onActionChangedListener) {
        this.mActionChangedListener = onActionChangedListener;
    }

    public void setRightActionImageExtraMarginRight(int dimen) {
        RelativeLayout.LayoutParams lParams =
                (RelativeLayout.LayoutParams) this.mActionGo.getLayoutParams();
        lParams.rightMargin = getResources().getDimensionPixelSize(dimen);
        mActionGo.setLayoutParams(lParams);
    }

    public void setRightActionEnable(boolean enable) {
        mActionGoWrapper.setEnabled(enable);
    }

    public void toggleRightButtonTextColor(boolean b) {
        if (b) {
            mActionWord.setTextColor(getContext().getResources().getColor(R.color.white));
        } else {
            mActionWord.setTextColor(
                    getContext().getResources().getColor(R.color.msc_title_bar_text_alpha));
        }
    }

    public static interface OnActionChangedListener {
        public void onLeftActionChanged();

        public void onRightActionChanged();

        public void onLeftToggleDomainAction();

        public void onRightSecondryAction();

        public void onTitleSearchAction();
    }

    /**
     * 标题栏刷新动画
     */
    public void setRefreshing(boolean isRefresh) {
        this.blRefreshing = isRefresh;
        if (isRefresh) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public boolean isRefreshing() {
        return blRefreshing;
    }

    @Override
    public void setBackgroundColor(int color) {
        container.setBackgroundColor(color);
    }

    public static class OnActionChangeAdapter implements OnActionChangedListener{

        @Override
        public void onLeftActionChanged() {

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
    public void setOnActionChangeAdapter(OnActionChangeAdapter onActionChangeAdapter){
        setOnActionChangedListener(onActionChangeAdapter);
    }
}
