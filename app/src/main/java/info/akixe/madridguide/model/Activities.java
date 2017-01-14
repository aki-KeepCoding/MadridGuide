package info.akixe.madridguide.model;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Activities implements IActivitiesIterable, IActivitiesUpdatable {

    List<Activity> activities;

    public static @NonNull Activities build(@NonNull final List<Activity> activityList) {
        Activities activities = new Activities(activityList);

        if (activityList == null) {
            activities.activities = new ArrayList<>();
        }

        return activities;
    }

    public static @NonNull Activities build() {
        return build(new ArrayList<Activity>());
    }

    private Activities(List<Activity> activities) {
        this.activities = activities;
    }

    private Activities() {
        activities = new ArrayList<>();
    }

    @Override
    public long size() {
        return activities.size();
    }

    @Override
    public Activity get(long index) {
        return activities.get((int)index);
    }

    @Override
    public List<Activity> allActivities() {
        return activities;
    }

    @Override
    public void add(Activity activity) {
        activities.add(activity);
    }

    @Override
    public void delete(Activity activity) {
        activities.remove(activity);
    }

    @Override
    public void edit(Activity newActivity, long index) {
        activities.set((int)index, newActivity);
    }
}

