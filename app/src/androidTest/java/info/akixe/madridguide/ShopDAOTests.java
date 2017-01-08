package info.akixe.madridguide;


import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.List;

import info.akixe.madridguide.manager.db.ShopDAO;
import info.akixe.madridguide.model.Shop;

public class ShopDAOTests extends AndroidTestCase {

    public static final String SHOP_TEST_NAME = "1";
    public static final String ADDRESS_TESTING = "AD 1";

    public void testCanInsertANewShop() {
        final ShopDAO sut = new ShopDAO(getContext());

        final int count = getCount(sut);

        final long id = insertTestShop(sut);

        assertTrue(id > 0);
        assertTrue(count + 1 == sut.queryCursor().getCount());
    }

    public void testCanDeleteAShop() {
        final ShopDAO sut = new ShopDAO(getContext());

        final long id = insertTestShop(sut);

        final int count = getCount(sut);

        final int deletedRowCount = sut.delete(id);

        assertEquals(1, deletedRowCount);
        assertTrue(count - 1 == sut.queryCursor().getCount());
    }

    public void testDeleteAll() {
        final ShopDAO sut = new ShopDAO(getContext());

        sut.deleteAll();

        final int count = getCount(sut);
        assertEquals(0, count);

    }
    
    public void testQueryOneShop() {
        final ShopDAO dao = new ShopDAO(getContext());
        
        final long id = insertTestShop(dao);
        
        Shop sut = dao.query(id);
        assertNotNull(sut);
        assertEquals(sut.getName(), SHOP_TEST_NAME);
        
    }

    public void testQueryAllShop() {
        final ShopDAO dao = new ShopDAO(getContext());

        final long id = insertTestShop(dao);

        List<Shop> shopList = dao.query();
        assertNotNull(shopList);
        assertTrue(shopList.size() > 0);
    }

    // TODO: 18/12/16 Falta el test del update

    private int getCount(ShopDAO sut) {
        final Cursor cursor = sut.queryCursor();
        return cursor.getCount();
    }

    private long insertTestShop(ShopDAO sut) {
        final Shop shop = new Shop(1, SHOP_TEST_NAME).setAddress(ADDRESS_TESTING);
        return sut.insert(shop);
    }
}
