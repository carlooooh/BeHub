package GestioneUtente.control;

import control.prodotto.ModificaProdottoControl;
import control.utente.LoginControl;
import control.utente.RegistrazioneControl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOImplementation.ProductDAOModel;
import model.DAOImplementation.UserDAOModel;
import model.bean.ProductBean;
import model.bean.UserBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RegistrazioneControlTest {

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
        RegistrazioneControl servlet = new RegistrazioneControl();
        UserBean userBean = Mockito.mock(UserBean.class);
        UserDAOModel userDAOModel = Mockito.mock(UserDAOModel.class);
        SimpleDateFormat dateFormat = Mockito.mock(SimpleDateFormat.class);

        //when
        when(request.getAttribute("email")).thenReturn("test1@gmail.com");
        when(request.getAttribute("password")).thenReturn("Test1234");
        when(request.getAttribute("nome")).thenReturn("test");
        when(request.getAttribute("cognome")).thenReturn("test");
        when(request.getAttribute("indirizzo")).thenReturn("test 1");
        when(request.getAttribute("telefono")).thenReturn("3516367622");
        when(request.getAttribute("data")).thenReturn("2023-12-12");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("http://localhost:8080/demoBeHub_war_exploded");

        //test
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        //verify
        assertEquals("test1@gmail.com", request.getAttribute("email"));
        assertEquals("Test1234", request.getAttribute("password"));
        assertEquals("test", request.getAttribute("nome"));
        assertEquals("test", request.getAttribute("cognome"));
        assertEquals("test 1", request.getAttribute("indirizzo"));
        assertEquals("3516367622", request.getAttribute("telefono"));
        assertEquals("2023-12-12", request.getAttribute("data"));

        //verifica inserimento database
        UserDAOModel model = new UserDAOModel();
        assertNotNull(model.login("test1@gmail.com", "Test1234"));
    }



}