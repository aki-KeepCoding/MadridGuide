package info.akixe.madridguide.model.mappers;


import java.util.LinkedList;
import java.util.List;

import info.akixe.madridguide.manager.net.ActivityEntity;
import info.akixe.madridguide.model.Activity;

public class ActivityEntityActivityMapper {

    public List<Activity> map(List<ActivityEntity> activityEntities) {
        List<Activity> activities = new LinkedList<>();

        for(ActivityEntity entity: activityEntities) {
            Activity activity = new Activity(entity.getId(), entity.getName())
                    .setDescription(entity.getDescriptionES())
                    .setAddress(entity.getAddress())
                    .setLongitude(entity.getLongitude())
                    .setLatitude(entity.getLatitude())
                    .setMapImgUrlWithLatLon(entity.getLatitude(), entity.getLongitude())
                    .setImgUrl(entity.getImg());
            activities.add(activity);
        }
        return  activities;
    }
}


