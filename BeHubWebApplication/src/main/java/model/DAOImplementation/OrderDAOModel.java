package model.DAOImplementation;

import model.DAOInterfaces.OrderDAO;
import model.bean.CartBean;
import model.bean.OrderBean;
import model.bean.ProductBean;
import model.bean.UserBean;
import model.utils.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class OrderDAOModel implements OrderDAO {
    /*
    Metodo per effettuare gli ordini dei prodotti nel carrello
     */
    public synchronized boolean doOrder(CartBean cart, UserBean user) {
        Collection<ProductBean> carrello = cart.getCarrello();
        Connection con = null;
        ProductDAOModel productModel = new ProductDAOModel();

        String sql = "INSERT INTO Ordine (codiceProdotto, emailCliente, prezzoTotale, quantity, dataAcquisto) VALUES (?, ?, ?, ?, current_date())";

        try {
            con = DriverManagerConnectionPool.getConnection();

            if (carrello != null && carrello.size() != 0) {
                for (Iterator<ProductBean> i = cart.getCarrello().iterator(); i.hasNext(); ) {
                    ProductBean bean = (ProductBean) i.next();
                    Double prezzoTot = ((bean.getPrezzo() + bean.getSpedizione()) * bean.getQuantity()); //Calcolo del prezzo totale di un prodotto in base alla quantità

                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, bean.getCodice());
                    ps.setString(2, user.getEmail());
                    ps.setDouble(3, prezzoTot);
                    ps.setInt(4, bean.getQuantity());

                    ps.executeUpdate();
                    con.commit();

                    productModel.diminuisciQuantità(bean.getCodice(), bean.getQuantity());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    /*
    Metodo per effettuare l'ordine di un solo prodotto
     */
    public synchronized boolean doOrder(ProductBean prodotto, UserBean user) {
        Connection con = null;
        ProductDAOModel productModel = new ProductDAOModel();

        String sql = "INSERT INTO Ordine (codiceProdotto, emailCliente, prezzoTotale, quantity, dataAcquisto) VALUES (?, ?, ?, ?, current_date())";

        try {
            con = DriverManagerConnectionPool.getConnection();

            Double prezzoTot = ((prodotto.getPrezzo() + prodotto.getSpedizione()) * prodotto.getQuantity()); //Calcolo del prezzo totale di un prodotto in base alla quantità

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, prodotto.getCodice());
            ps.setString(2, user.getEmail());
            ps.setDouble(3, prezzoTot);
            ps.setInt(4, prodotto.getQuantity());

            ps.executeUpdate();
            con.commit();

            productModel.diminuisciQuantità(prodotto.getCodice(), prodotto.getQuantity());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    /*
    Metodo per ottenere la lista di ordini di un utente
     */
    public synchronized Collection<OrderBean> getOrdini(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Collection<OrderBean> listaOrdini = new LinkedList<OrderBean>();

        String sql = "SELECT o.codiceOrdine, o.codiceProdotto, o.emailCliente , o.prezzoTotale, o.quantity, o.dataAcquisto, o.stato, o.tracking, p.nome, p.emailVenditore, p.urlImmagine FROM Ordine AS o, Prodotto AS p WHERE emailCliente = ? AND o.codiceProdotto = p.codice ORDER BY o.dataAcquisto DESC";

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();

            while (rs.next()) {
                ProductBean bean = new ProductBean();
                OrderBean ordine = new OrderBean();

                bean.setPrezzo(rs.getDouble("prezzoTotale"));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setNome(rs.getString("nome"));
                bean.setImmagine(rs.getString("urlImmagine"));
                bean.setCodice(rs.getInt("codiceProdotto"));
                bean.setEmail(rs.getString("emailVenditore"));
                ordine.setProdotto(bean);

                ordine.setCodice(rs.getInt("codiceOrdine"));
                ordine.setData(rs.getDate("dataAcquisto"));
                ordine.setEmail(rs.getString("emailCliente"));
                ordine.setTracking(rs.getString("tracking"));
                ordine.setStato(parseStato(rs.getString("stato")));
                listaOrdini.add(ordine);
            }
            return listaOrdini;
        } catch (Exception e) {
            return null;
        } finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    //Metodo per ottenere la lista di prodotti venduti dall'utente
    public synchronized Collection<OrderBean> getProdottiVenduti(String email) {
        String SQL = "SELECT * FROM Ordine AS o, Prodotto AS p WHERE o.codiceProdotto = p.codice AND p.emailVenditore = ? ORDER BY o.dataAcquisto DESC";
        PreparedStatement ps = null;
        Collection<OrderBean> listaOrdini = new LinkedList<OrderBean>();
        Connection con = null;

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                OrderBean ordine = new OrderBean();
                ProductBean prodotto = new ProductBean();

                prodotto.setCodice(rs.getInt("codiceProdotto"));
                prodotto.setEmail(email);
                prodotto.setPrezzo(rs.getDouble("prezzoTotale"));
                prodotto.setQuantity(rs.getInt("quantity"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setCondizione(ProductDAOModel.parseCondizione(rs.getString("condizione")));
                prodotto.setCategoria(ProductDAOModel.parseCategoria(rs.getString("nomeCategoria")));
                prodotto.setImmagine(rs.getString("urlImmagine"));

                ordine.setCodice(rs.getInt("codiceOrdine"));
                ordine.setData(rs.getDate("dataAcquisto"));
                ordine.setEmail(rs.getString("emailCliente"));
                ordine.setStato(parseStato(rs.getString("stato")));
                ordine.setTracking(rs.getString("tracking"));
                ordine.setProdotto(prodotto);
                listaOrdini.add(ordine);
            }
            return listaOrdini;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    /*
    Metodo per inserire il codice di tracking nell'ordine
     */
    public synchronized boolean inserisciTracking(String tracking, int codiceOrdine) {
        String sql = "UPDATE Ordine SET tracking = ?, stato = ? WHERE codiceOrdine = ?";
        Connection con = null;
         try {
             con = DriverManagerConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1, tracking);
             ps.setString(2, "Spedito");
             ps.setInt(3, codiceOrdine);

             ps.executeUpdate();
             con.commit();

             return true;
         }
         catch (Exception e) {
             e.printStackTrace();
             return false;
         }
         finally {
             if (con != null)
                 DriverManagerConnectionPool.releaseConnection(con);
         }
    }

    /*
    Metodo per convertire lo stato nel valore corretto
     */
    public static synchronized int parseStato(String stato) {
        if (stato.compareTo("In lavorazione") == 0)
            return 0;
        else if (stato.compareTo("Spedito") == 0)
            return 1;
        else
            return 2;
    }

    /*
    Metodo per convertire il valore dello stato nella stringa corretta
     */
    public static synchronized String parseStato(int valoreStato) {
        if (valoreStato == 0)
            return "In lavorazione";
        else if (valoreStato == 1)
            return "Spedito";
        else
            return "Consegnato";
    }
}