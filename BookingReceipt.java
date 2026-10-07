package WEEK5and6;

import java.util.Arrays;
import java.util.Scanner;

public final class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId,
                          String[] seatNumbers) {

        this.bookingId = bookingId;

        this.seatNumbers =
                Arrays.copyOf(
                        seatNumbers,
                        seatNumbers.length
                );
    }

    public String[] getSeatNumbers() {

        return Arrays.copyOf(
                seatNumbers,
                seatNumbers.length
        );
    }

    public BookingReceipt withUpdatedSeat(
            int index,
            String newSeat) {

        String[] updatedSeats =
                Arrays.copyOf(
                        seatNumbers,
                        seatNumbers.length
                );

        updatedSeats[index] = newSeat;

        return new BookingReceipt(
                bookingId,
                updatedSeats
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter booking ID: ");
        String bookingId = sc.nextLine();

        System.out.print("Enter number of seats: ");
        int n = sc.nextInt();

        String[] seats = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter seat " + (i + 1) + ": ");
            seats[i] = sc.next();
        }

        BookingReceipt receipt =
                new BookingReceipt(bookingId, seats);

        System.out.println("Original seats: "
                + Arrays.toString(
                        receipt.getSeatNumbers()
                  ));

        System.out.print("Enter seat index to update: ");
        int index = sc.nextInt();

        System.out.print("Enter new seat: ");
        String newSeat = sc.next();

        BookingReceipt updated =
                receipt.withUpdatedSeat(index, newSeat);

        System.out.println("Original receipt: "
                + Arrays.toString(
                        receipt.getSeatNumbers()
                  ));

        System.out.println("Updated receipt: "
                + Arrays.toString(
                        updated.getSeatNumbers()
                  ));

        sc.close();
    }
}

class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String[] seatNumbers,
            int groupSize) {

        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }
}

class SettlementProcessor {

    static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (BookingReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }
}