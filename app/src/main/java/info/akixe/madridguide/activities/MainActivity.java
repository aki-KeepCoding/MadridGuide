package info.akixe.madridguide.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.akixe.madridguide.R;
import info.akixe.madridguide.navigator.Navigator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_shops_button)
    Button shopsButton;

    @BindView(R.id.activity_main_activities_button)
    Button activitiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupShopsButton();
        setupActivitiesButton();

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
}
