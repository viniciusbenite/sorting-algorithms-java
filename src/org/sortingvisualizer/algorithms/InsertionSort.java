package org.sortingvisualizer.algorithms;

import org.sortingvisualizer.SortingVisualizer;
import org.sortingvisualizer.VisualizerFrame;

public class InsertionSort implements Runnable{

    private final Integer[] data;
    private final VisualizerFrame frames;

    public InsertionSort(Integer[] data, VisualizerFrame frames) {
        this.data = data;
        this.frames = frames;
    }

    @Override
    public void run() {
        sort();
        SortingVisualizer.isSorting = false;
    }

    void sort() {
        for (int i = 1 ; i < data.length ; i++) {
            int current = data[i];
            int previous = i - 1;
            frames.reDrawArray(data, previous, current);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            while (previous >= 0 && data[previous] > current) {
                data[previous + 1] = data[previous];
                previous--;
            }
            data[previous + 1] = current;
        }
        frames.reDrawArray(data);
    }
}
