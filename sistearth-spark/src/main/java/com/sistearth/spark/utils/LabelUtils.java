package com.sistearth.spark.utils;

import lombok.NoArgsConstructor;

import static java.util.ResourceBundle.getBundle;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class LabelUtils {
    public static String getLabel(String label) {
        return getBundle("strings").getString(label);
    }
}
