package control.prodotto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOInterfaces.ProductDAO;
import model.bean.ProductBean;
import model.DAOImplementation.ProductDAOModel;

import java.io.IOException;

@WebServlet(name = "ProdottoDaModificareControl", value = "/ProdottoDaModificareControl")
public class ProdottoDaModificareControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductBean prodotto;
        ProductDAO productModel = new ProductDAOModel();
        try {
            prodotto = productModel.doRetrieveByKey(Integer.parseInt(request.getParameter("codice")));
            request.setAttribute("updateProd", prodotto);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/modifica-prodotto.jsp");
        dispatcher.forward(request, response);
    }
}
