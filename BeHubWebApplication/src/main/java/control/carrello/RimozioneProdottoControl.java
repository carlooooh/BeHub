package control.carrello;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.CartBean;

import java.io.IOException;

@WebServlet(name = "RimozioneProdottoControl", value = "/RimozioneProdottoControl")
public class RimozioneProdottoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tempCodice = request.getParameter("codice");
        int codice = Integer.parseInt(tempCodice);
        CartBean cart = (CartBean) request.getSession().getAttribute("carrello");

        if (!cart.isEmpty()) { //se il carrello non è vuoto, rimuove il prodotto dal carrello
            cart.removeItem(codice);
        }

        request.getSession().setAttribute("carrello", cart);
        request.getRequestDispatcher("/carrello.jsp").forward(request, response);
    }
}
