package com.github.zipcodewilmington.casino.games.roulette;

import java.util.HashMap;
import java.util.Map;

public enum RouletteNumParam {
    //blackOrRed really means GREEN, blackOrRed
    // 100 = Green
    zero(0, 100, 100, 100, 100, 100),
    one(1, 1, 2, 1, 1, 1),
    two(2, 2, 1, 1, 1, 2),
    three(3, 1, 2, 1, 1, 3),
    four(4, 2, 1, 1, 1, 1),
    five(5, 1, 2, 1, 1,2),
    six(6, 2, 1, 1, 1, 3),
    seven(7, 1, 2, 1, 1, 1),
    eight(8, 2, 1, 1, 1, 2),
    nine(9, 1, 2, 1, 1, 3),
    ten(10, 2, 1, 1, 1, 1),
    eleven(11, 1, 1, 1, 1, 2),
    twelve(12, 2, 2, 1, 1, 3),
    thirteen(13, 1, 1, 1, 2, 1),
    fourteen(14, 2, 2, 1, 2, 2),
    fifteen(15, 1, 1, 1, 2, 3),
    sixteen(16, 2, 2, 1, 2, 1),
    seventeen(17, 1, 1, 1, 2, 2),
    eighteen(18, 2, 2, 1, 2, 3),
    nineteen(19, 1, 2, 2, 2, 1),
    twenty(20, 2, 1, 2, 2, 2),
    twenty_one(21, 1, 2, 2, 2, 3),
    twenty_two(22, 2, 1, 2, 2, 1),
    twenty_three(23, 1, 2, 2, 2, 2),
    twenty_four(24, 2, 1, 2, 2, 3),
    twenty_five(25, 1, 2, 2, 3, 1),
    twenty_six(26, 2, 1, 2, 3, 2),
    twenty_seven(27, 1, 2, 2, 3, 3),
    twenty_eight(28, 2, 1, 2, 3, 1),
    twenty_nine(29, 1, 1, 2, 3, 2),
    thirty(30, 2, 2, 2, 3, 3),
    thirty_one(31, 1, 1, 2, 3, 1),
    thirty_two(32, 2, 2, 2, 3, 2),
    thirty_three(33, 1, 1, 2, 3, 3),
    thirty_four(34, 2, 2, 2, 3, 1),
    thirty_five(35, 1, 1, 2, 3, 2),
    thirty_six(36, 2, 2, 2, 3, 3);
    public int rouletteNum;
    public int oddOrEven;
    public int blackOrRed;
    public int highOrLow;
    public int whichDoz;
    public int whichColumn;
RouletteNumParam(int rouletteNum, int oddOrEven, int blackOrRed, int highOrLow, int whichDoz, int whichColumn) {
        this.rouletteNum = rouletteNum;
        this.oddOrEven = oddOrEven;
        this.blackOrRed = blackOrRed;
        this.highOrLow = highOrLow;
        this.whichDoz = whichDoz;
        this.whichColumn = whichColumn;

}

}
