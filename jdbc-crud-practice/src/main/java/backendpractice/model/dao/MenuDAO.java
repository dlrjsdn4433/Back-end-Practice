package backendpractice.model.dao;


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

public class MenuDAO {

    private Properties prop = new Properties();

    public MenuDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/backendpractice/mapper/baedaldb-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
               // System.out.println(rset.getString("a.store_name"));
                System.out.println(rset.getString("b.menu_name"));
                System.out.println(rset.getString("b.menu_price"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
    }

    // 1. 사용자 등록
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

        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("userRegistration");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,name);
            pstmt.setString(2,phone);
            pstmt.setString(3,address);
            pstmt.setString(4,dist);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
    }

    // 2. 입력한 사용자가 사장인지 주문자인지 확인하는 메소드
    // 사장이면 1, 주문자면 0을 리턴
    public int userDistinction(Connection con) {
        Scanner sc = new Scanner(System.in);
        System.out.print("사용자 이름을 입력해주세요 : ");
        String name = sc.nextLine();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int result = 0;
        String dist = "";

        String query = prop.getProperty("userDistinction");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,name);
            rset = pstmt.executeQuery();

            if(rset.next()) {
                dist = rset.getString("distinction");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        switch(dist){
            case "사장":
                result = 1;
                break;
            case "주문자":
                result = 0;
                break;
        }
        return result;
    }



}
