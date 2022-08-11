package transactions;
import java.util.*;
import java.util.stream.Stream;

//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {

	 Map<String, Region> regionMap = new HashMap<>();
	 Map<String, Place> placesMap = new HashMap<>();

	Map<String, Carrier> carriersMap = new HashMap<>();
	Map<String, Request> requestMap = new HashMap<>();
	Map<String, Offer> offerMap = new HashMap<>();
	Map<String, Transaction> transactionMap = new HashMap<>();

	
//R1
	public List<String> addRegion(String regionName, String... placeNames) {
		Region region = new Region(regionName);
		regionMap.put(regionName, region);

		for (String placeName : placeNames) {
			if (!placesMap.containsKey(placeName)) {
				Place place = new Place(placeName, region);
				region.addPlace(place);
				placesMap.put(placeName, place);
			}
		}
		List<String> regionNames = new ArrayList<>(region.getPlaces().size());
		for (Place regionPlace : region.getPlaces()) {
			regionNames.add(regionPlace.getName());
		}

		return regionNames;
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) {
		Carrier carrier = new Carrier(carrierName);
		carriersMap.put(carrierName, carrier);
		for (String regionName : regionNames) {
			if (regionMap.containsKey(regionName)) carrier.addRegion(regionMap.get(regionName));
		}
		List<String> carrierRegionNames = new ArrayList<>();
		for (Region region : carrier.getRegions()) {
			carrierRegionNames.add(region.getName());
		}
		return carrierRegionNames;
	}
	
	public List<String> getCarriersForRegion(String regionName) {
		Region region = regionMap.get(regionName);
		List<String> carrierNames = new ArrayList<>(region.getCarriers().size());
		for (Carrier carrier : region.getCarriers()) {
			carrierNames.add(carrier.getName());
		}
		return carrierNames;
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId)  throws TMException {
		if (!placesMap.containsKey(placeName)) throw new TMException();
		if (requestMap.containsKey(requestId)) throw new TMException();

		Request request = new Request(requestId, productId, placesMap.get(placeName));
		requestMap.put(requestId, request);
	}
	
	public void addOffer(String offerId, String placeName, String productId)  throws TMException {
		if (!placesMap.containsKey(placeName)) throw new TMException();
		if (offerMap.containsKey(offerId)) throw new TMException();

		Offer offer = new Offer(offerId, productId, placesMap.get(placeName));
		offerMap.put(offerId, offer);
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId)  throws TMException {

		Carrier carrier = carriersMap.get(carrierName);
		Request request = requestMap.get(requestId);
		Offer offer = offerMap.get(offerId);

		for (Transaction transaction : transactionMap.values()) {
			if (transaction.getRequest().getRequestId().equals(requestId) ||
					transaction.getOffer().getOfferId().equals(offerId)) throw new TMException();
		}
		if (!request.getProductId().equals(offer.getProductId())) throw new TMException();
		if (!carrier.getRegions().contains(request.getPlace().getRegion()) ||
				!carrier.getRegions().contains(offer.getPlace().getRegion())) throw new TMException();

		Transaction transaction = new Transaction(transactionId, carrier, request, offer);

		transactionMap.put(transactionId, transaction);
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		if (score < 1 || score > 10) return false;
		Transaction transaction = transactionMap.get(transactionId);
		transaction.setScore(score);
		return true;
	}
	
//R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {

		return new TreeMap<Long, List<String>>();
	}
	
	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		return new TreeMap<String, Integer>();
	}
	
	public SortedMap<String, Long> nTPerProduct() {
		return new TreeMap<String, Long>();
	}

	public Map<String, Transaction> getTransactionMap() {
		return transactionMap;
	}
}

