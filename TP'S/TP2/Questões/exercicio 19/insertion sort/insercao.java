import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class insercao {

    public static void preencheArray(Integer[] array, int len) {
        Random random = new Random();
        random.setSeed(4);
        for (int i = 0; i < len; i++) {
            array[i] = (Math.abs(random.nextInt()) % 100) + 1;
        }
    }

    public static void insertionSort(Integer[] array, int len, File arquivo) {
        try (FileWriter fw = new FileWriter(arquivo)) {

            long comparacoes = 0;
            long movimentacoes = 0;
            long inicio = System.currentTimeMillis();

            for (int i = 1; i < len; i++) {
                int chave = array[i];
                movimentacoes++; // salvando chave temporariamente
                int j = i - 1;

                while (j >= 0 && array[j] > chave) {
                    comparacoes++;
                    array[j + 1] = array[j];
                    movimentacoes++;
                    j--;
                }

                comparacoes++; // comparação que termina o while
                array[j + 1] = chave;
                movimentacoes++;
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

        insertionSort(array100, 100, new File("insertion_100.txt"));
        insertionSort(array1000, 1000, new File("insertion_1000.txt"));
        insertionSort(array10000, 10000, new File("insertion_10000.txt"));
        insertionSort(array100000, 100000, new File("insertion_100000.txt"));
    }
}
