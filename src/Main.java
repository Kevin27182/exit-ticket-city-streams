import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String CSV_REGEX = "[,\n]";

    public static void main(String[] args) throws IOException {

        // Get the path to the data
        String dataPath = "data/world-cities.csv";
        Path pathObj = Path.of(dataPath);

        // Variable to hold the output
        String cities;

        // Create a new stream from CSV at `path`
        try (Stream<String> lines = Files.lines(pathObj)) {

            cities = lines

                    // Convert stream to parallel for efficiency
                    .parallel()

                    // Split line into list and extract the first element (city)
                    .map(line -> Pattern.compile(CSV_REGEX).splitAsStream(line).toList().getFirst())

                    // Convert to uppercase
                    .map(String::toUpperCase)

                    // Filter to only cities that start with the letter `B`
                    .filter(line -> line.startsWith("B"))

                    // Sort the stream alphabetically
                    .sorted()

                    // Reduce the stream to a comma-delimited string
                    .collect(Collectors.joining(", "));
        }

        // Print the output
        System.out.println(cities);
    }
}
