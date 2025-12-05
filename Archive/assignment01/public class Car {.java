public class Car {

    private String make;
    private int year;
    private int maxSpeed;
    
    public Car(){
        this.make = "Unknown";
        this.year = 0;
        this.maxSpeed = 0;
    }

    public Car(String make, int year, int maxSpeed){
        this.make = make;
        setYear(year);
        setMaxSpeed(maxSpeed);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year > 1885) { // The year the first car was made
            this.year = year;
        } else {
            System.out.println("Invalid year. Year must be greater than 1885.");
        }
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed > 0 && maxSpeed <=400) { // Assuming max speed should be positive and realistic
            this.maxSpeed = maxSpeed;
        } else {
            System.out.println("Invalid max speed. Max speed must be positive.");
        }
    }
}
