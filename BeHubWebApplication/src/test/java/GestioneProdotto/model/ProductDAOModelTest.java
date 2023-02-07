package GestioneProdotto.model;

import model.DAOImplementation.ProductDAOModel;
import model.bean.ProductBean;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOModelTest {

    @Test
    void doSaveTest() {
        ProductBean prodotto = new ProductBean();
        prodotto.setNome("PS5");
        prodotto.setDescrizione("PS5 usata");
        prodotto.setPrezzo(500);
        prodotto.setSpedizione(10);
        prodotto.setEmail("mariorossi@gmail.com");
        prodotto.setCondizione(1);
        prodotto.setQuantity(1);
        prodotto.setCategoria(2);
        prodotto.setImmagine("stringaUrl");

        ProductDAOModel productModel = new ProductDAOModel();
        try {
            productModel.doSave(prodotto);
            ProductBean prodotto2 = productModel.doRetrieveByKey(1004);
            assertEquals(prodotto.getNome(), prodotto2.getNome());
            assertEquals(prodotto.getDescrizione(), prodotto2.getDescrizione());
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void doRetrieveByKeyTest() {
        ProductDAOModel productDAOModel = new ProductDAOModel();
        try {
            ProductBean prodotto = productDAOModel.doRetrieveByKey(1000);
            assertNotNull(productDAOModel.doRetrieveByKey(1000));
            assertEquals(1000, prodotto.getCodice());
            assertEquals("Divina Commedia nuova edizione", prodotto.getNome());
            assertNotEquals("descrizione falsa", prodotto.getDescrizione());
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void doRetrieveAllTest() {
        ProductDAOModel productDAOModel = new ProductDAOModel();
        try {
            assertNotNull(productDAOModel.doRetrieveAll("Libri"));
            assertFalse(productDAOModel.doRetrieveAll("Libri").isEmpty());
            assertTrue(productDAOModel.doRetrieveAll("Abbigliamento").isEmpty());

        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void deleteProductTest() {
        ProductDAOModel productDAOModel = new ProductDAOModel();
        Collection<ProductBean> prodotti = new LinkedList<ProductBean>();
        productDAOModel.deleteProduct(1004, prodotti);
        try {
            ProductBean prodotto = productDAOModel.doRetrieveByKey(1004);
            assertEquals(-1, prodotto.getCodice());
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void updateProductTest() {
        ProductBean prodotto = new ProductBean();
        prodotto.setCodice(1001);
        prodotto.setNome("PS5");
        prodotto.setDescrizione("PS5 usata");
        prodotto.setPrezzo(500);
        prodotto.setSpedizione(10);
        prodotto.setEmail("mariorossi@gmail.com");
        prodotto.setCondizione(1);
        prodotto.setQuantity(10);
        prodotto.setCategoria(2);
        prodotto.setImmagine("stringaUrl");

        ProductDAOModel productDAOModel = new ProductDAOModel();
        productDAOModel.updateProduct(prodotto);
        try {
            ProductBean productBean = productDAOModel.doRetrieveByKey(1001);
            assertEquals(prodotto.getNome(), productBean.getNome());
            assertEquals(prodotto.getDescrizione(), productBean.getDescrizione());
            assertNotEquals(10, productBean.getPrezzo());
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getProdottiInVenditaTest() {
        ProductDAOModel productDAOModel = new ProductDAOModel();
        assertNotNull(productDAOModel.getProdottiInVendita("mariorossi@gmail.com"));
        assertTrue(productDAOModel.getProdottiInVendita("emailnonpresente@gmail.com").isEmpty());
        assertFalse(productDAOModel.getProdottiInVendita("mariorossi@gmail.com").isEmpty());
    }

    @Test
    void diminuisciQuantityTest() {
        ProductDAOModel productDAOModel = new ProductDAOModel();
        try {
            ProductBean oldProdotto = productDAOModel.doRetrieveByKey(1001);
            int oldQuantity = oldProdotto.getQuantity();

            productDAOModel.diminuisciQuantità(1001, 1);
            ProductBean prodotto = productDAOModel.doRetrieveByKey(1001);
            int quantity = prodotto.getQuantity();

            assertEquals(9, quantity);
            assertNotEquals(oldQuantity, prodotto.getQuantity());
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void controllaCondizioneTest() {
        assertEquals("Nuovo", ProductDAOModel.controllaCondizione(0));
        assertEquals("Usato", ProductDAOModel.controllaCondizione(1));
    }

    @Test
    void controllaCategoriaTest() {
        assertEquals("Abbigliamento", ProductDAOModel.controllaCategoria(0));
        assertEquals("Elettronica", ProductDAOModel.controllaCategoria(2));
        assertEquals("Calzature", ProductDAOModel.controllaCategoria(1));
        assertEquals("Libri", ProductDAOModel.controllaCategoria(3));
        assertEquals("Giocattoli", ProductDAOModel.controllaCategoria(4));
    }

    @Test
    void parseCategoriaTest() {
        assertEquals(0, ProductDAOModel.parseCategoria("Abbigliamento"));
        assertEquals(1, ProductDAOModel.parseCategoria("Calzature"));
        assertEquals(2, ProductDAOModel.parseCategoria("Elettronica"));
        assertEquals(3, ProductDAOModel.parseCategoria("Libri"));
        assertEquals(4, ProductDAOModel.parseCategoria("Giocattoli"));
    }

    @Test
    void parseCondizioneTest() {
        assertEquals(0, ProductDAOModel.parseCondizione("Nuovo"));
        assertEquals(1, ProductDAOModel.parseCondizione("Usato"));
    }
}