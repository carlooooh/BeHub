package GestioneUtente.control;

import control.utente.LoginControl;
import control.utente.ModificaInformazioniControl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOImplementation.UserDAOModel;
import model.DAOInterfaces.UserDAO;
import model.bean.UserBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ModificaInformazioniControlTest {

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
        ModificaInformazioniControl servlet = new ModificaInformazioniControl();
        UserBean userBean = Mockito.mock(UserBean.class);
        UserDAOModel userDAOModel = Mockito.mock(UserDAOModel.class);

        //when
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("luigiverdi@gmail.com");
        when(request.getParameter("email")).thenReturn("luigiverdiNEW@gmail.com");
        when(request.getParameter("nome")).thenReturn("LuigiNEW");
        when(request.getParameter("cognome")).thenReturn("VerdiNEW");
        when(request.getParameter("indirizzo")).thenReturn("via test 1");
        when(request.getParameter("telefono")).thenReturn("1231231231");
        when(request.getParameter("dataNascita")).thenReturn("2022-12-12");
        when(request.getContextPath()).thenReturn("http://localhost:8080/demoBeHub_war_exploded");

        //test
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        //verify
        assertEquals("luigiverdi@gmail.com", session.getAttribute("email"));
        assertEquals("luigiverdiNEW@gmail.com", request.getParameter("email"));
        assertEquals("LuigiNEW", request.getParameter("nome"));
        assertEquals("VerdiNEW", request.getParameter("cognome"));
        assertEquals("via test 1", request.getParameter("indirizzo"));
        assertEquals("1231231231", request.getParameter("telefono"));
        assertEquals("2022-12-12", request.getParameter("dataNascita"));

        //verifica modifica database
        UserDAOModel model = new UserDAOModel();
        UserBean user = model.login("luigiverdiNEW@gmail.com", "Test1234");

        assertEquals("luigiverdiNEW@gmail.com", user.getEmail());
        assertEquals("LuigiNEW", user.getNome());
        assertEquals("VerdiNEW", user.getCognome());
        assertEquals("via test 1", user.getIndirizzo());
        assertEquals("1231231231", user.getTelefono());
    }
}