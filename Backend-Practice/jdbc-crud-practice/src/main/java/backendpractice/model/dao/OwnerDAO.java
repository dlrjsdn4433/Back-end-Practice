package backendpractice.model.dao;

import backendpractice.model.dto.ReviewDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static backendpractice.common.JDBCTemplate.close;
import static backendpractice.common.JDBCTemplate.getConnection;

public class OwnerDAO extends UserDAO{

    Properties prop = new Properties();
    UserDAO user = new UserDAO();
    private int userCode;
    Scanner sc = new Scanner(System.in);

    public OwnerDAO (){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/backendpractice/mapper/owner-query.xml"));
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
        int usercode = getUserCode();
//        System.out.println(usercode);


        try {
            pstmt = con.prepareStatement(query);

//            pstmt.setString(1,"");
            pstmt.setString(1,storeName);
            pstmt.setInt(2,usercode);
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
    public void registMenu(Connection con){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("registMenu");

        System.out.println("메뉴를 등록합니다.");
        System.out.println("메뉴를 등록할 매장 명을 입력해주세요 : ");
        String storeName = sc.nextLine();
        System.out.println("등록하실 메뉴 명을 입력해주세요 : ");
        String menuName = sc.nextLine();
        System.out.println("등록하실 메뉴의 가격을 입력해주세요 : ");
        int menuPrice = sc.nextInt();
        sc.nextLine();
        System.out.println("등록하실 메뉴의 조리시간을 입력해주세요 : ");
        int cookingTime = sc.nextInt();
        sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,storeName);
            pstmt.setInt(2,getUserCode());
            pstmt.setString(3,menuName);
            pstmt.setInt(4,menuPrice);
            pstmt.setInt(5,cookingTime);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        if(result>0){
            System.out.println("메뉴 등록 완료!");
        } else {
            System.out.println("메뉴 등록 실패!");
        }
    }

    public void modifyMenuName(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("modifyMenuName");

        System.out.println("메뉴 이름을 수정합니다.");
        System.out.println("수정하실 메뉴가 있는 매장명을 입력해주세요 : ");
        String storeName = sc.nextLine();
        System.out.println("메뉴의 기존 이름을 입력해주세요 : ");
        String sMenuName = sc.nextLine();
        System.out.println("메뉴의 변경 후 이름을 입력해주세요 : ");
        String mMenuName = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,mMenuName);
            pstmt.setString(2,sMenuName);
            pstmt.setInt(3,getUserCode());
            pstmt.setString(4,storeName);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result>0){
            System.out.println("메뉴명 수정 완료!");
        } else {
            System.out.println("메뉴명 수정 실패!");
        }
    }

    public void modifyMenuPrice(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("modifyMenuPrice");

        System.out.println("메뉴 가격을 수정합니다.");
        System.out.println("수정하실 메뉴가 있는 매장명을 입력해주세요 : ");
        String storeName = sc.nextLine();
        System.out.println("가격을 수정하실 메뉴의 이름을 입력해주세요 : ");
        String menuName = sc.nextLine();
        System.out.println("메뉴의 변경 후 가격을 입력해주세요 : ");
        int menuPrice = sc.nextInt();
        sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,menuPrice);
            pstmt.setString(2,menuName);
            pstmt.setInt(3,getUserCode());
            pstmt.setString(4,storeName);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result>0){
            System.out.println("메뉴 가격 수정 완료!");
        } else {
            System.out.println("메뉴 가격 수정 실패!");
        }
    }

    public void deleteMenu(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("deleteMenu");

        System.out.println("메뉴를 삭제합니다.");
        System.out.println("삭제하실 메뉴가 있는 매장명을 입력해주세요 : ");
        String storeName = sc.nextLine();
        System.out.println("삭제하실 메뉴명을 입력해주세요 : ");
        String menuName = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuName);
            pstmt.setInt(2,getUserCode());
            pstmt.setString(3,storeName);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result>0){
            System.out.println("메뉴 삭제 완료!");
        } else {
            System.out.println("메뉴 삭제 실패!");
        }
    }

    public void viewReview(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ReviewDTO review = null;
        String query = prop.getProperty("viewReview");

        System.out.println("리뷰를 조회합니다.");
        System.out.println("매장명 : ");
        String storeName = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(2,getUserCode());
            pstmt.setString(1,storeName);
            rset = pstmt.executeQuery();
            while(rset.next()){
                review = new ReviewDTO();
                review.setReview(rset.getString("review"));
                review.setOrderCode(rset.getInt("order_code"));
                review.setStoreName(rset.getString("store_name"));

                System.out.println("review = " + review.showReviewsToOwners());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
            close(rset);
        }
    }

//    public void owner(Connection con){
//
//        while(true){
//            System.out.println("");
//            System.out.println("=============사장 메뉴===============");
//            System.out.println("");
//            System.out.println("1. 매장 관리");
//            System.out.println("2. 메뉴 관리");
//            System.out.println("3. 리뷰 조회");
//            System.out.println("0. 로그아웃");
//            System.out.println("");
//            System.out.println("메뉴 선택 : ");
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//            switch(choice){
//                case 1 :
//                    while(true){
//                        System.out.println("");
//                        System.out.println("=============매장 관리=============");
//                        System.out.println("");
//                        System.out.println("1. 매장 추가");
//                        System.out.println("2. 매장명 수정");
//                        System.out.println("3. 매장 삭제");
//                        System.out.println("0. 뒤로 가기");
//                        System.out.println("");
//                        System.out.println("메뉴 선택 : ");
//                        int num = sc.nextInt();
//
//                        switch(num){
//                            case 1 : reigstStore(con);break;
//                            case 2 : modifyStoreName(con);break;
//                            case 3 : deleteStore(con);break;
//                            case 0 : break;
//                        }
//                        if(num==0){
//                            break;
//                        }
//                    }
//                    break;
//                case 2 :
//                    while(true){
//                        System.out.println("");
//                        System.out.println("=============메뉴 관리=============");
//                        System.out.println("");
//                        System.out.println("1. 메뉴 추가");
//                        System.out.println("2. 메뉴명 수정");
//                        System.out.println("3. 메뉴가격 수정");
//                        System.out.println("4. 메뉴 삭제");
//                        System.out.println("0. 뒤로 가기");
//                        System.out.println("");
//                        System.out.println("메뉴 선택 : ");
//                        int num = sc.nextInt();
//
//                        switch(num){
//                            case 1 : registMenu(con);break;
//                            case 2 : modifyMenuName(con);break;
//                            case 3 : modifyMenuPrice(con);break;
//                            case 4 : deleteMenu(con); break;
//                            case 0 : break;
//                        }
//                        if(num==0){
//                            break;
//                        }
//                    }
//                    break;
//                case 3 : viewReview(con); break;
//                case 0 : user.logout(con); break;
//            }
//            if(choice ==0){
//                break;
//            }
//        }
//    }
}

























