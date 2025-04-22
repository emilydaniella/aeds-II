import java.text.DecimalFormat;
import java.util.*;

public class questãoJava {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.##"); 
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

         int temp=0, total = 0, coelhos = 0, ratos = 0, sapos = 0;
        char c;

        for (int i = 0; i<N; i++) {
             temp = sc.nextInt();
             c = sc.next().charAt(0);
             total+=temp;

             if (c == 'C') {
                coelhos += temp;
             } else if (c == 'R') {
                ratos+= temp;
             } else if (c == 'S') {
                sapos += temp;
             }
        }

        System.out.println("Total: " + total + " cobaias");
        System.out.println("Total de coelhos: " + coelhos);
        System.out.println("Total de ratos: " + ratos);
        System.out.println("Total de sapos: " + sapos);

        float saposFloat = sapos;
        float ratosFloat = ratos;
        float coelhosFloat = coelhos;

        System.out.printf("Percentual de coelhos: " + "%.2f%%", (coelhosFloat/total) * 100);
        System.out.println("");
        System.out.printf("Percentual de ratos: " + "%.2f%%", (ratosFloat/total) * 100);
        System.out.println("");
        System.out.printf("Percentual de sapos: " + "%.2f%%", (saposFloat/total) * 100);

        sc.close();
    }
}