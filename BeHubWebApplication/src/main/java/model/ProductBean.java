package model;

import java.io.Serializable;
import java.sql.Date;


public class ProductBean implements Serializable {
    private static final long serialVersionUID = -4893809106798767527L;

    public ProductBean() {
        codice = -1;
        nome = "";
        descrizione = "";
        prezzo = 0;
        spedizione = 0;
        email = "";
        categoria = "";
        data = null;
        immagine = null;
        quantity = 1;
    }

    public void setCodice(int newCodice) {
        codice = newCodice;
    }

    public int getCodice() {
        return codice;
    }

    public void setNome(String newNome) {
        nome = newNome;
    }

    public String getNome() {
        return nome;
    }

    public void setDescrizione(String newDesc) {
        descrizione = newDesc;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setPrezzo(double newPrezzo) {
        prezzo = newPrezzo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setSpedizione(double newSpeseSped) {
        spedizione = newSpeseSped;
    }

    public double getSpedizione() {
        return spedizione;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setCategoria(String newCategoria) {
        categoria = newCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setData(Date newData) {
        data = newData;
    }

    public Date getData() {
        return data;
    }

    public void setImmagine(String newImmagine) {
        immagine = newImmagine;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void addQuantity() {
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }

    private int codice;
    private String nome;
    private String descrizione;
    private double prezzo;
    private double spedizione;
    private String email;
    private String categoria;
    private Date data;
    private String immagine;
    private int quantity;
}