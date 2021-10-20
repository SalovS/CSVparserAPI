import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Language> languages = parser("src/main/resources/countrylanguage.csv", Language.class);

        List<City> city = parser("src/main/resources/city.csv", City.class);

        List<CityLanguage> cityLanguages = city.stream()
                .map(c -> new CityLanguage().convertToCity(c, languages))
                .collect(Collectors.toList());

        System.out.println(cityLanguages.stream()
                .filter(c -> {
                    int count = 0;
                    for (Language l : c.getLanguages()) {
                        if (l.isOfficial()) {
                            count++;
                        }
                    }
                    if (count >= 4) {
                        return true;
                    }
                    return false;
                })
                .map(c -> c.getName())
                .collect(Collectors.joining(
                        ", ", "List of cities with more than four official languages : ", ".")));

        int citiCount = (int) cityLanguages.stream()
                .filter(c -> {
                    for (Language l : c.getLanguages()) {
                        if (l.getLanguage().contains("Russian") && l.isOfficial() == false) {
                            return true;
                        }
                    }
                    return false;
                }).count();

        System.out.printf("The number of cities in which Russian is an unofficial language - %d", citiCount);
    }

    public static <T> List<T> parser(String address, Class<T> obj) {
        List answer = new ArrayList<>();
        try {
            answer = new CsvToBeanBuilder(new FileReader(
                    address))
                    .withType(obj)
                    .build()
                    .parse();
        } catch (Exception e) {
            System.err.println(e);
        }
        return answer;
    }
}
