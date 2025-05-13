import java.util.Scanner;

public class exercicio {



    public static void ordena (String [] nomes, int tamanho) {

        for (int i = 0; i< tamanho - 1; i++) {
            for (int j = 0; j< tamanho - i - 1; j++) {
                if (nomes[j].compareTo(nomes [j+1])> 0) {
                    String temp = nomes[j];
                    nomes[j] = nomes[j+1];
                    nomes[j+1] = temp;
                }
            }
        }
    }

    public static int removerDuplicatas(String[] nomes, int tamanho, int[] ordemInscricao) {
        int novoTamanho = 0;
        for (int i = 0; i < tamanho; i++) {
            boolean duplicado = false;
            for (int j = 0; j < novoTamanho; j++) {
                if (nomes[i].equals(nomes[j])) {
                    duplicado = true;
                    break;
                }
            }
            if (!duplicado) {
                nomes[novoTamanho] = nomes[i];
                ordemInscricao[novoTamanho] = ordemInscricao[i]; // aqui está o segredo
                novoTamanho++;
            }
        }
        return novoTamanho;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

    String [] nomesSim = new String [1000];
    String [] nomesNao = new String [1000];
    int[] ordemInscricaoSim = new int[1000];


    int qtdSim = 0;
    int qtdNao = 0;

    String linha = sc.nextLine();

    while (!linha.equals("FIM")) {

    String [] divisao = linha.split (" ");

    String nome = divisao[0];
    String escolha = divisao[1];

    if (escolha.equals("YES")) {
        nomesSim[qtdSim] = nome;
        ordemInscricaoSim[qtdSim] = qtdSim; // salva índice de inscrição
        qtdSim++;
    } else if (escolha.equals("NO")) {
        nomesNao[qtdNao++] = nome;
    }

        linha = sc.nextLine();
    }


sc.close();

qtdSim = removerDuplicatas(nomesSim, qtdSim, ordemInscricaoSim);

ordena (nomesSim, qtdSim);
ordena(nomesNao, qtdNao);




for (int i = 0; i< qtdSim; i++) {
    System.out.println(nomesSim[i]);
}

for (int i = 0; i<qtdNao; i++) {
    System.out.println(nomesNao[i]);
}

System.out.println ();

String vencedor = "";
int tamanho = -1;
int menorOrdem = Integer.MAX_VALUE;

for (int i = 0; i < qtdSim; i++) {
    int tam = nomesSim[i].length();
    int ordem = ordemInscricaoSim[i];

    if (tam > tamanho || (tam == tamanho && ordem < menorOrdem)) {
        tamanho = tam;
        menorOrdem = ordem;
        vencedor = nomesSim[i];
    }
}

System.out.println("Amigo do Habay:");
System.out.println(vencedor);


}
}