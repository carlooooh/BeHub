package control.prodotto;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import model.ProductBean;
import model.ProductModel;

import java.io.IOException;

@WebServlet(name = "EliminaProdottoCatalogoControl", value = "/EliminaProdottoCatalogoControl")
public class EliminaProdottoCatalogoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codiceProdotto = Integer.parseInt(request.getParameter("codice"));
        ProductModel productModel = new ProductModel();
        try {
            ProductBean prodotto = productModel.doRetrieveByKey(codiceProdotto);
            productModel.doDelete(codiceProdotto);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp?categoria=" + prodotto.getCategoria());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
