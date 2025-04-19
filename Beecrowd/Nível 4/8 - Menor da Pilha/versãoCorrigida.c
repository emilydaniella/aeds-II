#include <stdio.h>
#include <string.h>

#define MAX 1000000  

int stack[MAX];      // pilha principal
int min_stack[MAX];  // pilha para os mínimos
int top = -1;        // topo da pilha principal
int min_top = -1;    // topo da pilha de mínimos

void push(int value) {
    // Empilha normalmente
    stack[++top] = value;

    // Atualiza pilha de mínimos
    if (min_top == -1 || value <= min_stack[min_top]) {
        min_stack[++min_top] = value;
    }
}

void pop() {
    if (top == -1) {
        printf("EMPTY\n");
    } else {
        int popped = stack[top--];  // retira da pilha principal

        // Se for igual ao topo da pilha de mínimos, tira de lá também
        if (popped == min_stack[min_top]) {
            min_top--;
        }
    }
}

void get_min() {
    if (min_top == -1) {
        printf("EMPTY\n");
    } else {
        printf("%d\n", min_stack[min_top]);
    }
}

int main() {
    int n, value;
    char comando[20];

    scanf("%d\n", &n);  // lê número de operações

    for (int i = 0; i < n; i++) {
        fgets(comando, sizeof(comando), stdin);  // lê comando completo

        if (strncmp(comando, "PUSH", 4) == 0) {
            sscanf(comando + 5, "%d", &value);  // lê número após "PUSH "
            push(value);
        } else if (strncmp(comando, "POP", 3) == 0) {
            pop();
        } else if (strncmp(comando, "MIN", 3) == 0) {
            get_min();
        }
    }

    return 0;
}
