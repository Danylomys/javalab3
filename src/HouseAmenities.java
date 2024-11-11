import java.util.Arrays;
import java.util.List;

public enum HouseAmenities {
    WIFI("wifi", 100.0, 50.0),
    KITCHEN("kitchen", 300.0, 150.0),
    SMART_TV("smart tv", 175.0, 100.0),
    CONDITIONER("conditioner", 250.0, 100.0),
    WASHING_MACHINE("washing machine",230.0, 100.0),
    SAFE("safe",270.0,120.0),
    BED("bed",200.0,59.0),
    CHILD_BED("child bed",175.0,78.0),
    TERRACE("terrace",500.0,200.0),
    JACUZZI("jacuzzi",450.0,175.0),
    MINI_KITCHEN("mini kitchen",175.0,100.0);

    private final String amenitiesText;
    private final double price;
    private final double expense;

    HouseAmenities(final String amenitiesText, final double price, final double expense) {
        this.amenitiesText = amenitiesText;
        this.price = price;
        this.expense = expense;
    }

    public String getAmenitiesText() {
        return amenitiesText;
    }

    public static List<HouseAmenities> getListAmenities(){
        return Arrays.asList(HouseAmenities.values());
    }

    public double getPrice() {
        return price;
    }

    public double getExpense() {
        return expense;
    }
}

