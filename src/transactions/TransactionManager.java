package transactions;
import java.util.*;
import java.util.stream.Stream;

//import static java.util.stream.Collectors.*;
//import static java.util.Comparator.*;

public class TransactionManager {

	 Map<String, Region> regionMap = new HashMap<>();
	 Map<String, Place> placesMap = new HashMap<>();

	Map<String, Carrier> carriersMap = new HashMap<>();

	
//R1
	public List<String> addRegion(String regionName, String... placeNames) {
		Region region = new Region(regionName);
		regionMap.put(regionName, region);

		for (String placeName : placeNames) {
			Place place = new Place(placeName);
			if (!placesMap.containsKey(placeName)) {
				region.addPlace(place);
			}
			placesMap.put(placeName, place);
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

