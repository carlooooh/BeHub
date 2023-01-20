package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class CartBean implements Serializable {
    private static final long serialVersionUID = -4830856131240011211L;

    public CartBean() {
        carrello = new LinkedList<ProductBean>();
    }

    /*
        Metodo per aggiungere un prodotto al carrello modificando la quantità se già presente
     */
    public void setCarrello(ProductBean newProdotto) {
        ProductBean bean = this.retrieveByKey(newProdotto.getCodice());
        if (bean == null) {
            carrello.add(newProdotto);
        }
        else {
            this.addQuantity(bean.getCodice());
        }
    }

    /*
        Metodo deprecato per aggiungere prodotti al carrello
     */
    public void addProduct(ProductBean newProdotto) {
        carrello.add(newProdotto);
    }

    /*
        Metodo per ottenere un prodotto specifico dal carrello usando il codice del prodotto
     */
    public ProductBean retrieveByKey(int codiceProdotto) {
        for (Iterator<ProductBean> i = this.getCarrello().iterator(); i.hasNext(); ) {
            ProductBean bean = (ProductBean) i.next();
            if (bean.getCodice() == codiceProdotto)
                return bean;
        }
        return null;
    }

    /*
        Metodo per aumentare di 1 la quantità di un prodotto presente nel carrello
     */
    public void addQuantity(int codiceProdotto) {
        for (Iterator<ProductBean> i = this.getCarrello().iterator(); i.hasNext(); ) {
            ProductBean bean = (ProductBean) i.next();
            if (bean.getCodice() == codiceProdotto)
                bean.addQuantity();
        }
    }

    /*
        Metodo per diminuire di 1 la quantità di un prodotto presente nel carrello
     */
    public void decreaseQuantity(int codiceProdotto) {
        for (Iterator<ProductBean> i = this.getCarrello().iterator(); i.hasNext(); ) {
            ProductBean bean = (ProductBean) i.next();
            if (bean.getCodice() == codiceProdotto)
                bean.decreaseQuantity();
        }
    }

    /*
        Metodo getter
     */
    public Collection<ProductBean> getCarrello() {
        return carrello;
    }

    /*
        Metodo per rimuovere un prodotto dal carrello tramite codice prodotto
     */
    public void removeItem(int codiceProdotto) {
        if (carrello.size() > 0) {
            carrello.removeIf(a -> a.getCodice() == codiceProdotto);
        }
    }

    /*
        Metodo che ritorna true se il carrello è vuoto, false altrimenti
     */
    public boolean isEmpty() {
        return carrello.isEmpty();
    }

    /*
        Metodo per cancellare tutti gli oggetti dal carrello
     */
    public void removeAllItems() {
        carrello.removeAll(carrello);
    }

    /*
        Metodo setter
     */
    public void setLista(Collection<ProductBean> lista) {
        carrello = lista;
    }

    private Collection<ProductBean> carrello;
}
