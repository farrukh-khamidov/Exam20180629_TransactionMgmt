package transactions;

public class Place {
    private String name;

    private Region region;

    public Region getRegion() {
        return region;
    }

    public Place(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }
}
