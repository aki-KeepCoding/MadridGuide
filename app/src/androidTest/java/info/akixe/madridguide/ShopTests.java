package info.akixe.madridguide;

import android.test.AndroidTestCase;

import info.akixe.madridguide.model.Shop;


public class ShopTests extends AndroidTestCase {

    public static final String SHOP = "shop";
    public static final String TXT_ADDRESS = "ADDRESS";

    public void testCanCreateAShop () {
        Shop sut = new Shop (0, SHOP);
        assertNotNull(sut);
    }

    public void testANewShopStoresDataCorrectly (){
        Shop sut = new Shop(10, SHOP);
        assertEquals(10, sut.getId());
        assertEquals(SHOP, sut.getName());
    }

    public void testANewShopStoresDataInPropertiesCorrectly () {
        // TODO: 13/12/16 Extraer textos a constante. Ponerle txt por delante 
        Shop sut = new Shop(11, SHOP)
                .setAddress(TXT_ADDRESS)
                .setDescription("DESC")
                .setImageUrl("URL");

        assertEquals(sut.getAddress(), TXT_ADDRESS);
        assertEquals(sut.getDescription(), "DESC");
        assertEquals(sut.getImageUrl(), "URL");
        
    }
}
