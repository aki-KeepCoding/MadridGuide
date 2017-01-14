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

    @BindView(R.id.activity_shop_detail_activity_name_text)
    TextView activityNameText;

    @BindView(R.id.activity_shop_detail_activity_image)
    ImageView activityImage;
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
        Picasso.with(this)
                .load(activity.getImgUrl())
                .into(activityImage);
    }
}