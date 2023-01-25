package control.amministrazione;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "VisualizzazioneInformazioniUtenteControl", value = "/VisualizzazioneInformazioniUtenteControl")
public class VisualizzazioneInformazioniUtenteControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Controllo ordini
        String email = request.getParameter("email");
        if (request.getParameter("ordiniUtente") != null) {
            response.sendRedirect(request.getContextPath() + "/ListaOrdiniUtenteControl?emailOrdini=" + email);
        }
        //Controllo prodotti venduti
        else if (request.getParameter("prodottiVenduti") != null) {
            response.sendRedirect(request.getContextPath() + "/ProdottiVendutiControl?admin=true&emailUtente=" + email);
        }
        //Controllo prodotti in vendita
        else if (request.getParameter("prodottiInVendita") != null) {
            response.sendRedirect(request.getContextPath() + "/ProdottiInVenditaControl?admin=true&emailUtente=" + email);
        }
        //Controllo ticket aperti
        else if (request.getParameter("ticket") != null) {
            response.sendRedirect(request.getContextPath() + "/VisualizzazioneTicketUtentiControl?email=" + email);
        }
    }
}
