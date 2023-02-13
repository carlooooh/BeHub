package control.prodotto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.DAOImplementation.ProductDAOModel;
import model.DAOInterfaces.ProductDAO;
import model.bean.ProductBean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "VenditaControl", value = "/VenditaControl")
public class VenditaControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductBean product = new ProductBean();
        product.setEmail((String) request.getSession().getAttribute("email"));

        String UPLOAD_DIRECTORY = "C:/Users/eljon/Desktop/immaginiIS";

        for (Part p : request.getParts()) {
            if ("nome".equals(p.getName())) {
                String nome = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                product.setNome(nome);
            }
            if ("descrizione".equals(p.getName())) {
                String descrizione = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                product.setDescrizione(descrizione);
            }
            if ("prezzo".equals(p.getName())) {
                String prezzo = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                product.setPrezzo(Double.parseDouble(prezzo));
            }
            if ("spedizione".equals(p.getName())) {
                String spedizione = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                product.setSpedizione(Double.parseDouble(spedizione));
            }
            if ("quantity".equals(p.getName())) {
                String quantity = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                product.setQuantity(Integer.parseInt(quantity));
            }
            if ("categoria".equals(p.getName())) {
                String categoria = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                product.setCategoria(ProductDAOModel.parseCategoria(categoria));
            }
            if ("condizioni".equals(p.getName())) {
                String condizioni = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                product.setCondizione(ProductDAOModel.parseCondizione(condizioni));
            }
            if ("immagine".equals(p.getName())) {
                String nomeFile = p.getSubmittedFileName();
                p.write(UPLOAD_DIRECTORY + File.separator + nomeFile);
                product.setImmagine(UPLOAD_DIRECTORY + File.separator + nomeFile);
            }
        }
        request.setAttribute("prodottoVenduto", product);

        //Utilizzo della classe ProductModel per la vendita
        ProductDAO productModel = new ProductDAOModel();
        try {
            productModel.doSave((ProductBean) request.getAttribute("prodottoVenduto"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            String redirectedPage = "/ProdottiInVenditaControl";
            response.sendRedirect(request.getContextPath() + redirectedPage);
        }
    }

}
