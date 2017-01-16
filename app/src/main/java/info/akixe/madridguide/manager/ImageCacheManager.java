package info.akixe.madridguide.manager;


import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageCacheManager extends IntentService {
    public ImageCacheManager(){
        super("image_download_worker");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle b = intent.getExtras();
        List<String> urls = b.getStringArrayList("IMAGE_URLS");
        for (String url : urls) {
            Picasso.with(this)
                    .load(url)
                    .fetch();
        }
    }
}
