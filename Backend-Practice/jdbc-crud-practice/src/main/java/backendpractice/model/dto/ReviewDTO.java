package backendpractice.model.dto;

public class ReviewDTO {

    private int orderCode;
    private int userCode;
    private String review;
    private int storeCode;
    private String storeName;

    public ReviewDTO(){}

    public ReviewDTO(int orderCode, int userCode, String review, int storeCode) {
        this.orderCode = orderCode;
        this.userCode = userCode;
        this.review = review;
        this.storeCode = storeCode;
    }

    public int getReviewCode() {
        return orderCode;
    }

    public void setOrderCode(int reviewCode) {
        this.orderCode = reviewCode;
    }

    public int getOrdererCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "storeName='" + storeName + '\'' +
                ", userCode=" + userCode +
                ", review='" + review + '\'' +
                '}';
    }
}
