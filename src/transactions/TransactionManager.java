package transactions;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {

	Map<String, Place> placeMap = new HashMap<>();
	Map<String, Region> regionMap = new HashMap<>();
	Map<String, Carrier> carrierMap = new HashMap<>();
	Map<String, Request> requestMap = new HashMap<>();
	Map<String, Offer> offerMap = new HashMap<>();
	Map<String, Transaction> transactionMap = new HashMap<>();

//R1
	public List<String> addRegion(String regionName, String... placeNames) {
		Region region = new Region(regionName);
		regionMap.put(regionName, region);
		Stream.of(placeNames).filter(p -> !placeMap.containsKey(p)).forEach(p -> {
			placeMap.put(p, new Place(p, region));
			region.getPlaces().add(placeMap.get(p));
		});
		return region.getPlaces().stream().map(Place::getName).sorted().toList();
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) {
		Carrier carrier = new Carrier(carrierName);
		carrierMap.put(carrierName, carrier);
		Stream.of(regionNames).filter(r -> regionMap.containsKey(r)).forEach(r -> carrier.getRegions().add(regionMap.get(r)));
		return carrier.getRegions().stream().map(Region::getName).sorted().toList();
	}
	
	public List<String> getCarriersForRegion(String regionName) { 
		return carrierMap.values().stream().filter(carrier -> carrier.getRegions().stream().map(Region::getName)
				.collect(Collectors.toSet()).contains(regionName)).map(Carrier::getName).sorted().toList();
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId)  throws TMException {
		if (!placeMap.containsKey(placeName) || requestMap.containsKey(requestId)) throw new TMException();
		requestMap.put(requestId, new Request(requestId, productId, placeMap.get(placeName)));
	}
	
	public void addOffer(String offerId, String placeName, String productId)  throws TMException {
		if (!placeMap.containsKey(placeName) || offerMap.containsKey(offerId)) throw new TMException();
		offerMap.put(offerId, new Offer(offerId, productId, placeMap.get(placeName)));
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId)  throws TMException {
		Carrier carrier = carrierMap.get(carrierName);
		Request request = requestMap.get(requestId);
		Offer offer = offerMap.get(offerId);
		if (transactionMap.values().stream().anyMatch(transaction -> transaction.getRequest().getRequestId().equals(requestId)) ||
				transactionMap.values().stream().anyMatch(transaction -> transaction.getOffer().getOfferId().equals(offerId))) throw new TMException();
		if (!request.getProductId().equals(offer.getProductId())) throw new TMException();
		if (carrier.getRegions().stream().map(Region::getPlaces).flatMap(Collection::stream).noneMatch(place -> place.getName().equals(request.getPlace().getName()))) throw new TMException();
		if (carrier.getRegions().stream().noneMatch(region -> region.getName().equals(offer.getPlace().getRegion().getName()))) throw new TMException();

		Transaction transaction = new Transaction(transactionId, carrier, request, offer);
		transactionMap.put(transactionId, transaction);
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		if (score < 1 || score > 10) return false;
		transactionMap.get(transactionId).setScore(score);
		return true;
	}
	
//R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
		return transactionMap.values().stream().collect(Collectors.groupingBy(transaction -> transaction.getRequest().getPlace().getRegion().getName(), Collectors.counting()))
				.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
				.entrySet().stream().peek(entry -> Collections.sort(entry.getValue()))
				.collect(() -> new TreeMap<>(Comparator.reverseOrder()), (m, e) -> m.put(e.getKey(), e.getValue()), TreeMap::putAll);
	}
	
	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		return new TreeMap<String, Integer>();
	}
	
	public SortedMap<String, Long> nTPerProduct() {
		return new TreeMap<String, Long>();
	}
	
	
}

