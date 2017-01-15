package info.akixe.madridguide.manager;


import android.content.Context;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

public class ImageCacheManager {


    WeakReference<Context> context;

    public ImageCacheManager(Context context) {
        this.context = new WeakReference<Context>(context);
    }


    public void cacheImages(List<String> urls) {
        for (String url : urls) {
            Picasso.with(context.get())
                    .load(url)
                    .fetch();
        }
    }
}
/*
    @NonNull
    private ImageRequest getImageRequest(final String imgURLString) {
        return new ImageRequest(imgURLString,
                    new Response.Listener<Bitmap>() {


                @Override
                public void onResponse(final Bitmap bitmap) {
                    try{
                        final URL imgURL = new URL(imgURLString);
                        Log.v("AOA", "llega 5");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                persistIntoDisk(bitmap, imgURL.getPath() );

                            }
                        }).start();
                    } catch (Exception e) {

                    }


                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
    }

    private void persistIntoDisk(Bitmap bitmap, String fileName) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + fileName);
        try {
            Log.v("AOA", "llega 1");
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
            ostream.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}*/
/*

public class NetworkManager {


    public NetworkManager(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    public void getShopsFromServer(final GetShopsListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = context.get().getString(R.string.shops_url);



        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Log.d("JSON", response);
                        List<ShopEntity> shopResponse = parseResponse(response);
                        if (listener != null) {
                            listener.getShopEntitiesSuccess(shopResponse);
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.getShopEntitiesError();
                    }
                }
        );
        queue.add(request);
    }

    private List<ShopEntity> parseResponse(String response) {
        List<ShopEntity> result = null;
        try {
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();

            ShopResponse shopResponse = gson.fromJson(reader, ShopResponse.class);
            result = shopResponse.result;


        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void getActivitiesFromServer(final GetActivitiesListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context.get());
        String url = context.get().getString(R.string.activities_url);

        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Log.d("JSON", response);
                        List<ActivityEntity> activityResponse = parseActivityResponse(response);
                        if (listener != null) {
                            listener.getActivityEntitiesSuccess(activityResponse);
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.getActivityEntitiesError();
                    }
                }
        );
        queue.add(request);
    }

    // TODO: 12/1/17 Generalizar
    private List<ActivityEntity> parseActivityResponse(String response) {
        List<ActivityEntity> result = null;
        try {
            Reader reader = new StringReader(response);
            Gson gson = new GsonBuilder().create();

            ActivityResponse activityResponse = gson.fromJson(reader, ActivityResponse.class);
            result = activityResponse.result;


        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}


* private boolean cacheImage(String imgURL, Context context){
        try {
            URL url = new URL(imgURL);


            final String imageName = url.getFile();
            Log.d("TAG",  imageName);
            Picasso.with(context)
                    .load(imgURL)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                            MainThread.run(new Runnable() {
                                @Override
                                public void run() {

                                    File file = new File(Environment.getExternalStorageDirectory().getPath() + imageName);
                                    try {
                                        file.createNewFile();
                                        FileOutputStream ostream = new FileOutputStream(file);
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                                        ostream.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
            } catch (Exception e){
                return false;
            }
            return true;
        }
* */