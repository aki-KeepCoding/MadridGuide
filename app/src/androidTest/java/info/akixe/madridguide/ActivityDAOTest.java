package info.akixe.madridguide;


import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import info.akixe.madridguide.manager.db.ActivityDAO;
import info.akixe.madridguide.model.Activity;

public class ActivityDAOTest extends AndroidTestCase {

    public static final String TEST_ACTIVITY_NAME = "Test Activity";
    public static final int TEST_ACTIVITY_ID = 1;

    public void testCanInsertANewActivity(){
        final ActivityDAO sut = new ActivityDAO(getContext());

        final int preInsertElementCount = getElementCount(sut);
        final long id = insertTestActivity(sut);
        final int postInsertElementCount = getElementCount(sut);

        assertTrue(preInsertElementCount  + 1 == postInsertElementCount);
    }

    public void testDeleteAllActivities(){
        final ActivityDAO sut = new ActivityDAO(getContext());

        sut.deleteAll();

        final int count = getElementCount(sut);
        assertEquals(0, count);
    }

    public void testQueryOneActivity() {
        final ActivityDAO dao = new ActivityDAO(getContext());

        final long id = insertTestActivity(dao);

        Activity sut = dao.query(id);
        assertNotNull(sut);
        assertEquals(sut.getName(), TEST_ACTIVITY_NAME);

    }

    public void testQueryAllActivity() {
        final ActivityDAO dao = new ActivityDAO(getContext());

        insertTestActivity(dao);

        List<Activity> activityList = dao.query();
        assertNotNull(activityList);
        assertTrue(activityList.size() > 0);
    }


    private int getElementCount(ActivityDAO dao) {
        final Cursor cursor = dao.queryCursor();
        return cursor.getCount();
    }

    private long insertTestActivity(ActivityDAO dao) {
        final Activity activity = new Activity(TEST_ACTIVITY_ID, TEST_ACTIVITY_NAME);
        return dao.insert(activity);
    }
}