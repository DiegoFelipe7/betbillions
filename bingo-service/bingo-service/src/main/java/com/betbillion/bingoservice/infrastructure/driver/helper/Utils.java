package com.betbillion.bingoservice.infrastructure.driver.helper;


import com.betbillion.bingoservice.domain.model.enums.TypeGame;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }


    public static Double bonus(Integer level) {
        Map<Integer, Double> bonusMap = Map.of(
                1, 0.08,//2
                2, 0.05,//1.2
                3, 0.03,// 0,8
                4, 0.02 //0,5
        );
        return bonusMap.getOrDefault(level, 0.0);
    }

    public static LocalDateTime starDate(String starDate){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.US);
        return LocalDateTime.parse(starDate,dateFormatter);
    }

    public static String formatStartDate(LocalDateTime startDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return startDate.format(dateFormatter);
    }

    public static BigDecimal priceBingo (Integer size){
        if(size>5 && size <=7){
            return BigDecimal.valueOf(5);
        }
        return BigDecimal.valueOf(size);
    }

    public static List<Integer> processTypeGame(TypeGame typeGame){
        if(typeGame.equals(TypeGame.L)){
            return List.of(0, 1, 2, 3, 4, 9, 14, 19, 24);
        }
        return  List.of(0, 4, 6, 8, 12, 16, 18, 20, 24);
    }




}
