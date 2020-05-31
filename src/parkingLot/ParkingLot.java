package parkingLot;

/**
 * Created by wasim on 31-05-2020.
 */


import car.Car;
import java.util.ArrayList;

// Create parking lot class that has all the attributes and behaviours associated with a Parking Lot
public class ParkingLot {
    int capacity = 0;
    int slotId = 0;
    int nOfOccupiedSlots = 0;
    int[] slots;
    Car[] slotsCar;

    public ParkingLot() {
    }

    public int createParkingLot(int capacity) {
        this.capacity = capacity;
        this.slots = new int[capacity];
        this.slotsCar = new Car[capacity];

        for(int i = 0; i < capacity; ++i) {
            this.slots[i] = -1;
        }

        return this.capacity;
    }

    public int getEmptySlot() {
        for(int i = 0; i < this.capacity; ++i) {
            if(this.slots[i] == -1) {
                return i;
            }
        }

        return -1;
    }

    public int park(String regNumber, String colour) {
        if(this.nOfOccupiedSlots < this.capacity) {
            int emptySlotId = this.getEmptySlot(); //emptySlotId
            this.slots[emptySlotId] = emptySlotId;
            this.slotsCar[emptySlotId] = new Car(regNumber, colour);
            ++this.slotId;
            ++this.nOfOccupiedSlots;
            return emptySlotId + 1;
        } else {
            return -1;
        }
    }

    public boolean leave(int slotId) {
        if(this.nOfOccupiedSlots > 0 && this.slots[slotId] != -1) {
            this.slots[slotId - 1] = -1;
            this.slotsCar[slotId - 1] = null;
            --this.nOfOccupiedSlots;
            return true;
        } else {
            return false;
        }
    }

    public void status() {
        System.out.println("Slot No.\tRegistration No.\tColor");

        for(int i = 0; i < this.slots.length; ++i) {
            if(this.slots[i] != -1) {
                System.out.println(String.format("%d \t\t %s \t\t %s", this.slots[i], this.slotsCar[i].regNumber, this.slotsCar[i].color));
            }
        }
    }

    public ArrayList<String> getRegNumberFromColor(String colour) {
        ArrayList regNumbers = new ArrayList();

        for(int i = 0; i < this.slots.length; ++i) {
            if(this.slots[i] != -1 && this.slotsCar[i].color.equals(colour)) {
                regNumbers.add(this.slotsCar[i].regNumber);
            }
        }

        return regNumbers;
    }

    public int getSlotNumberFromRegNumber(String regNumber) {
        for(int i = 0; i < this.slots.length; ++i) {
            if(this.slotsCar[i].regNumber.equals(regNumber)) {
                return i + 1;
            }
        }

        return -1;
    }

    public ArrayList<String> getSlotNumberFromColor(String colour) {
        ArrayList slotNumbers = new ArrayList();

        for(int i = 0; i < this.slots.length; ++i) {
            if(this.slots[i] != -1 && this.slotsCar[i].color.equals(colour)) {
                slotNumbers.add(Integer.toString(i + 1));
            }
        }

        return slotNumbers;
    }

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
