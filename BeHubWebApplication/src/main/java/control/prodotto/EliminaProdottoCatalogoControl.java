package control.prodotto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartBean;
import model.ProductBean;
import model.ProductModel;

import java.io.IOException;
import java.util.Collection;

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
        CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");
        Collection<ProductBean> listaProdottiCarrello = carrello.getCarrello();

        try {
            ProductBean prodotto = productModel.doRetrieveByKey(codiceProdotto);
            listaProdottiCarrello = productModel.deleteProduct(codiceProdotto, listaProdottiCarrello);
            carrello.setLista(listaProdottiCarrello);
            request.getSession().setAttribute("carrello", carrello);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListaProdottiCategoriaControl?categoria=" + ProductModel.controllaCategoria(prodotto.getCategoria()));
            dispatcher.forward(request, response);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
