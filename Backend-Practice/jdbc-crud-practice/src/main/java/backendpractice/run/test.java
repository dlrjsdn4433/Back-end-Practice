package backendpractice.run;

import backendpractice.model.dao.CustomerDAO;
import backendpractice.model.dao.OwnerDAO;
import backendpractice.model.dao.UserDAO;

import java.sql.Connection;

import static backendpractice.common.JDBCTemplate.getConnection;

public class test {
    public static void main(String[] args) {

        Connection con = getConnection();
        OwnerDAO ownerDAO = new OwnerDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        UserDAO user = new UserDAO();

        int userCode = user.login(con);
//        ownerDAO.setUserCode(userCode);
        customerDAO.setUserCode(userCode);
        user.loginSuccess(userCode);

        // 유저코드랑 매장명이랑 안맞으면 오류 나는거 수정해야함

//        ownerDAO.reigstStore(con); // 매장 추가 해결
//        ownerDAO.modifyStoreName(con); // 매장명 수정 해결
//        ownerDAO.deleteStore(con); // 매장 삭제 해결
//        ownerDAO.registMenu(con); // 메뉴 추가 해결
//        ownerDAO.modifyMenuName(con); // 메뉴명 수정 해결
//        ownerDAO.modifyMenuPrice(con); // 메뉴가격 수정 해결
//        ownerDAO.deleteMenu(con); // 메뉴 삭제 해결
//        ownerDAO.viewReview(con); // 리뷰 조회 해결

//        customerDAO.viewAllStore(con); // 매장 전체 조회 해결 // 주문자가 조회하는거면 그냥 매장명만 출력
//        customerDAO.searchStore(con); // 매장명 검색 조회 해결
//        customerDAO.searchMenuName(con); // 메뉴명 검색 조회 해결
//        customerDAO.order(con); // 주문하기 해결
//        customerDAO.viewMyOrder(con); // 내 주문조회 해결
//        customerDAO.deleteOrder(con); // 주문 삭제 해결 // 메소드 안에 전체 주문조회 넣어야할듯 해결
//        customerDAO.modifyOrder(con); // 주문량 수정 해결
//        customerDAO.registReview(con); // 리뷰 작성 해결
//        customerDAO.modifyReview(con); // 리뷰 수정 해결
//        customerDAO.deleteReview(con); // 리뷰 삭제 해결
//        customerDAO.viewMyReview(con); // 리뷰 조회 해결




    }
}
