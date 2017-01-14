package info.akixe.madridguide.model;


public interface IActivitiesUpdatable {
    void add(Activity activity);
    void delete(Activity activity);
    void edit(Activity newActivity, long index);
}

