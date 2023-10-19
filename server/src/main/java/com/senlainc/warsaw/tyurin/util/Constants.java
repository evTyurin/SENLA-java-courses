package com.senlainc.warsaw.tyurin.util;

public class Constants {
    public static final String PATH_TO_CRAFTSMEN_CSV = "server\\src\\main\\resources\\craftsmen.csv";
    public static final String PATH_TO_GARAGE_PLACES_CSV = "server\\src\\main\\resources\\garagePlaces.csv";
    public static final String PATH_TO_ORDERS_CSV = "server\\src\\main\\resources\\orders.csv";
    public static final String CRAFTSMEN_CSV_HEADER = "id,name,surname";
    public static final String GARAGE_PLACES_CSV_HEADER = "id,number,space";
    public static final String ORDERS_CSV_HEADER = "id,price,submissionDate,startDate,completionDate,orderStatus,craftsmenId,garagePlaceId";
    public static final String ABILITY_TO_ADD_GARAGE_PLACE = "garage-place.add.enabled";
    public static final String ABILITY_TO_REMOVE_GARAGE_PLACE = "garage-place.remove.enabled";
    public static final String ABILITY_TO_SHIFT_ORDER_COMPLETION_TIME = "order.shift-completion-time.enabled";
    public static final String ABILITY_TO_REMOVE_ORDER = "order.remove.enabled";
    public static final String PATH_TO_CRAFTSMEN_JSON = "server\\src\\main\\resources\\craftsmen.json";
    public static final String PATH_TO_ORDERS_JSON = "server\\src\\main\\resources\\orders.json";
    public static final String PATH_TO_GARAGE_PLACES_JSON = "server\\src\\main\\resources\\garagePlaces.json";
}
