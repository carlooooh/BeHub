package control.prodotto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOInterfaces.CartDAO;
import model.DAOInterfaces.ProductDAO;
import model.bean.CartBean;
import model.DAOImplementation.CartDAOModel;
import model.bean.ProductBean;
import model.DAOImplementation.ProductDAOModel;

import java.io.IOException;

@WebServlet(name = "ModificaProdottoControl", value = "/ModificaProdottoControl")
public class ModificaProdottoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductBean prodotto = new ProductBean();
        prodotto.setCodice(Integer.parseInt(request.getParameter("codice")));
        prodotto.setNome(request.getParameter("nome"));
        prodotto.setDescrizione(request.getParameter("descrizione"));
        prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        prodotto.setSpedizione(Double.parseDouble(request.getParameter("spedizione")));
        prodotto.setCondizione(ProductDAOModel.parseCondizione(request.getParameter("condizione")));
        prodotto.setCategoria(ProductDAOModel.parseCategoria(request.getParameter("categoria")));
        prodotto.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        ProductDAO productModel = new ProductDAOModel();
        productModel.updateProduct(prodotto); //Modifica prodotto

        //Aggiornamento prodotto nel carrello
        if (request.getSession().getAttribute("carrello") != null) {
            CartDAO cartModel = new CartDAOModel();
            CartBean carrello = cartModel.updateCarrello(prodotto, (CartBean) request.getSession().getAttribute("carrello"));
            request.getSession().setAttribute("carrello", carrello);
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
