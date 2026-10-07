import java.util.Scanner;

public class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {

        if (otp != null && otp.matches("\\d{4,6}")) {
            this.otp = otp;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        MovieBookingProfile profile =
                new MovieBookingProfile(name);

        System.out.print("Is booking confirmed? (true/false): ");
        boolean confirmed = sc.nextBoolean();
        profile.setConfirmed(confirmed);

        System.out.print("Enter OTP: ");
        String otp = sc.next();

        profile.setOtp(otp);

        System.out.println("Name: " + profile.getName());
        System.out.println(
                "Confirmed: " + profile.isConfirmed()
        );

        System.out.println("OTP saved successfully.");

        sc.close();
    }
}