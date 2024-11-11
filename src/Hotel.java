import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private final String hotelName;
    private final List<HotelAmenities> hotelAmenities = new ArrayList<>(HotelAmenities.getListAmenities());
    private final List<Reserve> listOfReservation = new ArrayList<>();
    private final List<Client> listOfClients = new ArrayList<>();
    private final List<House> houses = new ArrayList<>();

    public Hotel(final String hotelName) {
        this.hotelName = hotelName;
    }

    public void reserve(final House house, final String checkInDate, final String checkOutDate, final Client client) {
        LocalDate inDate = DateUtil.dateFormatter(checkInDate);
        LocalDate outDate = DateUtil.dateFormatter(checkOutDate);


        boolean isAvailable = listOfReservation.stream()
                .noneMatch(reserve ->
                        reserve.getHouse().equals(house) &&
                                (inDate.isBefore(reserve.getCheckOutDate()) && outDate.isAfter(reserve.getCheckInDate()))
                );

        if (!isAvailable) {
            throw new BookingException("The house is already reserved for the selected dates.");
        }

        double priceForHouse = priceOfHouse(house);
        double totalPriceForStay = priceForHouse * (outDate.toEpochDay() - inDate.toEpochDay());


        if (inDate.getMonth() == Month.NOVEMBER || inDate.getMonth() == Month.MARCH) {
            double discount = 0.2;
            totalPriceForStay *= (1 - discount);
            System.out.println("Discount applied: 20%");
        }

        System.out.println("Total cost of payment in hrn: " + totalPriceForStay);
        System.out.println("Balance before payment: " + client.getBalance());


        if (client.getBalance() >= totalPriceForStay) {
            client.decrementBalance(totalPriceForStay);
            System.out.println("Balance after payment: " + client.getBalance());

            Reserve reserve = new Reserve(house, inDate, outDate, client);
            listOfReservation.add(reserve);
            listOfClients.add(client);
            houses.add(house);
        } else {
            throw new BookingException("Not enough money to pay.");
        }
    }

    public void calendarOfReservation(final Month month) {
        System.out.println("Available booking days in " + month.name().toLowerCase());

        int year = LocalDate.now().getYear();
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        int monthLength = month.length(firstDayOfMonth.isLeapYear());

        firstDayOfMonth.datesUntil(firstDayOfMonth.plusDays(monthLength))
                .filter(currentDay ->
                        listOfReservation.stream()
                                .noneMatch(reserve ->
                                        currentDay.isAfter(reserve.getCheckInDate().minusDays(1)) &&
                                                currentDay.isBefore(reserve.getCheckOutDate().plusDays(1))
                                )
                )
                .forEach(currentDay -> System.out.print(currentDay.getDayOfMonth() + "|"));

        System.out.println();
    }

    private double priceOfHouse(final House house) {
        return house.getHouseAmenities().stream()
                .mapToDouble(HouseAmenities::getPrice)
                .sum();
    }

    private double expenseOfHouse(final House house) {
        return house.getHouseAmenities().stream()
                .mapToDouble(HouseAmenities::getExpense)
                .sum();
    }

    private double calculateIncomeOrExpense(boolean isIncome, double discount) {
        return houses.stream()
                .mapToDouble(house -> {
                    Reserve reserve = getHouseFromReservation(house);

                    if (reserve == null) {
                        return 0;
                    }

                    Month reservationMonth = reserve.getCheckInDate().getMonth();

                    double price = isIncome ? priceOfHouse(house) : expenseOfHouse(house);
                    if (reservationMonth == Month.NOVEMBER || reservationMonth == Month.MARCH) {
                        return price * discount;
                    } else {
                        return price;
                    }
                })
                .sum();
    }


    public void printIncomeAndExpense() {
        final double discount = 0.8;
        double income = calculateIncomeOrExpense(true, discount);
        System.out.println("Income of hotel:");
        System.out.println(income);
        System.out.println("-------------------------------------------------------------------");
        double expense = calculateIncomeOrExpense(false, discount);
        System.out.println("Expense of hotel:");
        System.out.println(expense);
    }

    public void printHotelAmenities() {
        System.out.println("Hotel amenities:");
        hotelAmenities.forEach(e -> System.out.println(e.getTextAmenities()));
    }

    public void printHousesWithAmenities(final HouseCondition houseCondition) {
        List<House> houseList = houses.stream()
                .filter(e -> e.getHouseCondition().getTextCondition().equals(houseCondition.getTextCondition()))
                .toList();
        System.out.println("Houses with " + houseCondition.getTextCondition() + " condition:");
        houseList.forEach(System.out::println);
    }

    public void printHousesWithAmenities(final HouseAmenities houseAmenities) {
        List<House> houseList = houses.stream()
                .filter(house -> house.getHouseAmenities().contains(houseAmenities))
                .toList();
        System.out.println("Houses that contains " + houseAmenities.getAmenitiesText() + ":");
        houseList.forEach(System.out::println);
    }

    public void printListOfReservation() {
        System.out.println("List of reservation:");
        listOfReservation.forEach(System.out::println);
    }

    public String getHotelName() {
        return hotelName;
    }

    private Reserve getHouseFromReservation(final House house) {
        return listOfReservation.stream()
                .filter(reserve -> reserve.getHouse().equals(house))
                .findFirst()
                .orElse(null);
    }
}
