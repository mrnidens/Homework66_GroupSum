package ait.numbers.model;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int totalSum = Arrays.stream(numberGroups)
                .parallel()
                .flatMapToInt(Arrays::stream)
                .sum();
        return totalSum;
    }
}
