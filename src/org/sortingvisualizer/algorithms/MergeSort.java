package org.sortingvisualizer.algorithms;

import org.sortingvisualizer.SortingVisualizer;
import org.sortingvisualizer.VisualizerFrame;

public class MergeSort implements Runnable {

    private final Integer[] data;
    private final VisualizerFrame frames;
    private final int size;

    public MergeSort(Integer[] data, VisualizerFrame visualizerFrame, int size) {
        this.data = data;
        this.frames = visualizerFrame;
        this.size = size;
    }

    @Override
    public void run() {
        mergeSort(data);
        SortingVisualizer.isSorting = false;
    }

    public void mergeSort(Integer[] data) {
        sort(data, size);
    }

    private void merge(Integer[] data, Integer[] left, Integer[] right, int l, int r){
        int i = 0, j = 0, k = 0;
        while (i < l && j < r) {
            if (left[i] <= right[j]) {
                data[k++] = left[i++];
            } else {
                data[k++] = right[j++];
            }
            frames.reDrawArray(this.data, i, j, k);
            try {
                Thread.sleep(SortingVisualizer.sleep);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (i < l) {
            data[k++] = left[i++];
        }
        while (j < r) {
            data[k++] = right[j++];
        }
        frames.reDrawArray(this.data);
    }

    private void sort(Integer[] data, int size){
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[size - mid];

        System.arraycopy(data, 0, left, 0, mid);
        if (size - mid >= 0) System.arraycopy(data, mid, right, mid - mid, size - mid);
        sort(left, mid);
        sort(right, size - mid);
        merge(data, left, right, mid, size - mid);
    }
}
