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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductBean product = new ProductBean();
        product.setEmail((String) request.getSession().getAttribute("email"));

        String UPLOAD_DIRECTORY = "C:\\Users\\franc\\OneDrive\\Documenti\\apache-tomcat-10.0.27\\BeHub\\IMG";

        for (Part p : request.getParts()) {
            if ("nome".equals(p.getName())) {
                String nome = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(nome);
                product.setNome(nome);
            }
            else if ("descrizione".equals(p.getName())) {
                String descrizione = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(descrizione);
                product.setDescrizione(descrizione);
            }
            else if ("prezzo".equals(p.getName())) {
                String prezzo = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(prezzo);
                product.setPrezzo(Double.parseDouble(prezzo));
            }
            else if ("spedizione".equals(p.getName())) {
                String spedizione = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(spedizione);
                product.setSpedizione(Double.parseDouble(spedizione));
            }
            else if ("quantity".equals(p.getName())) {
                String quantity = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(quantity);
                product.setQuantity(Integer.parseInt(quantity));
            }
            else if ("categoria".equals(p.getName())) {
                String categoria = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(categoria);
                product.setCategoria(ProductDAOModel.parseCategoria(categoria));
            }
            else if ("condizioni".equals(p.getName())) {
                String condizioni = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(condizioni);
                product.setCondizione(ProductDAOModel.parseCondizione(condizioni));
            }
            else if ("immagine".equals(p.getName())) {
                String nomeFile = p.getSubmittedFileName();
                p.write(UPLOAD_DIRECTORY + File.separator + nomeFile);
                product.setImmagine(UPLOAD_DIRECTORY + File.separator + nomeFile);
            }
        }

        //Utilizzo della classe ProductModel per la vendita
        ProductDAO productModel = new ProductDAOModel();
        try {
            productModel.doSave(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            String redirectedPage = "/index.jsp";
            response.sendRedirect(request.getContextPath() + redirectedPage);
        }
    }

}
