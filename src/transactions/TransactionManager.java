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

//R1
	public List<String> addRegion(String regionName, String... placeNames) {
		Region region = new Region(regionName);
		regionMap.put(regionName, region);
		Stream.of(placeNames).filter(placeName -> !placeMap.containsKey(placeName)).map(Place::new).forEach(place -> {
			region.getPlaces().add(place);
			placeMap.put(place.getName(), place);
		});
		return region.getPlaces().stream().map(Place::getName).sorted().toList();
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) {
		Carrier carrier = new Carrier(carrierName);
		carrierMap.put(carrierName, carrier);
		Stream.of(regionNames).forEach(r -> carrier.getRegions().add(regionMap.get(r)));
		return carrier.getRegions().stream().map(Region::getName).sorted().toList();
	}
	
	public List<String> getCarriersForRegion(String regionName) { 
		return new ArrayList<String>();
	}
	
//R2
	public void addRequest(String requestId, String placeName, String productId) 
			throws TMException {
	}
	
	public void addOffer(String offerId, String placeName, String productId) 
			throws TMException {
	}
	

//R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId) 
			throws TMException {
	}
	
	public boolean evaluateTransaction(String transactionId, int score) {
		return false;
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
	
	
}

