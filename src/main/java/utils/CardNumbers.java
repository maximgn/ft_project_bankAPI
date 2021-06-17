package utils;

import java.util.*;

public class CardNumbers {
    public static List<String> codes = new ArrayList<>();
    static {
        for(int i = 10; i < 100; i++) {
            codes.add("" + i);
        }
    }
}
