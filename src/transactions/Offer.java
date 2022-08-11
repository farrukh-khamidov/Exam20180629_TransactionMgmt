package transactions;

public class Offer {
    private String offerId;
    private String productId;

    private Place place;

    public Offer(String offerId, String productId, Place place) {
        this.offerId = offerId;
        this.productId = productId;
        this.place = place;
    }

    public String getOfferId() {
        return offerId;
    }

    public String getProductId() {
        return productId;
    }

    public Place getPlace() {
        return place;
    }
}
