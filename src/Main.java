import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //3 вариант
        Scanner scanner = new Scanner(System.in);
        String domen = scanner.nextLine();
        String zone = scanner.nextLine();
        if (domen.matches("[A-Za-z0-9]+") && zone.matches("[A-Za-z0-9]+")) {
            Reciever reciever = new Reciever();
            reciever.getData(domen, zone);
        } else {
            System.out.println("Problem");
        }
    }
}
