package exercicio19;

import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 06/03/25
*/

public class exercicio19 {

    // método que converte um caractere numérico em um inteiro
    public static int toInt(char caractere) {
        return caractere - 48;
    }

    // método que converte um caractere '0' ou '1' em um booleano
    public static boolean toBoolean(char caractere) {
        return caractere != '0';
    }

    // método que retorna uma parte do texto entre os índices 'inicio' e 'fim'
    public static String extrairTexto(String texto, int inicio, int fim) {
        if (inicio >= fim) {
            return "";
        }
        return texto.charAt(inicio) + extrairTexto(texto, inicio + 1, fim);
    }

    // método que substitui parte do texto por outra string
    public static String substituirTexto(String texto, int inicio, int fim, String substituto) {
        String parteAntes = extrairTexto(texto, 0, inicio);
        String parteDepois = extrairTexto(texto, fim, texto.length());
        return parteAntes + substituto + parteDepois;
    }

    // método que extrai os operandos de uma operação lógica dentro dos parênteses
    public static char[] obterOperandos(String texto, int indice) {
        return obterOperandosRecursivo(texto, indice + 1, 0);
    }

    private static char[] obterOperandosRecursivo(String texto, int i, int contador) {
        if (texto.charAt(i) == ')') {
            return new char[contador];
        }
        if (texto.charAt(i) == '0' || texto.charAt(i) == '1') {
            char[] operandos = obterOperandosRecursivo(texto, i + 1, contador + 1);
            operandos[contador] = texto.charAt(i);
            return operandos;
        }
        return obterOperandosRecursivo(texto, i + 1, contador);
    }

    // operadores lógicos NOT, AND e OR
    public static char operadorNot(char[] operandos) {
        return operandos[0] == '0' ? '1' : '0';
    }

    public static char operadorAnd(char[] operandos) {
        return operadorAndRecursivo(operandos, 0);
    }

    private static char operadorAndRecursivo(char[] operandos, int indice) {
        if (indice == operandos.length) {
            return '1';
        }
        if (operandos[indice] == '0') {
            return '0';
        }
        return operadorAndRecursivo(operandos, indice + 1);
    }

    public static char operadorOr(char[] operandos) {
        return operadorOrRecursivo(operandos, 0);
    }

    private static char operadorOrRecursivo(char[] operandos, int indice) {
        if (indice == operandos.length) {
            return '0';
        }
        if (operandos[indice] == '1') {
            return '1';
        }
        return operadorOrRecursivo(operandos, indice + 1);
    }

    // método que resolve expressões lógicas no texto
    public static String resolverExpressao(String texto) throws Exception {
        return resolverExpressaoRecursivo(texto, texto.length() - 1);
    }

    private static String resolverExpressaoRecursivo(String texto, int i) throws Exception {
        if (i < 0) {
            return texto;
        }
        if (texto.charAt(i) == '(') {
            char[] operandos;
            char resultado;
            int fim;
            switch (texto.charAt(i - 1)) {
                case 'd':
                    operandos = obterOperandos(texto, i);
                    resultado = operadorAnd(operandos);
                    fim = encontrarFim(texto, i);
                    texto = substituirTexto(texto, i - 3, fim + 1, resultado + "");
                    return resolverExpressaoRecursivo(texto, i - 3);
                case 't':
                    operandos = obterOperandos(texto, i);
                    resultado = operadorNot(operandos);
                    texto = substituirTexto(texto, i - 3, i + 3, resultado + "");
                    return resolverExpressaoRecursivo(texto, i - 3);
                case 'r':
                    operandos = obterOperandos(texto, i);
                    resultado = operadorOr(operandos);
                    fim = encontrarFim(texto, i);
                    texto = substituirTexto(texto, i - 2, fim + 1, resultado + "");
                    return resolverExpressaoRecursivo(texto, i - 2);
                default:
                    throw new Exception("Erro");
            }
        }
        return resolverExpressaoRecursivo(texto, i - 1);
    }

    private static int encontrarFim(String texto, int i) {
        if (texto.charAt(i) == ')') {
            return i;
        }
        return encontrarFim(texto, i + 1);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();

        while (texto.charAt(0) != '0') {
            int cont = toInt(texto.charAt(0));
            boolean[] valores = new boolean[cont];

            if (cont == 2) {
                valores[0] = toBoolean(texto.charAt(2));
                valores[1] = toBoolean(texto.charAt(4));
                texto = extrairTexto(texto, 6, texto.length());
            } else if (cont == 3) {
                valores[0] = toBoolean(texto.charAt(2));
                valores[1] = toBoolean(texto.charAt(4));
                valores[2] = toBoolean(texto.charAt(6));
                texto = extrairTexto(texto, 8, texto.length());
            }

            // substitui variáveis A, B e C pelos valores correspondentes
            for (int i = 0; i < texto.length(); i++) {
                if (texto.charAt(i) == 'A') {
                    texto = substituirTexto(texto, i, i + 1, valores[0] ? "1" : "0");
                } else if (texto.charAt(i) == 'B') {
                    texto = substituirTexto(texto, i, i + 1, valores[1] ? "1" : "0");
                } else if (texto.charAt(i) == 'C') {
                    texto = substituirTexto(texto, i, i + 1, valores[2] ? "1" : "0");
                }
            }

            // resolve a expressão e imprime o resultado
            texto = resolverExpressao(texto);
            System.out.println(texto);

            texto = sc.nextLine();
        }

        sc.close();
    }
}
