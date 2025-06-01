#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

typedef struct {
    char *codigo;
    char *genero;
    char *nome;
    char *autor;
    char **atores;
    size_t qtdAtores;
    char *paisOrigem;
    Data dataInclusao;
    int anoLancamento;
    char *faixaEtaria;
    char *tempo;
    char **categorias;
    size_t qtdCategorias;
} Programa;

Programa copiarPrograma(Programa p) {
    Programa novo;
    novo.codigo = strdup(p.codigo);
    novo.genero = strdup(p.genero);
    novo.nome = strdup(p.nome);
    novo.autor = strdup(p.autor);

    novo.qtdAtores = p.qtdAtores;
    if (novo.qtdAtores > 0) {
        novo.atores = malloc(novo.qtdAtores * sizeof(char *));
        for (int i = 0; i < novo.qtdAtores; i++)
            novo.atores[i] = strdup(p.atores[i]);
    } else {
        novo.atores = NULL;
    }

    novo.paisOrigem = strdup(p.paisOrigem);
    novo.dataInclusao = p.dataInclusao;
    novo.anoLancamento = p.anoLancamento;
    novo.faixaEtaria = strdup(p.faixaEtaria);
    novo.tempo = strdup(p.tempo);

    novo.qtdCategorias = p.qtdCategorias;
    if (novo.qtdCategorias > 0) {
        novo.categorias = malloc(novo.qtdCategorias * sizeof(char *));
        for (int i = 0; i < novo.qtdCategorias; i++)
            novo.categorias[i] = strdup(p.categorias[i]);
    } else {
        novo.categorias = NULL;
    }
    return novo;
}

int mesParaNumero(char *mes) {
    if (strcmp(mes, "January") == 0) return 1;
    if (strcmp(mes, "February") == 0) return 2;
    if (strcmp(mes, "March") == 0) return 3;
    if (strcmp(mes, "April") == 0) return 4;
    if (strcmp(mes, "May") == 0) return 5;
    if (strcmp(mes, "June") == 0) return 6;
    if (strcmp(mes, "July") == 0) return 7;
    if (strcmp(mes, "August") == 0) return 8;
    if (strcmp(mes, "September") == 0) return 9;
    if (strcmp(mes, "October") == 0) return 10;
    if (strcmp(mes, "November") == 0) return 11;
    if (strcmp(mes, "December") == 0) return 12;
    return 0;
}

char *numeroParaMes(int m) {
    char *meses[] = {"", "January", "February", "March", "April", "May", "June", "July",
                     "August", "September", "October", "November", "December"};
    if (m >= 1 && m <= 12)
        return strdup(meses[m]);
    return strdup("NaN");
}

char *dataParaTexto(Data d) {
    char *saida = malloc(64);
    char *mes = numeroParaMes(d.mes);
    sprintf(saida, "%s %d, %d", mes, d.dia, d.ano);
    free(mes);
    return saida;
}

char *juntarArray(char **arr, int tam) {
    char *saida = malloc(512);
    saida[0] = '\0';
    for (int i = 0; i < tam; i++) {
        strcat(saida, arr[i]);
        if (i < tam - 1)
            strcat(saida, ", ");
    }
    return saida;
}

void exibirPrograma(Programa *p) {
    char *dataStr;
    if (p->dataInclusao.dia && p->dataInclusao.mes && p->dataInclusao.ano)
        dataStr = dataParaTexto(p->dataInclusao);
    else
        dataStr = strdup("NaN");

    char *atoresStr = (p->atores != NULL) ? juntarArray(p->atores, p->qtdAtores) : strdup("NaN");
    char *catStr = (p->categorias != NULL) ? juntarArray(p->categorias, p->qtdCategorias) : strdup("NaN");

    printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %d ## %s ## %s ## [%s] ##\n",
           p->codigo, p->nome, p->genero, p->autor, atoresStr, p->paisOrigem, dataStr,
           p->anoLancamento, p->faixaEtaria, p->tempo, catStr);

    free(dataStr);
    free(atoresStr);
    free(catStr);
}

void lerPrograma(Programa *p, char *linha) {
    char *campos[11];
    for (int i = 0; i < 11; i++) campos[i] = calloc(1024, 1);
    int k = 0, l = 0, len = strlen(linha);
    for (int i = 0; i < len && k < 11; i++) {
        if (linha[i] != ',') {
            if (linha[i] == '"') {
                i++;
                while (linha[i] != '"') campos[k][l++] = linha[i++];
            } else {
                campos[k][l++] = linha[i];
            }
        } else {
            campos[k][l] = '\0';
            l = 0;
            k++;
            while (linha[i + 1] == ',') {
                strcpy(campos[k], "NaN");
                i++;
                if (k < 11) k++;
            }
        }
    }

    p->codigo = strdup(campos[0]);
    p->genero = strdup(campos[1]);
    p->nome = strdup(campos[2]);
    p->autor = strdup(campos[3]);

    // Atores
    if (strcmp(campos[4], "NaN") != 0 && strlen(campos[4]) > 0) {
        int qtd = 1, tam = strlen(campos[4]);
        for (int j = 0; j < tam; j++) if (campos[4][j] == ',') qtd++;
        p->qtdAtores = qtd;
        p->atores = malloc(qtd * sizeof(char *));
        int ini = 0, idx = 0;
        for (int j = 0; j <= tam; j++) {
            if (campos[4][j] == ',' || campos[4][j] == '\0') {
                int sz = j - ini;
                while (campos[4][ini] == ' ') { ini++; sz--; }
                p->atores[idx] = calloc(sz + 1, 1);
                strncpy(p->atores[idx], campos[4] + ini, sz);
                p->atores[idx][sz] = '\0';
                idx++;
                ini = j + 1;
            }
        }
        // Ordena atores
        for (int j = 0; j < p->qtdAtores - 1; j++) {
            int menor = j;
            for (int k = j + 1; k < p->qtdAtores; k++)
                if (strcmp(p->atores[k], p->atores[menor]) < 0)
                    menor = k;
            char *aux = p->atores[j];
            p->atores[j] = p->atores[menor];
            p->atores[menor] = aux;
        }
    } else {
        p->qtdAtores = 0;
        p->atores = NULL;
    }

    p->paisOrigem = strdup(campos[5]);

    // Data
    if (strcmp(campos[6], "NaN") != 0) {
        char mes[32], dia[8], ano[8];
        sscanf(campos[6], "%s %[^,], %s", mes, dia, ano);
        p->dataInclusao.mes = mesParaNumero(mes);
        p->dataInclusao.dia = atoi(dia);
        p->dataInclusao.ano = atoi(ano);
    } else {
        p->dataInclusao.mes = 3;
        p->dataInclusao.dia = 1;
        p->dataInclusao.ano = 1900;
    }

    p->anoLancamento = atoi(campos[7]);
    p->faixaEtaria = strdup(campos[8]);
    p->tempo = strdup(campos[9]);

    // Categorias
    if (strcmp(campos[10], "NaN") != 0 && strlen(campos[10]) > 0) {
        int qtd = 1, tam = strlen(campos[10]);
        for (int j = 0; j < tam; j++) if (campos[10][j] == ',') qtd++;
        p->qtdCategorias = qtd;
        p->categorias = malloc(qtd * sizeof(char *));
        int ini = 0, idx = 0;
        for (int j = 0; j <= tam; j++) {
            if (campos[10][j] == ',' || campos[10][j] == '\0') {
                int sz = j - ini;
                while (campos[10][ini] == ' ') { ini++; sz--; }
                p->categorias[idx] = calloc(sz + 1, 1);
                strncpy(p->categorias[idx], campos[10] + ini, sz);
                p->categorias[idx][sz] = '\0';
                idx++;
                ini = j + 1;
            }
        }
        // Ordena categorias
        for (int j = 0; j < p->qtdCategorias - 1; j++) {
            int menor = j;
            for (int k = j + 1; k < p->qtdCategorias; k++)
                if (strcmp(p->categorias[k], p->categorias[menor]) < 0)
                    menor = k;
            char *aux = p->categorias[j];
            p->categorias[j] = p->categorias[menor];
            p->categorias[menor] = aux;
        }
    } else {
        p->qtdCategorias = 0;
        p->categorias = NULL;
    }

    for (int i = 0; i < 11; i++) free(campos[i]);
}

void liberarPrograma(Programa *p) {
    free(p->codigo);
    free(p->genero);
    free(p->nome);
    free(p->autor);
    free(p->paisOrigem);
    free(p->faixaEtaria);
    free(p->tempo);
    if (p->atores) {
        for (int i = 0; i < p->qtdAtores; i++) free(p->atores[i]);
        free(p->atores);
    }
    if (p->categorias) {
        for (int i = 0; i < p->qtdCategorias; i++) free(p->categorias[i]);
        free(p->categorias);
    }
}

typedef struct {
    Programa *dados;
    int inicio;
    int fim;
    int quantidade;
} FilaCircular;

FilaCircular *criarFila() {
    FilaCircular *f = malloc(sizeof(FilaCircular));
    f->dados = calloc(5, sizeof(Programa));
    f->inicio = 0;
    f->fim = 0;
    f->quantidade = 0;
    return f;
}

int filaCheia(FilaCircular *f) {
    return f->quantidade == 5;
}

int filaVazia(FilaCircular *f) {
    return f->quantidade == 0;
}

int calcularMedia(FilaCircular *f) {
    int soma = 0;
    for (int i = 0, j = f->inicio; i < f->quantidade; i++) {
        j = j % 5;
        soma += f->dados[j++].anoLancamento;
    }
    return soma / f->quantidade;
}

Programa tirarFila(FilaCircular *f) {
    Programa temp = f->dados[f->inicio];
    f->inicio = (f->inicio + 1) % 5;
    f->quantidade--;
    return temp;
}

void colocarFila(FilaCircular *f, Programa p) {
    if (filaCheia(f)) {
        Programa removido = tirarFila(f);
        liberarPrograma(&removido);
    }
    f->dados[f->fim] = copiarPrograma(p);
    f->fim = (f->fim + 1) % 5;
    f->quantidade++;
    printf("[Media] %d\n", calcularMedia(f));
}

void mostrarFila(FilaCircular *f) {
    int idx = f->quantidade - 1;
    for (int i = 0, j = f->inicio; i < f->quantidade; i++) {
        j = j % 5;
        printf("[%d] ", idx--);
        exibirPrograma(f->dados + j);
        j++;
    }
}

void lerLinhaArquivo(char *linha, int max, FILE *arq) {
    if (fgets(linha, max, arq) == NULL) {
        fprintf(stderr, "Erro ao ler linha.\n");
        exit(1);
    }
    size_t len = strlen(linha);
    if (linha[len - 1] == '\n') linha[len - 1] = '\0';
}

int main() {
    Programa *todos = calloc(1368, sizeof(Programa));
    FILE *arq = fopen("/tmp/disneyplus.csv", "r");
    char linha[1024];
    fgets(linha, 1024, arq); // pula header

    for (int i = 0; i < 1368; i++) {
        lerLinhaArquivo(linha, 1024, arq);
        lerPrograma(&todos[i], linha);
    }
    fclose(arq);

    FilaCircular *fila = criarFila();
    char entrada[32];
    scanf("%s", entrada);
    while (strcmp(entrada, "FIM") != 0) {
        int id = atoi(entrada + 1) - 1;
        colocarFila(fila, todos[id]);
        scanf("%s", entrada);
    }

    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        char op[8];
        scanf("%s", op);
        if (strcmp(op, "I") == 0) {
            char idstr[16];
            scanf("%s", idstr);
            int id = atoi(idstr + 1) - 1;
            colocarFila(fila, todos[id]);
        } else if (strcmp(op, "R") == 0) {
            Programa p = tirarFila(fila);
            printf("(R) %s\n", p.nome);
            liberarPrograma(&p);
        }
    }

    mostrarFila(fila);

    for (int i = 0; i < 1368; i++)
        liberarPrograma(&todos[i]);
    free(todos);
    free(fila->dados);
    free(fila);

    return 0;
}