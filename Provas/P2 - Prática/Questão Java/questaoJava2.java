/*Neste Trabalho Prático sua tarefa é organizar as informações dos jogadores disponíveis para exibição ao 
usuário. Os dados foram obtidos a partir de uma busca no site Kaggle. Você deve ler, organizar e armazenar os 
dados de cada jogador em memória, utilizando as estruturas de dados em aula (Lista, Pilhas, Filas). Em seguida 
executar as operações descritas nos arquivos de entrada. Fique atento a descrição dos dados que serão lidos e 
manipulados pelo seu sistema.
Para facilitar o exercício é apresentado abaixo uma possível implementação para a classe Jogadores. Sua classe 
terá os atributos Nome (String), Foto (String), Nascimento (Data), Id(int) e Times(Array de inteiros). Ela terá
também os métodos imprimir e ler. O método imprimir mostra a String ’id nome nascimento foto (times)’, contendo
todos os atributos da classe. O método ler deve efetuar a leitura dos atributos de um registro. Veja que, caso
queira, você implementar sua própria classe ou alterar a classe abaixo.

A partir daqui, você deve implementar as classes necessárias para o exercício.
Faça a inserção de alguns objetos no final de uma Lista e, em seguida, faça algumas pesquisas BINÁRIAS.
A chave primária de pesquisa será o atributo nome do jogador. A entrada padrão é composta por duas partes onde
a primeira é composta por várias linhas e cada uma contém dados do jogador que devem ser tratados e 
armazenados em objetos da classe Jogador. A última linha da entrada contém FIM. As demais linhas correspondem 
a segunda parte. A segunda parte é composta por várias linhas. Cada uma possui um elemento que deve ser 
pesquisado na Lista. A última linha terá a palavra FIM. A saída padrão será composta por várias linhas 
contendo as palavras SIM/NAO para indicar se existe cada um dos elementos pesquisados. Pode considerar que os
jogadores já foram inseridos ordenados pelo nome.
*/
import java.util.*;

//classes que ja vieram, 100% certas (verificadas)
class Date {
    String dia;
    String mes;
    String ano;
}

class Jogadores {
    public String nome;
    public String foto;
    public Date nascimento;
    public int id;
    public int[] times;

    public void imprimir() {
        System.out.print(this.id + " " + this.nome + " " + this.nascimento.dia + "/" +
                this.nascimento.mes + "/" + this.nascimento.ano + " " + this.foto + " " + "(");
        for (int i = 0; i < times.length - 1; i++) {
            System.out.print(times[i] + ", ");
        }
        System.out.println(times[times.length - 1] + ")");
    }

    public void ler(String input) {
        int i = 0;
        String index = separar(input, i, ',');
        i += index.length() + 1;
        this.nome = separar(input, i, ',');
        i += nome.length() + 1;
        this.foto = separar(input, i, ',');
        i += foto.length() + 1;
        this.nascimento = new Date();
        this.nascimento.dia = separar(input, i, '/');
        i += nascimento.dia.length() + 1;
        this.nascimento.mes = separar(input, i, '/');
        i += nascimento.mes.length() + 1;
        this.nascimento.ano = separar(input, i, ',');
        i += nascimento.ano.length() + 1;
        index = separar(input, i, ',');
        i += index.length() + 1;
        String idString = separar(input, i, ',');
        this.id = Integer.parseInt(idString);
        i += idString.length() + 1;
        int n = contarTimes(input, i);
        if (input.charAt(i) == '"') i += 2;
        else i++;
        this.times = new int[n];
        for (int j = 0; j < n - 1; j++) {
            String timeString = separar(input, i, ',');
            this.times[j] = Integer.parseInt(timeString);
            i += timeString.length() + 2;
        }
        String timeString = separar(input, i, ']');
        this.times[n - 1] = Integer.parseInt(timeString);
    }

    public String separar(String input, int i, char delimiter) {
        String stringer = "";
        while (input.charAt(i) != delimiter) {
            stringer += input.charAt(i);
            i++;
        }
        return stringer;
    }

    public int contarTimes(String input, int i) {
        int times = 1;
        while (input.charAt(i) != ']') {
            if (input.charAt(i) == ',') {
                times++;
            }
            i++;
        }
        return times;
    }
}


//meu codigo
public class questaoJava2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Jogadores> lista = new ArrayList<>();

        while (true) {
            String linha = scanner.nextLine();
            if (linha.equals("FIM"))
            break;

            Jogadores jogador = new Jogadores();
            jogador.ler(linha);
            lista.add(jogador);
        }

        while (true) {
            String nomeBuscado = scanner.nextLine();
            if (nomeBuscado.equals("FIM")) 
                break;
        
            boolean encontrado = buscaBinaria(lista, nomeBuscado);
            if (encontrado) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }

        scanner.close();
    }

    //busca
    public static boolean buscaBinaria(List<Jogadores> lista, String nome) {
        int inicio = 0;
        int fim = lista.size() - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            Jogadores jogador = lista.get(meio);

            int comparacao = nome.compareTo(jogador.nome);

            if (comparacao == 0) {
                return true; 
            } else if (comparacao < 0) {
                fim = meio - 1; 
            } else {
                inicio = meio + 1; 
            }
        }

        return false;
    }
}