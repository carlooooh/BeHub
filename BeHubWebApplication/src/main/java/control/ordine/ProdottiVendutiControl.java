package control.ordine;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderBean;
import model.OrderModel;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "ProdottiVendutiControl", value = "/ProdottiVendutiControl")
public class ProdottiVendutiControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       OrderModel orderModel = new OrderModel();
       String email = (String) request.getSession().getAttribute("email");
       Collection<OrderBean> listaProdottiVenduti = orderModel.getProdottiVenduti(email); //ottenimento lista prodotti venduti

       request.getSession().setAttribute("listaProdottiVenduti", listaProdottiVenduti);
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/prodotti-venduti.jsp");
       dispatcher.forward(request, response);
    }
}
