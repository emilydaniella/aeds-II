import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 15/02/25
*/

public class exercicio3 {


public static boolean parada (String texto) {
    boolean parada = true;

    if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
        parada = false; //metodo para verificar se a palavra é FIM
    }

    return parada;
}

    public static String ciframento (String texto) {
        String resultado = "";

        for (int i = 0; i < texto.length(); i++) {
            int valorLetra = texto.charAt(i) + 3; //metodo para cifrar a letra de acordo com a tabela ascii
            char letraCifra = (char) valorLetra; 

            resultado = resultado + letraCifra; // salvando a letra cifrada na variavel
        }
        return resultado;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner (System.in);
        
        String texto = sc.nextLine(); //lendo a primeira entrada
        //String texto = MyIO.readLine(); 

        while (!parada(texto)) { //enquanto a palavra digitada nao for FIM
            String resultado;

            resultado = ciframento(texto); //salva o resultado na variavel
            
            //MyIO.println(resultado);
            System.out.println(resultado); // printa o resultado
            //texto = MyIO.readLine();
            texto = sc.nextLine(); //le uma nova linha
            
        }
        sc.close();

    }
}
