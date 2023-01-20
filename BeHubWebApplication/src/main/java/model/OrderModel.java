package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class OrderModel {
    /*
    Metodo per creare un'istanza dell'ordine nel database dopo l'acquisto
     */
    public synchronized boolean doOrder(CartBean cart, UserBean user) {
        Collection<ProductBean> carrello = null;

        Connection con = null;
        carrello = cart.getCarrello();

        String sql = "INSERT INTO Ordine (codiceProdotto, emailCliente, prezzoTotale, quantity, dataAcquisto) VALUES (?, ?, ?, ?, current_date())";
        String modificaQuantità = "UPDATE Prodotto SET quantity = quantity - 1 WHERE codice = ?";

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
                }
                con.commit();
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
    Metodo per ottenere la lista di ordini di un utente
     */
    public synchronized Collection<OrderBean> getOrdini(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Collection<OrderBean> listaOrdini = new LinkedList<OrderBean>();

        String sql = "SELECT o.codiceOrdine, o.codiceProdotto, o.prezzoTotale, o.quantity, o.dataAcquisto, p.nome, p.emailVenditore, p.model, p.urlImmagine FROM Ordine AS o, Prodotto AS p WHERE emailCliente = ? AND o.codiceProdotto = p.codice ORDER BY o.dataAcquisto DESC";

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
                ordine.setProdotto(bean);

                ordine.setCodice(rs.getInt("codiceOrdine"));
                ordine.setData(rs.getDate("dataAcquisto"));
                ordine.setEmailVenditore(rs.getString("emailVenditore"));
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

    /*
    Metodo per aggiungere i prodotti del carrello alla lista di ordini salvata in sessione
     */
    public synchronized Collection<OrderBean> addOrdini(CartBean cart, String email, Collection<OrderBean> lista) throws SQLException {
        Collection<ProductBean> carrello = cart.getCarrello();
        String sql = "SELECT o.codiceOrdine, o.dataAcquisto, p.emailVenditore FROM Ordine AS o, Prodotto AS p WHERE o.codiceProdotto = p.codice AND o.codiceProdotto = ? AND o.emailCliente = ? ORDER BY o.dataAcquisto DESC";
        Connection con = null;
        PreparedStatement ps = null;

        if (carrello != null && carrello.size() != 0) {
            Iterator<ProductBean> it = carrello.iterator();
            while (it.hasNext()) {
                ProductBean bean = (ProductBean) it.next();
                OrderBean order = new OrderBean();
                try {
                    con = DriverManagerConnectionPool.getConnection();
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, bean.getCodice());
                    ps.setString(2, email);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        order.setProdotto(bean);
                        order.setCodice(rs.getInt(1));
                        order.setData(rs.getDate(2));
                        order.setEmailVenditore(rs.getString(3));

                        lista.add(order);
                    }
                    return lista;
                } catch (Exception e) {
                    return lista;
                } finally {
                    if (con != null) {
                        DriverManagerConnectionPool.releaseConnection(con);
                    }
                    if (ps != null) {
                        ps.close();
                    }
                }
            }
        }
        return lista;
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
                prodotto.setCondizione(ProductModel.parseCondizione(rs.getString("condizione")));
                prodotto.setCategoria(ProductModel.parseCategoria(rs.getString("categoria")));
                prodotto.setImmagine(rs.getString("urlImmagine"));

                ordine.setCodice(rs.getInt("codiceOrdine"));
                ordine.setData(rs.getDate("dataAcquisto"));
                ordine.setEmailVenditore(rs.getString("emailCliente"));
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
}