import java.io.*;
import java.util.*;

class Data {
    public int dia, mes, ano;

    public Data() {}
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
}

class Show {
    public String id, tipo, titulo, diretor, pais, classificacao, duracao;
    public String[] elenco, categorias;
    public int tamElenco, tamCategorias, anoLancamento;
    public Data dataAdicionado;

    public Show() {}

    public Show clone() {
        Show novo = new Show();
        novo.id = id;
        novo.tipo = tipo;
        novo.titulo = titulo;
        novo.diretor = diretor;
        novo.pais = pais;
        novo.classificacao = classificacao;
        novo.duracao = duracao;
        novo.anoLancamento = anoLancamento;
        novo.dataAdicionado = new Data(dataAdicionado.dia, dataAdicionado.mes, dataAdicionado.ano);

        if (elenco != null) {
            novo.elenco = Arrays.copyOf(elenco, elenco.length);
            novo.tamElenco = elenco.length;
        }
        if (categorias != null) {
            novo.categorias = Arrays.copyOf(categorias, categorias.length);
            novo.tamCategorias = categorias.length;
        }
        return novo;
    }

    public static int mesParaInt(String mes) {
        switch (mes) {
            case "January": return 1;
            case "February": return 2;
            case "March": return 3;
            case "April": return 4;
            case "May": return 5;
            case "June": return 6;
            case "July": return 7;
            case "August": return 8;
            case "September": return 9;
            case "October": return 10;
            case "November": return 11;
            case "December": return 12;
            default: return 0;
        }
    }

    public static String intParaMes(int m) {
        String[] meses = {"", "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        if (m >= 1 && m <= 12) return meses[m];
        return "NaN";
    }

    public String dataParaString() {
        return intParaMes(dataAdicionado.mes) + " " + dataAdicionado.dia + ", " + dataAdicionado.ano;
    }

    public String arrayParaString(String[] arr) {
        if (arr == null || arr.length == 0) return "NaN";
        return String.join(", ", arr);
    }

    public void mostrar() {
        String dataStr = (dataAdicionado.dia != 0 && dataAdicionado.mes != 0 && dataAdicionado.ano != 0)
                ? dataParaString() : "NaN";
        String elencoStr = (elenco != null && elenco.length > 0) ? arrayParaString(elenco) : "NaN";
        String catStr = (categorias != null && categorias.length > 0) ? arrayParaString(categorias) : "NaN";
        System.out.printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %d ## %s ## %s ## [%s] ##\n",
                id, titulo, tipo, diretor, elencoStr, pais, dataStr, anoLancamento, classificacao, duracao, catStr);
    }
}

class Pilha {
    private ArrayList<Show> pilha;

    public Pilha() {
        pilha = new ArrayList<>();
    }

    public void empilhar(Show s) {
        pilha.add(0, s.clone());
    }

    public Show desempilhar() {
        if (pilha.isEmpty()) throw new RuntimeException("Pilha vazia");
        return pilha.remove(0);
    }

    public void mostrar() {
        int idx = pilha.size() - 1;
        for (Show s : pilha) {
            System.out.print("[" + (idx--) + "] ");
            s.mostrar();
        }
    }
}

public class exercicio9 {
    public static Show lerShow(String linha) {
        String[] campos = new String[11];
        int k = 0;
        boolean aspas = false;
        StringBuilder atual = new StringBuilder();
        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);
            if (c == '"') {
                aspas = !aspas;
            } else if (c == ',' && !aspas) {
                if (k < 11) campos[k++] = atual.toString();
                atual.setLength(0);
            } else {
                atual.append(c);
            }
        }
        if (k < 11) campos[k] = atual.toString();
        // Preenche campos faltantes com "NaN"
        for (int i = 0; i < 11; i++) {
            if (campos[i] == null) campos[i] = "NaN";
        }

        Show s = new Show();
        s.id = campos[0];
        s.tipo = campos[1];
        s.titulo = campos[2];
        s.diretor = campos[3];

        // Elenco
        if (campos[4] != null && !campos[4].equals("NaN") && !campos[4].isEmpty()) {
            String[] elencoArr = campos[4].split(", ");
            Arrays.sort(elencoArr);
            s.elenco = elencoArr;
            s.tamElenco = elencoArr.length;
        }

        s.pais = campos[5];

        // Data
        if (campos[6] != null && !campos[6].equals("NaN") && !campos[6].isEmpty()) {
            String[] partes = campos[6].split(" ");
            if (partes.length >= 3) {
                s.dataAdicionado = new Data(
                        Integer.parseInt(partes[1].replace(",", "")),
                        Show.mesParaInt(partes[0]),
                        Integer.parseInt(partes[2])
                );
            } else {
                s.dataAdicionado = new Data(0, 0, 0);
            }
        } else {
            s.dataAdicionado = new Data(0, 0, 0);
        }

        try {
            s.anoLancamento = Integer.parseInt(campos[7]);
        } catch (Exception e) {
            s.anoLancamento = 0;
        }
        s.classificacao = campos[8];
        s.duracao = campos[9];

        // Categorias
        if (campos[10] != null && !campos[10].equals("NaN") && !campos[10].isEmpty()) {
            String[] catArr = campos[10].split(", ");
            Arrays.sort(catArr);
            s.categorias = catArr;
            s.tamCategorias = catArr.length;
        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("/tmp/disneyplus.csv"));
        String header = br.readLine();
        ArrayList<Show> todos = new ArrayList<>();
        String linha;
        while ((linha = br.readLine()) != null) {
            todos.add(lerShow(linha));
        }
        br.close();

        Pilha pilha = new Pilha();
        Scanner sc = new Scanner(System.in);
        String entrada = sc.next();
        while (!entrada.equals("FIM")) {
            int id = Integer.parseInt(entrada.substring(1)) - 1;
            pilha.empilhar(todos.get(id));
            entrada = sc.next();
        }

        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String op = sc.next();
            if (op.equals("I")) {
                String idstr = sc.next();
                int id = Integer.parseInt(idstr.substring(1)) - 1;
                pilha.empilhar(todos.get(id));
            } else if (op.equals("R")) {
                Show s = pilha.desempilhar();
                System.out.println("(R) " + s.titulo);
            }
        }
        pilha.mostrar();
    }
}