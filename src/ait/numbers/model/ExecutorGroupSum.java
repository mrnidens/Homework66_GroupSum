package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum {
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() throws InterruptedException {
        // TODO Homework: reduce sum numbers of numberGroups, use ExecutorService
        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];

        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }

        ExecutorService executorService = Executors.newWorkStealingPool();


        for (int i = 0; i < tasks.length; i++) {
            executorService.execute(tasks[i]);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);


        int res = Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();

        return res;
    }
}
