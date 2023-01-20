package control.utente;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import model.UserBean;
import model.UserModel;

import java.io.IOException;

@WebServlet(name = "LoginControl", value = "/LoginControl")
public class LoginControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel userModel = new UserModel();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("ciao");
        UserBean user = userModel.login(email, password);

        //Login avvenuto con successo
        if (user != null) {
            request.getSession().setAttribute("utente", user);
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("ruolo", user.getRole());
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
        //Login non avvenuto con successo
        else {
            response.sendRedirect(request.getContextPath() + "/accesso.jsp?login-error=true");
        }
    }
}
