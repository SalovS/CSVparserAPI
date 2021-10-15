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

        System.out.println(cityLanguages.stream()
                .filter(c -> {
                    int count = 0;
                    for(Language l:c.getLanguages()){
                        if(l.isOfficial()){
                            count++;
                        }
                    }
                    if(count >= 4){
                        return true;
                    }
                    return false;
                })
                .map(c -> c.getName())
                .collect(Collectors.joining(
                        ", ", "Перечень городов, в которых более 4 официальных языков : ", ".")));

        int citiCount = (int) cityLanguages.stream()
                .filter(c ->{
                    for(Language l: c.getLanguages()){
                        if(l.getLanguage().contains("Russian") && l.isOfficial() == false){
                            return true;
                        }
                    }
                    return false;
                }).count();

        System.out.printf("Количество городов, в которых русский язык является не официальным - %d", citiCount);
    }
}
