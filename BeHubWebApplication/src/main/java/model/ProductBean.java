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
        categoria = Categoria.Abbigliamento;
        data = null;
        immagine = null;
        quantity = 1;
        condizione = Condizione.Usato;
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

    public void setCategoria(int valoreCategoria) {
        if (valoreCategoria == 0) categoria = Categoria.Abbigliamento;
        else if (valoreCategoria == 1) categoria = Categoria.Calzature;
        else if (valoreCategoria == 2) categoria = Categoria.Elettronica;
        else if (valoreCategoria == 3) categoria = Categoria.Libri;
        else if (valoreCategoria == 4) categoria = Categoria.Giocattoli;
    }

    public int getCategoria() {
        if (categoria == Categoria.Abbigliamento) return 0;
        else if (categoria == Categoria.Calzature) return 1;
        else if (categoria == Categoria.Elettronica) return 2;
        else if (categoria == Categoria.Libri) return 3;
        else return 4;
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

    public void setCondizione(int valoreCondizione) {
        if (valoreCondizione == 0) condizione = Condizione.Nuovo;
        else condizione = Condizione.Usato;
    }

    public int getCondizione() {
        if (condizione == Condizione.Nuovo) {
            return 0;
        }
        else return 1;
    }

    //Variabili
    private int codice;
    private String nome;
    private String descrizione;
    private double prezzo;
    private double spedizione;
    private String email;
    public enum Categoria {
        Abbigliamento,
        Calzature,
        Elettronica,
        Libri,
        Giocattoli;
    }
    private Categoria categoria;
    private Date data;
    private String immagine;
    private int quantity;
    public enum Condizione {
        Nuovo,
        Usato;
    }
    private Condizione condizione;
}