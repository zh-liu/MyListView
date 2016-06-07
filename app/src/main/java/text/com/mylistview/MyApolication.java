package text.com.mylistview;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.androidannotations.annotations.EApplication;

import java.io.File;

/**
 * Created by liuzh on 2016/6/7.
 */
@EApplication
public class MyApolication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoad();
    }

    private void initImageLoad() {
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).delayBeforeLoading(0).cacheOnDisc(true).build();

        File cacheDir = StorageUtils.getCacheDirectory(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options).memoryCache(new FIFOLimitedMemoryCache(100)).denyCacheImageMultipleSizesInMemory().memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13).diskCache(new UnlimitedDiscCache(cacheDir)).discCacheSize(50 * 1024 * 1024).discCacheFileCount(100).threadPoolSize(8).build();
        ImageLoader.getInstance().init(config);
    }
}
