package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum{

    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum()  {
        // TODO Homework: reduce sum numbers of numberGroups, use Threads

        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }

        Thread[] threads = new Thread[numberGroups.length];


        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();

        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int res = Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();

        return res;
    }
}
