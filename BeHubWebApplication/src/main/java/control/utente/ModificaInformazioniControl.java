package control.utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserBean;
import model.UserModel;

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
        UserModel userModel = new UserModel();
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
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/account.jsp?update=success");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/account.jsp?update=failure");
            dispatcher.forward(request, response);
        }
    }
}
