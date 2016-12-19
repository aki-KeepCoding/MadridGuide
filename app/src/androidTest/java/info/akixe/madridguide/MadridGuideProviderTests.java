package info.akixe.madridguide;


import android.content.ContentResolver;
import android.database.Cursor;
import android.test.AndroidTestCase;

import info.akixe.madridguide.manager.db.DBConstants;
import info.akixe.madridguide.manager.db.provider.MadridGuideProvider;

public class MadridGuideProviderTests extends AndroidTestCase {
    public void testQueryAllShops() {
        ContentResolver cr = getContext().getContentResolver();

        Cursor c = cr.query(MadridGuideProvider.SHOPS_URI, DBConstants.ALL_COLUMNS, null, null, null);
        assertNotNull(c);
    }
}
