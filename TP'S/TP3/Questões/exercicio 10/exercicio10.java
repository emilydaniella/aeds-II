import java.io.*;
import java.util.*;

class Data {
    int dia, mes, ano;

    Data() {
        this.dia = 0;
        this.mes = 0;
        this.ano = 0;
    }

    Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int compareTo(Data b) {
        if (this.ano != b.ano) return this.ano - b.ano;
        if (this.mes != b.mes) return this.mes - b.mes;
        return this.dia - b.dia;
    }
}

class Show {
    String idShow, tipo, titulo, diretor, pais, classificacao, duracao;
    List<String> elenco = new ArrayList<>();
    List<String> generos = new ArrayList<>();
    Data dataAdicionado = new Data();
    int anoLancamento;

    public Show() {}

    public Show(Show s) {
        this.idShow = s.idShow;
        this.tipo = s.tipo;
        this.titulo = s.titulo;
        this.diretor = s.diretor;
        this.pais = s.pais;
        this.classificacao = s.classificacao;
        this.duracao = s.duracao;
        this.anoLancamento = s.anoLancamento;
        this.dataAdicionado = new Data(s.dataAdicionado.dia, s.dataAdicionado.mes, s.dataAdicionado.ano);
        this.elenco = new ArrayList<>(s.elenco);
        this.generos = new ArrayList<>(s.generos);
    }

    public void imprimir() {
        Collections.sort(elenco);
        Collections.sort(generos);
        System.out.print("=> " + idShow + " ## " + titulo + " ## " + tipo + " ## " + diretor + " ## [");
        for (int i = 0; i < elenco.size(); i++) {
            System.out.print(elenco.get(i));
            if (i < elenco.size() - 1) System.out.print(", ");
        }
        System.out.print("] ## " + pais + " ## ");
        String[] meses = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        if (dataAdicionado.mes > 0 && dataAdicionado.mes <= 12)
            System.out.print(meses[dataAdicionado.mes] + " " + dataAdicionado.dia + ", " + dataAdicionado.ano);
        else
            System.out.print("NaN");
        System.out.print(" ## " + anoLancamento + " ## " + classificacao + " ## " + duracao + " ## [");
        for (int i = 0; i < generos.size(); i++) {
            System.out.print(generos.get(i));
            if (i < generos.size() - 1) System.out.print(", ");
        }
        System.out.println("] ##");
    }
}

// Lista Duplamente Encadeada
class Celula {
    Show elemento;
    Celula prox, ant;

    Celula() {
        this(null);
    }

    Celula(Show elemento) {
        this.elemento = elemento;
        this.prox = null;
        this.ant = null;
    }
}

class Lista {
    Celula primeiro, ultimo;

    Lista() {
        primeiro = new Celula();
        ultimo = new Celula();
        primeiro.prox = ultimo;
        ultimo.ant = primeiro;
    }

    void inserirFim(Show show) {
        Celula nova = new Celula(new Show(show));
        nova.ant = ultimo.ant;
        ultimo.ant.prox = nova;
        nova.prox = ultimo;
        ultimo.ant = nova;
    }

    int tamanho() {
        int tam = 0;
        for (Celula i = primeiro.prox; i != ultimo; i = i.prox) tam++;
        return tam;
    }

    void mostrar() {
        for (Celula i = primeiro.prox; i != ultimo; i = i.prox) {
            i.elemento.imprimir();
        }
    }
}

public class exercicio10 {
    static int comparacoes = 0, movimentacoes = 0;

    static int comparar(Celula a, Celula b) {
        comparacoes++;
        int cmp = a.elemento.dataAdicionado.compareTo(b.elemento.dataAdicionado);
        if (cmp != 0) return cmp;
        comparacoes++;
        return a.elemento.titulo.compareTo(b.elemento.titulo);
    }

    static void trocar(Celula a, Celula b) {
        Show tmp = a.elemento;
        a.elemento = b.elemento;
        b.elemento = tmp;
        movimentacoes++;
    }

    static Celula particao(Celula low, Celula high) {
        Celula i = low.ant;
        for (Celula j = low; j != high; j = j.prox) {
            if (comparar(j, high) <= 0) {
                i = (i == null) ? low : i.prox;
                if (i != j) trocar(i, j);
            }
        }
        i = (i == null) ? low : i.prox;
        if (i != high) trocar(i, high);
        return i;
    }

    static void quicksort(Celula low, Celula high) {
        if (low != null && high != null && low != high && low.ant != high) {
            Celula pi = particao(low, high);
            if (pi != null && pi.ant != null)
                quicksort(low, pi.ant);
            if (pi != null && pi.prox != null)
                quicksort(pi.prox, high);
        }
    }

    static int mesParaInteiro(String w) {
        switch (w) {
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

    static void lerShow(Show show, String linha) {
        String[] atributos = new String[11];
        Arrays.fill(atributos, "NaN");
        int k = 0, l = 0;
        boolean aspas = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < linha.length() && k < 11; i++) {
            char c = linha.charAt(i);
            if (c == '"') {
                aspas = !aspas;
            } else if (c == ',' && !aspas) {
                atributos[k++] = sb.toString();
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (k < 11) atributos[k] = sb.toString();

        show.idShow = atributos[0];
        show.tipo = atributos[1];
        show.titulo = atributos[2];
        show.diretor = atributos[3];
        show.elenco = new ArrayList<>();
        if (!atributos[4].equals("NaN") && !atributos[4].isEmpty()) {
            for (String s : atributos[4].split(","))
                show.elenco.add(s.trim());
        }
        show.pais = atributos[5];
        if (!atributos[6].equals("NaN") && !atributos[6].isEmpty()) {
            String[] data = atributos[6].split(" ");
            if (data.length == 3) {
                show.dataAdicionado.mes = mesParaInteiro(data[0]);
                show.dataAdicionado.dia = Integer.parseInt(data[1].replace(",", ""));
                show.dataAdicionado.ano = Integer.parseInt(data[2]);
            }
        }
        show.anoLancamento = atributos[7].matches("\\d+") ? Integer.parseInt(atributos[7]) : 0;
        show.classificacao = atributos[8];
        show.duracao = atributos[9];
        show.generos = new ArrayList<>();
        if (!atributos[10].equals("NaN") && !atributos[10].isEmpty()) {
            for (String s : atributos[10].split(","))
                show.generos.add(s.trim());
        }
    }

    static Show[] lerArquivo(String path, int total) throws IOException {
        Show[] shows = new Show[total];
        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine(); // cabeçalho
        for (int i = 0; i < total; i++) {
            String linha = br.readLine();
            shows[i] = new Show();
            lerShow(shows[i], linha);
        }
        br.close();
        return shows;
    }

    static Show buscarShowPorId(Show[] shows, String id) {
        for (Show s : shows) {
            if (s.idShow != null && s.idShow.equals(id))
                return s;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        int totalShows = 1368;
        Show[] shows = lerArquivo("/tmp/disneyplus.csv", totalShows);

        Lista lista = new Lista();
        Scanner sc = new Scanner(System.in);
        String entrada = sc.next();
        while (!entrada.equals("FIM")) {
            Show s = buscarShowPorId(shows, entrada);
            if (s != null) lista.inserirFim(s);
            entrada = sc.next();
        }

        long inicio = System.currentTimeMillis();
        quicksort(lista.primeiro.prox, lista.ultimo.ant);
        long fim = System.currentTimeMillis();

        lista.mostrar();

        PrintWriter log = new PrintWriter("859238_quicksort3.txt");
        log.printf("859238\t%d\t%d\t%.6fms\n", comparacoes, movimentacoes, (fim - inicio) * 1.0);
        log.close();
    }
}