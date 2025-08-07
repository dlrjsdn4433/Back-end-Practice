package backendpractice.model.dto;

public class MenuDTO {

    private String name;
    private int time;
    private String orderableStatus;
    private int storeCode;

    public MenuDTO () {}

    public MenuDTO(String name, int time, String orderableStatus, int storeCode) {
        this.name = name;
        this.time = time;
        this.orderableStatus = orderableStatus;
        this.storeCode = storeCode;
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

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", orderableStatus='" + orderableStatus + '\'' +
                ", storeCode=" + storeCode +
                '}';
    }
}
