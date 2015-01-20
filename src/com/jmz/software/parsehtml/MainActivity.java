package com.jmz.software.parsehtml;

import org.sufficientlysecure.htmltextview.HtmlTextView;
import org.sufficientlysecure.htmltextview.images.ImageCacheManager;
import org.sufficientlysecure.htmltextview.images.ImageCacheManager.CacheType;
import org.sufficientlysecure.htmltextview.images.RequestManager;

import android.app.Activity;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;

public class MainActivity extends Activity {

    private HtmlTextView textView;
    
    private static int DISK_IMAGECACHE_SIZE = 1024*1024*10;
    private static CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = CompressFormat.PNG;
    private static int DISK_IMAGECACHE_QUALITY = 100;  //PNG is lossless so quality is ignored but must be provided

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        String  html = "<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>" 
        + "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1" 
                + "</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p><p>" 
        + "下面是网络图片</p><img src=\"https://raw.githubusercontent.com/CFutureTeam/android-image-map/master/screenshot.png\"/>"
        + "</body></html>";

        textView = (HtmlTextView) this.findViewById(R.id.textView1);
        textView.setHtmlFromString(html);;

    }
    /**
     * Intialize the request manager and the image cache 
     */
    private void init() {
        RequestManager.init(this);
        createImageCache();
    }
    
    /**
     * Create the image cache. Uses Memory Cache by default. Change to Disk for a Disk based LRU implementation.  
     */
    private void createImageCache(){
        ImageCacheManager.getInstance().init(this,
                this.getPackageCodePath()
                , DISK_IMAGECACHE_SIZE
                , DISK_IMAGECACHE_COMPRESS_FORMAT
                , DISK_IMAGECACHE_QUALITY
                , CacheType.MEMORY);
    }
}
