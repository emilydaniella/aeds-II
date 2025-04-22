import java.net.*;
import java.util.Scanner;
import java.io.*;

/*
Autor: Daniella Silva
Data: 27/02/25
*/

public class exercicio13 {

    // método que verifica se o texto digitado é fim, indicando a condição de parada
    public static boolean parada(String texto) {
        boolean parada = true;

        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false;
        }

        return parada;
    }

    // método que obtém o conteúdo HTML de uma URL
    public static String getHtml(String endereco) {
        StringBuffer resposta = new StringBuffer();
        try {
            URL obj = new URL(endereco);
            HttpURLConnection conexao = (HttpURLConnection) obj.openConnection();

            // define o método de requisição como GET
            conexao.setRequestMethod("GET");

            // obtém o código de resposta da conexão
            int codigoResposta = conexao.getResponseCode();

            // se a conexão foi bem-sucedida (código 200)
            if (codigoResposta == HttpURLConnection.HTTP_OK) {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                String linha;

                while ((linha = entrada.readLine()) != null) {
                    resposta.append(linha);
                }

                // fecha os buffers
                entrada.close();
            } else {
                System.out.println("Erro na conexão: " + codigoResposta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resposta.toString();
    }

    // método que conta a frequência de caracteres específicos em um texto HTML
    public static void contador(String texto, int tamanho, String nome) {

        int saida01 = 0; // a
        int saida02 = 0; // e
        int saida03 = 0; // i
        int saida04 = 0; // o
        int saida05 = 0; // u
        int saida06 = 0; // á
        int saida07 = 0; // é
        int saida08 = 0; // í
        int saida09 = 0; // ó
        int saida10 = 0; // ú
        int saida11 = 0; // à
        int saida12 = 0; // è
        int saida13 = 0; // ì
        int saida14 = 0; // ò
        int saida15 = 0; // ù
        int saida16 = 0; // ã
        int saida17 = 0; // õ
        int saida18 = 0; // â
        int saida19 = 0; // ê
        int saida20 = 0; // ô
        int saida21 = 0; // î
        int saida22 = 0; // û
        int saida23 = 0; // consoante
        int saida24 = 0; // <br>
        int saida25 = 0; // <table>

        for (int i = 0; i < tamanho; i++) {
            if (texto.charAt(i) == '<') { 
                if (texto.startsWith("br>", i + 1)) {
                    saida24++;
                    i += 3;
                } else if (texto.startsWith("table>", i + 1)) {
                    saida25++;
                    i += 6;
                }
            } else if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if (texto.charAt(i) == 'a') saida01++;
                else if (texto.charAt(i) == 'e') saida02++;
                else if (texto.charAt(i) == 'i') saida03++;
                else if (texto.charAt(i) == 'o') saida04++;
                else if (texto.charAt(i) == 'u') saida05++;
                else saida23++; // se não for vogal, é consoante
            } else {
                switch (texto.charAt(i)) {
                    case 225: saida06++; break; // á
                    case 233: saida07++; break; // é
                    case 237: saida08++; break; // í
                    case 243: saida09++; break; // ó
                    case 250: saida10++; break; // ú
                    case 224: saida11++; break; // à
                    case 232: saida12++; break; // è
                    case 236: saida13++; break; // ì
                    case 242: saida14++; break; // ò
                    case 249: saida15++; break; // ù
                    case 227: saida16++; break; // ã
                    case 245: saida17++; break; // õ
                    case 226: saida18++; break; // â
                    case 234: saida19++; break; // ê
                    case 238: saida20++; break; // î
                    case 244: saida21++; break; // ô
                    case 251: saida22++; break; // û
                }
            }
        }

        //do jeito q o bonito do verde gosta de ler
        MyIO.println("a(" + saida01 + ") e(" + saida02 + ") i(" + saida03 + ") o(" + saida04 + ") u(" + saida05 + ") á(" + saida06 + ") é(" + saida07 + ") í(" + saida08 + ") ó(" + saida09 + ") ú(" + saida10 + ") à(" + saida11 + ") è(" + saida12 + ") ì(" + saida13 + ") ò(" + saida14 + ") ù(" + saida15 + ") ã(" + saida16 + ") õ(" + saida17 + ") â(" + saida18 + ") ê(" + saida19 + ") î(" + saida20 + ") ô(" + saida21 + ") û(" + saida22 + ") consoante(" + saida23 + ") <br>(" + saida24 + ") <table>(" + saida25 + ") " + nome);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();

        while (!parada(texto)) {
            String url = sc.nextLine(); // lê a URL da página
            String conteudoHtml = getHtml(url); // obtém o conteúdo HTML da página

            contador(conteudoHtml, conteudoHtml.length(), texto); 

            texto = sc.nextLine(); // lê a próxima entrada
        }

        sc.close();
    }
}
