package info.akixe.madridguide.interactors;


import android.content.Context;

import java.util.List;

import info.akixe.madridguide.manager.net.ActivityEntity;
import info.akixe.madridguide.manager.net.NetworkManager;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.model.mappers.ActivityEntityActivityMapper;

public class GetAllActivitiesInteractor {
    public interface GetAllActivitiesInteractorResponse {
        public void onResponse(Activities activities);
    }

    public void execute(final Context context, final GetAllActivitiesInteractorResponse responder) {
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getActivitiesFromServer(new NetworkManager.GetActivitiesListener() {

            @Override
            public void getActivityEntitiesSuccess(List<ActivityEntity> result) {
                List<Activity> activities = new ActivityEntityActivityMapper().map(result);
                if (responder != null ) {
                    responder.onResponse(Activities.build(activities));
                }
            }

            @Override
            public void getActivityEntitiesError() {
                if (responder != null) {
                    responder.onResponse(null);
                }
            }
        });
    }
}