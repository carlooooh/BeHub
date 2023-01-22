package model;

import java.io.Serializable;
import java.sql.Date;

public class OrderBean implements Serializable {
    private static final long serialVersionUID = -8545747656922088947L;

    public OrderBean() {
        prodotto = null;
        data = null;
        codice = 0;
        email = "";
        tracking = null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String codiceTracking) {
        tracking = codiceTracking;
    }

    public void setStato(int valoreStato) {
        if (valoreStato == 0)
            stato = StatoOrdine.In_Lavorazione;
        else if (valoreStato == 1)
            stato = StatoOrdine.Spedito;
        else if (valoreStato == 2)
            stato = StatoOrdine.Consegnato;
    }

    public int getStato() {
        if (stato == StatoOrdine.In_Lavorazione)
            return 0;
        else if (stato == StatoOrdine.Spedito)
            return 1;
        else if (stato == StatoOrdine.Consegnato)
            return 2;
        else return -1;
    }

    private ProductBean prodotto;
    private Date data;
    private int codice;
    private String email;
    public enum StatoOrdine {
        In_Lavorazione, //valore 0
        Spedito,        //valore 1
        Consegnato,     //valore 2
    }
    private StatoOrdine stato;
    private String tracking;
}