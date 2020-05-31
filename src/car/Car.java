/**
 * Created by wasim on 30-05-2020.
 */
package car;

// A vehicle class
class Vehicle {
    public String regNumber;
    public String color;
    public Vehicle(String regNumber, String color) {
        this.regNumber = regNumber;
        this.color = color;
    }
}

// Car class that inherits from Vehicle class
public class Car extends Vehicle {
    public Car(String regNumber, String color) {
        super(regNumber, color);
    }
}
