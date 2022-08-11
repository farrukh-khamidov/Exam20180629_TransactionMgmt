package transactions;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {

	Map<String, Place> placeMap = new HashMap<>();
	Map<String, Region> regionMap = new HashMap<>();

//R1
	public List<String> addRegion(String regionName, String... placeNames) {

		Region region = new Region(regionName);
		regionMap.put(regionName, region);
		for (String placeName : placeNames) {
			Place place = new Place(placeName);
			if (!placeMap.containsKey(placeName)) {
				region.getPlaces().add(place);
			}
			placeMap.put(placeName, place);
		}
		return region.getPlaces().stream().map(Place::getName).sorted().toList();
	}
	
	public List<String> addCarrier(String carrierName, String... regionNames) { 
		return new ArrayList<String>();
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

