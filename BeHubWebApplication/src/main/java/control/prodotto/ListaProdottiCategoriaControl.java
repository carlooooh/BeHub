package control.prodotto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductModel;

import java.io.IOException;

@WebServlet(name = "ListaProdottiCategoriaControl", value = "/ListaProdottiCategoriaControl")
public class ListaProdottiCategoriaControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("categoria");
        ProductModel productModel = new ProductModel();
        try {
            request.setAttribute("products", productModel.doRetrieveAll(categoria)); //lista prodotti della categoria
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp?categoria=" + categoria);
            dispatcher.forward(request, response);
        }
    }
}
