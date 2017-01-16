package info.akixe.madridguide.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.akixe.madridguide.R;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.util.Constants;

public class ActivityDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_activity_detail_activity_name_text)
    TextView activityNameText;

    @BindView(R.id.activity_activity_detail_activity_description_text)
    TextView activityDescriptionText;

    @BindView(R.id.activity_activity_detail_activity_address_text)
    TextView activityAddressText;

    @BindView(R.id.activity_activity_detail_activity_image)
    ImageView activityImage;

    @BindView(R.id.activity_activity_detail_activity_map_image)
    ImageView activityMapImage;
    /*
    *
    * */

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);

        ButterKnife.bind(this);

        getDetailActivityFromCallingIntent();

        updateUI();
    }

    private void getDetailActivityFromCallingIntent() {
        Intent intent = getIntent();
        if(intent != null) {
            activity = (Activity) intent.getSerializableExtra(Constants.INTENT_KEY_ACTIVITY_DETAIL);
        }
    }

    private void updateUI() {
        activityNameText.setText(activity.getName());
        activityAddressText.setText(activity.getAddress());
        activityDescriptionText.setText(activity.getDescription());
        Picasso.with(this)
                .load(activity.getImgUrl())
                .into(activityImage);
        Picasso.with(this)
                .load(activity.getMapImgUrl())
                .into(activityMapImage);


    }
}