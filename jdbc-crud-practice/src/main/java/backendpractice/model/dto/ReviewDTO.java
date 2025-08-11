package backendpractice.model.dto;

public class ReviewDTO {

    private int reviewCode;
    private int userCode;
    private String review;
    private int storeCode;

    public ReviewDTO(){}

    public ReviewDTO(int reviewCode, int userCode, String review, int storeCode) {
        this.reviewCode = reviewCode;
        this.userCode = userCode;
        this.review = review;
        this.storeCode = storeCode;
    }

    public int getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(int reviewCode) {
        this.reviewCode = reviewCode;
    }

    public int getUserCode() {
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

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewCode=" + reviewCode +
                ", userCode=" + userCode +
                ", review='" + review + '\'' +
                ", storeCode=" + storeCode +
                '}';
    }
}
