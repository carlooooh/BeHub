package GestioneUtente.model;

import model.DAOImplementation.UserDAOModel;
import model.bean.UserBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOModelTest {

    @Test
    void updateTest() {
        UserBean user = new UserBean();
        UserDAOModel usertest = new UserDAOModel();

        user.setNome("test");
        user.setEmail("test1@gmail.com");
        user.setCognome("test");
        user.setIndirizzo("test 1, test");
        user.setTelefono("test1234");
        user.setNumero("test1234");
        String test = "2001-12-12";
        Date data = Date.valueOf(test);
        user.setData(data);

        assertTrue(usertest.update(user, "luigiverdi@gmail.com"));
        assertNotNull(usertest.login("test1@gmail.com", "Test1234"));

    }


    @Test
    void insertTest(){
        UserDAOModel userDAOModel = new UserDAOModel();
        UserBean user = new UserBean();

        user.setNome("test");
        user.setEmail("test2@gmail.com");
        user.setCognome("test");
        user.setIndirizzo("test 1, test");
        user.setTelefono("test1234");
        user.setNumero("test1234");
        String test = "2001-12-12";
        Date data = Date.valueOf(test);
        user.setData(data);


        assertTrue(userDAOModel.insert(user, "test1"));
        assertNotNull(userDAOModel.login("test2@gmail.com", "test1"));

    }

    @Test
    void loginTest(){
        UserDAOModel userDAOModel = new UserDAOModel();

        assertNotNull(userDAOModel.login("mariorossi@gmail.com", "Test1234"));
        assertNull(userDAOModel.login("mariorossigmail.com", "Test1234"));
        assertNull(userDAOModel.login("mariorossi@gmail.com", "Test234"));

    }

}