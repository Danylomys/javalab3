import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Kurort");

        House house1 = new House(HouseCondition.ECONOMY,1,hotel);
        House house2 = new House(HouseCondition.ECONOMY,2, hotel);
        House house3 = new House(HouseCondition.PRESIDENT,3,hotel);

        Client client1 = new Client("Vasyl",121,1000000);
        Client client2 = new Client("Andriy",122,2000000);
        Client client3 = new Client("Oleh",123,3000000);

        house1.addAdditionalAmenities(HouseAmenities.BED);
        hotel.reserve(house1,"13-10-2024","15-10-2024",client1);
        hotel.reserve(house2,"12-03-2024","15-03-2024",client2);
        hotel.calendarOfReservation(Month.MARCH);
        hotel.reserve(house3,"13-02-2024","18-03-2024",client3);

        house1.printAmenities();
        hotel.printIncomeAndExpense();
        hotel.printHousesWithAmenities(HouseCondition.ECONOMY);
        hotel.printHousesWithAmenities(HouseAmenities.KITCHEN);
        hotel.printListOfReservation();
    }
}
