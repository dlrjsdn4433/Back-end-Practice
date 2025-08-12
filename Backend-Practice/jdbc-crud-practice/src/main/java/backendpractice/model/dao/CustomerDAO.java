package backendpractice.model.dao;

import backendpractice.model.dto.MenuDTO;
import backendpractice.model.dto.OrderDTO;
import backendpractice.model.dto.ReviewDTO;
import backendpractice.model.dto.StoreDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static backendpractice.common.JDBCTemplate.close;

public class CustomerDAO {

    UserDAO user = new UserDAO();
    private int userCode;
    Properties prop = new Properties();
    Scanner sc= new Scanner(System.in);

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public CustomerDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/backendpractice/mapper/Customer-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void viewAllStore(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        StoreDTO store = null;
        String query = prop.getProperty("viewAllStore");

        System.out.println("전체 매장을 조회합니다.");
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while(rset.next()){
                store = new StoreDTO();
                store.setUserCode(rset.getInt("user_code"));
                store.setStoreCode(rset.getInt("store_code"));
                store.setName(rset.getString("store_name"));

                System.out.println("store = " + store);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }
    }

    public void searchStore(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("searchStore");
        MenuDTO menu = null;

        System.out.println("매장을 조회합니다.");
        System.out.println("조회하실 매장 명을 입력해주세요 : ");
        String storeName = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,storeName);
            rset = pstmt.executeQuery();
            while(rset.next()){
                menu = new MenuDTO();
                menu.setName(rset.getString("menu_name"));
                menu.setPrice(rset.getInt("menu_price"));
                menu.setTime(rset.getInt("cooking_time"));
                menu.setOrderableStatus(rset.getString("orderable_status"));

                System.out.println("menu = " + menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
    }
    public void searchStore(Connection con, String sname) {
        PreparedStatement pstmt1 = null;
        ResultSet rset1 = null;
        String query = prop.getProperty("searchStore");
        MenuDTO menu = null;

        try {
            pstmt1 = con.prepareStatement(query);
            pstmt1.setString(1,sname);
            rset1 = pstmt1.executeQuery();
            while(rset1.next()){
                menu = new MenuDTO();
                menu.setName(rset1.getString("menu_name"));
                menu.setPrice(rset1.getInt("menu_price"));
                menu.setTime(rset1.getInt("cooking_time"));
                menu.setOrderableStatus(rset1.getString("orderable_status"));

                System.out.println("menu = " + menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt1);
            close(rset1);
        }
    }

    public void searchMenuName(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("searchMenuName");

        System.out.println("메뉴명 검색 조회를 시작합니다.");
        System.out.println("조회하실 메뉴명을 입력해주세요 : ");
        String menuName = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,menuName);
            rset = pstmt.executeQuery();
            while(rset.next()){
                String sname = rset.getString("store_name");
                String mname = rset.getString("menu_name");
                int mprice = rset.getInt("menu_price");
                System.out.println("매장명 : " + sname);
                System.out.println("메뉴명 : " + mname);
                System.out.println("메뉴가격 : " + mprice);
                System.out.println("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }
    }

    public void order(Connection con) {

        PreparedStatement pstmt = null;
        int result= 0;
        String query = prop.getProperty("order");

        System.out.println("주문을 시작합니다.");
        viewAllStore(con);
        System.out.println("매장을 선택하세요 : ");
        String storeName = sc.nextLine();

        searchStore(con,storeName);
        System.out.println("메뉴 선택하세요 : ");
        String menuName = sc.nextLine();


        System.out.println("메뉴 수량을 선택하세요 : ");
        int menuAmount = sc.nextInt();
        sc.nextLine();

        System.out.println("1. 한집 배달 : 10분, 배달비 + 2000");
        System.out.println("2. 알뜰 배달 : 20분, 배달비 + 1000");
        System.out.println("3. 기본 배달 : 25분, 배달비 무료" );
        System.out.println("배달 방식을 선택하세요 : ");
        int dm = sc.nextInt();
        sc.nextLine();
        int dt=0;
        int dp=0;
        switch(dm){
            case 1: dt = 10; dp = 2000; break;
            case 2: dt = 20; dp = 1000; break;
            case 3: dt = 25; break;
            default:
                System.out.println("잘못 입력하셨습니다.");
                System.out.println("기본 배달로 자동 선택됩니다.");
                dt = 25;
                break;
        }
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,dt);
            pstmt.setString(2,menuName);
            pstmt.setString(3,storeName);
            pstmt.setInt(4,userCode);
            pstmt.setString(5,menuName);
            pstmt.setInt(6,menuAmount);
            pstmt.setString(7,storeName);
            pstmt.setString(8,menuName);
            pstmt.setString(9,storeName);
            pstmt.setInt(10,menuAmount);
            pstmt.setInt(11,dp);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        if(result >0){
            System.out.println("주문 성공!");
        } else {
            System.out.println("주문 실패!");
        }
    }



    public void deleteOrder(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("deleteOrder");

        System.out.println("주문을 삭제합니다.");
        viewMyOrder(con);
        System.out.println("삭제하실 주문의 번호를 입력해주세요 : ");
        int dnum = sc.nextInt();
        sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,dnum);
            pstmt.setInt(2,userCode);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result >0){
            System.out.println("주문 삭제 성공!");
        } else {
            System.out.println("주문 삭제 실패!");
        }
    }

    public void viewMyOrder(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        OrderDTO order = null;
        String query = prop.getProperty("viewMyOrder");

        System.out.println("주문 목록을 조회합니다.");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,userCode);
            rset = pstmt.executeQuery();
            while(rset.next()){
                order = new OrderDTO();
                order.setOrdercode(rset.getInt("order_code"));
                order.setTime(rset.getInt("delivery_time"));
                order.setUserCode(rset.getInt("user_code"));
                order.setMenuName(rset.getString("menu_name"));
                order.setAmount(rset.getInt("menu_amount"));
                order.setTotalPrice(rset.getInt("total_price"));
                order.setStoreCode(rset.getInt("store_code"));
                order.setReviewStatus(rset.getString("review_status"));

                System.out.println( order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }
    }

    // 리뷰 없는 주문만 조회하는 메소드
    public void viewMyOrderWithoutReview(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        OrderDTO order = null;
        String query = prop.getProperty("viewMyOrderWithoutReview");

        System.out.println("주문 목록을 조회합니다.");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,userCode);
            rset = pstmt.executeQuery();
            while(rset.next()){
                order = new OrderDTO();
                order.setOrdercode(rset.getInt("order_code"));
                order.setTime(rset.getInt("delivery_time"));
                order.setUserCode(rset.getInt("user_code"));
                order.setMenuName(rset.getString("menu_name"));
                order.setAmount(rset.getInt("menu_amount"));
                order.setTotalPrice(rset.getInt("total_price"));
                order.setStoreCode(rset.getInt("store_code"));
                order.setReviewStatus(rset.getString("review_status"));

                System.out.println(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }
    }

    public void modifyOrder(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("modifyOrder");

        System.out.println("주문의 수량을 수정합니다.");
        viewMyOrder(con);
        System.out.println("수정할 주문의 번호를 입력해주세요 : ");
        int mnum = sc.nextInt();
        System.out.println("변경할 수량을 입력해주세요 : ");
        int mamount = sc.nextInt();
        sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,mamount);
            pstmt.setInt(2,mnum);
            pstmt.setInt(3,userCode);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result >0){
            System.out.println("주문량 수정 성공!");
        } else {
            System.out.println("주문량 수정 실패!");
        }
    }

    // 리뷰 작성하면 주문의 리뷰 작성 유무 y로 바뀌게
    public void registReview(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("registReview");

        System.out.println("리뷰를 작성합니다.");
        System.out.println("");
        viewMyOrderWithoutReview(con);
        System.out.println("");
        System.out.println("리뷰를 작성할 주문 번호를 입력해주세요 : ");
        int onum = sc.nextInt();
        sc.nextLine();
        System.out.println("리뷰 내용을 입력해주세요 : ");
        String review = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,review);
            pstmt.setInt(2,userCode);
            pstmt.setInt(3,onum);
            pstmt.setInt(4,onum);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        updateOrders(con,onum);

        if(result >0){
            System.out.println("리뷰 작성 성공!");
        } else {
            System.out.println("리뷰 작성 실패!");
        }
    }

    public void updateOrders(Connection con,int onum){
        PreparedStatement pstmt2 = null;
        int result2 = 0;
        String query2 = prop.getProperty("updateOrders");

        try {
            pstmt2 = con.prepareStatement(query2);
            pstmt2.setInt(1,onum);
            pstmt2.setInt(2,getUserCode());
            result2 = pstmt2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt2);
        }
    }

    public void modifyReview(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("modifyReview");

        System.out.println("리뷰를 수정합니다.");
        viewMyReview(con);
        System.out.println("수정할 리뷰의 주문번호를 입력해주세요 : ");
        int onum = sc.nextInt();
        sc.nextLine();
        System.out.println("수정할 리뷰의 내용을 입력해주세요 : ");
        String mReview = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,mReview);
            pstmt.setInt(2,onum);
            pstmt.setInt(3,userCode);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result >0){
            System.out.println("리뷰 수정 성공!");
        } else {
            System.out.println("리뷰 수정 실패!");
        }

    }

    public void deleteReview(Connection con) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("deleteReview");

        System.out.println("리뷰를 삭제합니다.");
        viewMyReview(con);
        System.out.println("삭제할 리뷰의 주문번호를 입력해주세요 : ");
        int onum = sc.nextInt();
        sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,onum);
            pstmt.setInt(2,userCode);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }
        if(result >0){
            System.out.println("리뷰 삭제 성공!");
        } else {
            System.out.println("리뷰 삭제 실패!");
        }

    }

    public void viewMyReview(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ReviewDTO review = null;
        String query = prop.getProperty("viewMyReview");

        System.out.println("사용자의 리뷰를 조회합니다.");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,userCode);
            rset = pstmt.executeQuery();
            while (rset.next()){
                review = new ReviewDTO();
                review.setReview(rset.getString("review"));
                review.setOrderCode(rset.getInt("order_code"));
                review.setUserCode(rset.getInt("user_code"));
                review.setStoreCode(rset.getInt("store_code"));

                System.out.println("review = " + review.showReviewToCustomers());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }

    }
//    public void customer(Connection con){
//
//        while(true){
//            System.out.println("==========주문자 메뉴==========");
//            System.out.println("");
//            System.out.println("1. 매장 및 메뉴 조회");
//            System.out.println("2. 주문 하기");
//            System.out.println("3. 리뷰 하기");
//            System.out.println("0. 로그아웃");
//            System.out.println("");
//            System.out.println("메뉴 선택 : ");
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//
//            switch(choice){
//                case 1 :
//                    while(true){
//                        System.out.println("");
//                        System.out.println("==========매장 및 메뉴 조회==========");
//                        System.out.println("");
//                        System.out.println("1. 전체 매장 조회");
//                        System.out.println("2. 매장명 검색 조회");
//                        System.out.println("3. 메뉴명 검색 조회");
//                        System.out.println("0. 뒤로 가기");
//                        System.out.println("");
//                        System.out.println("메뉴 선택 : ");
//                        int num = sc.nextInt();
//
//                        switch(num){
//                            case 1 : viewAllStore(con);break;
//                            case 2 : searchStore(con);break;
//                            case 3 : searchMenuName(con);break;
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
//                        System.out.println("==========주문 하기==========");
//                        System.out.println("");
//                        System.out.println("1. 주문 추가");
//                        System.out.println("2. 주문량 수정");
//                        System.out.println("3. 주문 삭제");
//                        System.out.println("4. 주문 조회");
//                        System.out.println("0. 뒤로 가기");
//                        System.out.println("");
//                        System.out.println("메뉴 선택 : ");
//                        int num = sc.nextInt();
//                        sc.nextLine();
//
//                        switch(num){
//                            case 1 : order(con);break;
//                            case 2 : modifyOrder(con);break;
//                            case 3 : deleteOrder(con);break;
//                            case 4 : viewMyOrder(con); break;
//                            case 0 : break;
//                        }
//                        if(num==0){
//                            break;
//                        }
//                    }
//                    break;
//                case 3 :
//                    while(true){
//                        System.out.println("");
//                        System.out.println("==========리뷰 하기==========");
//                        System.out.println("");
//                        System.out.println("1. 리뷰 작성");
//                        System.out.println("2. 리뷰 수정");
//                        System.out.println("3. 리뷰 삭제");
//                        System.out.println("4. 리뷰 조회");
//                        System.out.println("0. 뒤로 가기");
//                        System.out.println("");
//                        System.out.println("메뉴 선택 : ");
//                        int num = sc.nextInt();
//
//
//                        switch(num){
//                            case 1 : registReview(con);break;
//                            case 2 : modifyReview(con);break;
//                            case 3 : deleteReview(con);break;
//                            case 4 : viewMyReview(con); break;
//                            case 0 : break;
//                        }
//                        if(num==0){
//                            break;
//                        }
//                    }
//                    break;
//                case 0 : user.logout(con); break;
//            }
//            if(choice ==0){
//                break;
//            }
//        }
//    }


}

















