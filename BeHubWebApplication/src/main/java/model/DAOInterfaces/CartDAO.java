package model.DAOInterfaces;

import model.bean.CartBean;
import model.bean.ProductBean;

public interface CartDAO {
    public CartBean updateCarrello(ProductBean bean, CartBean cart);
    public CartBean aggiungiAlCarrello(CartBean carrello, int codiceProdotto);
    public CartBean aggiungiAlCarrello(CartBean carrello, int codiceProdotto, int quantità);
}
