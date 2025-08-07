package backendpractice.model.dto;

public class StoreDTO {

    private String name;
    private int userCode;
    private int storeCode;

    public StoreDTO (){}

    public StoreDTO(String name, int userCode, int storeCode) {
        this.name = name;
        this.userCode = userCode;
        this.storeCode = storeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    @Override
    public String toString() {
        return "StoreDTO{" +
                "name='" + name + '\'' +
                ", userCode=" + userCode +
                ", storeCode=" + storeCode +
                '}';
    }
}
