package info.akixe.madridguide.interactors;


import android.content.Context;

import java.util.List;

import info.akixe.madridguide.manager.db.ActivityDAO;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.util.MainThread;

public class GetAllActivitiesFromLocalCacheInteractor {

    public interface OnGetAllActivitiesFromLocalCache {
        public void completion(Activities activities);
    }

    public void execute(final Context context, final OnGetAllActivitiesFromLocalCache backgroundTask) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityDAO dao = new ActivityDAO(context);
                List<Activity> activityList = dao.query();
                final Activities activities = Activities.build(activityList);

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        backgroundTask.completion(activities);
                    }
                });
            }
        }).start();
    }
}
