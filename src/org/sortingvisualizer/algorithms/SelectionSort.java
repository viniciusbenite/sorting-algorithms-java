package org.sortingvisualizer.algorithms;

import org.sortingvisualizer.SortingVisualizer;
import org.sortingvisualizer.VisualizerFrame;

public class SelectionSort implements Runnable {

    private final Integer[] data;
    private final VisualizerFrame frames;

    public SelectionSort(Integer[] data, VisualizerFrame frames) {
        this.data = data;
        this.frames = frames;
    }

    @Override
    public void run() {
        sort();
        SortingVisualizer.isSorting = false;
    }

    void sort() {
        for (int i = 0 ; i < data.length - 1 ; i++) {
            int minIndex = i;
            for (int j = i+1 ; j < data.length ; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
                frames.reDrawArray(data, minIndex, j-1);
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int aux = data[minIndex];
            data[minIndex] = data[i];
            data[i] = aux;
        }
        frames.reDrawArray(data);
    }
}
