package model;


import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class UserModel {
    public synchronized Collection<String> retrieveAllEmail() {
        Collection<String> collection = new LinkedList<String>();
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "SELECT email FROM Utente";

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                collection.add(rs.getString(1));
            }
            return collection;
        }
        catch (Exception e) {
            return collection;
        }
        finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    /*
    Metodo che modifica le informazioni di un account
     */
    public synchronized UserBean update(UserBean bean, String emailOld) { //prima insert
        String email = bean.getEmail();
        String nome = bean.getNome();
        String cognome = bean.getCognome();
        String indirizzo = bean.getIndirizzo();
        String telefono = bean.getTelefono();
        String carta = bean.getNumero();
        String intestatario = bean.getIntestatario();
        bean.setRole("RU");
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String sql = "UPDATE Utente SET email = ?, nome = ?, cognome = ?, indirizzo = ?, telefono = ?, numero = ?, intestatario = ? WHERE email = ?";

            //Modifica UserAccount
            String oldEmail = emailOld;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, nome);
            ps.setString(3, cognome);
            ps.setString(4, indirizzo);
            ps.setString(5, telefono);
            ps.setString(6, carta);
            ps.setString(7, intestatario);
            ps.setString(8, oldEmail);

            ps.executeUpdate();
            con.commit();
            DriverManagerConnectionPool.releaseConnection(con);
            return bean;
        }
        catch (Exception e) {
            e.printStackTrace();
            return bean;
        }
    }

    public synchronized boolean insert(UserBean user, String psw) {
        String email = user.getEmail();
        String password = psw;
        String nome = user.getNome();
        String cognome = user.getCognome();
        String indirizzo = user.getIndirizzo();
        String telefono = user.getTelefono();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String sql = "INSERT INTO Utente(email, passwordUser, nome, cognome, indirizzo, telefono) VALUES (?, SHA2(?, 256), ?, ?, ?, ?)";

            //Aggiungi a UserAccount
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, nome);
            ps.setString(4, cognome);
            ps.setString(5, indirizzo);
            ps.setString(6, telefono);

            ps.executeUpdate();
            con.commit();

            DriverManagerConnectionPool.releaseConnection(con);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    Metodo per il login che ritorna un UserBean se ha successo, null altrimenti
     */
    public synchronized UserBean login(String email, String password) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String sql = "SELECT email, passwordUser, ruolo, nome, cognome, indirizzo, telefono, numeroCarta, intestatario FROM Utente";

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString(1));
                if (email.compareTo(rs.getString(1)) == 0) {
                    String psw = DigestUtils.sha256Hex(password);
                    System.out.println(rs.getString(2));
                    System.out.println(psw);
                    if (psw.compareTo(rs.getString(2)) == 0) {
                        UserBean registeredUser = new UserBean();
                        registeredUser.setEmail(rs.getString(1));
                        registeredUser.setNome(rs.getString(4));
                        registeredUser.setCognome(rs.getString(5));
                        registeredUser.setIndirizzo(rs.getString(6));
                        registeredUser.setTelefono(rs.getString(7));
                        registeredUser.setNumero(rs.getString(8));
                        registeredUser.setIntestatario(rs.getString(9));
                        registeredUser.setRole(rs.getString(3));
                        /*
                        request.getSession().setAttribute("registeredUser", registeredUser);
                        request.getSession().setAttribute("role", registeredUser.getRole());
                        request.getSession().setAttribute("email", rs.getString(1));
                        request.getSession().setAttribute("nome", rs.getString(6));

                        /*
                        OrderModel model = new OrderModel();
                        request.getSession().setAttribute("listaOrdini", model.getOrders(rs.getString(1)));
                        */
                        DriverManagerConnectionPool.releaseConnection(con);
                        return registeredUser;
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
