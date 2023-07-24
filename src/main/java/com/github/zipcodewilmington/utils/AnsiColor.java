package com.github.zipcodewilmington.utils;

/**
 * Created by leon on 7/21/2020.
 * used to create color for output of `IOConsole`
 */
public enum AnsiColor {
    AUTO("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[91m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[93m"),
    DULL_CYAN("\u001B[36m"),
    BLUE("\u001B[94m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[96m"),
    WHITE("\u001B[37m"),    // this actually looks like gray - not sure why - a dull white?
    MAGENTA("\u001B[95m"),
    WHITE_BACKGROUND("\u001B[107m");

    private final String color;

    AnsiColor(String ansiColor) {
        this.color = ansiColor;
    }

    public String getColor() {
        return color;
    }

}