package transactions;

import java.util.*;

public class Region {
    private String name;

    private Set<Place> places = new TreeSet<>(Comparator.comparing(Place::getName));
    private Set<Carrier> carriers = new TreeSet<>(Comparator.comparing(Carrier::getName));

    public Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPlace(Place place) {
        places.add(place);
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void addCarrier(Carrier carrier) {
        carriers.add(carrier);
    }

    public Set<Carrier> getCarriers() {
        return carriers;
    }
}
