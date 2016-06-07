package text.com.mylistview;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends Application {

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