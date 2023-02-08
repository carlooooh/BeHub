package GestioneTicket.model;

import model.DAOImplementation.TicketDAOModel;
import model.bean.TicketBean;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TicketDAOModelTest {
    @Test
    void aggiuntaTicketTest(){

        TicketBean ticket=new TicketBean();
        ticket.setOggetto("Supporto");
        ticket.setTesto("Mi serve aiuto");
        ticket.setEmailUtente("antoniobianchi@gmail.com");
        ticket.setStato(1);
        TicketDAOModel ticketDAOModel = new TicketDAOModel();

        assertTrue(ticketDAOModel.aggiungiTicket(ticket));
    }

    @Test
    void getListaTicketTest(){

        Collection<TicketBean> collection=new LinkedList<>();
        TicketDAOModel ticketDAOModel=new TicketDAOModel();
        collection=ticketDAOModel.getListaTicket("mariorossi@gmail.com");

        assertNotEquals(0, collection.size());

    }

    @Test
    void chiudiTicketTest(){
        TicketBean ticket=new TicketBean();
        ticket.setStato(1);
        TicketDAOModel ticketDAOModel=new TicketDAOModel();

        assertEquals(1, ticket.getStato());
    }


    @Test
    void getListaTicketApertiTest(){
        Collection<TicketBean> collection=new LinkedList<>();
        TicketDAOModel ticketDAOModel=new TicketDAOModel();
        collection=ticketDAOModel.getListaTicket("mariorossi@gmail.com");

        assertNotEquals(0, collection.size());


        Collection<TicketBean> collection2=new LinkedList<>();
        TicketDAOModel ticketDAOModel2=new TicketDAOModel();
        collection=ticketDAOModel.getListaTicket("luigiverdi@gmail.com");

            assertEquals(0, collection2.size());

    }



    @Test
    void retrieveByKeyTest(){
        TicketBean ticket=new TicketBean();
        TicketDAOModel ticketDAOModel=new TicketDAOModel();
        ticket=ticketDAOModel.retrieveByKey(10);
        assertEquals("Richiesta rimborso De Monarchia", ticket.getOggetto());
    }




}