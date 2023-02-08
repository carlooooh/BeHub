package GestioneOrdine.model;

import model.DAOImplementation.OrderDAOModel;
import model.DAOImplementation.ProductDAOModel;
import model.DAOImplementation.TicketDAOModel;
import model.DAOImplementation.UserDAOModel;
import model.bean.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOModelTest {





    @Test
    void doOrderTest(){                             /*-------------DA RIVEDERE-----------*/

        OrderBean orderBean=new OrderBean();
        OrderDAOModel orderDAOModel=new OrderDAOModel();
        UserBean userBean=new UserBean();
        CartBean cartBean=new CartBean();

        assertTrue(orderDAOModel.doOrder(cartBean,userBean));

    }

    @Test
    void doOrderProdottoTest(){                     /*--------DA RIVEDERE-------------*/
        OrderBean orderBean=new OrderBean();
        ProductBean productBean=new ProductBean();
        ProductDAOModel productDAOModel=new ProductDAOModel();
        UserBean userBean=new UserBean();
        OrderDAOModel orderDAOModel=new OrderDAOModel();



        assertEquals(productBean.getCodice(), orderBean.getCodice());

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
