package model.DAOInterfaces;

import model.bean.ProductBean;

import java.sql.SQLException;
import java.util.Collection;

public interface ProductDAO {
    public void doSave(ProductBean product) throws SQLException;
    public ProductBean doRetrieveByKey(int code) throws SQLException;
    public Collection<ProductBean> doRetrieveAll(String where) throws SQLException;
    public Collection<ProductBean> deleteProduct(int codiceProdotto, Collection<ProductBean> lista);
    public void updateProduct(ProductBean bean);
    public Collection<ProductBean> getProdottiInVendita(String email);
    public boolean diminuisciQuantità(int codiceProdotto, int quantitàAcquistata);
}
