package control.utente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOInterfaces.UserDAO;
import model.bean.UserBean;
import model.DAOImplementation.UserDAOModel;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "ModificaInformazioniControl", value = "/ModificaInformazioniControl")
public class ModificaInformazioniControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Smistamento lista ordini
        if (request.getParameter("ordini") != null) {
            String email = (String) request.getSession().getAttribute("email");
            response.sendRedirect(request.getContextPath() + "/ListaOrdiniUtenteControl?emailOrdini=" + email);
        }
        //Smistamento lista ticket
        else if (request.getParameter("ticket") != null) {
            response.sendRedirect(request.getContextPath() + "/ListaTicketControl");
        }
        //Smistamento lista prodotti venduti
        else if (request.getParameter("prodottiVenduti") != null) {
            response.sendRedirect(request.getContextPath() + "/ProdottiVendutiControl");
        }
        //Smistamento lista prodotti in vendita
        else if (request.getParameter("prodottiInVendita") != null) {
            response.sendRedirect(request.getContextPath() + "/ProdottiInVenditaControl");
        }
        else {
            UserDAO userModel = new UserDAOModel();
            String oldEmail = (String) request.getSession().getAttribute("email");
            UserBean utente = new UserBean();
            String newEmail = request.getParameter("email");

            utente.setEmail(newEmail);
            utente.setNome(request.getParameter("nome"));
            utente.setCognome(request.getParameter("cognome"));
            utente.setIndirizzo(request.getParameter("indirizzo"));
            utente.setTelefono(request.getParameter("telefono"));
            String data = request.getParameter("dataNascita");
            Date dataNascita = Date.valueOf(data);
            utente.setData(dataNascita);
            if (request.getParameter("intestatario") != null) {
                utente.setIntestatario(request.getParameter("intestatario"));
            }
            if (request.getParameter("carta") != null) {
                utente.setNumero(request.getParameter("carta"));
            }

            Boolean control = userModel.update(utente, oldEmail); //aggiornamento dell'utente
            request.getSession().setAttribute("email", newEmail); //aggiornamento email nella sessione
            request.getSession().setAttribute("utente", utente); //aggiornamento utente nella sessione

            if (control == true) {
                response.sendRedirect(request.getContextPath() + "/account.jsp?update=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/account.jsp?update=failure");
            }
        }
    }
}
