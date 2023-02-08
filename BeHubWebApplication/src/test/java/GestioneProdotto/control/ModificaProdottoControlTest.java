package GestioneProdotto.control;

import control.prodotto.ModificaProdottoControl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOImplementation.ProductDAOModel;
import model.bean.ProductBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ModificaProdottoControlTest {
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
        ModificaProdottoControl servlet = new ModificaProdottoControl();
        ProductBean productBean = Mockito.mock(ProductBean.class);
        ProductDAOModel productDAOModel = Mockito.mock(ProductDAOModel.class);

        //when
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("codice")).thenReturn("1000");
        when(request.getParameter("nome")).thenReturn("Divina Commedia");
        when(request.getParameter("descrizione")).thenReturn("divina commedia nuovo");
        when(request.getParameter("prezzo")).thenReturn("50");
        when(request.getParameter("spedizione")).thenReturn("10");
        when(request.getParameter("condizione")).thenReturn("Nuovo");
        when(request.getParameter("categoria")).thenReturn("Libri");
        when(request.getParameter("quantity")).thenReturn("20");
        when(request.getContextPath()).thenReturn("http://localhost:8080/demoBeHub_war_exploded");

        //test
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        //verify
        assertEquals("1000", request.getParameter("codice"));
        assertEquals("Divina Commedia", request.getParameter("nome"));
        assertEquals("divina commedia nuovo", request.getParameter("descrizione"));
        assertEquals("50", request.getParameter("prezzo"));
        assertEquals("10", request.getParameter("spedizione"));
        assertEquals("Nuovo", request.getParameter("condizione"));
        assertEquals("Libri", request.getParameter("categoria"));
        assertEquals("20", request.getParameter("quantity"));

        //verifica modifica database
        ProductDAOModel model = new ProductDAOModel();
        try {
            String nome = model.doRetrieveByKey(1000).getNome();
            assertEquals("Divina Commedia", nome);

        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
}