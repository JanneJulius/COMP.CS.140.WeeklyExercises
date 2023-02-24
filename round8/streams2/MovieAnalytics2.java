import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;


public class MovieAnalytics2 {
    private List<Movie> movies;

    public MovieAnalytics2() {
        movies = new ArrayList<>();
    }

    public void populateWithData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            this.movies = br.lines()
                    .map(line -> {
                        String[] parts = line.split(";");
                        String title = parts[0];
                        int releaseYear = Integer.parseInt(parts[1]);
                        int duration = Integer.parseInt(parts[2]);
                        String genre = parts[3];
                        double score = Double.parseDouble(parts[4]);
                        String director = parts[5];
                        return new Movie(title, releaseYear, duration, genre, score, director);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCountByDirector(int n) {
        Map<String, Long> directorMovieCount = movies.stream()
            .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));

        List<String> topDirectors = directorMovieCount.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
            .limit(n)
            .map(entry -> entry.getKey() + ": " + entry.getValue() + " movies")
            .collect(Collectors.toList());
        
        topDirectors.forEach(System.out::println);
    }

    public void printAverageDurationByGenre() {
        Map<String, Double> genreAverageDuration = movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingInt(Movie::getDuration)));

        List<String> genresByAverageDuration = genreAverageDuration.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .map(entry -> entry.getKey() + ": " + String.format("%.2f", entry.getValue()))
            .collect(Collectors.toList());

        genresByAverageDuration.forEach(System.out::println);
    }

    public void printAverageScoreByGenre() {
        Map<String, Double> genreAverageScore = movies.stream()
            .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getScore)));

        List<String> genresByAverageScore = genreAverageScore.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
            .map(entry -> entry.getKey() + ": " + String.format("%.2f", entry.getValue()))
            .collect(Collectors.toList());

        genresByAverageScore.forEach(System.out::println);
    }
}
