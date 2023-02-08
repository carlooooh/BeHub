package GestioneUtente.control;


import control.utente.LoginControl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.DAOImplementation.UserDAOModel;

import model.bean.UserBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LoginControlTest {
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
        LoginControl servlet = new LoginControl();
        UserBean userBean = Mockito.mock(UserBean.class);
        UserDAOModel userDAOModel = Mockito.mock(UserDAOModel.class);

        //when
        when(request.getParameter("email")).thenReturn("mariorossi@gmail.com");
        when(request.getParameter("password")).thenReturn("Test1234");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("http://localhost:8080/demoBeHub_war_exploded");

        //test
        servlet.init(servletConfig);
        servlet.doPost(request, response);

        //verify
        assertEquals("mariorossi@gmail.com", request.getParameter("email"));
        assertEquals("Test1234", request.getParameter("password"));
        assertFalse(Boolean.parseBoolean(request.getParameter("login-error")));
    }



}