package control.ordine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderModel;

import java.io.IOException;

@WebServlet(name = "InserimentoTrackingControl", value = "/InserimentoTrackingControl")
public class InserimentoTrackingControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tracking = request.getParameter("tracking");
        int codiceOrdine = Integer.parseInt(request.getParameter("codiceOrdine"));
        OrderModel orderModel = new OrderModel();

        orderModel.inserisciTracking(tracking, codiceOrdine);

        response.sendRedirect(request.getContextPath() + "/ProdottiVendutiControl");

    }
}
