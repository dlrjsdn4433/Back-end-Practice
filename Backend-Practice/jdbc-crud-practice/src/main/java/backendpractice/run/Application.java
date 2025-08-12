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
        Application app = new Application();

        /*
        * 추가할 기능
        * 1.주문 테이블에 리뷰 작성 유무 칼럼 추가 (기본값 n) 리뷰 작성하는 쿼리문에 해당 주문번호의
        * 리뷰 작성 유무 칼럼의 속성 값이 y로 업데이트 되게 하고, 리뷰 작성 메소드 안에 있는 주문 조회 메소드는
        * 리뷰 작성 유무 칼럼의 값이 n인 주문만 조회되는 쿼리문 추가. // 해결
        *
        * 2. 주문에 메뉴수량 * 메뉴가격으로 가격 책정하기 // 해결
        *
        *
        * 사장 리뷰 조회 시 주문자번호가 다 0으로 나오는 문제 해결해야함
        * */
        while(true){
            System.out.println("=========배달 프로그램=========");
            System.out.println("");
            System.out.println("1. 회원가입 ");
            System.out.println("2. 로그인 ");
            System.out.println("0. 프로그램 종료 ");
            System.out.println("");
            System.out.print("메뉴 선택 : ");
            int choice = sc.nextInt();


            // 회원가입 or 로그인 선택, 회원가입하면 다시 돌아오고 로그인하면
            // 로그인한 사용자 구분에 따라 기능 실행
                switch(choice){
                    case 1 :
                        user.userRegistration(con);
                        break;
                    case 2 :
                        int num = user.login(con); // num은 로그인한 사용자의 usercode로 초기화
                        int dist = user.loginSuccess(num); // dist는 사용자의 구분에 따라 사장이면 0, 주문자면 1로 초기화

                        System.out.println("num = " + num);
                        System.out.println("dist = " + dist);
                        
                        // usercode를 로그인한 사용자의 것으로 초기화
                        if(dist==0){
                            owner.setUserCode(num);
                        }else if(dist==1){
                            customer.setUserCode(num);
                        }
                        app.setUserCode(num);
                        System.out.println("초기화 후");
                        while(true){
                            switch(dist){
                                case 0 :
                                    app.owner(con); // 메소드 안에 switch 문으로 사용자가 사장일 때 하는 기능 실행
                                    break;
                                case 1 :
                                    app.customer(con); // 사용자가 주문자일때 하는 기능 실행
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
    Scanner sc = new Scanner(System.in);
    CustomerDAO customer = new CustomerDAO();
    OwnerDAO owner = new OwnerDAO();
    UserDAO user = new UserDAO();
    private int userCode = 0;

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void customer(Connection con){
        customer.setUserCode(getUserCode());
        System.out.println("customer.getUserCode() = " + customer.getUserCode());
        while(true){
            System.out.println("==========주문자 메뉴==========");
            System.out.println("");
            System.out.println("1. 매장 및 메뉴 조회");
            System.out.println("2. 주문 하기");
            System.out.println("3. 리뷰 하기");
            System.out.println("0. 로그아웃");
            System.out.println("");
            System.out.println("메뉴 선택 : ");
            int choice = sc.nextInt();
            sc.nextLine();


            switch(choice){
                case 1 :
                    while(true){
                        System.out.println("");
                        System.out.println("==========매장 및 메뉴 조회==========");
                        System.out.println("");
                        System.out.println("1. 전체 매장 조회");
                        System.out.println("2. 매장명 검색 조회");
                        System.out.println("3. 메뉴명 검색 조회");
                        System.out.println("0. 뒤로 가기");
                        System.out.println("");
                        System.out.println("메뉴 선택 : ");
                        int num = sc.nextInt();

                        switch(num){
                            case 1 : customer.viewAllStore(con);break;
                            case 2 : customer.searchStore(con);break;
                            case 3 : customer.searchMenuName(con);break;
                            case 0 : break;
                        }
                        if(num==0){
                            break;
                        }
                    }
                    break;
                case 2 :
                    while(true){
                        System.out.println("");
                        System.out.println("==========주문 하기==========");
                        System.out.println("");
                        System.out.println("1. 주문 추가");
                        System.out.println("2. 주문량 수정");
                        System.out.println("3. 주문 삭제");
                        System.out.println("4. 주문 조회");
                        System.out.println("0. 뒤로 가기");
                        System.out.println("");
                        System.out.println("메뉴 선택 : ");
                        int num = sc.nextInt();
                        sc.nextLine();

                        switch(num){
                            case 1 : customer.order(con);break;
                            case 2 : customer.modifyOrder(con);break;
                            case 3 : customer.deleteOrder(con);break;
                            case 4 : customer.viewMyOrder(con); break;
                            case 0 : break;
                        }
                        if(num==0){
                            break;
                        }
                    }
                    break;
                case 3 :
                    while(true){
                        System.out.println("");
                        System.out.println("==========리뷰 하기==========");
                        System.out.println("");
                        System.out.println("1. 리뷰 작성");
                        System.out.println("2. 리뷰 수정");
                        System.out.println("3. 리뷰 삭제");
                        System.out.println("4. 리뷰 조회");
                        System.out.println("0. 뒤로 가기");
                        System.out.println("");
                        System.out.println("메뉴 선택 : ");
                        int num = sc.nextInt();
                        sc.nextLine();


                        switch(num){
                            case 1 : customer.registReview(con);break;
                            case 2 : customer.modifyReview(con);break;
                            case 3 : customer.deleteReview(con);break;
                            case 4 : customer.viewMyReview(con); break;
                            case 0 : break;
                        }
                        if(num==0){
                            break;
                        }
                    }
                    break;
                case 0 : user.logout(con); break;
            }
            if(choice ==0){
                break;
            }
        }
    }

    public void owner(Connection con){
        owner.setUserCode(getUserCode());
        System.out.println("owner.getUserCode() = " + owner.getUserCode());
        while(true){
            System.out.println("");
            System.out.println("=============사장 메뉴===============");
            System.out.println("");
            System.out.println("1. 매장 관리");
            System.out.println("2. 메뉴 관리");
            System.out.println("3. 리뷰 조회");
            System.out.println("0. 로그아웃");
            System.out.println("");
            System.out.println("메뉴 선택 : ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1 :
                    while(true){
                        System.out.println("");
                        System.out.println("=============매장 관리=============");
                        System.out.println("");
                        System.out.println("1. 매장 추가");
                        System.out.println("2. 매장명 수정");
                        System.out.println("3. 매장 삭제");
                        System.out.println("0. 뒤로 가기");
                        System.out.println("");
                        System.out.println("메뉴 선택 : ");
                        int num = sc.nextInt();

                        switch(num){
                            case 1 : owner.reigstStore(con);break;
                            case 2 : owner.modifyStoreName(con);break;
                            case 3 : owner.deleteStore(con);break;
                            case 0 : break;
                        }
                        if(num==0){
                            break;
                        }
                    }
                    break;
                case 2 :
                    while(true){
                        System.out.println("");
                        System.out.println("=============메뉴 관리=============");
                        System.out.println("");
                        System.out.println("1. 메뉴 추가");
                        System.out.println("2. 메뉴명 수정");
                        System.out.println("3. 메뉴가격 수정");
                        System.out.println("4. 메뉴 삭제");
                        System.out.println("0. 뒤로 가기");
                        System.out.println("");
                        System.out.println("메뉴 선택 : ");
                        int num = sc.nextInt();

                        switch(num){
                            case 1 : owner.registMenu(con);break;
                            case 2 : owner.modifyMenuName(con);break;
                            case 3 : owner.modifyMenuPrice(con);break;
                            case 4 : owner.deleteMenu(con); break;
                            case 0 : break;
                        }
                        if(num==0){
                            break;
                        }
                    }
                    break;
                case 3 : owner.viewReview(con); break;
                case 0 : user.logout(con); break;
            }
            if(choice ==0){
                break;
            }
        }
    }
}




