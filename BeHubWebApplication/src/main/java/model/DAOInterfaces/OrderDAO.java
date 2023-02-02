package model.DAOInterfaces;

import model.bean.CartBean;
import model.bean.OrderBean;
import model.bean.ProductBean;
import model.bean.UserBean;

import java.util.Collection;

public interface OrderDAO {
    public boolean doOrder(CartBean cart, UserBean user);
    public boolean doOrder(ProductBean prodotto, UserBean user);
    public Collection<OrderBean> getOrdini(String email);
    public Collection<OrderBean> getProdottiVenduti(String email);
    public boolean inserisciTracking(String tracking, int codiceOrdine);
}
