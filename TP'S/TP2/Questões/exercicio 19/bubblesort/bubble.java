import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class bubble {

    public static void preencheArray(Integer[] array, int len) {
        Random random = new Random();
        random.setSeed(4); //

        for (int i = 0; i < len; i++) {
            array[i] = (Math.abs(random.nextInt()) % 100) + 1;
        }
    }

    public static void swap(Integer[] array, int a, int b) {
        int aux = array[a];
        array[a] = array[b];
        array[b] = aux;
    }

//bubble sort
    // O(n^2) - pior caso: array ordenado, melhor caso: array reversamente ordenado
    public static void bubbleSort(Integer[] array, int len, File arquivo) {
        try (FileWriter fw = new FileWriter(arquivo)) {

            long inicio = System.currentTimeMillis();
            long comparacoes = 0;
            long movimentacoes = 0;

            for (int i = 0; i < len - 1; i++) {
                for (int j = 0; j < len - i - 1; j++) {
                    comparacoes++;
                    if (array[j + 1] < array[j]) {
                        swap(array, j + 1, j);
                        movimentacoes += 3; // 3 movimentações por troca
                    }
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

        bubbleSort(array100, 100, new File("bubble_100.txt"));
        bubbleSort(array1000, 1000, new File("bubble_1000.txt"));
        bubbleSort(array10000, 10000, new File("bubble_10000.txt"));
        bubbleSort(array100000, 100000, new File("bubble_100000.txt"));
    }
}
