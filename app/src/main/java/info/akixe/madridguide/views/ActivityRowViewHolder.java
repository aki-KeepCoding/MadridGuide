package info.akixe.madridguide.views;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import info.akixe.madridguide.R;
import info.akixe.madridguide.model.Activity;
import info.akixe.madridguide.views.base.POIRowViewHolder;

public class ActivityRowViewHolder  extends POIRowViewHolder<Activity> {

    private TextView nameTextView;
    private ImageView imageView;

    private WeakReference<Context> context;
    public ActivityRowViewHolder(View rowActivity) {
        super(rowActivity);
        context = new WeakReference<Context>(rowActivity.getContext());

        bindViews(rowActivity);
    }

    @Override
    public void bindViews(View rowActivity) {
        nameTextView = (TextView) rowActivity.findViewById(R.id.row_activity_name);
        imageView = (ImageView) rowActivity.findViewById(R.id.row_activity_image);
    }


    @Override
    public void setModel(@NonNull Activity activity) {
        if (activity == null) {
            return;
        }
        nameTextView.setText(activity.getName());
        Picasso.with(context.get())
                .load(activity.getImgUrl())
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(imageView);
    }

}

