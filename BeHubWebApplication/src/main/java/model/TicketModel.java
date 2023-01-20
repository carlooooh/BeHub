package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class TicketModel {
    /*
    Metodo per aggiungere un ticket nel database
     */
    public synchronized boolean aggiungiTicket(TicketBean ticket) {
        Connection connection = null;
        String sql = "INSERT INTO Ticket (testo, oggetto, dataInvio, emailUtente, stato) VALUES (?, ?, current_date(), ?, ?)";

        try{
            connection = DriverManagerConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, ticket.getTesto());
            ps.setString(2, ticket.getOggetto());
            ps.setString(3, ticket.getEmailUtente());
            ps.setString(4, controllaStato(ticket.getStato()));

            ps.executeUpdate();
            connection.commit();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (connection != null)
                DriverManagerConnectionPool.releaseConnection(connection);
        }
    }

    /*
    Metodo per ottenere la lista di ticket dell'utente
     */
    public synchronized Collection<TicketBean> getListaTicket(String emailUtente) {
        Connection connection = null;
        String sql = "SELECT * FROM Ticket WHERE emailUtente = ? ORDER BY dataInvio DESC";
        Collection<TicketBean> listaTicket = new LinkedList<TicketBean>();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, emailUtente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TicketBean ticket = new TicketBean();
                ticket.setCodice(rs.getInt("codice"));
                ticket.setData(rs.getDate("dataInvio"));
                ticket.setEmailUtente(emailUtente);
                ticket.setOggetto(rs.getString("oggetto"));
                ticket.setTesto(rs.getString("testo"));
                ticket.setStato(parseStato(rs.getString("stato")));

                listaTicket.add(ticket);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
            return listaTicket;
        }
    }

    /*
    Metodo che modifica lo stato del TicketBean da "Aperto" a "Chiuso"
    */
    public synchronized boolean chiudiTicket(TicketBean ticket) {
        Connection connection = null;
        String sql = "UPDATE Ticket SET stato = ? WHERE codice = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "Chiuso");
            ps.setInt(2, ticket.getCodice());
            ps.executeUpdate();
            connection.commit();

            //return modificaListaTicket(listaTicket, ticket.getCodice());
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (connection != null) {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    private synchronized Collection<TicketBean> modificaListaTicket(Collection<TicketBean> listaTicket, int codiceTicket) {
        Iterator<TicketBean> it = listaTicket.iterator();
        TicketBean ticket = null;

        //cerca il ticket corretto da modificare
        while (it.hasNext()) {
            TicketBean tempTicket = (TicketBean) it.next();
            if (ticket.getCodice() == codiceTicket) {
                ticket = tempTicket;
                break;
            }
        }

        //modifica il ticket e lo sostituisce nella lista
        if (ticket != null) {
            listaTicket.remove(ticket);
            ticket.setStato(1);
            listaTicket.add(ticket);
        }

        return listaTicket;
    }

    /*
    Metodo interno per utilizzare correttamente il valore dello stato del TicketBean
     */
    private synchronized String controllaStato(int codiceStato) {
        if (codiceStato == 0) return "Aperto";
        else return "Chiuso";
    }

    /*
    Metodo interno per convertire una stringa in un codice stato
     */
    private synchronized int parseStato(String stato) {
        if (stato.compareTo("Aperto") == 0) return 0;
        else return 1;
    }
}