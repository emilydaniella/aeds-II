package exercicio5;

import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 17/02/25
*/

public class exercicio5 {

    // método que converte um caractere numérico em um inteiro
    public static int toInt(char c) {
        return c - '0'; // converte o caractere para seu valor numérico
    }

    // método que converte um caractere '0' ou '1' em um booleano
    public static boolean toBoolean(char c) {
        return c == '1'; // retorna verdadeiro se for '1', falso se for '0'
    }

    // método que retorna uma parte do texto entre os índices 'inicio' e 'fim'
    public static String cortarTexto(String texto, int inicio, int fim) {
        String resultado = "";

        for (int i = inicio; i < fim; i++) {
            resultado += texto.charAt(i);
        }

        return resultado;
    }

    // método que substitui parte do texto por outra string
    public static String trocarTexto(String texto, int inicio, int fim, String substituto) {
        String parteAntes = cortarTexto(texto, 0, inicio);
        String parteDepois = cortarTexto(texto, fim, texto.length());

        return parteAntes + substituto + parteDepois;
    }

    // método que extrai os operandos de uma operação lógica dentro dos parênteses
    public static char[] obterValores(String texto, int indice) {
        int qtd = 0;

        for (int i = indice + 1; texto.charAt(i) != ')'; i++) {
            if (texto.charAt(i) == '0' || texto.charAt(i) == '1') {
                qtd++;
            }
        }

        char[] valores = new char[qtd];

        for (int i = indice + 1, j = 0; texto.charAt(i) != ')'; i++) {
            if (texto.charAt(i) == '0' || texto.charAt(i) == '1') {
                valores[j] = texto.charAt(i);
                j++;
            }
        }

        return valores;
    }

    // operador lógico NOT
    public static char not(char[] valores) {
        return valores[0] == '0' ? '1' : '0';
    }

    // operador lógico AND
    public static char and(char[] valores) {
        for (char v : valores) {
            if (v == '0') {
                return '0';
            }
        }
        return '1';
    }

    // operador lógico OR
    public static char or(char[] valores) {
        for (char v : valores) {
            if (v == '1') {
                return '1';
            }
        }
        return '0';
    }

    // método que resolve expressões lógicas no texto
    public static String resolverExpressao(String texto) throws Exception {
        char[] valores;
        char resultado;
        int fim;

        for (int i = texto.length() - 1; i >= 0; i--) {
            if (texto.charAt(i) == '(') {
                switch (texto.charAt(i - 1)) {
                    case 'd': // operação AND
                        valores = obterValores(texto, i);
                        resultado = and(valores);
                        for (fim = i; texto.charAt(fim) != ')'; fim++);
                        texto = trocarTexto(texto, i - 3, fim + 1, resultado + "");
                        i -= 3;
                        break;
                    case 't': // operação NOT
                        valores = obterValores(texto, i);
                        resultado = not(valores);
                        texto = trocarTexto(texto, i - 3, i + 3, resultado + "");
                        i -= 3;
                        break;
                    case 'r': // operação OR
                        valores = obterValores(texto, i);
                        resultado = or(valores);
                        for (fim = i; texto.charAt(fim) != ')'; fim++);
                        texto = trocarTexto(texto, i - 2, fim + 1, resultado + "");
                        i -= 2;
                        break;
                    default:
                        throw new Exception("Erro ao resolver expressão.");
                }
            }
        }

        return cortarTexto(texto, 0, 1);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();

        while (texto.charAt(0) != '0') {
            int qtdVariaveis = toInt(texto.charAt(0));

            boolean[] valores = new boolean[qtdVariaveis];

            // obtém valores de A, B e C
            if (qtdVariaveis == 2) {
                valores[0] = toBoolean(texto.charAt(2));
                valores[1] = toBoolean(texto.charAt(4));
                texto = cortarTexto(texto, 6, texto.length());
            } else if (qtdVariaveis == 3) {
                valores[0] = toBoolean(texto.charAt(2));
                valores[1] = toBoolean(texto.charAt(4));
                valores[2] = toBoolean(texto.charAt(6));
                texto = cortarTexto(texto, 8, texto.length());
            }

            // substitui variáveis A, B e C pelos valores correspondentes
            for (int i = 0; i < texto.length(); i++) {
                if (texto.charAt(i) == 'A') {
                    texto = trocarTexto(texto, i, i + 1, valores[0] ? "1" : "0");
                } else if (texto.charAt(i) == 'B') {
                    texto = trocarTexto(texto, i, i + 1, valores[1] ? "1" : "0");
                } else if (texto.charAt(i) == 'C') {
                    texto = trocarTexto(texto, i, i + 1, valores[2] ? "1" : "0");
                }
            }

            // resolve a expressão e imprime o resultado
            texto = resolverExpressao(texto);
            System.out.println(texto);

            texto = sc.nextLine(); // lê a próxima entrada
        }

        sc.close();
    }
}
