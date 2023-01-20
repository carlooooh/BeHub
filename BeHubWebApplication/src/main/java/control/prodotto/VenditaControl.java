package control.prodotto;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import model.ProductBean;
import model.ProductModel;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VenditaControl", value = "/VenditaControl")
public class VenditaControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Creazione ProductBean da salvare
        ProductBean prodotto = new ProductBean();
        prodotto.setNome(request.getParameter("nome"));
        prodotto.setDescrizione(request.getParameter("descrizione"));
        prodotto.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        prodotto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        prodotto.setSpedizione(Double.parseDouble(request.getParameter("spedizione")));
        prodotto.setCategoria(ProductModel.parseCategoria(request.getParameter("categoria")));
        prodotto.setCondizione(ProductModel.parseCondizione(request.getParameter("condizioni")));
        System.out.println(request.getParameter("immagine"));
        prodotto.setImmagine(request.getParameter("immagine"));
        prodotto.setEmail((String) request.getSession().getAttribute("email"));

        //Utilizzo della classe ProductModel per la vendita
        ProductModel productModel = new ProductModel();
        try {
            productModel.doSave(prodotto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            String redirectedPage = "/index.jsp";
            response.sendRedirect(request.getContextPath() + redirectedPage);
        }
    }
}
