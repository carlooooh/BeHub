package model.DAOInterfaces;

import model.bean.UserBean;

public interface UserDAO {
    public boolean update(UserBean bean, String emailOld);
    public boolean insert(UserBean user, String psw);
    public UserBean login(String email, String password);
}
