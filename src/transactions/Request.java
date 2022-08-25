package transactions;

public class Request {
    private String requestId;
    private String productId;
    private Place place;

    public Request(String requestId, String productId, Place place) {
        this.requestId = requestId;
        this.productId = productId;
        this.place = place;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getProductId() {
        return productId;
    }

    public Place getPlace() {
        return place;
    }
}
