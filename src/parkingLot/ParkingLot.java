package parkingLot;

/**
 * Created by wasim on 31-05-2020.
 */


import car.Car;
import java.util.ArrayList;

// Create ParkingLot class that has all the attributes and behaviours associated with a Parking Lot
public class ParkingLot {
    int capacity = 0;
    int slotId = 0;
    int nOfOccupiedSlots = 0;
    int[] slots;
    Car[] slotsCar;

    // Parameterized constructor which sets the initial slots as unallocated by setting each slot value to -1
    public int createParkingLot(int capacity) {
        this.capacity = capacity;
        this.slots = new int[capacity];
        this.slotsCar = new Car[capacity];

        for(int i = 0; i < capacity; ++i) {
            this.slots[i] = -1;
        }

        return this.capacity;
    }

    // Fetch unallocated slot
    public int getEmptySlot() {
        for(int i = 0; i < this.capacity; ++i) {
            if(this.slots[i] == -1) {
                return i;
            }
        }

        // If no unallocated slot exists return -1
        return -1;
    }


    // Park car into unallocated slot
    public int park(String regNumber, String colour) {

        // Check if the parking lot is empty
        if(this.nOfOccupiedSlots < this.capacity) {

            // Get id of the empty slot
            int emptySlotId = this.getEmptySlot();

            // Allocate car to slot
            this.slots[emptySlotId] = emptySlotId;
            this.slotsCar[emptySlotId] = new Car(regNumber, colour);

            // Increment attributes associated with slots
            ++this.slotId;
            ++this.nOfOccupiedSlots;

            return emptySlotId + 1;
        } else {

            // If the parking lot is full return -1
            return -1;
        }
    }

    // When a car leaves unallocated the slot allocated to the car
    public boolean leave(int slotId) {

        // Check if the parking lot is full
        if(this.nOfOccupiedSlots > 0 && this.slots[slotId] != -1) {

            // Unallocate the slot assigned to the given car
            this.slots[slotId - 1] = -1;
            this.slotsCar[slotId - 1] = null;
            --this.nOfOccupiedSlots;
            return true;
        } else {

            // If the parking lot is full return false
            return false;
        }
    }

    // Fetch the current state of the parking lot
    public void status() {
        System.out.println("Slot No.\tRegistration No.\tColor");

        for(int i = 0; i < this.slots.length; ++i) {
            if(this.slots[i] != -1) {
                System.out.println(String.format("%d \t\t %s \t\t %s", this.slots[i], this.slotsCar[i].regNumber, this.slotsCar[i].color));
            }
        }
    }

    // Get the registration number of all the cars of a given colour
    public ArrayList<String> getRegNumberFromColor(String colour) {
        ArrayList regNumbers = new ArrayList();

        for(int i = 0; i < this.slots.length; ++i) {

            // Check if the slot is empty and if car of the given colour is present in the slot
            if(this.slots[i] != -1 && this.slotsCar[i].color.equals(colour)) {
                regNumbers.add(this.slotsCar[i].regNumber);
            }
        }

        // return the registration numbers
        return regNumbers;
    }

    // Get slot number that contain car of a given registration number
    public int getSlotNumberFromRegNumber(String regNumber) {
        for(int i = 0; i < this.slots.length; ++i) {

            // Check if there is a slot that contains car of given registration number
            if(this.slotsCar[i].regNumber.equals(regNumber)) {
                return i + 1;
            }
        }

        // If no slot contains car with the given registration number then return -1
        return -1;
    }

    // Get slot numbers that contain car of a given color
    public ArrayList<String> getSlotNumberFromColor(String colour) {
        ArrayList slotNumbers = new ArrayList();

        for(int i = 0; i < this.slots.length; ++i) {

            // Check if the slot is empty and if car of the given colour is present in the slot
            if(this.slots[i] != -1 && this.slotsCar[i].color.equals(colour)) {
                slotNumbers.add(Integer.toString(i + 1));
            }
        }

        return slotNumbers;
    }

    // Function that checks the commands given by the user and executes appropriate methods depending on the user command
    public void show(String var1) {
        int var2;
        int var3;
        if(var1.startsWith("create_parking_lot")) {
            var2 = Integer.parseInt(var1.split(" ")[1]);
            var3 = this.createParkingLot(var2);
            System.out.println(String.format("Created a parking lot with %d slots", var3));
        } else {
            String var5;
            if(var1.startsWith("park")) {
                var5 = var1.split(" ")[1];
                String var6 = var1.split(" ")[2];
                int var4 = this.park(var5, var6);
                if(var4 == -1) {
                    System.out.println("Sorry, parking lot is full");
                } else {
                    System.out.println(String.format("Allocated slot number: %d", var4));
                }
            } else if(var1.startsWith("leave")) {
                var2 = Integer.parseInt(var1.split(" ")[1]);
                boolean var7 = this.leave(var2);
                if(var7) {
                    System.out.println(String.format("Slot number %d is free", var7));
                }
            } else if(var1.startsWith("status")) {
                this.status();
            } else {
                ArrayList var8;
                if(var1.startsWith("registration_numbers_for_cars_with_colour")) {
                    var5 = var1.split(" ")[1];
                    var8 = this.getRegNumberFromColor(var5);
                    System.out.println(String.join(", ", var8));
                } else if(var1.startsWith("slot_numbers_for_cars_with_colour")) {
                    var5 = var1.split(" ")[1];
                    var8 = this.getSlotNumberFromColor(var5);
                    System.out.println(String.join(", ", var8));
                } else if(var1.startsWith("slot_number_for_registration_number")) {
                    var5 = var1.split(" ")[1];
                    var3 = this.getSlotNumberFromRegNumber(var5);
                    if(var3 == -1) {
                        System.out.println("Not found");
                    } else {
                        System.out.println(var3);
                    }
                } else {
                    System.out.println("You've entered an invalid command");
                }
            }
        }
    }
}
