package text.com.mylistview.listview.listhelper.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JohnTsai(mailto:johntsai.work@gmail.com) on 16/4/22.
 */
public class OrderChangePagerAdapter extends SimplePagerAdapter {

    private Map<Long,Fragment> mFragmentMap = new HashMap<>();

    public OrderChangePagerAdapter(FragmentManager fm, PagerModelFactory pagerModelFactory) {
        super(fm, pagerModelFactory);
    }

    @Override
    public Fragment getItem(int position) {
        long id = getItemId(position);
        if(mFragmentMap.get(id)!=null){
            return mFragmentMap.get(id);
        }
        Fragment fragment = pagerModelFactory.getItem(position);
        mFragmentMap.put(id,fragment);
        return fragment;
    }

    @Override
    public long getItemId(int position) {
        return pagerModelFactory.getFragmentList().get(position).hashCode();
    }

    @Override
    public int getItemPosition(Object object) {
        Fragment fragment = (Fragment) object;
        for(int i =0;i<getCount();i++){
            Fragment item = getItem(i);
            if(item.equals(fragment)){
                return i;
            }
        }
        for(Map.Entry<Long,Fragment>entry:mFragmentMap.entrySet()){
            if(entry.getValue().equals(fragment)){
                mFragmentMap.remove(entry.getKey());
                break;
            }
        }
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerModelFactory.getTitle(position);
    }
}
