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

@WebServlet(name = "DettagliProdottoControl", value = "/DettagliProdottoControl")
public class DettagliProdottoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempCodice = request.getParameter("codice");
        int codice = Integer.parseInt(tempCodice);
        ProductDAO productModel = new ProductDAOModel();
        try {
            ProductBean prodotto = productModel.doRetrieveByKey(codice);
            request.setAttribute("prodottoInDettaglio", prodotto);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/prodotto.jsp?codiceProdotto=" + prodotto.getCodice());
            dispatcher.forward(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/index.jsp");
        }
    }
}
