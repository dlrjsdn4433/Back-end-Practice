package backendpractice.run;

import backendpractice.model.dao.CustomerDAO;
import backendpractice.model.dao.OwnerDAO;
import backendpractice.model.dao.UserDAO;

import java.sql.Connection;
import java.util.Scanner;

import static backendpractice.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        Connection con = getConnection();
        UserDAO user = new UserDAO();
        CustomerDAO customer = new CustomerDAO();
        OwnerDAO owner = new OwnerDAO();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("=========배달 프로그램=========");
            System.out.println("");
            System.out.println("1. 회원가입 ");
            System.out.println("2. 로그인 ");
            System.out.println("0. 프로그램 종료 ");
            System.out.println("");
            System.out.print("메뉴 선택 : ");
            int choice = sc.nextInt();


            // 회원가입 or 로그인 선택 회원가입하면 다시 돌아오고 로그인하면 break
                switch(choice){
                    case 1 :
                        user.userRegistration(con);
                        break;
                    case 2 :
                        int num = user.login(con);
                        int dist = user.loginSuccess(num);

                        if(dist==0){
                            owner.setUserCode(num);
                        }else if(dist==1){
                            customer.setUserCode(num);
                        }

                        while(true){
                            switch(dist){
                                case 0 :
                                    owner.owner(con);
                                    break;
                                case 1 :
                                    customer.customer(con);
                                    break;
                            }
                            break;
                        }


                }
                if(choice==0){
                    System.out.println("프로그램을 종료합니다.");
                     break;
                }
        }
    }
}




