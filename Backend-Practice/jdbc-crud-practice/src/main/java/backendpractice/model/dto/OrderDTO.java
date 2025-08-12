package backendpractice.model.dto;

public class OrderDTO {

    private int orderCode;
    private int time;
    private int userCode;
    private String menuName;
    private int amount;
    private int storeCode;
    private String reviewStatus;
    private int totalPrice;

    public OrderDTO (){}

    public OrderDTO(int ordercode, int time, int userCode,String menuName, int amount, int storeCode) {
        this.orderCode = ordercode;
        this.time = time;
        this.userCode = userCode;
        this.menuName = menuName;
        this.amount = amount;
        this.storeCode = storeCode;
    }

    public int getOrdercode() {
        return orderCode;
    }

    public void setOrdercode(int ordercode) {
        this.orderCode = ordercode;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return //"주문 내역{" +
                "주문 번호 = " + orderCode +
                ", 배달 시간 = " + time +
//                ", 주문자 번호 = " + userCode +
                ", 메뉴명 = '" + menuName + '\'' +
                ", 메뉴 수량 = " + amount +
                ", 매장 코드 = " + storeCode +
//                ", 리뷰 작성 여부 = " + reviewStatus +
                ", 총 가격 = " + totalPrice;
//                + '}';
    }
}
