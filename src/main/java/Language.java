import com.opencsv.bean.CsvBindByName;

public class Language {
    @CsvBindByName(column = "CountryCode")
    private String countryCode;

    @CsvBindByName(column = "Language")
    private String language;

    @CsvBindByName(column = "IsOfficial")
    private boolean isOfficial;

    public String getCountryCode() {
        return countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isOfficial() {
        return isOfficial;
    }
}
