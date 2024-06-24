package com.lofri.generator;

import com.lofri.NumberConstants;
import com.lofri.NumberFormat;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamGenerators {

    private StreamGenerators() {}

    public static Stream<String> stringNumbersStream() {
        return StreamGenerators.stringNumbersStream(1);
    }

    public static Stream<String> stringNumbersStream(int size) {
        if (size > NumberConstants.NUMBERS.length) {
            throw new IllegalArgumentException("Supported max length is " + NumberConstants.NUMBERS.length);
        }
        return Stream.of(Arrays.copyOf(NumberConstants.NUMBERS, size));
    }

    public static Stream<Integer> intNumbersStream() {
        return StreamGenerators.intNumbersStream(11);
    }

    public static Stream<Integer> intNumbersStream(int size) {
        return Stream.iterate(0, i -> i + 1)
                .limit(size);
    }

    public static Stream<NumberFormat> numberFormatsStream() {
        return StreamGenerators.numberFormatsStream(11);
    }

    public static Stream<NumberFormat> numberFormatsStream(int size) {
        if (NumberConstants.NUMBERS.length < size) {
            throw new IllegalArgumentException("Supported max length is " + NumberConstants.NUMBERS.length);
        }

        NumberFormat[] numberFormats = new NumberFormat[size];
        for (int i = 0 ; i < size; i++) {
            numberFormats[i] = new NumberFormat(i, NumberConstants.NUMBERS[i] + "Name");
        }
        return Stream.of(numberFormats);
    }
}
