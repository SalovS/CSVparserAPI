import java.nio.charset.StandardCharsets;

public class City {
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private long population;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public long getPopulation() {
        return population;
    }

    public City convertToCity(String[] arr){
        this.id = Integer.parseInt(arr[0]);
        this.name = arr[1];
        this.countryCode = arr[2];
        this.district = arr[3];
        this.population = Long.parseLong(arr[4]);
        return this;
    }
}
