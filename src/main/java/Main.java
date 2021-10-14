import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {

        CSVReader reader = new CSVReaderBuilder(
                new FileReader("src/main/resources/city.csv"))
                .withSkipLines(1)
                .build();

        List<String[]> myEntries = reader.readAll();

        List<City> city = myEntries.stream()
                .map(c -> new City().convertToCity(c))
                .collect(Collectors.toList());

        reader = new CSVReaderBuilder(
                new FileReader("src/main/resources/countrylanguage.csv"))
                .withSkipLines(1)
                .build();

        myEntries = reader.readAll();

        List<Language> languages = myEntries.stream()
                .map(l -> new Language().convertToLanguage(l))
                .collect(Collectors.toList());

        List<CityLanguage> cityLanguages = city.stream()
                .map(c -> new CityLanguage().convertToCity(c,languages))
                .collect(Collectors.toList());

        for(CityLanguage c: cityLanguages){
            System.out.printf("%5d\t%30s\t%6s\t%20s\t%8d\t",
                    c.getId(),
                    c.getName(),
                    c.getCountryCode(),
                    c.getDistrict(),
                    c.getPopulation());
            for(Language l:c.getLanguages()){
                System.out.printf(" %10s\t",l.getLanguage());
            }
            System.out.println();
        }
    }
}
