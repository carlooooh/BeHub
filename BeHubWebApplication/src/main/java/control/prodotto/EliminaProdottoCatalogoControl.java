package control.prodotto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOInterfaces.ProductDAO;
import model.bean.CartBean;
import model.bean.ProductBean;
import model.DAOImplementation.ProductDAOModel;

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
        ProductDAO productModel = new ProductDAOModel();
        CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");
        Collection<ProductBean> listaProdottiCarrello = carrello.getCarrello();

        try {
            ProductBean prodotto = productModel.doRetrieveByKey(codiceProdotto);
            listaProdottiCarrello = productModel.deleteProduct(codiceProdotto, listaProdottiCarrello);
            carrello.setLista(listaProdottiCarrello);
            request.getSession().setAttribute("carrello", carrello);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListaProdottiCategoriaControl?categoria=" + ProductDAOModel.controllaCategoria(prodotto.getCategoria()));
            dispatcher.forward(request, response);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
