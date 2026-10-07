import java.util.Scanner;

public class Membership {

    static class LibraryMember {

        protected int borrowLimit;
        protected int booksBorrowed;

        private static int membersEnrolled = 0;

        public final String memberNumber;

        public LibraryMember(int borrowLimit) {

            if (borrowLimit <= 0) {
                throw new IllegalArgumentException(
                    "Borrow limit must be positive"
                );
            }

            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;

            membersEnrolled++;

            this.memberNumber =
                    "LIB-" + (100 + membersEnrolled);
        }

        public void borrowBook() {

            if (booksBorrowed < borrowLimit) {
                booksBorrowed++;
            }
        }

        public void borrowBook(String genre) {

            System.out.println(
                "Genre recorded: " + genre
            );

            borrowBook();
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public static boolean isValidRenewalCode(
                String code) {

            if (code == null || code.length() != 4) {
                return false;
            }

            if (code.charAt(0) != 'R') {
                return false;
            }

            if (!Character.isDigit(code.charAt(1))) {
                return false;
            }

            if (!Character.isDigit(code.charAt(2))) {
                return false;
            }

            if (!Character.isUpperCase(code.charAt(3))) {
                return false;
            }

            return true;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }
    }

    static class FacultyMember extends LibraryMember {

        private String department;

        public FacultyMember(
                int borrowLimit,
                String department) {

            super(borrowLimit);
            this.department = department;
        }

        public String getDepartment() {
            return department;
        }
    }

    public static String processNightlyAudit(
            LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
            "Enter regular member borrow limit: "
        );

        int regularLimit = sc.nextInt();

        LibraryMember m1 =
                new LibraryMember(regularLimit);

        System.out.println(
            "Member Number: " + m1.memberNumber
        );

        System.out.println(
            "Members Enrolled: "
            + LibraryMember.getMembersEnrolled()
        );

        System.out.print(
            "Enter renewal code: "
        );

        String code = sc.next();

        System.out.println(
            "Valid Renewal Code: "
            + LibraryMember.isValidRenewalCode(code)
        );

        System.out.print(
            "Enter genre: "
        );

        sc.nextLine();
        String genre = sc.nextLine();

        m1.borrowBook();
        m1.borrowBook(genre);

        System.out.println(
            "Books Borrowed: "
            + m1.getBooksBorrowed()
        );

        FacultyMember faculty =
                new FacultyMember(
                    5,
                    "Physics"
                );

        LibraryMember[] members = {
            faculty,
            null,
            m1
        };

        System.out.println(
            processNightlyAudit(members)
        );

        sc.close();
    }
}