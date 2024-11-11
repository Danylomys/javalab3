import java.util.Arrays;
import java.util.List;

public enum HotelAmenities {
    FREE_WIFI("free Wi-Fi"),
    BREAKFAST("breakfast"),

    GYM("gym"),
    SWIMMING_POOL("swimming pool"),
    SPA_SERVICES("spa services"),
    RESTAURANT("restaurant"),
    CONCIERGE_SERVICE("concierge service"),
    PARKING("parking");

    private final String textAmenities;
    HotelAmenities(final String textAmenities){
        this.textAmenities = textAmenities;
    }

    public String getTextAmenities() {
        return textAmenities;
    }

    public static List<HotelAmenities> getListAmenities(){
        return Arrays.asList(HotelAmenities.values());
    }
}

