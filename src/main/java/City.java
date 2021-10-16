import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvNumber;
import com.opencsv.bean.CsvNumbers;

public class City {

    @CsvBindByName(column = "ID")
    @CsvNumbers({
            @CsvNumber(value = "0")
    })
    private int id;

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "CountryCode")
    private String countryCode;

    @CsvBindByName(column = "District")
    private String district;

    @CsvBindByName(column = "Population")
    @CsvNumbers({
            @CsvNumber(value = "0")
    })
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
}
