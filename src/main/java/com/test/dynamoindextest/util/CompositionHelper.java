package com.test.dynamoindextest.util;

public class CompositionHelper {

    public static String concatValues(String...values) {
        var builder = new StringBuilder();

        for(int i = 0; i<values.length; i++) {
            builder.append(values[i]);

            if (i < values.length -1)
                builder.append("#");
        }

        return builder.toString();
    }
}
