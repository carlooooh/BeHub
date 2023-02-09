package GestioneOrdine.model;

import model.DAOImplementation.OrderDAOModel;
import model.DAOImplementation.ProductDAOModel;
import model.DAOImplementation.TicketDAOModel;
import model.DAOImplementation.UserDAOModel;
import model.bean.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOModelTest {
    @Test
    void doOrderTest(){                             /*-------------DA RIVEDERE-----------*/

        OrderBean orderBean=new OrderBean();
        OrderDAOModel orderDAOModel=new OrderDAOModel();
        UserBean userBean=new UserBean();
        userBean.setEmail("antoniobianchi@gmail.com");
        CartBean cartBean=new CartBean();
        ProductBean productBean = new ProductBean();
        productBean.setCodice(1000);
        productBean.setPrezzo(10);
        productBean.setSpedizione(2);
        productBean.setQuantity(1);
        cartBean.setCarrello(productBean);

        assertTrue(orderDAOModel.doOrder(cartBean,userBean));

        Collection<OrderBean> collection = orderDAOModel.getOrdini("antoniobianchi@gmail.com");
        Iterator<OrderBean> iterator = collection.iterator();
        OrderBean ordinetemp = iterator.next();
        OrderBean ordine = iterator.next();

        assertEquals(105, ordine.getCodice());
        assertEquals("antoniobianchi@gmail.com", ordine.getEmail());
    }

    @Test
    void doOrderProdottoTest(){                     /*--------DA RIVEDERE-------------*/
        OrderBean orderBean=new OrderBean();
        OrderDAOModel orderDAOModel=new OrderDAOModel();
        UserBean userBean=new UserBean();
        userBean.setEmail("antoniobianchi@gmail.com");
        ProductBean productBean = new ProductBean();
        productBean.setCodice(1000);
        productBean.setPrezzo(10);
        productBean.setSpedizione(2);
        productBean.setQuantity(1);

        assertTrue(orderDAOModel.doOrder(productBean,userBean));

        Collection<OrderBean> collection = orderDAOModel.getOrdini("antoniobianchi@gmail.com");
        Iterator<OrderBean> iterator = collection.iterator();
        OrderBean ordine = iterator.next();

        assertEquals(104, ordine.getCodice());
        assertEquals("antoniobianchi@gmail.com", ordine.getEmail());

    }

    @Test
    void getOrdiniTest(){
        Collection<OrderBean> collection=new LinkedList<>();
        OrderDAOModel ticketDAOModel=new OrderDAOModel();
        collection=ticketDAOModel.getOrdini("luigiverdi@gmail.com");

        assertNotEquals(0, collection.size());
    }

    @Test
    void getProdottiVenduti(){
        Collection<OrderBean> collection=new LinkedList<>();
        OrderDAOModel ticketDAOModel=new OrderDAOModel();
        collection=ticketDAOModel.getProdottiVenduti("mariorossi@gmail.com");

        assertNotEquals(0, collection.size());
    }

    @Test
    void insertTrackingTest(){
        OrderBean orderBean=new OrderBean();
        OrderDAOModel orderDAOModel=new OrderDAOModel();
        String tracking="daojdaiow";
        int codiceOrdine=100;

        assertTrue(orderDAOModel.inserisciTracking(tracking,codiceOrdine));
    }


}
