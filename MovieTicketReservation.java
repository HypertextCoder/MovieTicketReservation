import java.util.Random; //Importing random class in order to randomly assign availability of seats.
import java.util.Scanner; // Using Scanner class for Input

public class MovieTicketReservation {

    // Declare variables outside the main method
    static final int totalSeats = 50;
    static char[] seatingChart = new char[totalSeats];  // 1D array for simplicity
    static String[] movies = {
            "Animal", "Oppenheimer", "Multiverse of Madness", "All of Us Are Dead",
            "Train to Busan", "Fifty Shades of Grey", "Avatar: The Way of Water",
            "Annabelle", "Aquaman", "Wakanda Forever"
    }; // Declaring 10 Movies , you can change the code as per your comfort. 

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();

        // Movie selection (same as before)
        System.out.println("Welcome to the Movie Ticket Reservation System");
        System.out.println("Available Movies:");
        for (int i = 0; i < movies.length; i++) {
            System.out.println((i + 1) + ". " + movies[i]);
        }

        System.out.print("Select a movie by entering the corresponding number: ");
        int movieChoice;
        do {
            movieChoice = in.nextInt() - 1; // Adjust for zero-based indexing
            if (movieChoice < 0 || movieChoice >= movies.length) {
                System.out.println("Invalid choice. Please enter a number between 1 and " + movies.length + ".");
            }
        } while (movieChoice < 0 || movieChoice >= movies.length);

        // Seat reservation section
        int availableSeats = 12;
        for (int i = 0; i < totalSeats; i++) {
            seatingChart[i] = ' '; // Initialize with spaces for symmetrical layout
        }

        System.out.println("Available seats are shown as '+' and unavailable seats as '-'.");

        System.out.println("\nSeating Chart:");
        for (int i = 0; i < totalSeats; i++) {
            seatingChart[i] = random.nextBoolean() ? '+' : '-';
            System.out.print(seatingChart[i] + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }

        System.out.print("Enter the number of tickets (up to " + availableSeats + " available): ");
        int numberOfTickets;
        do {
            numberOfTickets = in.nextInt();

            // Consume newline character
            in.nextLine();

            // Validate the number of tickets
            if (numberOfTickets > availableSeats || numberOfTickets <= 0) {
                System.out.println("Invalid number of tickets. Please enter a number between 1 and " + availableSeats + ".");
            }
        } while (numberOfTickets > availableSeats || numberOfTickets <= 0);

        // Reserve seats and collect attendee details
        int reservedSeats = 0;
        String[] names = new String[numberOfTickets];
        String[] phoneNumbers = new String[numberOfTickets];
        while (reservedSeats < numberOfTickets) {
            System.out.print("Enter seat number (" + (reservedSeats + 1) + " out of " + numberOfTickets + "): ");
            int seatChoice;
            do {
                seatChoice = in.nextInt(); // Seat number as per user input
                if (seatChoice < 1 || seatChoice > totalSeats) {
                    System.out.println("Invalid seat number. Please enter a number between 1 and " + totalSeats + ".");
                }
            } while (seatChoice < 1 || seatChoice > totalSeats);

            if (seatingChart[seatChoice - 1] == '+') {
                seatingChart[seatChoice - 1] = '-';
                reservedSeats++;

                in.nextLine(); // Consume newline character

                System.out.print("Enter name of Person " + (reservedSeats) + ": ");
                names[reservedSeats - 1] = in.nextLine();
                System.out.print("Enter phone number of Person " + (reservedSeats) + ": ");
                phoneNumbers[reservedSeats - 1] = in.nextLine();

                System.out.println("Seat " + seatChoice + " reserved successfully!");

                System.out.println("\n*** Ticket Details ***");
                System.out.println("Movie: " + movies[movieChoice]);
                System.out.println("Seat Number: " + seatChoice);
                System.out.println("Name: " + names[reservedSeats - 1]);
                System.out.println("Phone Number: " + phoneNumbers[reservedSeats - 1]);
                System.out.println("********************\n");
            } else {
                System.out.println("Seat " + seatChoice + " is unavailable. Please try again.");
            }
        }

        // Confirmation
        System.out.println("\nReservation Confirmed!");
        System.out.println("Movie: " + movies[movieChoice]);
        System.out.println("Number of tickets: " + numberOfTickets);

        // Print reserved seat numbers and attendee details
        System.out.println("\nReserved Seats:");
        for (int i = 0; i < numberOfTickets; i++) {
            System.out.println("  - Seat: " + (i + 1));
            System.out.println("    Name: " + names[i]);
            System.out.println("    Phone Number: " + phoneNumbers[i]);
        }
    } // Closing brace for main method
}
