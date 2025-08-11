package backendpractice.model.dto;

public class OrderDTO {

    private int orderCode;
    private int time;
    private int userCode;
    private String menuName;
    private int amount;
    private int storeCode;

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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderCode=" + orderCode +
                ", time=" + time +
                ", userCode=" + userCode +
                ", menuName=" + menuName +
                ", amount=" + amount +
                ", storeCode=" + storeCode +
                '}';
    }
}
