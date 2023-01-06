package main.java.model;

import java.io.Serializable;
import java.sql.Date;

public class TicketBean implements Serializable {
    private static final long serialVersionUID = -6587747643222088947L;

    public TicketBean() {
        codice = 0;
        testo = "";
        oggetto = "";
        data = null;
        emailUtente = "";
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int newCodice) {
        codice = newCodice;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String newTesto) {
        testo = newTesto;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String newOggetto) {
        oggetto = newOggetto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date newDate) {
        data = newDate;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String newEmailUtente) {
        emailUtente = newEmailUtente;
    }


    private int codice;
    private String testo;
    private String oggetto;
    private Date data;
    private String emailUtente;
}