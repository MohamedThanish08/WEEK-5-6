import java.util.Scanner;

public class CineScreen {

    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {

        if (seatsTotal <= 0) {
            throw new IllegalArgumentException(
                    "seatsTotal must be positive"
            );
        }

        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {

        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {

        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total seats: ");
        int total = sc.nextInt();

        CineScreen screen;

        try {
            screen = new CineScreen(total);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid seat count.");
            sc.close();
            return;
        }

        System.out.print("Enter number of bookings: ");
        int bookings = sc.nextInt();

        for (int i = 0; i < bookings; i++) {
            screen.bookSeat();
        }

        System.out.println(
                "Available seats: " +
                screen.getSeatsAvailable()
        );

        System.out.print("Enter number of cancellations: ");
        int cancellations = sc.nextInt();

        for (int i = 0; i < cancellations; i++) {
            screen.cancelBooking();
        }

        System.out.println(
                "Available seats after cancellation: " +
                screen.getSeatsAvailable()
        );

        sc.close();
    }
}