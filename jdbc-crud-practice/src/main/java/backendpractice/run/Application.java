package backendpractice.run;

import backendpractice.model.dao.MenuDAO;
import backendpractice.model.dto.MenuDTO;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import static backendpractice.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        Connection con = getConnection();
        MenuDAO registDAO = new MenuDAO();

//        registDAO.viewMenuOfStore(con);
//
//        registDAO.userDistinction(con);

        registDAO.userRegistration(con);







    }
}
