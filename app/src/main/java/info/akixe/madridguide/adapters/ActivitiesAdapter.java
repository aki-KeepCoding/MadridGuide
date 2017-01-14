package info.akixe.madridguide.adapters;


/*public class ActivitiesAdapter extends RecyclerView.Adapter<ActivityRowViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Activities activities;

    private OnPOIElementClick<Activity> listener;

    public ActivitiesAdapter(Activities activities, Context context) {
        this.activities = activities;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = layoutInflater.inflate(R.layout.row_activity, parent, false);
        return new ActivityRowViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ActivityRowViewHolder row, final int position) {
        final Activity activity = activities.get(position);
        row.setActivity(activity);
        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivitiesAdapter.this.listener != null){
                    ActivitiesAdapter.this.listener.clikedOn(activity, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return (int) activities.size();
    }

    public void setOnElementClickListener(@NonNull final OnPOIElementClick listener) {
        this.listener = listener;
    }


}*/


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.akixe.madridguide.R;
import info.akixe.madridguide.adapters.base.POIAdapter;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.views.ActivityRowViewHolder;
import info.akixe.madridguide.views.base.OnPOIElementClick;

public class ActivitiesAdapter extends POIAdapter<Activity> {

    private final LayoutInflater layoutInflater;
    private final Activities activities;


    private OnPOIElementClick<Activity> listener;

    public ActivitiesAdapter(Activities activities, Context context) {
        super(activities.all(), context);
        this.activities = activities;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ActivityRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_activity, parent, false);
        return new ActivityRowViewHolder(view);
    }
}
