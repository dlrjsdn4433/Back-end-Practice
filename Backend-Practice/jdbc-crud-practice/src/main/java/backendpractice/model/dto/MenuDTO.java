package backendpractice.model.dto;

public class MenuDTO {

    private String name;
    private int price;
    private int time;
    private String orderableStatus;
    private int storeCode;


    public MenuDTO () {}

    public MenuDTO(String name, int time, String orderableStatus, int storeCode, int price) {
        this.name = name;
        this.time = time;
        this.orderableStatus = orderableStatus;
        this.storeCode = storeCode;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "메뉴명 = '" + name + '\'' +
                ", 메뉴 가격 = " + price +
                ", 조리 시간 = " + time +
                ", 주문 가능 여부 = '" + orderableStatus + '\'' +
                '}';
    }
}

