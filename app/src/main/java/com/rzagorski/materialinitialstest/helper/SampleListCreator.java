package com.rzagorski.materialinitialstest.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @see <a href="http://stackoverflow.com/a/41156/6507689>http://stackoverflow.com/a/41156/6507689</a>
 * Created by Robert Zagórski on 2016-11-21.
 */
public class SampleListCreator {

    private static final char[] symbols;
    private static final Random random = new Random();

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = 'A'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }

    public static List<String[]> populateList(int listSize, int arraySize, int words) {
        List<String[]> outputList = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; ++i) {
            String[] listItem = new String[arraySize];
            for (int j = 0; j < arraySize; ++j) {
                listItem[j] = createRandomString(5, words);
            }
            outputList.add(i, listItem);
        }
        return outputList;
    }

    private static String createRandomString(int length, int words) {
        char[] buf = new char[length * words + 1];
        for (int idx = 0; idx < buf.length; ++idx) {
            if (idx > 0 && idx % length == 0) {
                buf[idx] = ' ';
                continue;
            }
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }
}
