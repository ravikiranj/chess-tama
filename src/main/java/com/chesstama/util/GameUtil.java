package com.chesstama.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * GameUtil
 *
 * @author ravikiranj
 * @since Aug 2017
 */
public class GameUtil {

    public static <T> List<T> getRandomSubList(final List<T> input, final int subsetSize) {
        Random r = new Random();

        // Copy list and operate on that
        List<T> copyList = input.stream().collect(Collectors.toList());
        int inputSize = copyList.size();
        for (int i = 0; i < subsetSize; i++) {
            int indexToSwap = i + r.nextInt(inputSize - i);
            T temp = copyList.get(i);
            copyList.set(i, copyList.get(indexToSwap));
            copyList.set(indexToSwap, temp);
        }

        return copyList.subList(0, subsetSize);
    }
}
