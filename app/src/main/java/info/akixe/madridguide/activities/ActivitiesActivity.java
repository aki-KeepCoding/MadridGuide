package info.akixe.madridguide.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import info.akixe.madridguide.R;
import info.akixe.madridguide.fragments.ActivitiesListFragment;
import info.akixe.madridguide.interactors.GetAllActivitiesFromLocalCacheInteractor;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.navigator.Navigator;
import info.akixe.madridguide.views.base.OnPOIElementClick;

import static info.akixe.madridguide.util.Constants.MADRID_LATLNG;

public class ActivitiesActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener {
    private ActivitiesListFragment activitiesListFragment;
    private SupportMapFragment activitiesMapFragment;
    private GoogleMap map;
    Map<Marker, Activity> allMarkersMap = new HashMap<Marker, Activity>();



    private EditText filterField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        setToolbar();

        setActivityListFragment();
        setActivitiesMapFragment();
        setActivityFilter();
    }

    private void setActivityFilter() {
        filterField = (EditText)findViewById(R.id.edit);
        filterField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                activitiesListFragment.filter(editable.toString());
                updateMapActivities(activitiesListFragment.getFilteredActivities());
            }
        });
    }

    private void loadActivities(){
        GetAllActivitiesFromLocalCacheInteractor i = new GetAllActivitiesFromLocalCacheInteractor();
        i.execute(this, new GetAllActivitiesFromLocalCacheInteractor.OnGetAllActivitiesFromLocalCache() {
            @Override
            public void completion(Activities activities) {
                activitiesListFragment.setListener(new OnPOIElementClick<Activity>() {
                    @Override
                    public void clikedOn(Activity activity, int position) {
                        goToActivityDetail(activity);
                    }
                });
                activitiesListFragment.setActivities(activities);
                updateMapActivities(activities);

            }
        });
    }

    private void goToActivityDetail(Activity activity) {
        Navigator.navigateFromActivitiesActivityToActivityDetailActivity(ActivitiesActivity.this, activity);
    }

    private void updateMapActivities(Activities activities){
        map.clear();
        for (Activity activity: activities.all()) {
            Marker marker = map.addMarker(new MarkerOptions()
                        .title(activity.getName())
                        .position(new LatLng(activity.getLatitude(), activity.getLongitude()))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            allMarkersMap.put(marker, activity);
        }

    }

    private void setActivityListFragment() {
        activitiesListFragment = (ActivitiesListFragment) this.getSupportFragmentManager().findFragmentById(R.id.activity_activities_frag_activities_list);
        loadActivities();
    }

    private void setActivitiesMapFragment(){
        Log.v("AOA", getSupportFragmentManager().getFragments().toString());
        activitiesMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_activities_frag_map);
        activitiesMapFragment.getMapAsync(this);

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(MADRID_LATLNG, 12.0f));
        this.map.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        this.map.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Activity activity = allMarkersMap.get(marker);
        goToActivityDetail(activity);
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View infoWindowView;

        CustomInfoWindowAdapter(){
            infoWindowView = getLayoutInflater().inflate(R.layout.custom_info_window, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView title = (TextView)infoWindowView.findViewById(R.id.custom_info_window_title);
            title.setText(marker.getTitle());

            ImageView image = (ImageView) infoWindowView.findViewById(R.id.custom_info_window_image);
            String imgUrl = allMarkersMap.get(marker).getImgUrl();
            Picasso.with(getApplicationContext())
                    .load(imgUrl)
                    .into(image);


            return infoWindowView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }
}

