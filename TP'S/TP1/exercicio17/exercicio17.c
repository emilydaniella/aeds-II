#include <stdio.h>  
#include <string.h>  
#include <stdbool.h>  

/*
Autor: Daniella Silva  
Data: 15/02/25  
*/

// função recursiva para verificar se a string é um palíndromo  
bool palindromo(char texto[], int i, int tamanho) {
    //se os índices se cruzaram, a string é um palíndromo  
    if (i >= tamanho - 1) {  
        return true;
    }

    // se os caracteres das extremidades forem diferentes, não é palíndromo  
    if (texto[i] != texto[tamanho - 1]) {  
        return false;
    }

    // chamada recursiva movendo os índices para o centro  
    return palindromo(texto, i + 1, tamanho - 1);
}

int main() {
    char texto[1000];  

    // lê uma linha de entrada e remove o caractere de nova linha '\n'  
    fgets(texto, sizeof texto, stdin);  
    texto[strcspn(texto, "\n")] = 0;

    // laço de repetição até que o usuário digite FIM  
    while (strcmp(texto, "FIM") != 0) {  
        int tamanho = strlen(texto);  // obtém o tamanho da string  

        // chama a função para verificar se é um palíndromo  
        if (palindromo(texto, 0, tamanho)) {  
            printf("SIM\n");  // se for um palíndromo, imprime SIM
        } else {
            printf("NAO\n");  // caso contrário, imprime NAO  
        }

        // lê a próxima linha de entrada e remove o '\n' novamente  
        fgets(texto, sizeof texto, stdin);  
        texto[strcspn(texto, "\n")] = 0;
    }
        
    return 0;  
}
