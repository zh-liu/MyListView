package text.com.mylistview.imageload;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.nostra13.universalimageloader.core.process.BitmapProcessor;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

import text.com.mylistview.R;

/**
 * Created by liuzh on 2016/6/7.
 */
@EBean(scope = EBean.Scope.Singleton)
public class BitmapProcessorImp implements BitmapProcessor {
    @DimensionPixelSizeRes(R.dimen.tweet_gridview_column_height_width_one) int tweetGridviewColumnHeightWidthOne ;
    @DimensionPixelSizeRes(R.dimen.tweet_gridview_column_height_width_length) int tweetGridviewColumnHeightWidthLength;
    @Override
    public Bitmap process(Bitmap bitmap){
        if(bitmap!=null){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int scaleWidth = (int)(bitmap.getWidth()*(tweetGridviewColumnHeightWidthOne/(float)height));

            if(scaleWidth<tweetGridviewColumnHeightWidthLength){
                final float scale = tweetGridviewColumnHeightWidthLength/(float)width;
                if(height * scale > tweetGridviewColumnHeightWidthOne){
                    height = (int) (tweetGridviewColumnHeightWidthOne/scale);

                }
                Matrix matrix = new Matrix();
                matrix.postScale(scale, scale);
                Bitmap resizeBmap = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
                if(resizeBmap != null){
                    bitmap.recycle();
                    bitmap = null;
                    return resizeBmap;

                }
            }
        }
        return bitmap;
    }
}
