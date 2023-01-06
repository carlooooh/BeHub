package main.java.model;

import java.io.Serializable;
import java.sql.Date;

public class OrderBean implements Serializable {
    private static final long serialVersionUID = -8545747656922088947L;

    public OrderBean() {
        prodotto = null;
        data = null;
        codice = 0;
    }

    public void setProdotto(ProductBean newProdotto) {
        prodotto = newProdotto;
    }

    public ProductBean getProdotto() {
        return prodotto;
    }

    public void setData(Date newData) {
        data = newData;
    }

    public Date getData() {
        return data;
    }

    public void setCodice(int newCodice) {
        codice = newCodice;
    }

    public int getCodice() {
        return codice;
    }

    private ProductBean prodotto;
    private Date data;
    private int codice;
}