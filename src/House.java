import java.util.ArrayList;
import java.util.List;

public class House {
    private final List<HouseAmenities> houseAmenities = new ArrayList<>();
    private final HouseCondition houseCondition;
    private final int houseNumber;
    private int personInHouse;
    private final Hotel hotel;

    public House(final HouseCondition houseCondition, final int houseNumber, Hotel hotel) {
        this.houseCondition = houseCondition;
        this.houseNumber = houseNumber;
        this.hotel = hotel;
        includeAmenities();
    }

    public void addAdditionalAmenities(HouseAmenities choice) {
        HouseAmenities houseAmenities = null;
        switch (choice) {
            case WIFI -> houseAmenities = HouseAmenities.WIFI;
            case KITCHEN -> houseAmenities = HouseAmenities.KITCHEN;
            case SMART_TV -> houseAmenities = HouseAmenities.SMART_TV;
            case CONDITIONER -> houseAmenities = HouseAmenities.CONDITIONER;
            case WASHING_MACHINE -> houseAmenities = HouseAmenities.WASHING_MACHINE;
            case SAFE -> houseAmenities = HouseAmenities.SAFE;
            case BED -> {
                houseAmenities = HouseAmenities.BED;
                personInHouse++;
            }
            case CHILD_BED -> {
                houseAmenities = HouseAmenities.CHILD_BED;
                personInHouse++;
            }
            case TERRACE -> houseAmenities = HouseAmenities.TERRACE;
            case JACUZZI -> houseAmenities = HouseAmenities.JACUZZI;
            case MINI_KITCHEN -> houseAmenities = HouseAmenities.MINI_KITCHEN;
            default -> System.out.println("There is no choice like " + choice.getAmenitiesText());
        }
        if (!this.houseAmenities.isEmpty()) {
            System.out.println(houseAmenities.getAmenitiesText() + " was successfully added.");
            this.houseAmenities.add(houseAmenities);
        }
    }


    private void includeAmenities() {
        switch (houseCondition.getTextCondition()) {
            case "economy" -> {
                houseAmenities.add(HouseAmenities.WIFI);
                houseAmenities.add(HouseAmenities.BED);
                houseAmenities.add(HouseAmenities.MINI_KITCHEN);
                personInHouse++;
            }
            case "standard" -> {
                houseAmenities.add(HouseAmenities.WIFI);
                houseAmenities.add(HouseAmenities.BED);
                houseAmenities.add(HouseAmenities.MINI_KITCHEN);
                houseAmenities.add(HouseAmenities.CONDITIONER);
                houseAmenities.add(HouseAmenities.SAFE);
                personInHouse++;
            }
            case "luxury" -> {
                houseAmenities.add(HouseAmenities.WIFI);
                houseAmenities.add(HouseAmenities.BED);
                houseAmenities.add(HouseAmenities.BED);
                houseAmenities.add(HouseAmenities.KITCHEN);
                houseAmenities.add(HouseAmenities.CONDITIONER);
                houseAmenities.add(HouseAmenities.SAFE);
                houseAmenities.add(HouseAmenities.SMART_TV);
                houseAmenities.add(HouseAmenities.WASHING_MACHINE);
                personInHouse += 2;
            }
            case "president" -> {
                houseAmenities.add(HouseAmenities.WIFI);
                houseAmenities.add(HouseAmenities.BED);
                houseAmenities.add(HouseAmenities.BED);
                houseAmenities.add(HouseAmenities.BED);
                houseAmenities.add(HouseAmenities.KITCHEN);
                houseAmenities.add(HouseAmenities.CONDITIONER);
                houseAmenities.add(HouseAmenities.SAFE);
                houseAmenities.add(HouseAmenities.SMART_TV);
                houseAmenities.add(HouseAmenities.WASHING_MACHINE);
                houseAmenities.add(HouseAmenities.TERRACE);
                houseAmenities.add(HouseAmenities.JACUZZI);
                personInHouse += 3;
            }
        }
    }

    public void printAmenities() {
        System.out.println("House amenities:");
        houseAmenities.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");
        hotel.printHotelAmenities();
    }

    public HouseCondition getHouseCondition() {
        return houseCondition;
    }

    public List<HouseAmenities> getHouseAmenities() {
        return houseAmenities;
    }


    public int getPersonInHouse() {
        return personInHouse;
    }


    @Override
    public String toString() {
        return "House{" +
                "house number=" + houseNumber +
                ", house condition=" + houseCondition +
                ", house amenities=" + houseAmenities +
                ", person in house=" + personInHouse +
                '}';
    }
}

