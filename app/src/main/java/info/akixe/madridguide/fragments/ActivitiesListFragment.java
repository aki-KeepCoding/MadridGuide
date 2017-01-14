package info.akixe.madridguide.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.akixe.madridguide.R;
import info.akixe.madridguide.adapters.ActivitiesAdapter;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.views.base.OnPOIElementClick;

public class ActivitiesListFragment extends Fragment {

    private Activities activities;
    private RecyclerView activitiesRecyclerView;
    private ActivitiesAdapter adapter;

    private OnPOIElementClick<Activity> listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities_list, container, false);
        activitiesRecyclerView = (RecyclerView) view.findViewById(R.id.activities_list_recycler_view);
        activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    public void updateUI() {
        adapter = new ActivitiesAdapter(activities, getActivity());
        activitiesRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new OnPOIElementClick<Activity>() {
            @Override
            public void clikedOn(Activity activity, int position) {
                if (ActivitiesListFragment.this.listener != null) {
                    ActivitiesListFragment.this.listener.clikedOn(activity, position);
                }
            }
        });
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
        updateUI();
    }

    public OnPOIElementClick<Activity> getListener(){
        return listener;
    }

    public void setListener(OnPOIElementClick<Activity> listener) {
        this.listener = listener;
    }
}