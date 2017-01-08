package info.akixe.madridguide;


import android.test.AndroidTestCase;

import info.akixe.madridguide.model.Activity;


public class ActivityTest extends AndroidTestCase {

    public static final String ACTIVITY_NAME= "TestActivity";
    public static final String ACTIVITY_IMG_URL = "img_url";
    public static final String ACTIVITY_ADDRESS = "address";
    public static final String ACTIVITY_URL= "url";
    public static final String ACTIVITY_DESCRIPTION= "description";
    public static final float ACTIVITY_LATITUDE= 0.1f;
    public static final float ACTIVITY_LONGITUDE= 0.2f;

    public void testCanCreateAnAcivity () {
        Activity sut = new Activity(0, ACTIVITY_NAME);
        assertNotNull(sut);
    }

    public void testANewShopStoresDataCorrectly (){
        Activity sut = new Activity(10, ACTIVITY_NAME);
        sut.setImgUrl(ACTIVITY_IMG_URL);
        sut.setAddress(ACTIVITY_ADDRESS);
        sut.setUrl(ACTIVITY_URL);
        sut.setDescription(ACTIVITY_DESCRIPTION);
        sut.setLatitude(ACTIVITY_LATITUDE);
        sut.setLongitude(ACTIVITY_LONGITUDE);

        assertEquals(10, sut.getId());
        assertEquals(ACTIVITY_NAME, sut.getName());
        assertEquals(ACTIVITY_IMG_URL, sut.getImgUrl());
        assertEquals(ACTIVITY_ADDRESS, sut.getAddress());
        assertEquals(ACTIVITY_URL, sut.getUrl());
        assertEquals(ACTIVITY_DESCRIPTION, sut.getDescription());
        assertEquals(ACTIVITY_LATITUDE, sut.getLatitude());
        assertEquals(ACTIVITY_LONGITUDE, sut.getLongitude());

    }

}
