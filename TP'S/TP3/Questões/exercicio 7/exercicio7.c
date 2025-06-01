#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <err.h>

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

typedef struct {
    char *idShow;
    char *tipo;
    char *titulo;
    char *diretor;
    char **elenco;
    size_t elencoTam;
    char *pais;
    Data dataAdicionada;
    int anoLancamento;
    char *classificacao;
    char *duracao;
    char **categorias;
    size_t categoriasTam;
} Show;

Show copiarShow(Show original) {
    Show copia;

    copia.idShow = (char *)calloc(strlen(original.idShow) + 1, sizeof(char));
    strcpy(copia.idShow, original.idShow);

    copia.tipo = (char *)calloc(strlen(original.tipo) + 1, sizeof(char));
    strcpy(copia.tipo, original.tipo);

    copia.titulo = (char *)calloc(strlen(original.titulo) + 1, sizeof(char));
    strcpy(copia.titulo, original.titulo);

    copia.diretor = (char *)calloc(strlen(original.diretor) + 1, sizeof(char));
    strcpy(copia.diretor, original.diretor);

    copia.elencoTam = original.elencoTam;
    if (copia.elencoTam > 0) {
        copia.elenco = (char **)calloc(copia.elencoTam, sizeof(char *));
        for (int i = 0; i < copia.elencoTam; i++) {
            copia.elenco[i] = (char *)calloc(strlen(original.elenco[i]) + 1, sizeof(char));
            strcpy(copia.elenco[i], original.elenco[i]);
        }
    } else {
        copia.elenco = NULL;
    }

    copia.pais = (char *)calloc(strlen(original.pais) + 1, sizeof(char));
    strcpy(copia.pais, original.pais);

    copia.dataAdicionada = original.dataAdicionada;
    copia.anoLancamento = original.anoLancamento;

    copia.classificacao = (char *)calloc(strlen(original.classificacao) + 1, sizeof(char));
    strcpy(copia.classificacao, original.classificacao);

    copia.duracao = (char *)calloc(strlen(original.duracao) + 1, sizeof(char));
    strcpy(copia.duracao, original.duracao);

    copia.categoriasTam = original.categoriasTam;
    if (copia.categoriasTam > 0) {
        copia.categorias = (char **)calloc(copia.categoriasTam, sizeof(char *));
        for (int i = 0; i < copia.categoriasTam; i++) {
            copia.categorias[i] = (char *)calloc(strlen(original.categorias[i]) + 1, sizeof(char));
            strcpy(copia.categorias[i], original.categorias[i]);
        }
    } else {
        copia.categorias = NULL;
    }

    return copia;
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

char *numeroParaMes(int numero) {
    char *mes = (char *)malloc(25 * sizeof(char));
    switch (numero) {
        case 1: strcpy(mes, "January"); break;
        case 2: strcpy(mes, "February"); break;
        case 3: strcpy(mes, "March"); break;
        case 4: strcpy(mes, "April"); break;
        case 5: strcpy(mes, "May"); break;
        case 6: strcpy(mes, "June"); break;
        case 7: strcpy(mes, "July"); break;
        case 8: strcpy(mes, "August"); break;
        case 9: strcpy(mes, "September"); break;
        case 10: strcpy(mes, "October"); break;
        case 11: strcpy(mes, "November"); break;
        case 12: strcpy(mes, "December"); break;
        default: strcpy(mes, "Desconhecido"); break;
    }
    return mes;
}

char *intParaString(int valor) {
    char *str = (char *)malloc(12 * sizeof(char));
    sprintf(str, "%d", valor);
    return str;
}

char *dataParaString(Data data) {
    char *strData = (char *)calloc(255, sizeof(char));
    char *mes = numeroParaMes(data.mes);
    char *dia = intParaString(data.dia);
    char *ano = intParaString(data.ano);

    strcat(strData, mes);
    strcat(strData, " ");
    strcat(strData, dia);
    strcat(strData, ", ");
    strcat(strData, ano);

    free(mes);
    free(dia);
    free(ano);

    return strData;
}

char *arrayParaString(char **array, size_t tamanho) {
    char *saida = (char *)calloc(255, sizeof(char));
    for (int i = 0; i < tamanho; i++) {
        strcat(saida, array[i]);
        if (i != tamanho - 1)
            strcat(saida, ", ");
    }
    return saida;
}

void exibirShow(Show *s) {
    char *strData;
    bool temMes = (s->dataAdicionada.mes != 0);
    bool temDia = (s->dataAdicionada.dia != 0);
    bool temAno = (s->dataAdicionada.ano != 0);

    if (temMes && temDia && temAno) {
        strData = dataParaString(s->dataAdicionada);
    } else {
        strData = (char *)calloc(5, sizeof(char));
        strcpy(strData, "NaN");
    }

    char *strElenco;
    if (s->elenco != NULL) {
        strElenco = arrayParaString(s->elenco, s->elencoTam);
    } else {
        strElenco = (char *)calloc(5, sizeof(char));
        strcpy(strElenco, "NaN");
    }

    char *strCategorias;
    if (s->categorias != NULL) {
        strCategorias = arrayParaString(s->categorias, s->categoriasTam);
    } else {
        strCategorias = (char *)calloc(5, sizeof(char));
        strcpy(strCategorias, "NaN");
    }

    printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %d ## %s ## %s ## [%s] ##\n",
           s->idShow, s->titulo, s->tipo, s->diretor, strElenco, s->pais, strData,
           s->anoLancamento, s->classificacao, s->duracao, strCategorias);

    free(strData);
    free(strCategorias);
    free(strElenco);
}

char **separarAtributos(char *linha) {
    int tam = strlen(linha);
    char **atributos = (char **)malloc(11 * sizeof(char *));
    int k = 0, l = 0;
    for (int i = 0; i < 11; i++) {
        atributos[i] = (char *)calloc(1024, sizeof(char));
        strcpy(atributos[i], "NaN");
    }
    for (int i = 0; i < tam && k < 11; i++) {
        if (linha[i] != ',') {
            if (linha[i] == '"') {
                i++;
                while (linha[i] != '"') {
                    atributos[k][l++] = linha[i++];
                }
            } else {
                atributos[k][l++] = linha[i];
            }
        } else {
            atributos[k][l] = '\0';
            l = 0;
            k++;
            while (linha[i + 1] == ',') {
                atributos[k][l++] = 'N';
                atributos[k][l++] = 'a';
                atributos[k][l++] = 'N';
                atributos[k][l] = '\0';
                i++;
                if (k < 11)
                    k++;
                l = 0;
            }
        }
    }
    return atributos;
}

void definirIdShow(Show *s, char *atributo) {
    size_t tam = strlen(atributo);
    s->idShow = (char *)malloc((tam + 1) * sizeof(char));
    strcpy(s->idShow, atributo);
}

void definirTipo(Show *s, char *atributo) {
    size_t tam = strlen(atributo);
    s->tipo = (char *)malloc((tam + 1) * sizeof(char));
    strcpy(s->tipo, atributo);
}

void definirTitulo(Show *s, char *atributo) {
    size_t tam = strlen(atributo);
    s->titulo = (char *)calloc((tam + 1), sizeof(char));
    strcpy(s->titulo, atributo);
}

void definirDiretor(Show *s, char *atributo) {
    size_t tam = strlen(atributo);
    s->diretor = (char *)malloc((tam + 1) * sizeof(char));
    strcpy(s->diretor, atributo);
}

void definirElenco(Show *s, char *atributo) {
    if (strcmp(atributo, "NaN") != 0 && strlen(atributo) != 0) {
        int qtd = 1;
        int tam = strlen(atributo);
        for (int j = 0; j < tam; j++)
            if (atributo[j] == ',')
                qtd++;
        s->elencoTam = qtd;
        s->elenco = (char **)calloc(qtd, sizeof(char *));
        for (int j = 0; j < qtd; j++) {
            s->elenco[j] = (char *)calloc(tam, sizeof(char));
        }
        for (int j = 0, k = 0, l = 0; j < tam; j++) {
            if (atributo[j] != ',') {
                s->elenco[k][l++] = atributo[j];
            } else {
                s->elenco[k++][l] = '\0';
                l = 0;
                if (atributo[j + 1] == ' ')
                    j++;
            }
        }
        size_t len = s->elencoTam;
        for (int j = 0; j < len - 1; j++) {
            int menor = j;
            for (int k = j + 1; k < len; k++) {
                if (strcmp(s->elenco[k], s->elenco[menor]) < 0) {
                    menor = k;
                }
            }
            char *aux = s->elenco[j];
            s->elenco[j] = s->elenco[menor];
            s->elenco[menor] = aux;
        }
    } else {
        s->elencoTam = 0;
        s->elenco = NULL;
    }
}

void definirPais(Show *s, char *atributo) {
    size_t tam = strlen(atributo);
    s->pais = (char *)malloc((tam + 1) * sizeof(char));
    strcpy(s->pais, atributo);
}

void definirData(Show *s, char *atributo) {
    if (strcmp(atributo, "NaN") != 0) {
        int tam = strlen(atributo);
        char c_mes[tam], c_dia[tam], c_ano[tam];
        int k;
        for (int j = 0; j < tam; j++) {
            if (atributo[j] != ' ') {
                c_mes[j] = atributo[j];
            } else {
                c_mes[j] = '\0';
                k = j + 1;
                j = tam;
            }
        }
        for (int j = k, l = 0; j < tam; j++) {
            if (atributo[j] != ',') {
                c_dia[l++] = atributo[j];
            } else {
                c_dia[l] = '\0';
                k = j + 2;
                j = tam;
            }
        }
        for (int j = k, l = 0; j < tam; j++) {
            c_ano[l++] = atributo[j];
            if (j == tam - 1)
                c_ano[l] = '\0';
        }
        s->dataAdicionada.mes = mesParaNumero(c_mes);
        s->dataAdicionada.dia = atoi(c_dia);
        s->dataAdicionada.ano = atoi(c_ano);
    } else {
        s->dataAdicionada.mes = 3;
        s->dataAdicionada.dia = 1;
        s->dataAdicionada.ano = 1900;
    }
}

void definirAnoLancamento(Show *s, char *atributo) {
    s->anoLancamento = atoi(atributo);
}

void definirClassificacao(Show *s, char *atributo) {
    size_t tam = strlen(atributo);
    s->classificacao = (char *)malloc((tam + 1) * sizeof(char));
    strcpy(s->classificacao, atributo);
}

void definirDuracao(Show *s, char *atributo) {
    size_t tam = strlen(atributo);
    s->duracao = (char *)malloc((tam + 1) * sizeof(char));
    strcpy(s->duracao, atributo);
}

void definirCategorias(Show *s, char *atributo) {
    if (strcmp(atributo, "NaN") != 0) {
        int qtd = 1;
        int tam = strlen(atributo);
        for (int j = 0; j < tam; j++)
            if (atributo[j] == ',')
                qtd++;
        s->categoriasTam = qtd;
        s->categorias = (char **)malloc(qtd * sizeof(char *));
        for (int j = 0; j < qtd; j++) {
            s->categorias[j] = (char *)malloc(tam * sizeof(char));
        }
        for (int j = 0, k = 0, l = 0; j < tam; j++) {
            if (atributo[j] != ',') {
                s->categorias[k][l++] = atributo[j];
            } else {
                s->categorias[k++][l] = '\0';
                l = 0;
                if (atributo[j + 1] == ' ')
                    j++;
            }
        }
        size_t len = s->categoriasTam;
        for (int j = 0; j < len - 1; j++) {
            int menor = j;
            for (int k = j + 1; k < len; k++) {
                if (strcmp(s->categorias[k], s->categorias[menor]) < 0) {
                    menor = k;
                }
            }
            char *aux = s->categorias[j];
            s->categorias[j] = s->categorias[menor];
            s->categorias[menor] = aux;
        }
    } else {
        s->categoriasTam = 0;
        s->categorias = NULL;
    }
}

void lerShow(Show *s, char *linha) {
    char **atributos = separarAtributos(linha);

    definirIdShow(s, atributos[0]);
    definirTipo(s, atributos[1]);
    definirTitulo(s, atributos[2]);
    definirDiretor(s, atributos[3]);
    definirElenco(s, atributos[4]);
    definirPais(s, atributos[5]);
    definirData(s, atributos[6]);
    definirAnoLancamento(s, atributos[7]);
    definirClassificacao(s, atributos[8]);
    definirDuracao(s, atributos[9]);
    definirCategorias(s, atributos[10]);

    for (int i = 0; i < 11; i++) {
        free(atributos[i]);
    }
    free(atributos);
}

void lerLinha(char *linha, int maxTam, FILE *arquivo) {
    if (arquivo == NULL) {
        fprintf(stderr, "Erro: ponteiro de arquivo NULL passado para lerLinha().\n");
        exit(1);
    }
    if (fgets(linha, maxTam, arquivo) == NULL) {
        fprintf(stderr, "Erro ao ler linha do arquivo ou fim do arquivo atingido.\n");
        exit(1);
    }
    size_t tam = strlen(linha);
    if (linha[tam - 1] == '\n')
        linha[tam - 1] = '\0';
}

void liberarShow(Show *s) {
    free(s->idShow);
    free(s->tipo);
    free(s->titulo);
    free(s->diretor);
    free(s->pais);
    free(s->classificacao);
    free(s->duracao);
    if (s->elenco != NULL) {
        for (int j = 0; j < s->elencoTam; j++) {
            free(s->elenco[j]);
        }
        free(s->elenco);
    }
    if (s->categorias != NULL) {
        for (int j = 0; j < s->categoriasTam; j++) {
            free(s->categorias[j]);
        }
        free(s->categorias);
    }
}

typedef struct Celula {
    Show *elemento;
    struct Celula *proximo;
} Celula;

Celula *novaCelula() {
    Celula *tmp = (Celula *)malloc(sizeof(Celula));
    tmp->elemento = (Show *)malloc(sizeof(Show));
    tmp->proximo = NULL;
    return tmp;
}

Celula *novaCelulaComShow(Show show) {
    Celula *tmp = (Celula *)malloc(sizeof(Celula));
    tmp->elemento = (Show *)malloc(sizeof(Show));
    *(tmp->elemento) = copiarShow(show);
    tmp->proximo = NULL;
    return tmp;
}

typedef struct {
    Celula *primeiro;
    Celula *ultimo;
} Fila;

Fila *novaFila() {
    Fila *tmp = (Fila *)malloc(sizeof(Fila));
    tmp->primeiro = novaCelula();
    tmp->ultimo = tmp->primeiro;
    return tmp;
}

int tamanhoFila(Fila *fila) {
    int tam = 0;
    Celula *i;
    for (i = fila->primeiro; i != fila->ultimo; i = i->proximo, tam++);
    return tam;
}

Show removerDaFila(Fila *);
int mediaFila(Fila *);

void inserirNaFila(Fila *fila, Show show) {
    int tam = tamanhoFila(fila);
    if (tam == 5) {
        removerDaFila(fila);
    }
    Celula *tmp = novaCelulaComShow(show);
    fila->ultimo->proximo = tmp;
    fila->ultimo = tmp;
    fila->ultimo->proximo = fila->primeiro->proximo;
    tmp = NULL;
    printf("[Media] %d\n", mediaFila(fila));
}

Show removerDaFila(Fila *fila) {
    Show resp;
    if (fila->primeiro == fila->ultimo) {
        errx(1, "Erro ao remover\n");
    } else {
        Celula *tmp = fila->primeiro->proximo;
        fila->primeiro->proximo = fila->primeiro->proximo->proximo;
        fila->ultimo->proximo = fila->primeiro->proximo->proximo;
        tmp->proximo = NULL;
        resp = copiarShow(*(tmp->elemento));
        free(tmp);
    }
    return resp;
}

int mediaFila(Fila *fila) {
    int soma = 0;
    int tam = tamanhoFila(fila);
    Celula *i = fila->primeiro->proximo;
    for (int j = tam; j > 0; j--, soma += i->elemento->anoLancamento, i = i->proximo);
    return soma / tam;
}

void mostrarFila(Fila *fila) {
    Celula *i = fila->primeiro->proximo;
    int tam = tamanhoFila(fila);
    for (int j = tam - 1; j >= 0; j--, i = i->proximo) {
        printf("[%d] ", j);
        exibirShow(i->elemento);
    }
}

void lerArquivo(Show *);
void preencherFilaInicial(Fila *, Show *);
int lerIdShow();
void executarOperacao(char *, Fila *, Show *);

int main() {
    Show *shows = (Show *)calloc(1368, sizeof(Show));
    lerArquivo(shows);

    Fila *filaShows = novaFila();
    preencherFilaInicial(filaShows, shows);

    int qtdOperacoes;
    scanf("%d", &qtdOperacoes);
    getchar();

    for (int i = 0; i < qtdOperacoes; i++) {
        char *op = (char *)malloc(255 * sizeof(char));
        scanf("%s", op);
        getchar();
        executarOperacao(op, filaShows, shows);
        free(op);
    }

    mostrarFila(filaShows);

    for (int i = 0; i < 1368; i++)
        liberarShow(shows + i);
    free(shows);

    return 0;
}

void lerArquivo(Show *shows) {
    FILE *arquivo = fopen("/tmp/disneyplus.csv", "r");
    char *linha = (char *)malloc(1024 * sizeof(char));
    while (fgetc(arquivo) != '\n');
    for (int i = 0; i < 1368; i++) {
        lerLinha(linha, 1024, arquivo);
        lerShow((shows + i), linha);
    }
    free(linha);
    fclose(arquivo);
}

void preencherFilaInicial(Fila *fila, Show *shows) {
    char *entrada = (char *)malloc(255 * sizeof(char));
    scanf("%s", entrada);
    while (strcmp(entrada, "FIM") != 0) {
        int id = atoi((entrada + 1));
        inserirNaFila(fila, shows[--id]);
        scanf("%s", entrada);
    }
    free(entrada);
}

int lerIdShow() {
    char *id = (char *)malloc(255 * sizeof(char));
    scanf("%s", id);
    getchar();
    int resp = atoi(id + 1);
    free(id);
    return resp;
}

void executarOperacao(char *op, Fila *filaShows, Show *shows) {
    if (strcmp(op, "I") == 0) {
        int id = lerIdShow();
        inserirNaFila(filaShows, shows[--id]);
    } else if (strcmp(op, "R") == 0) {
        Show removido = removerDaFila(filaShows);
        printf("(R) %s\n", removido.titulo);
    }
}