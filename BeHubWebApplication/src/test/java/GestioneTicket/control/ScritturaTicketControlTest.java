package GestioneTicket.control;

import control.ordine.AcquistoControl;
import control.ticket.ScritturaTicketControl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOImplementation.OrderDAOModel;
import model.DAOImplementation.TicketDAOModel;
import model.bean.CartBean;
import model.bean.OrderBean;
import model.bean.TicketBean;
import model.bean.UserBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ScritturaTicketControlTest {

    @Test
    void doPostTest() throws ServletException, IOException {
        //mock
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        ServletConfig servletConfig = Mockito.mock(ServletConfig.class);
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        Connection connection = Mockito.mock(Connection.class);
        DriverManagerConnectionPool driverManagerConnectionPool = Mockito.mock(DriverManagerConnectionPool.class);
        ScritturaTicketControl servlet = new ScritturaTicketControl();
        TicketBean ticketBean = Mockito.mock(TicketBean.class);
        TicketDAOModel ticketDAOModel = Mockito.mock(TicketDAOModel.class);

        //when
        when(request.getParameter("oggetto")).thenReturn("Oggetto test");
        when(request.getParameter("msg")).thenReturn("Messaggio test");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("luigiverdi@gmail.com");

        //test
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        //verify
        assertEquals("Oggetto test", request.getParameter("oggetto"));
        assertEquals("Messaggio test", request.getParameter("msg"));
        assertEquals("luigiverdi@gmail.com", session.getAttribute("email"));

        //verifica inserimento database
        TicketDAOModel model = new TicketDAOModel();
        TicketBean ticket = model.retrieveByKey(12);
        assertEquals("Oggetto test", ticket.getOggetto());
        assertEquals("Messaggio test", ticket.getTesto());
        assertEquals("luigiverdi@gmail.com", ticket.getEmailUtente());
        assertEquals(0, ticket.getStato());
    }
}