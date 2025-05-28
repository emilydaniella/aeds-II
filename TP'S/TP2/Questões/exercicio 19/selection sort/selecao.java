import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class selecao {

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

    public static void selectionSort(Integer[] array, int len, File arquivo) {
        try (FileWriter fw = new FileWriter(arquivo)) {

            long comparacoes = 0;
            long movimentacoes = 0;
            long inicio = System.currentTimeMillis();

            for (int i = 0; i < len - 1; i++) {
                int min = i;
                for (int j = i + 1; j < len; j++) {
                    comparacoes++;
                    if (array[j] < array[min]) {
                        min = j;
                    }
                }
                if (min != i) {
                    swap(array, i, min);
                    movimentacoes += 3;
                }
            }

            long fim = System.currentTimeMillis();
            long duracao = fim - inicio;

            fw.write(comparacoes + "\t" + movimentacoes + "\t" + duracao + "\n");
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        selectionSort(array100, 100, new File("selection_100.txt"));
        selectionSort(array1000, 1000, new File("selection_1000.txt"));
        selectionSort(array10000, 10000, new File("selection_10000.txt"));
        selectionSort(array100000, 100000, new File("selection_100000.txt"));
    }
}
