package info.akixe.madridguide.interactors;


import android.content.Context;

import java.util.List;

import info.akixe.madridguide.manager.ImageCacheManager;


public class CacheAllImagesInteractor {
    public interface CacheAllImagesInteractorResponse {
        public void onCached(boolean success);
    }

    public void execute(final Context context,
                        final List<String> imageURLs) {

        ImageCacheManager imageCacheManger = new ImageCacheManager(context);

        imageCacheManger.cacheImages(imageURLs);
    }

}
