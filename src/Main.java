import java.util.Scanner;
import java.util.Arrays;

// Item class representing a movie
class Movie {
    private String title;
    private int year;
    private double rating;

    public Movie(String title, int year, double rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    // Method to return detailed formatted information about the movie
    public String getDescription() {
        return "Title: " + title + "\nYear: " + year + "\nRating: " + rating;
    }
}

// Custom collection class for managing movies
class MovieCollection {
    private Movie[] movies;
    private int itemCount;

    public MovieCollection() {
        movies = new Movie[10]; // Initial capacity
        itemCount = 0;
    }

    // Add a new movie to the collection
    public void addMovie(Movie movie) {
        if (itemCount >= movies.length) {
            movies = Arrays.copyOf(movies, movies.length * 2); // Increase array size if needed
        }
        movies[itemCount] = movie;
        itemCount++;
    }

    // Remove a movie from the collection by index
    public void removeMovie(int index) {
        if (index >= 0 && index < itemCount) {
            movies[index] = null;
            itemCount--;
            movies = Arrays.copyOf(movies, itemCount); // Create a new array without the removed movie
        }
    }

    // Sort movies by year
    public void sortMoviesByYear() {
        Arrays.sort(movies, 0, itemCount, (m1, m2) -> Integer.compare(m1.getYear(), m2.getYear()));
    }

    // Search movies by a specific year
    public void searchByYear(int year) {
        for (int i = 0; i < itemCount; i++) {
            if (movies[i].getYear() == year) {
                System.out.println("Index " + i + ": " + movies[i].getTitle() + " (" + movies[i].getYear() + ")");
            }
        }
    }

    // Print detailed information about a movie by index
    public void printMovie(int index) {
        if (index >= 0 && index < itemCount && movies[index] != null) {
            System.out.println(movies[index].getDescription());
        } else {
            System.out.println("Invalid index or movie not found.");
        }
    }

    // Print basic information about all movies
    public void printAllMovies() {
        for (int i = 0; i < itemCount; i++) {
            System.out.println(i + ": " + movies[i].getTitle() + " (" + movies[i].getYear() + ")");
        }
    }
}

// Main class to manage user input and interaction
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieCollection collection = new MovieCollection();

        while (true) {
            System.out.println("Welcome to the movie database!");
            System.out.println("Choose an option to proceed:");
            System.out.println("1 - Print movie list");
            System.out.println("2 - Add new movie");
            System.out.println("3 - Remove movie");
            System.out.println("4 - Sort movies by year");
            System.out.println("5 - Search movies by year");
            System.out.println("6 - Print detailed movie");
            System.out.println("0 - Exit the program");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    collection.printAllMovies();
                    break;
                case 2:
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter release year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter rating: ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    Movie movie = new Movie(title, year, rating);
                    collection.addMovie(movie);
                    break;
                case 3:
                    System.out.print("Enter index of the movie to remove: ");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    collection.removeMovie(index);
                    break;
                case 4:
                    collection.sortMoviesByYear();
                    System.out.println("Movies sorted by year.");
                    break;
                case 5:
                    System.out.print("Enter year to search: ");
                    int searchYear = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    collection.searchByYear(searchYear);
                    break;
                case 6:
                    System.out.print("Enter index of the movie to print: ");
                    int movieIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    collection.printMovie(movieIndex);
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
