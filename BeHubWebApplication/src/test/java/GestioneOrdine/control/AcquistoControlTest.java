package GestioneOrdine.control;

import control.ordine.AcquistoControl;
import control.prodotto.ModificaProdottoControl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOImplementation.OrderDAOModel;
import model.DAOImplementation.ProductDAOModel;
import model.bean.CartBean;
import model.bean.OrderBean;
import model.bean.ProductBean;
import model.utils.DriverManagerConnectionPool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AcquistoControlTest {

  
}