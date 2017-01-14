package info.akixe.madridguide.views.base;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;

public abstract class POIRowViewHolder<T> extends RecyclerView.ViewHolder {
    private WeakReference<Context> context;

    public POIRowViewHolder(View rowView) {
        super(rowView);

    }

    public abstract void bindViews(View rowView);
    public abstract void setModel(final @NonNull T model);

}