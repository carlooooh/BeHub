package model.DAOInterfaces;

import model.bean.TicketBean;

import java.util.Collection;

public interface TicketDAO {
    public boolean aggiungiTicket(TicketBean ticket);
    public Collection<TicketBean> getListaTicket(String emailUtente);
    public boolean chiudiTicket(TicketBean ticket);
    public Collection<TicketBean> getListaTicketAperti(String emailUtente);
    public TicketBean retrieveByKey(int codiceTicket);
}
