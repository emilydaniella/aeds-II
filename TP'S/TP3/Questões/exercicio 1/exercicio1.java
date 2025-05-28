import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

class Lista {
    private Show[] array;
    private int tam;

    public Lista() {
        array = new Show[1368];
        tam = 0;
    }

    public void inserirInicio(Show show) {
        for (int i = tam; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = show;
        tam++;
    }

    public void inserir(Show show, int posicao) {
        for (int i = tam; i > posicao; i--) {
            array[i] = array[i - 1];
        }
        array[posicao] = show;
        tam++;
    }

    public void inserirFim(Show show) {
        array[tam++] = show;
    }

    public Show removerInicio() {
        Show tmp = array[0];
        for (int i = 0; i < tam - 1; i++) {
            array[i] = array[i + 1];
        }
        tam--;
        return tmp;
    }

    public Show remover(int posicao) {
        Show tmp = array[posicao];
        for (int i = posicao; i < tam - 1; i++) {
            array[i] = array[i + 1];
        }
        tam--;
        return tmp;
    }

    public Show removerFim() {
        return array[--tam];
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < tam; i++) {
            System.out.print(array[i].getId() + " ");
        }
        System.out.println("]");
    }

    public void mostrarTodos() {
        for (int i = 0; i < tam; i++) {
            array[i].imprimir();
        }
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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDiretor() {
        return this.diretor;
    }

    public void setElenco(String[] elenco) {
        int len = elenco.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                String atual = elenco[j];
                String prox = elenco[j + 1];
                if (atual.compareTo(prox) > 0) {
                    String aux = elenco[j];
                    elenco[j] = elenco[j + 1];
                    elenco[j + 1] = aux;
                }
            }
        }
        this.elenco = elenco;
    }

    public String[] getElenco() {
        return this.elenco;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return this.pais;
    }

    public void setDataAdicionado(LocalDate dataAdicionado) {
        this.dataAdicionado = dataAdicionado;
    }

    public LocalDate getDataAdicionado() {
        return this.dataAdicionado;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getAnoLancamento() {
        return this.anoLancamento;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getClassificacao() {
        return this.classificacao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDuracao() {
        return this.duracao;
    }

    public void setCategorias(String[] categorias) {
        int len = categorias.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                String atual = categorias[j];
                String prox = categorias[j + 1];
                if (atual.compareTo(prox) > 0) {
                    String aux = categorias[j];
                    categorias[j] = categorias[j + 1];
                    categorias[j + 1] = aux;
                }
            }
        }
        this.categorias = categorias;
    }

    public String[] getCategorias() {
        return this.categorias;
    }

    public String elencoParaString() {
        int quantidade = this.elenco.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quantidade; i++) {
            sb.append(this.elenco[i]);
            if (i != quantidade - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public String categoriasParaString() {
        int quantidade = this.categorias.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quantidade; i++) {
            sb.append(this.categorias[i]);
            if (i != quantidade - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public void imprimir() {
        String data = "NaN";
        if (this.dataAdicionado != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            data = dataAdicionado.format(formatter);
        }
        System.out.println("=> " + id + " ## " + titulo + " ## " + tipo + " ## " + diretor
                + " ## [" + elencoParaString() + "] ## " + pais + " ## " + data + " ## " + anoLancamento
                + " ## " + classificacao + " ## " + duracao + " ## [" + categoriasParaString() + "] ##");
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
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                        l_dataAdicionado = LocalDate.parse(splittedWords[i], formatter);
                        setDataAdicionado(l_dataAdicionado);
                    } else {
                        splittedWords[i] = "March 1, 1900";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
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
}

public class exercicio1 {
    public static void main(String[] args) throws FileNotFoundException {
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

        Lista listaShows = new Lista();

        String getId = sc.nextLine();
        while (!getId.equals("FIM")) {
            int id = Integer.parseInt(getId.substring(1, getId.length()));
            listaShows.inserirFim(shows[id - 1].clonar());
            getId = sc.nextLine();
        }

        int operacoes = sc.nextInt();

        for (int i = 0; i < operacoes; i++) {
            String getOp = sc.next();

            if (getOp.equals("II")) {
                getId = sc.next();
                int id = Integer.parseInt(getId.substring(1, getId.length()));
                listaShows.inserirInicio(shows[id - 1].clonar());
            } else if (getOp.equals("IF")) {
                getId = sc.next();
                int id = Integer.parseInt(getId.substring(1, getId.length()));
                listaShows.inserirFim(shows[id - 1].clonar());
            } else if (getOp.equals("I*")) {
                int pos = sc.nextInt();
                getId = sc.next();
                int id = Integer.parseInt(getId.substring(1, getId.length()));
                listaShows.inserir(shows[id - 1].clonar(), pos);
            } else if (getOp.equals("RI")) {
                System.out.println("(R) " + listaShows.removerInicio().getTitulo());
            } else if (getOp.equals("RF")) {
                System.out.println("(R) " + listaShows.removerFim().getTitulo());
            } else if (getOp.equals("R*")) {
                int pos = sc.nextInt();
                System.out.println("(R) " + listaShows.remover(pos).getTitulo());
            }
        }

        listaShows.mostrarTodos();

        filesc.close();
        sc.close();
    }
}