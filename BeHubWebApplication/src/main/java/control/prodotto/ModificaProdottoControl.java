package control.prodotto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartBean;
import model.CartModel;
import model.ProductBean;
import model.ProductModel;

import java.io.IOException;

@WebServlet(name = "ModificaProdottoControl", value = "/ModificaProdottoControl")
public class ModificaProdottoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductBean prodotto = new ProductBean();
        prodotto.setCodice(Integer.parseInt(request.getParameter("codice")));
        prodotto.setNome(request.getParameter("nome"));
        prodotto.setDescrizione(request.getParameter("descrizione"));
        prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        prodotto.setSpedizione(Double.parseDouble(request.getParameter("spedizione")));
        prodotto.setCondizione(ProductModel.parseCondizione(request.getParameter("condizione")));
        prodotto.setCategoria(ProductModel.parseCategoria(request.getParameter("categoria")));
        prodotto.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        ProductModel productModel = new ProductModel();
        productModel.updateProduct(prodotto); //Modifica prodotto

        //Aggiornamento prodotto nel carrello
        if (request.getSession().getAttribute("carrello") != null) {
            CartModel cartModel = new CartModel();
            CartBean carrello = cartModel.updateCarrello(prodotto, (CartBean) request.getSession().getAttribute("carrello"));
            request.getSession().setAttribute("carrello", carrello);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
