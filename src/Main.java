/**
 * Created by wasim on 31-05-2020.
 */
// Importing packages
import parkingLot.ParkingLot;

// Importing I/O packages
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Instantiate ParkingLot class
        ParkingLot parkingLot = new ParkingLot();

        // Giving the input file via command line argument
        if (args.length != 0) {
            try {
                File myObj = new File(args[0]);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
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
