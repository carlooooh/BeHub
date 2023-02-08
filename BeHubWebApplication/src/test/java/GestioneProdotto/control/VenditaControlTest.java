package GestioneProdotto.control;

import control.prodotto.ModificaProdottoControl;
import control.prodotto.VenditaControl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.DAOImplementation.ProductDAOModel;
import model.bean.ProductBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

class VenditaControlTest {

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
        VenditaControl servlet = new VenditaControl();
        ProductBean productBean = Mockito.mock(ProductBean.class);
        ProductDAOModel productDAOModel = Mockito.spy(ProductDAOModel.class);
        Part part = Mockito.mock(Part.class);
        Collection<Part> collection = Mockito.mock(Collection.class);
        List<Part> list = new ArrayList<>();
        list.add(part);

        //Prodotto test da inserire
        ProductBean prodotto = new ProductBean();
        prodotto.setNome("PS5");
        prodotto.setDescrizione("PS5 nuova");
        prodotto.setPrezzo(550);
        prodotto.setSpedizione(0);
        prodotto.setQuantity(1);
        prodotto.setCategoria(2);
        prodotto.setCondizione(0);
        prodotto.setImmagine("url");
        prodotto.setEmail("mariorossi@gmail.com");

        //when
        when(request.getSession()).thenReturn(session);
        when(request.getParts()).thenReturn(list);
        when(session.getAttribute("email")).thenReturn("mariorossi@gmail.com");
        when(request.getContextPath()).thenReturn("http://localhost:8080/demoBeHub_war_exploded");
        when(request.getAttribute("prodottoVenduto")).thenReturn(prodotto);

        //test
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        //verify
        ProductDAOModel model = new ProductDAOModel();
        try {
            String nome = model.doRetrieveByKey(1004).getNome();
            assertEquals("PS5", nome);
            String descrizione = model.doRetrieveByKey(1004).getDescrizione();
            assertEquals("PS5 nuova", descrizione);
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        }
    }
}