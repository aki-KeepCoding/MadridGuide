package info.akixe.madridguide;

import android.test.AndroidTestCase;

import info.akixe.madridguide.model.Shop;


public class ShopTests extends AndroidTestCase {

    public static final String SHOP = "shop";
    public static final int TEST_SHOP_ID = 10;
    public static final String TXT_SHOP_ADDRESS = "ADDRESS";
    public static final String TEST_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL";
    public static final String TEST_SHOP_IMAGE_URL = "IMAGE_URL";
    public static final String TEST_SHOP_DESC = "DESC";
    private static final float TEST_SHOP_LONGITUDE = 1.0f;
    private static final float TEST_SHOP_LATITUDE = 1.0f;

    public void testCanCreateAShop () {
        Shop sut = new Shop (0, SHOP);
        assertNotNull(sut);
    }

    public void testANewShopStoresDataCorrectly (){
        Shop sut = new Shop(TEST_SHOP_ID, SHOP);
        assertEquals(TEST_SHOP_ID, sut.getId());
        assertEquals(SHOP, sut.getName());
    }

    public void testANewShopStoresDataInPropertiesCorrectly () {
        Shop sut = new Shop(TEST_SHOP_ID, SHOP)
                .setAddress(TXT_SHOP_ADDRESS)
                .setDescription(TEST_SHOP_DESC)
                .setLogoImgUrl(TEST_SHOP_LOGO_IMAGE_URL)
                .setImageUrl(TEST_SHOP_IMAGE_URL)
                .setLatitude(TEST_SHOP_LATITUDE)
                .setLongitude(TEST_SHOP_LONGITUDE);

        assertEquals(sut.getAddress(), TXT_SHOP_ADDRESS);
        assertEquals(sut.getDescription(), TEST_SHOP_DESC);
        assertEquals(sut.getLogoImgUrl(), TEST_SHOP_LOGO_IMAGE_URL);
        assertEquals(sut.getImageUrl(), TEST_SHOP_IMAGE_URL);
        assertEquals(sut.getLongitude(), TEST_SHOP_LONGITUDE);
        assertEquals(sut.getLatitude(), TEST_SHOP_LATITUDE);

    }
}
