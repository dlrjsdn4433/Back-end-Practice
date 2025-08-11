package backendpractice.model.dao;

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



public class UserDAO {
    private Properties prop = new Properties();
    Scanner sc = new Scanner(System.in);
    private int userCode;

    public UserDAO(){
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

    // 1. 사용자 등록(회원가입 후 사용자 구분 사장인지 주문자인지)
    public void userRegistration(Connection con) {
        System.out.println("");
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
        if(result > 0 ){
            System.out.println("회원가입 성공!");
        } else{
            System.out.println("회원가입 실패!");
        }
    }

    // 로그인
    public int login(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("login");

        Scanner sc = new Scanner(System.in);
        System.out.println("");
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
    // 로그아웃 -> usercode 0으로 초기화
    public int logout(Connection con){
        userCode=0;
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
//                System.out.println("환영합니다! " + rset.getString("user_name")+ "님");
//                System.out.println(dist+"(으)로 로그인합니다.");

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
