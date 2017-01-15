package info.akixe.madridguide.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ViewSwitcher;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.akixe.madridguide.R;
import info.akixe.madridguide.interactors.CacheAllActivitiesInteractor;
import info.akixe.madridguide.interactors.CacheAllImagesInteractor;
import info.akixe.madridguide.interactors.CacheAllShopsInteractor;
import info.akixe.madridguide.interactors.GetAllActivitiesInteractor;
import info.akixe.madridguide.interactors.GetAllShopsInteractor;
import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Shops;
import info.akixe.madridguide.navigator.Navigator;
import info.akixe.madridguide.util.HelperFunctions;
import info.akixe.madridguide.util.MainThread;

public class MainActivity extends AppCompatActivity {

    private int pendingDataLoadsCounter = 0;

    private static final int LOADING_VIEW_INDEX = 0;
    private static final int MAIN_VIEW_INDEX = 1;

    @BindView(R.id.activity_main_shops_button)
    Button shopsButton;

    @BindView(R.id.activity_main_activities_button)
    Button activitiesButton;

    @BindView(R.id.activity_main_view_switcher)
    ViewSwitcher viewSwitcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();


        ButterKnife.bind(this);

        setupShopsButton();
        setupActivitiesButton();

        viewSwitcher.setInAnimation(this, android.R.anim.fade_in);
        viewSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        viewSwitcher.setDisplayedChild(MAIN_VIEW_INDEX);

        if (shouldLoadDataFromRemote()){
            viewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            loadData();
        }

    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Madrid Guide");
    }

    private void setupShopsButton() {
        shopsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
            }
        });
    }

    private void setupActivitiesButton() {
        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromMainActivityToActivitiesActivity(MainActivity.this);
            }
        });
    }


    private boolean shouldLoadDataFromRemote() {
        return HelperFunctions.isConnected(this) &&
                !HelperFunctions.isCacheValid(this);
    }


    private void loadData() {
        loadShopsData();
        loadActivitiesData();
    }

    private void loadShopsData() {
        this.pendingDataLoadsCounter +=1;
        GetAllShopsInteractor i = new GetAllShopsInteractor();
        i.execute(getApplicationContext(), getAllShopsResponder());
    }

    private void loadActivitiesData() {
        this.pendingDataLoadsCounter +=1;
        GetAllActivitiesInteractor i =new GetAllActivitiesInteractor();
        i.execute(getApplicationContext(), getAllActivitiesResponder());
    }

    @NonNull
    private GetAllShopsInteractor.GetAllShopsInteractorResponse getAllShopsResponder() {
        return new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
            @Override
            public void onResponse(final Shops shops) {
                cacheAllShops(shops);
            }
        };
    }


    @NonNull
    private GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse getAllActivitiesResponder() {
        return new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
            @Override
            public void onResponse(final Activities activities) {
                cacheAllActivities(activities);
            }
        };
    }

    private void cacheAllActivities(final Activities activities) {
        new CacheAllActivitiesInteractor().execute(getApplicationContext(),
                activities,
                new CacheAllActivitiesInteractor.CacheAllActivitiesInteractorResponse() {

                    @Override
                    public void onResponse(boolean success) {
                        MainActivity.this.pendingDataLoadsCounter -= 1;
                        if (success){
                            cacheImages(activities.getAllImageURLStrings());
                            HelperFunctions.updateCacheUpdateDate(getApplicationContext());
                            onDataLoaded();
                        }

                    }
                });
    }

    private void cacheAllShops(final Shops shops) {
        new CacheAllShopsInteractor().execute(this,
                shops,
                new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {

                    @Override
                    public void onResponse(boolean success) {
                        MainActivity.this.pendingDataLoadsCounter -= 1;
                        if (success){
                            HelperFunctions.updateCacheUpdateDate(getApplicationContext());
                            cacheImages(shops.getAllImageURLStrings());
                            onDataLoaded();
                        }
                    }
                });
    }

    private void cacheImages(List<String> allImageURLStrings) {
        new CacheAllImagesInteractor()
                .execute(getApplicationContext(),
                        allImageURLStrings);
    }

    private void onDataLoaded() {
        if (this.pendingDataLoadsCounter == 0) {
            MainThread.run(new Runnable() {
                @Override
                public void run() {
                    viewSwitcher.setDisplayedChild(MAIN_VIEW_INDEX);
                }
            });

        }
    }
}
