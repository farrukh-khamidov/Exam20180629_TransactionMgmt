package transactions;

public class Transaction {
    private String transactionId;
    private Carrier carrier;
    private Request request;
    private Offer offer;

    private int score;

    public Transaction(String transactionId, Carrier carrier, Request request, Offer offer) {
        this.transactionId = transactionId;
        this.carrier = carrier;
        this.request = request;
        this.offer = offer;
        this.score = 0;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public Request getRequest() {
        return request;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", carrier=" + carrier.getName() +
                ", request=" + request.getRequestId() +
                ", deliveryPlace=" + request.getPlace().getName() +
                ", deliveryRegion=" + request.getPlace().getRegion().getName() +
                ", offer=" + offer.getOfferId() +
                ", score=" + score +
                "}\n";
    }
}
