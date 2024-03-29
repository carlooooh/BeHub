package GestioneOrdine.control;

import control.ordine.AcquistoControl;
import control.prodotto.ModificaProdottoControl;
import control.utente.ModificaInformazioniControl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOImplementation.OrderDAOModel;
import model.DAOImplementation.ProductDAOModel;
import model.DAOImplementation.UserDAOModel;
import model.bean.CartBean;
import model.bean.OrderBean;
import model.bean.ProductBean;
import model.bean.UserBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AcquistoControlTest {
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
        AcquistoControl servlet = new AcquistoControl();
        OrderBean orderBean = Mockito.mock(OrderBean.class);
        OrderDAOModel orderDAOModel = Mockito.mock(OrderDAOModel.class);
        CartBean cartBean = Mockito.mock(CartBean.class);
        UserBean userBean = Mockito.mock(UserBean.class);

        //when
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("carrello")).thenReturn(cartBean);
        when(session.getAttribute("utente")).thenReturn(userBean);
        when(session.getAttribute("email")).thenReturn("mariorossi@gmail.com");
        when(request.getContextPath()).thenReturn("http://localhost:8080/demoBeHub_war_exploded");

        //test
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        //verify
        assertEquals("mariorossi@gmail.com", session.getAttribute("email"));
        assertNotEquals("failure", request.getParameter("acquisto"));
    }
  
}