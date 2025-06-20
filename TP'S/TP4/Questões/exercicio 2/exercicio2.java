import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

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

    public void ler(String line) {
        String[] splittedWords = new String[11];
        StringBuilder sb = new StringBuilder();
        int len = line.length();
        for (int i = 0, k = 0; i < len && k < 11; i++) {
            if (line.charAt(i) != ',') {
                if (line.charAt(i) == '"') {
                    i++;
                    while (i < len && line.charAt(i) != '"') {
                        sb.append(line.charAt(i++));
                    }
                } else {
                    sb.append(line.charAt(i));
                }
            } else if (line.charAt(i) == ',' && (i + 1 < len && line.charAt(i + 1) == ',')) {
                splittedWords[k++] = sb.toString();
                sb = new StringBuilder();
                sb.append("NaN");
                splittedWords[k++] = sb.toString();
                sb = new StringBuilder();
                i++;
            } else if (line.charAt(i) == ',') {
                splittedWords[k++] = sb.toString();
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0 && splittedWords[10] == null) splittedWords[10] = sb.toString();

        this.id = splittedWords[0];
        this.tipo = splittedWords[1];
        this.titulo = splittedWords[2];
        this.diretor = splittedWords[3];
        this.elenco = splittedWords[4].split(", ");
        this.pais = splittedWords[5];
        try {
            if (!splittedWords[6].equals("NaN")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", java.util.Locale.US);
                this.dataAdicionado = LocalDate.parse(splittedWords[6], formatter);
            } else {
                this.dataAdicionado = LocalDate.of(1900, 3, 1);
            }
        } catch (Exception e) {
            this.dataAdicionado = LocalDate.of(1900, 3, 1);
        }
        this.anoLancamento = splittedWords[7].equals("NaN") ? 0 : Integer.parseInt(splittedWords[7]);
        this.classificacao = splittedWords[8];
        this.duracao = splittedWords[9];
        this.categorias = splittedWords[10].split(", ");
    }

    public Show clonar() {
        return new Show(this.id, this.tipo, this.titulo, this.diretor, this.elenco, this.pais, this.dataAdicionado, this.anoLancamento, this.classificacao, this.duracao, this.categorias);
    }
}

// Nó da árvore secundária (por título)
class NoSecundario {
    public String chave;
    public Show elemento;
    public NoSecundario esq, dir;

    public NoSecundario(Show elemento) {
        this.chave = elemento.getTitulo();
        this.elemento = elemento;
        this.esq = this.dir = null;
    }
}

// Árvore binária secundária (por título)
class ArvoreSecundaria {
    public NoSecundario raiz;

    public ArvoreSecundaria() {
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

    private NoSecundario inserir(Show x, NoSecundario i) throws Exception {
        if (i == null) {
            i = new NoSecundario(x);
        } else if (x.getTitulo().compareTo(i.chave) < 0) {
            i.esq = inserir(x, i.esq);
        } else if (x.getTitulo().compareTo(i.chave) > 0) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Elemento já existe!");
        }
        return i;
    }
}

// Nó da árvore principal (por releaseYear % 15)
class NoPrincipal {
    public int chave;
    public NoPrincipal esq, dir;
    public ArvoreSecundaria arvoreSec;

    public NoPrincipal(int chave) {
        this.chave = chave;
        this.esq = this.dir = null;
        this.arvoreSec = new ArvoreSecundaria();
    }
}

// Árvore de árvore
class ArvoreDeArvore {
    private NoPrincipal raiz;

    public ArvoreDeArvore() {
        raiz = null;
    }

    public void inserir(Show x) {
        int chave = x.getAnoLancamento() % 15;
        raiz = inserir(x, chave, raiz);
    }

    private NoPrincipal inserir(Show x, int chave, NoPrincipal i) {
        if (i == null) {
            i = new NoPrincipal(chave);
            i.arvoreSec.inserir(x);
        } else if (chave < i.chave) {
            i.esq = inserir(x, chave, i.esq);
        } else if (chave > i.chave) {
            i.dir = inserir(x, chave, i.dir);
        } else {
            i.arvoreSec.inserir(x);
        }
        return i;
    }

    public boolean pesquisar(String titulo, int anoLancamento, StringBuilder caminho) {
        caminho.append("raiz");
        return pesquisar(titulo, anoLancamento % 15, raiz, caminho);
    }

    private boolean pesquisar(String titulo, int chave, NoPrincipal i, StringBuilder caminho) {
        if (i == null) return false;
        if (chave < i.chave) {
            caminho.append(" esq");
            return pesquisar(titulo, chave, i.esq, caminho);
        }
        if (chave > i.chave) {
            caminho.append(" dir");
            return pesquisar(titulo, chave, i.dir, caminho);
        }
        // Entrou na árvore secundária
        caminho.append("  ESQ");
        return pesquisarSecundaria(titulo, i.arvoreSec.raiz, caminho);
    }

    private boolean pesquisarSecundaria(String titulo, NoSecundario i, StringBuilder caminho) {
        if (i == null) return false;
        if (titulo.equals(i.chave)) return true;
        if (titulo.compareTo(i.chave) < 0) {
            caminho.append(" esq");
            return pesquisarSecundaria(titulo, i.esq, caminho);
        } else {
            caminho.append(" dir");
            return pesquisarSecundaria(titulo, i.dir, caminho);
        }
    }
}

public class exercicio2 {
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

        ArvoreDeArvore arvore = new ArvoreDeArvore();

        String getId = sc.nextLine();
        while (!getId.equals("FIM")) {
            int id = Integer.parseInt(getId.substring(1));
            arvore.inserir(shows[id - 1].clonar());
            getId = sc.nextLine();
        }

        String pesquisa = sc.nextLine();
        while (!pesquisa.equals("FIM")) {
            // Para pesquisar, precisamos do título e do ano de lançamento
            String titulo = pesquisa;
            int ano = -1;
            for (Show s : shows) {
                if (s.getTitulo().equals(titulo)) {
                    ano = s.getAnoLancamento();
                    break;
                }
            }
            StringBuilder caminho = new StringBuilder();
            boolean achou = (ano != -1) && arvore.pesquisar(titulo, ano, caminho);
            System.out.println(caminho.toString() + (achou ? " SIM" : " NAO"));
            pesquisa = sc.nextLine();
        }

        long fim = System.currentTimeMillis();
        long tempoExecucao = fim - inicio;

        String matricula = "859238";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("859238 arvoreArvore.txt"))) {
            bw.write(matricula + "\t" + tempoExecucao + "\t" + "0");
        } catch (IOException e) {
            System.out.println("Erro ao escrever log: " + e.getMessage());
        }

        filesc.close();
        sc.close();
    }
}