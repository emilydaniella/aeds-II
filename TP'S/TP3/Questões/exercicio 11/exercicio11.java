import java.util.Scanner;

class Matriz {
    private int linhas, colunas;
    private Celula inicio;

    private class Celula {
        int elemento;
        Celula dir, inf;

        Celula(int elemento) {
            this.elemento = elemento;
            this.dir = null;
            this.inf = null;
        }
    }

    // Construtor: aloca as células dinamicamente
    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        if (linhas <= 0 || colunas <= 0) return;

        // Cria a primeira célula
        inicio = new Celula(0);

        // Cria a primeira linha
        Celula atual = inicio;
        for (int j = 1; j < colunas; j++) {
            atual.dir = new Celula(0);
            atual = atual.dir;
        }

        // Cria as demais linhas
        Celula linhaAcima = inicio;
        for (int i = 1; i < linhas; i++) {
            Celula novaLinha = new Celula(0);
            linhaAcima.inf = novaLinha;
            atual = novaLinha;
            Celula auxAcima = linhaAcima.dir;
            for (int j = 1; j < colunas; j++) {
                atual.dir = new Celula(0);
                if (auxAcima != null) {
                    auxAcima.inf = atual.dir;
                    auxAcima = auxAcima.dir;
                }
                atual = atual.dir;
            }
            linhaAcima = linhaAcima.inf;
        }
    }

    // Preenche a matriz com valores da entrada
    public void preencher(Scanner sc) {
        Celula linha = inicio;
        for (int i = 0; i < linhas; i++) {
            Celula coluna = linha;
            for (int j = 0; j < colunas; j++) {
                coluna.elemento = sc.nextInt();
                coluna = coluna.dir;
            }
            linha = linha.inf;
        }
    }

    // Soma de matrizes
    public Matriz soma(Matriz m) {
        if (this.linhas != m.linhas || this.colunas != m.colunas)
            throw new IllegalArgumentException("Dimensões incompatíveis para soma");

        Matriz resp = new Matriz(linhas, colunas);
        Celula aLinha = this.inicio, bLinha = m.inicio, rLinha = resp.inicio;
        for (int i = 0; i < linhas; i++) {
            Celula aCol = aLinha, bCol = bLinha, rCol = rLinha;
            for (int j = 0; j < colunas; j++) {
                rCol.elemento = aCol.elemento + bCol.elemento;
                aCol = aCol.dir;
                bCol = bCol.dir;
                rCol = rCol.dir;
            }
            aLinha = aLinha.inf;
            bLinha = bLinha.inf;
            rLinha = rLinha.inf;
        }
        return resp;
    }

    // Multiplicação de matrizes
    public Matriz multiplicacao(Matriz m) {
        if (this.colunas != m.linhas)
            throw new IllegalArgumentException("Dimensões incompatíveis para multiplicação");

        Matriz resp = new Matriz(this.linhas, m.colunas);
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < m.colunas; j++) {
                int soma = 0;
                for (int k = 0; k < this.colunas; k++) {
                    soma += this.get(i, k) * m.get(k, j);
                }
                resp.set(i, j, soma);
            }
        }
        return resp;
    }

    // Mostra diagonal principal
    public void mostrarDiagonalPrincipal() {
        Celula atual = inicio;
        for (int i = 0; i < Math.min(linhas, colunas); i++) {
            System.out.print(atual.elemento + (i < Math.min(linhas, colunas) - 1 ? " " : ""));
            if (atual.dir != null && atual.inf != null)
                atual = atual.dir.inf;
        }
        System.out.println();
    }

    // Mostra diagonal secundária
    public void mostrarDiagonalSecundaria() {
        Celula linha = inicio;
        for (int i = 0; i < linhas - 1; i++) linha = linha.inf;
        Celula atual = linha;
        for (int i = 0; i < Math.min(linhas, colunas); i++) {
            System.out.print(atual.elemento + (i < Math.min(linhas, colunas) - 1 ? " " : ""));
            if (atual.dir != null && atual.inf != null)
                atual = atual.dir.inf;
            else if (atual.dir != null)
                atual = atual.dir;
            else if (atual.inf != null)
                atual = atual.inf;
        }
        System.out.println();
    }

    // Get valor na posição (i, j)
    public int get(int i, int j) {
        Celula linha = inicio;
        for (int x = 0; x < i; x++) linha = linha.inf;
        Celula coluna = linha;
        for (int y = 0; y < j; y++) coluna = coluna.dir;
        return coluna.elemento;
    }

    // Set valor na posição (i, j)
    public void set(int i, int j, int valor) {
        Celula linha = inicio;
        for (int x = 0; x < i; x++) linha = linha.inf;
        Celula coluna = linha;
        for (int y = 0; y < j; y++) coluna = coluna.dir;
        coluna.elemento = valor;
    }

    // Imprime a matriz
    public void imprimir() {
        Celula linha = inicio;
        for (int i = 0; i < linhas; i++) {
            Celula coluna = linha;
            for (int j = 0; j < colunas; j++) {
                System.out.print(coluna.elemento + (j < colunas - 1 ? " " : ""));
                coluna = coluna.dir;
            }
            System.out.println();
            linha = linha.inf;
        }
    }
}

public class exercicio11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = sc.nextInt();
        for (int t = 0; t < casos; t++) {
            int l1 = sc.nextInt();
            int c1 = sc.nextInt();
            Matriz m1 = new Matriz(l1, c1);
            m1.preencher(sc);

            int l2 = sc.nextInt();
            int c2 = sc.nextInt();
            Matriz m2 = new Matriz(l2, c2);
            m2.preencher(sc);

            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();

            if (l1 == l2 && c1 == c2) {
                Matriz soma = m1.soma(m2);
                soma.imprimir();
            }

            if (c1 == l2) {
                Matriz mult = m1.multiplicacao(m2);
                mult.imprimir();
            }
        }
        sc.close();
    }
}