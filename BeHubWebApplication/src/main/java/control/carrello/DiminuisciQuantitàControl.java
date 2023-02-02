package control.carrello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.CartBean;

import java.io.IOException;

@WebServlet(name = "DiminuisciQuantitàControl", value = "/DiminuisciQuantitàControl")
public class DiminuisciQuantitàControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempCodice = request.getParameter("dimCodice");
        int codice = Integer.parseInt(tempCodice);
        CartBean cart = (CartBean) request.getSession().getAttribute("carrello");
        if (cart.retrieveByKey(codice).getQuantity() == 1)  { //Se la quantità è uguale a 1, il prodotto viene rimosso
            cart.removeItem(codice);
        }
        else {
            cart.decreaseQuantity(codice); //altrimenti viene solo decrementata la quantità
        }
        request.getSession().setAttribute("carrello", cart);
        request.getRequestDispatcher("/carrello.jsp").forward(request, response);
    }
}
