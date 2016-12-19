package info.akixe.madridguide;

import android.support.annotation.NonNull;
import android.test.AndroidTestCase;

import java.util.ArrayList;
import java.util.List;

import info.akixe.madridguide.model.Shop;
import info.akixe.madridguide.model.Shops;


public class ShopsTests extends AndroidTestCase {
    public void testCreatingAShopsWithNullListReturnsNonNullShops () {
        Shops sut = Shops.build(null);
        assertNotNull(sut);
        assertNotNull(sut.allShops());
    }

    public void testCreatingAShopsWithAListReturnsNonNullShops () {
        List<Shop> data = getShops();

        Shops sut = Shops.build(data);
        assertNotNull(sut);
        assertNotNull(sut.allShops());
        assertEquals(sut.allShops(), data);
        assertEquals(sut.allShops().size(), data.size());
    }

    @NonNull
    private List<Shop> getShops() {
        // TODO: 14/12/16 Extraer textos a constantes
        List<Shop> data = new ArrayList<>();
        data.add(new Shop(1, "1").setAddress("AD 1"));
        data.add(new Shop(2, "2").setAddress("AD 2"));
        return data;
    }
}
