package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class CartModel {
    public synchronized CartBean updateCarrello(ProductBean bean, CartBean cart) {
        Collection<ProductBean> collection = cart.getCarrello();
        Collection<ProductBean> lista = new LinkedList<ProductBean>();

        if (collection != null && collection.size() != 0) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                ProductBean prodotto = (ProductBean) it.next();
                if (prodotto.getCodice() == bean.getCodice()) {
                    prodotto.setNome(bean.getNome());
                    prodotto.setDescrizione(bean.getDescrizione());
                    prodotto.setPrezzo(bean.getPrezzo());
                    prodotto.setSpedizione(bean.getSpedizione());
                    prodotto.setCategoria(bean.getCategoria());
                    prodotto.setQuantity(bean.getQuantity());
                    prodotto.setCondizione(bean.getCondizione());
                    prodotto.setImmagine(bean.getImmagine());
                }
                lista.add(prodotto);
            }
            cart.setLista(lista);
        }
        return cart;
    }

    /*
    Metodo per inserire negli ordini tutti gli elementi del carrello dopo l'acquisto
     */
    public synchronized CartBean acquista(CartBean cart, UserBean user) {
        Connection con = null;
        Collection<ProductBean>carrello = cart.getCarrello();
        String sql = "INSERT INTO Ordine (codiceProdotto, emailCliente, prezzoTotale, quantity, dataAcquisto) VALUES (?, ?, ?, ?, current_date())";

        try {
            con = DriverManagerConnectionPool.getConnection();

            if (carrello != null && carrello.size() != 0) {
                for (Iterator<ProductBean> i = cart.getCarrello().iterator(); i.hasNext();) { //per ogni prodotto del carrello viene creato un ordine
                    ProductBean bean = (ProductBean) i.next();
                    Double prezzoTot = ((bean.getPrezzo() + bean.getSpedizione()) * bean.getQuantity());

                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, bean.getCodice());
                    ps.setString(2, user.getEmail());
                    ps.setDouble(3, prezzoTot);
                    ps.setInt(4, bean.getQuantity());

                    ps.executeUpdate();
                }
                con.commit();
                cart.removeAllItems();
            }
            return cart;
        }
        catch (Exception e) {
            return cart;
        }
        finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    /*
    Metodo per aggiungere un prodotto al carrello
     */
    public synchronized CartBean aggiungiAlCarrello(CartBean carrello, int codiceProdotto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ProductBean bean = new ProductBean();

        String selectSQL = "SELECT p.nome, p.descrizione, p.prezzo, p.speseSpedizione, p.emailVenditore, p.nomeCategoria, p.condizione, p.quantity, p.dataAnnuncio, p.urlImmagine FROM Prodotto AS p, Immagine AS i WHERE p.codice = ? AND p.deleted = false";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, codiceProdotto);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setCodice(rs.getInt("codice"));
                bean.setNome(rs.getString("nome"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                bean.setSpedizione(rs.getDouble("speseSpedizione"));
                bean.setEmail(rs.getString("emailVenditore"));
                bean.setCategoria(ProductModel.parseCategoria(rs.getString("nomeCategoria")));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setData(rs.getDate("dataAnnuncio"));
                bean.setImmagine(rs.getString("urlImmagine"));
                bean.setCondizione(ProductModel.parseCondizione(rs.getString("condizione")));
            }
            carrello.setCarrello(bean);
            return carrello;
        }
        catch (Exception e) {
            return carrello;
        }
        finally {
            if (connection != null) {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }
}
