package kdk10_lab3;

public class Tv {

    int id;            
    String name;  
    String manufacturer;

    public Tv() {
    }

    public Tv(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    
    @Override
    public String toString() {
        return String.format("Модель=%s, Производитель=%s", name, manufacturer);
    }
}
