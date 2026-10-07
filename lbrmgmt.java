import java.util.Scanner;

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null ||
            memberId.trim().isEmpty() ||
            memberId.length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        if (borrowLimit <= 0) {
            throw new IllegalArgumentException("Borrow limit must be positive");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println(
            "General Member | Books Borrowed: "
            + booksBorrowed
        );
    }
}

class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(
            String memberId,
            int borrowLimit,
            String course) {

        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Student Member | Course: " + course
            + " | Books Borrowed: " + booksBorrowed
        );
    }
}

class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(
            String memberId,
            int borrowLimit,
            String course,
            int bonusLimit) {

        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Honors Student Member | Course: " + course
            + " | Bonus Limit: " + bonusLimit
            + " | Books Borrowed: " + booksBorrowed
        );
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(
            String memberId,
            int borrowLimit,
            String department) {

        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Faculty Member | Department: " + department
            + " | Books Borrowed: " + booksBorrowed
        );
    }
}

public class lbrmgmt{

    public static String classifyGeneration(LibraryMember member) {

        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof StudentMember) {
            return "Student Member";
        }

        return "General Member";
    }

    public static int getTotalBooksBorrowed(
            LibraryMember[] members) {

        int total = 0;

        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student member ID: ");
        String studentId = sc.nextLine();

        System.out.print("Enter student borrow limit: ");
        int studentLimit = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        System.out.print("Enter honors member ID: ");
        String honorsId = sc.nextLine();

        System.out.print("Enter honors borrow limit: ");
        int honorsLimit = sc.nextInt();

        System.out.print("Enter bonus limit: ");
        int bonusLimit = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter faculty member ID: ");
        String facultyId = sc.nextLine();

        System.out.print("Enter faculty borrow limit: ");
        int facultyLimit = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter department: ");
        String department = sc.nextLine();

        StudentMember student =
                new StudentMember(studentId, studentLimit, course);

        HonorsStudentMember honors =
                new HonorsStudentMember(
                        honorsId,
                        honorsLimit,
                        "ECE",
                        bonusLimit
                );

        FacultyMember faculty =
                new FacultyMember(
                        facultyId,
                        facultyLimit,
                        department
                );

        student.borrowBook();
        student.borrowBook();

        honors.borrowBook();

        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        student.displayInfo();
        honors.displayInfo();
        faculty.displayInfo();

        System.out.println(
            classifyGeneration(honors)
        );

        System.out.println(
            classifyGeneration(faculty)
        );

        LibraryMember[] members = {
            student,
            honors,
            faculty
        };

        System.out.println(
            "Total Books Borrowed: "
            + getTotalBooksBorrowed(members)
        );

        sc.close();
    }
}