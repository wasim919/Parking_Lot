package parkingLot;

/**
 * Created by wasim on 31-05-2020.
 */


import car.Car;
import java.util.ArrayList;

public class ParkingLot {
    int capacity = 0;
    int slotId = 0;
    int nOfOccupiedSlots = 0;
    int[] slots;
    Car[] slotsCar;

    public ParkingLot() {
    }

    public int createParkingLot(int var1) {
        this.capacity = var1;
        this.slots = new int[var1];
        this.slotsCar = new Car[var1];

        for(int var2 = 0; var2 < var1; ++var2) {
            this.slots[var2] = -1;
        }

        return this.capacity;
    }

    public int getEmptySlot() {
        for(int var1 = 0; var1 < this.capacity; ++var1) {
            if(this.slots[var1] == -1) {
                return var1;
            }
        }

        return -1;
    }

    public int park(String var1, String var2) {
        if(this.nOfOccupiedSlots < this.capacity) {
            int var3 = this.getEmptySlot();
            this.slots[var3] = var3;
            this.slotsCar[var3] = new Car(var1, var2);
            ++this.slotId;
            ++this.nOfOccupiedSlots;
            return var3 + 1;
        } else {
            return -1;
        }
    }

    public boolean leave(int var1) {
        if(this.nOfOccupiedSlots > 0 && this.slots[var1] != -1) {
            this.slots[var1 - 1] = -1;
            this.slotsCar[var1 - 1] = null;
            --this.nOfOccupiedSlots;
            return true;
        } else {
            return false;
        }
    }

    public void status() {
        System.out.println("Slot No.\tRegistration No.\tColor");

        for(int var1 = 0; var1 < this.slots.length; ++var1) {
            if(this.slots[var1] != -1) {
                System.out.println(String.format("%d \t\t %s \t\t %s", new Object[]{Integer.valueOf(var1 + 1), this.slotsCar[var1].regNumber, this.slotsCar[var1].color}));
            }
        }

    }

    public ArrayList<String> getRegNumberFromColor(String var1) {
        ArrayList var2 = new ArrayList();

        for(int var3 = 0; var3 < this.slots.length; ++var3) {
            if(this.slots[var3] != -1 && this.slotsCar[var3].color.equals(var1)) {
                var2.add(this.slotsCar[var3].regNumber);
            }
        }

        return var2;
    }

    public int getSlotNumberFromRegNumber(String var1) {
        for(int var2 = 0; var2 < this.slots.length; ++var2) {
            if(this.slotsCar[var2].regNumber.equals(var1)) {
                return var2 + 1;
            }
        }

        return -1;
    }

    public ArrayList<String> getSlotNumberFromColor(String var1) {
        ArrayList var2 = new ArrayList();

        for(int var3 = 0; var3 < this.slots.length; ++var3) {
            if(this.slots[var3] != -1 && this.slotsCar[var3].color.equals(var1)) {
                var2.add(Integer.toString(var3 + 1));
            }
        }

        return var2;
    }

    public void show(String var1) {
        int var2;
        int var3;
        if(var1.startsWith("create_parking_lot")) {
            var2 = Integer.parseInt(var1.split(" ")[1]);
            var3 = this.createParkingLot(var2);
            System.out.println(String.format("Created a parking lot with %d slots", new Object[]{Integer.valueOf(var3)}));
        } else {
            String var5;
            if(var1.startsWith("park")) {
                var5 = var1.split(" ")[1];
                String var6 = var1.split(" ")[2];
                int var4 = this.park(var5, var6);
                if(var4 == -1) {
                    System.out.println("Sorry, parking lot is full");
                } else {
                    System.out.println(String.format("Allocated slot number: %d", new Object[]{Integer.valueOf(var4)}));
                }
            } else if(var1.startsWith("leave")) {
                var2 = Integer.parseInt(var1.split(" ")[1]);
                boolean var7 = this.leave(var2);
                if(var7) {
                    System.out.println(String.format("Slot number %d is free", new Object[]{Integer.valueOf(var2)}));
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
