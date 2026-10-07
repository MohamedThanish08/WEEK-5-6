import java.util.*;

public class BookCopyCirculationGuard {

    private int copiesTotal;
    private int copiesAvailable;

    public BookCopyCirculationGuard(int copiesTotal) {
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total copies: ");
        int total = sc.nextInt();

        BookCopyCirculationGuard book =
            new BookCopyCirculationGuard(total);

        System.out.print("Enter number of check-outs: ");
        int checkOuts = sc.nextInt();

        for (int i = 0; i < checkOuts; i++) {
            book.checkOut();
        }

        System.out.print("Enter number of check-ins: ");
        int checkIns = sc.nextInt();

        for (int i = 0; i < checkIns; i++) {
            book.checkIn();
        }

        System.out.println(
            "Available copies: " +
            book.getCopiesAvailable()
        );

        sc.close();
    }
}