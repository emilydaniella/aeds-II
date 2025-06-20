import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

class No {
    public Show elemento;
    public No esq, dir;

    public No(Show elemento) {
        this.elemento = elemento;
        this.esq = this.dir = null;
    }
}

class ArvoreBinaria {
    private No raiz;
    private int comparacoes = 0;

    public ArvoreBinaria() {
        raiz = null;
    }

    public boolean inserir(Show x) {
        try {
            raiz = inserir(x, raiz);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private No inserir(Show x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) < 0) {
            i.esq = inserir(x, i.esq);
        } else if (x.getTitulo().compareTo(i.elemento.getTitulo()) > 0) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Elemento já existe!");
        }
        return i;
    }

public boolean pesquisar(String x) {
    comparacoes = 0;
    System.out.print("=>raiz");
    boolean resp = pesquisar(x, raiz);
    System.out.println(resp ? " SIM" : " NAO");
    return resp;
}

private boolean pesquisar(String x, No i) {
    boolean resp;
    if (i == null) {
        comparacoes++;
        resp = false;
    } else if (x.equals(i.elemento.getTitulo())) {
        comparacoes++;
        resp = true;
    } else if (x.compareTo(i.elemento.getTitulo()) < 0) {
        comparacoes++;
        System.out.print(" esq");
        resp = pesquisar(x, i.esq);
    } else {
        comparacoes++;
        System.out.print(" dir");
        resp = pesquisar(x, i.dir);
    }
    return resp;
}

    public int getComparacoes() {
        return comparacoes;
    }
}

class Show {
    private String id;
    private String tipo;
    private String titulo;
    private String diretor;
    private String[] elenco;
    private String pais;
    private LocalDate dataAdicionado;
    private int anoLancamento;
    private String classificacao;
    private String duracao;
    private String[] categorias;

    public Show() {
        this.id = "";
        this.tipo = "";
        this.titulo = "";
        this.diretor = "";
        this.elenco = new String[1];
        this.pais = "";
        this.dataAdicionado = LocalDate.now();
        this.anoLancamento = 0;
        this.classificacao = "";
        this.duracao = "";
        this.categorias = new String[1];
    }

    public void ler(String line) {
        int len = line.length();
        String[] splittedWords = new String[11];
        StringBuilder sb = new StringBuilder();
        for (int i = 0, k = 0, l = 0; i < len && k < 11; i++) {
            if (line.charAt(i) != ',') {
                if (line.charAt(i) == '"') {
                    i++;
                    while (line.charAt(i) != '"') {
                        sb.append(line.charAt(i++));
                    }
                } else {
                    sb.append(line.charAt(i));
                }
            } else if (line.charAt(i) == ',' && line.charAt(i + 1) == ',') {
                splittedWords[k] = sb.toString();
                sb = new StringBuilder();
                k++;
                l = 0;
                sb.append("NaN");
                splittedWords[k] = sb.toString();
            } else if (line.charAt(i) == ',' && line.charAt(i + 1) != ',') {
                splittedWords[k] = sb.toString();
                sb = new StringBuilder();
                k++;
                l = 0;
            }
        }

        String l_id = "";
        String l_tipo = "";
        String l_titulo = "";
        String l_diretor = "";
        String[] l_elenco = new String[1];
        String l_pais = "";
        LocalDate l_dataAdicionado = LocalDate.now();
        int l_anoLancamento = 0;
        String l_classificacao = "";
        String l_duracao = "";
        String[] l_categorias = new String[1];

        for (int i = 0; i < 11; i++) {
            switch (i) {
                case 0:
                    l_id = splittedWords[i];
                    setId(l_id);
                    break;
                case 1:
                    l_tipo = splittedWords[i];
                    setTipo(l_tipo);
                    break;
                case 2:
                    l_titulo = splittedWords[i];
                    setTitulo(l_titulo);
                    break;
                case 3:
                    l_diretor = splittedWords[i];
                    setDiretor(l_diretor);
                    break;
                case 4:
                    int countElenco = 1;
                    int elencoLen = splittedWords[i].length();
                    for (int j = 0; j < elencoLen; j++) {
                        if (splittedWords[i].charAt(j) == ',')
                            countElenco++;
                    }
                    l_elenco = new String[countElenco];
                    sb = new StringBuilder();
                    for (int j = 0, k = 0; j < elencoLen; j++) {
                        if (splittedWords[i].charAt(j) != ',') {
                            sb.append(splittedWords[i].charAt(j));
                        } else if (splittedWords[i].charAt(j) == ',') {
                            j++;
                            l_elenco[k] = sb.toString();
                            k++;
                            sb = new StringBuilder();
                        }
                        if (j == elencoLen - 1) {
                            l_elenco[k] = sb.toString();
                            k++;
                            sb = new StringBuilder();
                        }
                    }
                    setElenco(l_elenco);
                    break;
                case 5:
                    l_pais = splittedWords[i];
                    setPais(l_pais);
                    break;
case 6:
    if (!splittedWords[i].equals("NaN")) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", java.util.Locale.US);
        l_dataAdicionado = LocalDate.parse(splittedWords[i], formatter);
        setDataAdicionado(l_dataAdicionado);
    } else {
        splittedWords[i] = "March 1, 1900";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", java.util.Locale.US);
        l_dataAdicionado = LocalDate.parse(splittedWords[i], formatter);
        setDataAdicionado(l_dataAdicionado);
    }
    break;
                case 7:
                    l_anoLancamento = Integer.parseInt(splittedWords[i]);
                    setAnoLancamento(l_anoLancamento);
                    break;
                case 8:
                    l_classificacao = splittedWords[i];
                    setClassificacao(l_classificacao);
                    break;
                case 9:
                    l_duracao = splittedWords[i];
                    setDuracao(l_duracao);
                    break;
                case 10:
                    int countCategorias = 1;
                    int catLen = splittedWords[i].length();
                    for (int j = 0; j < catLen; j++) {
                        if (splittedWords[i].charAt(j) == ',')
                            countCategorias++;
                    }
                    l_categorias = new String[countCategorias];
                    sb = new StringBuilder();
                    for (int j = 0, k = 0; j < catLen; j++) {
                        if (splittedWords[i].charAt(j) != ',') {
                            sb.append(splittedWords[i].charAt(j));
                        } else if (splittedWords[i].charAt(j) == ',') {
                            j++;
                            l_categorias[k] = sb.toString();
                            k++;
                            sb = new StringBuilder();
                        }
                        if (j == catLen - 1) {
                            l_categorias[k] = sb.toString();
                            k++;
                            sb = new StringBuilder();
                        }
                    }
                    setCategorias(l_categorias);
                    break;
            }
        }
    }

    public Show clonar() {
        return new Show(this.id, this.tipo, this.titulo, this.diretor, this.elenco, this.pais, this.dataAdicionado, this.anoLancamento, this.classificacao, this.duracao, this.categorias);
    }

    public Show(String id, String tipo, String titulo, String diretor, String[] elenco, String pais,
            LocalDate dataAdicionado, int anoLancamento, String classificacao, String duracao, String[] categorias) {
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.diretor = diretor;
        this.elenco = elenco;
        this.pais = pais;
        this.dataAdicionado = dataAdicionado;
        this.anoLancamento = anoLancamento;
        this.classificacao = classificacao;
        this.duracao = duracao;
        this.categorias = categorias;
    }


    public void setId(String id) { this.id = id; }
    public String getId() { return id; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getTipo() { return tipo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTitulo() { return titulo; }
    public void setDiretor(String diretor) { this.diretor = diretor; }
    public String getDiretor() { return this.diretor; }
    public void setElenco(String[] elenco) { this.elenco = elenco; }
    public String[] getElenco() { return this.elenco; }
    public void setPais(String pais) { this.pais = pais; }
    public String getPais() { return this.pais; }
    public void setDataAdicionado(LocalDate dataAdicionado) { this.dataAdicionado = dataAdicionado; }
    public LocalDate getDataAdicionado() { return this.dataAdicionado; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }
    public int getAnoLancamento() { return this.anoLancamento; }
    public void setClassificacao(String classificacao) { this.classificacao = classificacao; }
    public String getClassificacao() { return this.classificacao; }
    public void setDuracao(String duracao) { this.duracao = duracao; }
    public String getDuracao() { return this.duracao; }
    public void setCategorias(String[] categorias) { this.categorias = categorias; }
    public String[] getCategorias() { return this.categorias; }
}

public class exercicio1 {
    public static void main(String[] args) throws FileNotFoundException {
        long inicio = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        File arquivo = new File("/tmp/disneyplus.csv");
        Scanner filesc = new Scanner(arquivo, "UTF-8");
        filesc.nextLine();

        Show[] shows = new Show[1368];
        for (int i = 0; i < 1368; i++) {
            String line = filesc.nextLine();
            shows[i] = new Show();
            shows[i].ler(line);
        }

        ArvoreBinaria arvore = new ArvoreBinaria();

        String getId = sc.nextLine();
        while (!getId.equals("FIM")) {
            int id = Integer.parseInt(getId.substring(1));
            arvore.inserir(shows[id - 1].clonar());
            getId = sc.nextLine();
        }

        String pesquisa = sc.nextLine();
        int totalComparacoes = 0;
        while (!pesquisa.equals("FIM")) {
            arvore.pesquisar(pesquisa);
            totalComparacoes += arvore.getComparacoes();
            pesquisa = sc.nextLine();
        }

        long fim = System.currentTimeMillis();
        long tempoExecucao = fim - inicio;

        String matricula = "859238";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("859238_arvoreBinaria.txt"))) {
            bw.write(matricula + "\t" + tempoExecucao + "\t" + totalComparacoes);
        } catch (IOException e) {
            System.out.println("Erro ao escrever log: " + e.getMessage());
        }

        filesc.close();
        sc.close();
    }
}