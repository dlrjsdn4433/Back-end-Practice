package backendpractice.run;

import backendpractice.model.dao.MenuDAO;
import backendpractice.model.dao.OwnerDAO;

import java.sql.Connection;

import static backendpractice.common.JDBCTemplate.getConnection;

public class test {
    public static void main(String[] args) {

        Connection con = getConnection();
        OwnerDAO ownerDAO = new OwnerDAO();
        MenuDAO menuDAO = new MenuDAO();

        int userCode = menuDAO.login(con);

        ownerDAO.setUserCode(userCode);
        menuDAO.loginSuccess(userCode);
        ownerDAO.reigstStore(con);




    }
}
