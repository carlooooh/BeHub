package control.prodotto;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
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
            request.setAttribute("products", productModel.doRetrieveAll(categoria));
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
