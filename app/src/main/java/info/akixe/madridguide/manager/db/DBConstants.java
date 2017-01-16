package info.akixe.madridguide.manager.db;


public class DBConstants {

    public static final String TABLE_SHOP = "SHOP";
    public static final String KEY_SHOP_ID = "_id";
    public static final String KEY_SHOP_NAME = "NAME";
    public static final String KEY_SHOP_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";
    public static final String KEY_SHOP_ADDRESS = "ADDRESS";
    public static final String KEY_SHOP_URL = "URL";
    public static final String KEY_SHOP_LATITUDE = "LATITUDE";
    public static final String KEY_SHOP_LONGITUDE = "LONGITUDE";
    public static final String KEY_SHOP_DESCRIPTION = "DESCRIPTION";


    public static final String TABLE_ACTIVITY = "ACTIVITY";
    public static final String KEY_ACTIVITY_ID = "_id";
    public static final String KEY_ACTIVITY_NAME = "NAME";
    public static final String KEY_ACTIVITY_IMAGE_URL = "IMAGE_URL";
    public static final String KEY_ACTIVITY_MAP_IMAGE_URL = "MAP_IMAGE_URL";
    public static final String KEY_ACTIVITY_ADDRESS = "ADDRESS";
    public static final String KEY_ACTIVITY_URL = "URL";
    public static final String KEY_ACTIVITY_LATITUDE = "LATITUDE";
    public static final String KEY_ACTIVITY_LONGITUDE = "LONGITUDE";
    public static final String KEY_ACTIVITY_DESCRIPTION = "DESCRIPTION";


    public static final String[] ALL_COLUMNS= {
            KEY_SHOP_ID,
            KEY_SHOP_NAME,
            KEY_SHOP_IMAGE_URL,
            KEY_SHOP_LOGO_IMAGE_URL,
            KEY_SHOP_ADDRESS,
            KEY_SHOP_URL,
            KEY_SHOP_LATITUDE,
            KEY_SHOP_LONGITUDE,
            KEY_SHOP_DESCRIPTION
    };

    public static final String[] ALL_ACTIVITY_COLUMNS= {
            KEY_ACTIVITY_ID,
            KEY_ACTIVITY_NAME,
            KEY_ACTIVITY_IMAGE_URL,
            KEY_ACTIVITY_MAP_IMAGE_URL,
            KEY_ACTIVITY_ADDRESS,
            KEY_ACTIVITY_URL,
            KEY_ACTIVITY_LATITUDE,
            KEY_ACTIVITY_LONGITUDE,
            KEY_ACTIVITY_DESCRIPTION
    };

    public static final String SQL_CREATE_SHOP_TABLE =
            "create table " + TABLE_SHOP
                    + "( "
                    + KEY_SHOP_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text,"
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_LATITUDE + " real,"
                    + KEY_SHOP_LONGITUDE + " real, "
                    + KEY_SHOP_DESCRIPTION + " text "
                    + ");";

    public static final String SQL_CREATE_ACTIVITY_TABLE =
            "create table " + TABLE_ACTIVITY
                    + "( "
                    + KEY_ACTIVITY_ID + " integer primary key autoincrement, "
                    + KEY_ACTIVITY_NAME + " text not null,"
                    + KEY_ACTIVITY_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_MAP_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_ADDRESS + " text,"
                    + KEY_ACTIVITY_URL + " text,"
                    + KEY_ACTIVITY_LATITUDE + " real,"
                    + KEY_ACTIVITY_LONGITUDE + " real, "
                    + KEY_ACTIVITY_DESCRIPTION + " text "
                    + ");";

    public static final String[] CREATE_DATABASE = {
            SQL_CREATE_SHOP_TABLE, SQL_CREATE_ACTIVITY_TABLE
    };

    public static final String DROP_DATABASE_SCRIPTS = "";

}
