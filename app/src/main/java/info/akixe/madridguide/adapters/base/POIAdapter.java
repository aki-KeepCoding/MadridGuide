package info.akixe.madridguide.adapters.base;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import info.akixe.madridguide.views.base.OnPOIElementClick;
import info.akixe.madridguide.views.base.POIRowViewHolder;


public abstract class POIAdapter<T> extends RecyclerView.Adapter<POIRowViewHolder<T>> {
    private final List<T> items;
    private OnPOIElementClick<T> listener;

    public POIAdapter(List<T> items, Context context) {
        this.items = items;
    }


    @Override
    public void onBindViewHolder(POIRowViewHolder row, final int position) {
        final T item = items.get(position);
        row.setModel(item);
        row.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (POIAdapter.this.listener != null) {
                    POIAdapter.this.listener.clikedOn(item, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int)items.size();
    }

    public void setOnElementClickListener(@NonNull final OnPOIElementClick listener) {
        this.listener = listener;
    }
}