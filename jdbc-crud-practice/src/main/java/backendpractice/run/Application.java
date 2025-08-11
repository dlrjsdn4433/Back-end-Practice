package backendpractice.run;

import backendpractice.model.dao.MenuDAO;
import backendpractice.model.dao.UserDAO;
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
        UserDAO userDAO = new UserDAO();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("=========배달 프로그램=========");
            System.out.println("1. 회원가입 ");
            System.out.println("2. 로그인 ");
            System.out.print("메뉴 선택 : ");
            int choice = sc.nextInt();

            switch(choice){
                case 1 :
                    userDAO.userRegistration(con);
                    break;
                case 2 :
                    userDAO.setUserCode(userDAO.loginSuccess(userDAO.login(con)));
                    break;

            }
            if(choice==2){
                break;
            }
        }

        System.out.println(userDAO.getUserCode());

        switch(userDAO.getUserCode()){
            case 0 : break; //사용자가 사장인경우
            case 1 : break; // 사용자가 주문자인경우
        }

//        registDAO.userRegistration(con); //사용자 등록
//        registDAO.userDistinction(con); //사용자 구분
//        registDAO.viewAllStore(con); //전체 매장 조회
//        registDAO.viewMenuOfStore(con); // 매장 내 메뉴 전체조회
//        registDAO.viewMenu(con); // 메뉴 이름 특정하여 조회
//        registDAO.deleteMenu(con); // 특정 메뉴 삭제
//        registDAO.loginSuccess(registDAO.login(con)); // 아이디, 비밀번호 입력으로 로그인

    }
}
