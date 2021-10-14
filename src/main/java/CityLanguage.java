import java.util.List;
import java.util.stream.Collectors;

public class CityLanguage {
    private int id;
    private String name;
    private String countryCode;
    private String district;
    private long population;
    private List<Language> languages;

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

    public List<Language> getLanguages() {
        return languages;
    }

    public CityLanguage convertToCity(City city, List<Language> languages){
        this.id = city.getId();
        this.name = city.getName();
        this.countryCode = city.getCountryCode();
        this.district = city.getDistrict();
        this.population = city.getPopulation();
        this.languages = languages.stream()
                .filter(l -> l.getCountryCode().contains(this.countryCode))
                .collect(Collectors.toList());
        return this;
    }
}
