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

public class OwnerDAO {

    Properties prop = new Properties();
    private int userCode=0;
    Scanner sc = new Scanner(System.in);

    public OwnerDAO (){
        try {
            prop.load(new FileInputStream("src/main/java/backendpractice/mapper/Owner-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int user_code) {
        this.userCode = user_code;
    }
    /*  1. 매장 등록
        2. 매장명 수정
        3. 매장 삭제
        4. 메뉴 등록
        5. 메뉴명 수정
        6. 메뉴가격 수정
        7. 메뉴 삭제
        8. 리뷰 조회
        */

    // 1. 매장 등록
    public void reigstStore(Connection con){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("registStore");

        System.out.println("매장을 등록합니다.");
        System.out.print("등록하실 매장 명을 입력해주세요 : ");
        String storeName = sc.nextLine();


        try {
            pstmt = con.prepareStatement(query);
//            pstmt.setString(1,"");
            pstmt.setString(2,storeName);
            pstmt.setInt(3,getUserCode());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            close(pstmt);
        }
        if(result>0){
            System.out.println("매장 등록 완료!");
        } else {
            System.out.println("매장 등록 실패!");
        }
    }

    // 2. 매장명 수정
    public void modifyStoreName(Connection con){
        PreparedStatement pstmt = null;
        int result = 0;
        String qeury = prop.getProperty("modifyStoreName");

        System.out.println("매장명을 수정합니다.");
        System.out.print("기존 매장명을 입력하세요 : ");
        String sname = sc.nextLine();
        System.out.print("수정하실 매장명을 입력하세요 : ");
        String mname = sc.nextLine();

        try {
            pstmt = con.prepareStatement(qeury);
            pstmt.setString(1,mname);
            pstmt.setString(2,sname);
            pstmt.setInt(3,getUserCode());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result>0){
            System.out.println("매장명 수정 완료!");
        } else {
            System.out.println("매장명 수정 실패!");
        }
    }

    // 매장 삭제
    public void deleteStore(Connection con){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("deleteStore");

        System.out.println("매장을 삭제합니다.");
        System.out.print("삭제할 매장명을 입력해주세요 : ");
        String dname = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,dname);
            pstmt.setInt(2,getUserCode());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result>0){
            System.out.println("매장 삭제 완료!");
        } else {
            System.out.println("매장 삭제 실패!");
        }
    }

    // 4. 메뉴 등록

}

























