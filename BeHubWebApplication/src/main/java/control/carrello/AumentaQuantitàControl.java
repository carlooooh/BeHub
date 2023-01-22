package control.carrello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartBean;

import java.io.IOException;

@WebServlet(name = "AumentaQuantitàControl", value = "/AumentaQuantitàControl")
public class AumentaQuantitàControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempCodice = request.getParameter("aumCodice");
        int codice = Integer.parseInt(tempCodice);
        CartBean cart = (CartBean) request.getSession().getAttribute("carrello");
        if (cart.retrieveByKey(codice).getQuantity() != cart.retrieveByKey(codice).getMaxQuantity())  {  //maxQuantity è la quantità massima del prodotto
            cart.retrieveByKey(codice).addQuantity();
        }
        request.getSession().setAttribute("carrello", cart);
        request.getRequestDispatcher("/carrello.jsp").forward(request, response);
    }
}
