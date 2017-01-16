package info.akixe.madridguide.interactors;


import android.content.Context;

import info.akixe.madridguide.manager.db.ActivityDAO;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.util.MainThread;

public class CacheAllActivitiesInteractor {

    public interface CacheAllActivitiesInteractorResponse {
        public void onResponse(boolean success);
    }

    public void execute(final Context context,
                        final Activities activities,
                        final CacheAllActivitiesInteractorResponse responder) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityDAO dao = new ActivityDAO(context);
                dao.deleteAll();
                boolean success = false;
                for(Activity activity: activities.all()){
                    success = dao.insert(activity) > 0;
                    if(!success) {
                        break;
                    }
                }

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        responder.onResponse(true);
                    }
                });

            }
        }).start();

    }



}

