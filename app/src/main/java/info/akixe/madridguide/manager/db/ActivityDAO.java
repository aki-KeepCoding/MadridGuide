package info.akixe.madridguide.manager.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import info.akixe.madridguide.model.Activities;
import info.akixe.madridguide.model.Activity;

import static info.akixe.madridguide.manager.db.DBConstants.ALL_ACTIVITY_COLUMNS;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_ADDRESS;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_DESCRIPTION;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_ID;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_IMAGE_URL;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_LATITUDE;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_LONGITUDE;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_MAP_IMAGE_URL;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_NAME;
import static info.akixe.madridguide.manager.db.DBConstants.KEY_ACTIVITY_URL;
import static info.akixe.madridguide.manager.db.DBConstants.TABLE_ACTIVITY;

public class ActivityDAO implements DAOPersistable<Activity> {

    private WeakReference<Context> context;
    DBHelper dbHelper;
    SQLiteDatabase db;

    public ActivityDAO(Context context, DBHelper dbHelper) {
        this.context = new WeakReference<Context>(context);
        this.dbHelper = dbHelper;
        this.db = dbHelper.getDB();
    }

    public ActivityDAO(Context context) {
        this(context, DBHelper.getInstance(context));
    }

    @Override
    public long insert(@NonNull Activity activity) {
        if (activity == null) {
            return 0;
        }

        db.beginTransaction();
        long id = DBHelper.INVALID_ID;
        try {
            id = db.insert(TABLE_ACTIVITY, null, this.getContentValues(activity));
            activity.setId(id);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return id;
    }

    @Override
    public void update(long id, @NonNull Activity data) {

    }

    @Override
    public int delete(long id) {
        return db.delete(TABLE_ACTIVITY, KEY_ACTIVITY_ID + " = " + id, null);
    }

    @Override
    public void deleteAll() {
        db.delete(TABLE_ACTIVITY, null, null);

    }

    @Nullable
    @Override
    public Cursor queryCursor() {
        Cursor cursor = db.query(TABLE_ACTIVITY, ALL_ACTIVITY_COLUMNS, null, null, null, null, KEY_ACTIVITY_ID);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public Cursor queryCursor(long id) {
        // TODO: 20/12/16 Implementar queryCursor(long id) de forma más bonita para la práctica (no repe código)
        Cursor cursor = db.query(TABLE_ACTIVITY, ALL_ACTIVITY_COLUMNS, "ID = " + id, null, null, null, KEY_ACTIVITY_ID);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    @Override
    public Activity query(long id) {
        Cursor cursor = db.query(TABLE_ACTIVITY, ALL_ACTIVITY_COLUMNS, KEY_ACTIVITY_ID + " = " + id, null, null, null, KEY_ACTIVITY_ID);

        if (cursor != null && cursor.getCount() == 1) {
            cursor.moveToFirst();
        } else {
            return null;
        }

        Activity activity = getActivity(cursor);

        return activity;
    }

    @Nullable
    @Override
    public List<Activity> query() {

        Cursor cursor = queryCursor();

        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }

        List<Activity> activities = new LinkedList<>();
        do  {
            Activity activity = getActivity(cursor);
            activities.add(activity);
        } while (cursor.moveToNext());
        return activities;
    }

    @NonNull
    public static Activity getActivity(Cursor c) {
        long ident = c.getLong(c.getColumnIndex(KEY_ACTIVITY_ID));
        String name = c.getString(c.getColumnIndex(KEY_ACTIVITY_NAME));
        Activity activity = new Activity(ident, name);

        activity.setAddress(c.getString(c.getColumnIndex(KEY_ACTIVITY_ADDRESS)));
        activity.setDescription(c.getString(c.getColumnIndex(KEY_ACTIVITY_DESCRIPTION)));
        activity.setImgUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_IMAGE_URL)));
        activity.setMapImgUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_MAP_IMAGE_URL)));
        activity.setLatitude(c.getFloat(c.getColumnIndex(KEY_ACTIVITY_LATITUDE)));
        activity.setLongitude(c.getFloat(c.getColumnIndex(KEY_ACTIVITY_LONGITUDE)));
        activity.setUrl(c.getString(c.getColumnIndex(KEY_ACTIVITY_URL)));
        return activity;
    }

    @NonNull
    public static Activities getActivities(Cursor cursor) {
        List<Activity> activityList = new LinkedList<>();
        while(cursor.moveToNext()) {
            Activity activity = ActivityDAO.getActivity(cursor);
            activityList.add(activity);
        }
        return Activities.build(activityList);
    }

    public static ContentValues getContentValues(final @NonNull Activity activity) {
        final ContentValues contentValues = new ContentValues();

        if (activity == null) {
            return contentValues;
        }

        contentValues.put(KEY_ACTIVITY_ADDRESS, activity.getAddress());
        contentValues.put(KEY_ACTIVITY_DESCRIPTION, activity.getDescription());
        contentValues.put(KEY_ACTIVITY_URL, activity.getUrl());
        contentValues.put(KEY_ACTIVITY_IMAGE_URL, activity.getImgUrl());
        contentValues.put(KEY_ACTIVITY_MAP_IMAGE_URL, activity.getMapImgUrl());
        contentValues.put(KEY_ACTIVITY_LONGITUDE, activity.getLongitude());
        contentValues.put(KEY_ACTIVITY_LATITUDE, activity.getLatitude());
        contentValues.put(KEY_ACTIVITY_NAME, activity.getName());

        return contentValues;
    }
}