/**
 * Created by wasim on 31-05-2020.
 */
// Importing packages
import parkingLot.ParkingLot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();
        if (args.length != 0) {

        }
        // Type commands from the console
        else {
            Scanner sc = new Scanner(System.in);
            System.out.print("$ ");
            String line = sc.nextLine();
            while (true) {
                if (line.equals("exit")) {
                    System.exit(0);
                }
                parkingLot.show(line);
                System.out.print("$ ");
                line = sc.nextLine();
            }
        }
    }
}
