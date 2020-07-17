package org.sortingvisualizer.algorithms;

import org.sortingvisualizer.SortingVisualizer;
import org.sortingvisualizer.VisualizerFrame;

public class BubbleSort implements Runnable{

    private final Integer[] data;
    private final VisualizerFrame frames;

    public BubbleSort(Integer[] data, VisualizerFrame frames) {
        this.data = data;
        this.frames = frames;
    }

    @Override
    public void run() {
        sort();
        SortingVisualizer.isSorting = false;
    }

    void sort() {
        int aux;
        boolean breakCondition;
        for (int frame = 0 ; frame < data.length - 1 ; frame++) {
            breakCondition = false;
            for (int i = 0 ; i < data.length - 1 ; i++) {
                if (data[i] > data[i + 1]) {
                    aux = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = aux;
                    breakCondition = true;
                }
                frames.reDrawArray(data, i, i + 1);
                try {
                    Thread.sleep(SortingVisualizer.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!breakCondition) { break; }
        }
        frames.reDrawArray(data);
    }
}
