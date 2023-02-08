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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RegistrazioneControl", value = "/RegistrazioneControl")
public class RegistrazioneControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userModel = new UserDAOModel();

        //Creazione UserBean
        UserBean user = new UserBean();
        String password = (String) request.getAttribute("password");
        user.setEmail((String) request.getAttribute("email"));
        user.setNome((String) request.getAttribute("nome"));
        user.setCognome((String) request.getAttribute("cognome"));
        user.setIndirizzo((String) request.getAttribute("indirizzo"));
        user.setTelefono((String) request.getAttribute("telefono"));
        String data = (String) request.getAttribute("data");
        try {
            Date tempdata = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            java.sql.Date veraData = new java.sql.Date(tempdata.getTime());
            user.setData(veraData);
        } catch (ParseException e) {
           e.printStackTrace();
        }

        //Inserimento UserBean nel database
        Boolean control = userModel.insert(user, password);

        //Registrazione avvenuta con successo
        if (control == true) {
            response.sendRedirect(request.getContextPath() + "/accesso.jsp?registrazione-error=false");
        }
        //Registrazione fallita
        else {
            response.sendRedirect(request.getContextPath() + "/accesso.jsp?registrazione-error=true");
        }
    }
}
