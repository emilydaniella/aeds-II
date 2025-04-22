import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 21/02/25
*/

public class exercicio9 {
    
    public static void bubbleSort(char[] caracteres, int tamanho) {
        char temp;

        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (caracteres[j] > caracteres[j + 1]) {
                    temp = caracteres[j];
                    caracteres[j] = caracteres[j + 1];
                    caracteres[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String input = sc.nextLine();
        
        while (!(input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')) {
            String[] palavras= input.split("-");

                String palavra1 = palavras[0];
                String palavra2 = palavras[1];

                if (palavra1.length() != palavra2.length()) {
                    System.out.println("Nï¿½O");
                } else {
                    int tamanho = palavra1.length();
                    char[] array1 = new char[tamanho];
                    char[] array2 = new char[tamanho];

                    for (int i = 0; i < tamanho; i++) {
                        char c1 = palavra1.charAt(i);
                        char c2 = palavra2.charAt(i);

                        if (c1 >= 'A' && c1 <= 'Z') {
                            c1 = (char) (c1 + 32);
                        }
                        if (c2 >= 'A' && c2 <= 'Z') {
                            c2 = (char) (c2 + 32);
                        }

                        array1[i] = c1;
                        array2[i] = c2;
                    }

                    bubbleSort(array1, tamanho);
                    bubbleSort(array2, tamanho);

                    boolean anagrama = true;
                    for (int i = 0; i < tamanho; i++) {
                        if (array1[i] != array2[i]) {
                            anagrama = false;
                        }
                    }

                    if (anagrama ==true) {
                        System.out.println("SIM");
                    } else {
                        System.out.println("NÃO");
                    }
                }
            

            input = sc.nextLine();
        }

        sc.close();
    }
}
