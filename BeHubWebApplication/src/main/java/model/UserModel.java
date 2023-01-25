package model;


import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class UserModel {

    /*
    Metodo che modifica le informazioni di un account
     */
    public synchronized boolean update(UserBean bean, String emailOld) {
        String email = bean.getEmail();
        String nome = bean.getNome();
        String cognome = bean.getCognome();
        String indirizzo = bean.getIndirizzo();
        String telefono = bean.getTelefono();
        String carta = bean.getNumero();
        String intestatario = bean.getIntestatario();
        Date data = bean.getData();
        bean.setRole("RU");
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String sql = "UPDATE Utente SET email = ?, nome = ?, cognome = ?, indirizzo = ?, telefono = ?, numeroCarta = ?, intestatario = ?, dataNascita = ? WHERE email = ?";

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
            ps.setDate(8, data);
            ps.setString(9, oldEmail);

            ps.executeUpdate();
            con.commit();
            DriverManagerConnectionPool.releaseConnection(con);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /*
    * Inserisce un nuovo UserBean nel database
    * */
    public synchronized boolean insert(UserBean user, String psw) {
        String email = user.getEmail();
        String password = psw;
        String nome = user.getNome();
        String cognome = user.getCognome();
        String indirizzo = user.getIndirizzo();
        String telefono = user.getTelefono();
        Date data = user.getData();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String sql = "INSERT INTO Utente(email, passwordUser, nome, cognome, indirizzo, telefono, dataNascita) VALUES (?, SHA2(?, 256), ?, ?, ?, ?, ?)";

            //Aggiungi a UserAccount
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, nome);
            ps.setString(4, cognome);
            ps.setString(5, indirizzo);
            ps.setString(6, telefono);
            ps.setDate(7, data);

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
            String sql = "SELECT email, passwordUser, ruolo, nome, cognome, indirizzo, telefono, dataNascita, numeroCarta, intestatario FROM Utente";

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                if (email.compareTo(rs.getString(1)) == 0) {
                    String psw = DigestUtils.sha256Hex(password);
                    if (psw.compareTo(rs.getString(2)) == 0) {
                        UserBean registeredUser = new UserBean();
                        registeredUser.setEmail(rs.getString(1));
                        registeredUser.setNome(rs.getString(4));
                        registeredUser.setCognome(rs.getString(5));
                        registeredUser.setIndirizzo(rs.getString(6));
                        registeredUser.setTelefono(rs.getString(7));
                        registeredUser.setData(rs.getDate(8));
                        registeredUser.setNumero(rs.getString(9));
                        registeredUser.setIntestatario(rs.getString(10));
                        registeredUser.setRole(rs.getString(3));

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
