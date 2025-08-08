package backendpractice.model.dao;


import backendpractice.model.dto.StoreDTO;
import backendpractice.model.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static backendpractice.common.JDBCTemplate.close;
import static backendpractice.common.JDBCTemplate.getConnection;

public class MenuDAO {

    private Properties prop = new Properties();
    private int userCode;

    public MenuDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/backendpractice/mapper/baedaldb-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    // 매장이름 입력하면 메뉴 조회되는 메소드
    public void viewMenuOfStore(Connection con){
        Scanner sc = new Scanner(System.in);
        System.out.println("매장 이름을 입력하시면 메뉴 목록을 보여드립니다.");
        System.out.print("매장 이름을 입력해주세요 : ");
        String store = sc.nextLine();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("viewMenuOfStore");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,store);
            rset = pstmt.executeQuery();
            while(rset.next()){
                System.out.print(rset.getString("b.menu_name") + " : ");
                System.out.println(rset.getString("b.menu_price"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
    }

    // 1. 사용자 등록(회원가입 후 사용자 구분 사장인지 주문자인지)
    public void userRegistration(Connection con) {
        System.out.println("사용자를 등록합니다.");
        Scanner sc = new Scanner(System.in);
        System.out.print("사용자 이름 : ");
        String name = sc.nextLine();
        System.out.print("사용자 전화번호 : ");
        String phone = sc.nextLine();
        System.out.print("사용자 주소 : ");
        String address = sc.nextLine();
        System.out.print("사용자 구분 : ");
        String dist = sc.nextLine();
        System.out.print("아이디 : ");
        String id = sc.nextLine();
        System.out.print("비밀번호 : ");
        String pwd = sc.nextLine();

        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("userRegistration");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,name);
            pstmt.setString(2,phone);
            pstmt.setString(3,address);
            pstmt.setString(4,dist);
            pstmt.setString(5,id);
            pstmt.setString(6,pwd);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
    }


    // 전체 매장 조회
    public void viewAllStore(Connection con){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("viewAllStore");

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while(rset.next()){
                Properties p = new Properties();
                p.setProperty(rset.getString("store_name"),rset.getString("user_name"));
                System.out.println("p = " + p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
    }

    // 메뉴명으로 조회
    public void viewMenu(Connection con) {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회하실 메뉴 명을 입력해주세요 : ");
        String menuName = sc.nextLine();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("viewMenu");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuName);
            rset = pstmt.executeQuery();

            if(rset.next()){
                System.out.println("메뉴명 : " + menuName);
                System.out.println("매장명 : " + rset.getString("b.store_name"));
                System.out.println("메뉴 가격 : " + rset.getInt("a.menu_price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }

    }
    // 메뉴 삭제
    public void deleteMenu(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("deleteMenu");

        Scanner sc = new Scanner(System.in);
        System.out.print("삭제하실 메뉴명을 입력해주세요 : ");
        String delMenu = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,delMenu);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        if(result>0){
            System.out.println("메뉴 삭제 완료");
        } else {
            System.out.println("메뉴 삭제 실패");
        }
    }

    // 로그인
    public int login(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("login");

        Scanner sc = new Scanner(System.in);
        System.out.print("아이디를 입력하세요 : ");
        String userId = sc.nextLine();
        System.out.print("비밀번호를를 입력하세요 : ");
        String userPwd = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,userId);
            pstmt.setString(2,userPwd);
            rset = pstmt.executeQuery();

            if(rset.next()){
                userCode= rset.getInt("user_code");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        return userCode;
    }

    // 로그인 완료되면 사장 or 주문자인지 알림해주고 사장이면0, 주문자이면 1을 반환.
    public int loginSuccess(int userCode){
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int result = 0;

        String query= prop.getProperty("userDistinctionByCode");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,userCode);
            rset = pstmt.executeQuery();

            if(rset.next()){
                String dist = rset.getString("distinction");
                System.out.println("환영합니다! " + rset.getString("user_name")+ "님");
                System.out.println(dist+"(으)로 로그인합니다.");

                switch(dist){
                    case "주문자" : result = 1;break;
                    case "사장" : result = 0 ;break;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }
        return result;
    }
}
