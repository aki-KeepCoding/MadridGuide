package info.akixe.madridguide.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import info.akixe.madridguide.R;
import info.akixe.madridguide.fragments.ActivitiesListFragment;
import info.akixe.madridguide.interactors.GetAllActivitiesFromLocalCacheInteractor;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.navigator.Navigator;
import info.akixe.madridguide.views.base.OnPOIElementClick;

public class ActivitiesActivity extends AppCompatActivity {
    ActivitiesListFragment activitiesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        activitiesListFragment = (ActivitiesListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activities_fragment_activities_list);

        GetAllActivitiesFromLocalCacheInteractor i = new GetAllActivitiesFromLocalCacheInteractor();
        i.execute(this, new GetAllActivitiesFromLocalCacheInteractor.OnGetAllActivitiesFromLocalCache() {
            @Override
            public void completion(Activities activities) {
                activitiesListFragment.setListener(new OnPOIElementClick<Activity>() {
                    @Override
                    public void clikedOn(Activity activity, int position) {
                        Navigator.navigateFromActivitiesActivityToActivityDetailActivity(ActivitiesActivity.this, activity);
                    }
                });
                activitiesListFragment.setActivities(activities);
            }
        });
    }
}

