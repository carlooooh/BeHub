package model.DAOImplementation;

import model.DAOInterfaces.CartDAO;
import model.bean.CartBean;
import model.bean.ProductBean;
import model.bean.UserBean;
import model.utils.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class CartDAOModel implements CartDAO {
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
                    prodotto.setMaxQuantity(bean.getMaxQuantity());
                }
                lista.add(prodotto);
            }
            cart.setLista(lista);
        }
        return cart;
    }

    /*
    Metodo per aggiungere un prodotto al carrello
     */
    public synchronized CartBean aggiungiAlCarrello(CartBean carrello, int codiceProdotto) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ProductBean bean = new ProductBean();

        String selectSQL = "SELECT p.codice, p.nome, p.descrizione, p.prezzo, p.speseSpedizione, p.emailVenditore, p.nomeCategoria, p.condizione, p.quantity, p.dataAnnuncio, p.urlImmagine FROM Prodotto AS p WHERE p.codice = ? AND p.deleted = false";

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
                bean.setCategoria(ProductDAOModel.parseCategoria(rs.getString("nomeCategoria")));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setData(rs.getDate("dataAnnuncio"));
                bean.setImmagine(rs.getString("urlImmagine"));
                bean.setCondizione(ProductDAOModel.parseCondizione(rs.getString("condizione")));
                bean.setMaxQuantity(rs.getInt("quantity"));

                carrello.setCarrello(bean);
            }

            return carrello;
        }
        catch (Exception e) {
            e.printStackTrace();
            return carrello;
        }
        finally {
            if (connection != null) {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    /*
    Metodo per aggiungere un prodotto al carrello con quantità fissata
     */
    public synchronized CartBean aggiungiAlCarrello(CartBean carrello, int codiceProdotto, int quantità) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ProductBean bean = new ProductBean();

        String selectSQL = "SELECT p.codice, p.nome, p.descrizione, p.prezzo, p.speseSpedizione, p.emailVenditore, p.nomeCategoria, p.condizione, p.quantity, p.dataAnnuncio, p.urlImmagine FROM Prodotto AS p WHERE p.codice = ? AND p.deleted = false";

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
                bean.setCategoria(ProductDAOModel.parseCategoria(rs.getString("nomeCategoria")));
                bean.setQuantity(quantità); //setta la quantità del prodotto
                bean.setData(rs.getDate("dataAnnuncio"));
                bean.setImmagine(rs.getString("urlImmagine"));
                bean.setCondizione(ProductDAOModel.parseCondizione(rs.getString("condizione")));
                bean.setMaxQuantity(rs.getInt("quantity"));

                carrello.setCarrello(bean);
            }

            return carrello;
        }
        catch (Exception e) {
            e.printStackTrace();
            return carrello;
        }
        finally {
            if (connection != null) {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }
}
