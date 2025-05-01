import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class quicksort {

    public static void preencheArray(Integer[] array, int len) {
        Random random = new Random();
        random.setSeed(4);
        for (int i = 0; i < len; i++) {
            array[i] = (Math.abs(random.nextInt()) % 100) + 1;
        }
    }

    public static void swap(Integer[] array, int a, int b) {
        int aux = array[a];
        array[a] = array[b];
        array[b] = aux;
    }

    public static void quick(Integer[] array, int len, File arquivo) {
        long[] comp = {0};
        long[] mov = {0};

        long inicio = System.currentTimeMillis();
        quicksortRec(array, 0, len - 1, comp, mov);
        long fim = System.currentTimeMillis();
        long duracao = fim - inicio;

        try (FileWriter fw = new FileWriter(arquivo)) {
            fw.write(comp[0] + "\t" + mov[0] + "\t" + duracao + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void quicksortRec(Integer[] array, int esq, int dir, long[] comp, long[] mov) {
        if (esq < dir) {
            int pivo = particiona(array, esq, dir, comp, mov);
            quicksortRec(array, esq, pivo - 1, comp, mov);
            quicksortRec(array, pivo + 1, dir, comp, mov);
        }
    }

    private static int particiona(Integer[] array, int esq, int dir, long[] comp, long[] mov) {
        int pivo = array[dir];
        mov[0]++;
        int i = esq - 1;

        for (int j = esq; j < dir; j++) {
            comp[0]++;
            if (array[j] < pivo) {
                i++;
                swap(array, i, j);
                mov[0] += 3;
            }
        }

        swap(array, i + 1, dir);
        mov[0] += 3;
        return i + 1;
    }

    public static void main(String[] args) {
        Integer[] array100 = new Integer[100];
        Integer[] array1000 = new Integer[1000];
        Integer[] array10000 = new Integer[10000];
        Integer[] array100000 = new Integer[100000];

        preencheArray(array100, 100);
        preencheArray(array1000, 1000);
        preencheArray(array10000, 10000);
        preencheArray(array100000, 100000);

        quick(array100, 100, new File("quicksort_100.txt"));
        quick(array1000, 1000, new File("quicksort_1000.txt"));
        quick(array10000, 10000, new File("quicksort_10000.txt"));
        quick(array100000, 100000, new File("quicksort_100000.txt"));
    }
}
