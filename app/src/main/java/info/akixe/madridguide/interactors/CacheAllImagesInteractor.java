package info.akixe.madridguide.interactors;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import info.akixe.madridguide.manager.ImageCacheManager;


public class CacheAllImagesInteractor {
    public interface CacheAllImagesInteractorResponse {
        public void onCached(boolean success);
    }

    public void execute(final Context context,
                        final List<String> imageURLs) {


        Bundle b=new Bundle();
        b.putStringArrayList("IMAGE_URLS", new ArrayList<String>(imageURLs));


        Intent i = new Intent(context, ImageCacheManager.class);
        i.putExtras(b);
        context.startService(i);

    }

}
