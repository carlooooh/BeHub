package control.prodotto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOInterfaces.ProductDAO;
import model.bean.ProductBean;
import model.DAOImplementation.ProductDAOModel;

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
        prodotto.setCategoria(ProductDAOModel.parseCategoria(request.getParameter("categoria")));
        prodotto.setCondizione(ProductDAOModel.parseCondizione(request.getParameter("condizioni")));
        prodotto.setImmagine(request.getParameter("immagine"));
        prodotto.setEmail((String) request.getSession().getAttribute("email"));

        //Utilizzo della classe ProductModel per la vendita
        ProductDAO productModel = new ProductDAOModel();
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
