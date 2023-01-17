package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ProductModel {

    private static final String TABLE_NAME = "Prodotto";

    /*
    Metodo per inserire nel database un prodotto passando in input un ProductBean e un url ad una immagine
     */
    public synchronized void doSave(ProductBean product) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //Inserimento prodotto nel database
        String insertSQL = "INSERT INTO Prodotto (nome, descrizione, prezzo, speseSpedizione, emailVenditore, condizione, quantity, nomeCategoria, dataAnnuncio, urlImmagine) VALUES (?, ?, ?, ?, ?, ?, ?, ?, current_date(), ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, product.getNome());
            preparedStatement.setString(2, product.getDescrizione());
            preparedStatement.setDouble(3, product.getPrezzo());
            preparedStatement.setDouble(4, product.getSpedizione());
            preparedStatement.setString(5, product.getEmail());
            preparedStatement.setString(6, controllaCondizione(product.getCondizione()));
            preparedStatement.setInt(7, product.getQuantity());
            preparedStatement.setString(8, controllaCategoria(product.getCategoria()));
            preparedStatement.setString(9, product.getImmagine());

            preparedStatement.executeUpdate();
            connection.commit();

        }
        finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                if (connection != null)
                    DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    /*
    Metodo per ottenere un prodotto dal database attraverso il valore del codice
     */
    public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ProductBean bean = new ProductBean();

        String selectSQL = "SELECT * FROM " + ProductModel.TABLE_NAME + " WHERE codice = ? AND deleted = false";

        try {
            //Creazione ProductBean dalla tavola Prodotto del database
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, code);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setCodice(code);
                bean.setNome(rs.getString("nome"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                bean.setSpedizione(rs.getDouble("speseSpedizione"));
                bean.setEmail(rs.getString("emailVenditore"));
                bean.setCategoria(parseCategoria(rs.getString("nomeTipologia")));
                bean.setData(rs.getDate("dataAnnuncio"));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setCondizione(parseCondizione(rs.getString("condizione")));
                bean.setImmagine(rs.getString("urlImmagine"));
            }

            return bean;
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null) {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
            }
        }
    }

    public synchronized boolean doDelete(int code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "UPDATE " + ProductModel.TABLE_NAME + "SET deleted = false WHERE codice = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, code);

            result = preparedStatement.executeUpdate();
            connection.commit();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return (result != 0); //Ritorna true se l'operazione ha successo, false altrimenti
    }
    public synchronized Collection<ProductBean> doRetrieveAll(String where) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        Collection<ProductBean> products = new LinkedList<ProductBean>();

        String selectSQL = "SELECT * FROM " + ProductModel.TABLE_NAME + " WHERE deleted = 'false' AND nomeCategoria = '" + where + "'";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ProductBean bean = new ProductBean();

                bean.setCodice(rs.getInt("codice"));
                bean.setNome(rs.getString("nome"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                bean.setSpedizione(rs.getDouble("speseSpedizione"));
                bean.setEmail(rs.getString("emailVenditore"));
                bean.setCategoria(parseCategoria(rs.getString("nomeCategoria")));
                bean.setData(rs.getDate("dataAnnuncio"));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setCondizione(parseCondizione(rs.getString("condizione")));
                bean.setImmagine(rs.getString("urlImmagine"));

                products.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null) {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
            }
        }
        return products;
    }

    /*
    Metodo per eliminare un prodotto dal database (settando false la variabile deleted)
     */
    public synchronized Collection<ProductBean> deleteProduct(int codiceProdotto, Collection<ProductBean> lista) {
        String sql = "UPDATE Prodotto SET deleted = ? WHERE codice = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, codiceProdotto);

            ps.executeUpdate();
            con.commit();

            if (lista.size() > 0) {
                lista.removeIf(a -> a.getCodice() == codiceProdotto);
            }

            return lista;
        }
        catch (Exception e) {
            return lista;
        }
        finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    public synchronized void updateProduct(ProductBean bean) {
        String sql = "UPDATE Prodotto SET nome = ?, descrizione = ?, prezzo = ?, speseSpedizione = ?, nomeCategoria = ?, quantity = ?, condizione = ? WHERE codice = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManagerConnectionPool.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, bean.getNome());
            ps.setString(2, bean.getDescrizione());
            ps.setDouble(3, bean.getPrezzo());
            ps.setDouble(4, bean.getSpedizione());
            ps.setString(5, controllaCategoria(bean.getCategoria()));
            ps.setInt(6, bean.getQuantity());
            ps.setString(7, controllaCondizione(bean.getCondizione()));
            ps.setInt(8, bean.getCodice());

            ps.executeUpdate();
            con.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    /*
    Metodo interno per controllare il tipo di condizione
     */
    public static synchronized String controllaCondizione(int valoreCondizione) {
        if (valoreCondizione == 0) return "Nuovo";
        else return "Usato";
    }

    /*
    Metodo interno per controllare la categoria del prodotto
     */
    public static synchronized String controllaCategoria(int valoreCategoria) {
        if (valoreCategoria == 0) return "Abbigliamento";
        else if (valoreCategoria == 1) return "Calzature";
        else if (valoreCategoria == 2) return "Elettronica";
        else if (valoreCategoria == 3) return "Libri";
        else return "Giocattoli";
    }

    /*
    Metodo interno per ottenere il valore della categoria da una stringa
     */
    public static synchronized int parseCategoria(String categoria) {
        if (categoria.compareTo("Abbigliamento") == 0) return 0;
        else if (categoria.compareTo("Calzature") == 0) return 1;
        else if (categoria.compareTo("Elettronica") == 0) return 2;
        else if (categoria.compareTo("Libri") == 0) return 3;
        else return 4;
    }

    /*
    Metodo interno per ottenere il valore della condizione da una stringa
     */
    public static synchronized int parseCondizione(String condizione) {
        if (condizione.compareTo("Nuovo") == 0) return 0;
        else return 1;
    }
}

