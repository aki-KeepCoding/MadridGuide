package info.akixe.madridguide.model;
import java.util.List;

public interface IActivitiesIterable {
    long size();
    Activity get(long index);
    List<Activity> allActivities();
}


