import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Language> languages = new CsvToBeanBuilder(new FileReader(
                "src/main/resources/countrylanguage.csv"))
                .withType(Language.class)
                .build()
                .parse();

        List<City> city = new CsvToBeanBuilder(new FileReader("src/main/resources/city.csv"))
                .withType(City.class)
                .build()
                .parse();

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
                        ", ", "List of cities with more than four official languages : ", ".")));

        int citiCount = (int) cityLanguages.stream()
                .filter(c ->{
                    for(Language l: c.getLanguages()){
                        if(l.getLanguage().contains("Russian") && l.isOfficial() == false){
                            return true;
                        }
                    }
                    return false;
                }).count();

        System.out.printf("The number of cities in which Russian is an unofficial language - %d", citiCount);
    }
}
