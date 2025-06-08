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
    char *id;
    char *tipo;
    char *titulo;
    char *diretor;
    char **elenco;
    size_t tamElenco;
    char *pais;
    Data dataAdicao;
    int anoLancamento;
    char *classificacao;
    char *duracao;
    char **categorias;
    size_t tamCategorias;
} Programa;

Programa copiarPrograma(Programa p) {
    Programa novo;
    novo.id = strdup(p.id);
    novo.tipo = strdup(p.tipo);
    novo.titulo = strdup(p.titulo);
    novo.diretor = strdup(p.diretor);

    novo.tamElenco = p.tamElenco;
    if (novo.tamElenco > 0) {
        novo.elenco = malloc(novo.tamElenco * sizeof(char *));
        for (size_t i = 0; i < novo.tamElenco; i++)
            novo.elenco[i] = strdup(p.elenco[i]);
    } else {
        novo.elenco = NULL;
    }

    novo.pais = strdup(p.pais);
    novo.dataAdicao = p.dataAdicao;
    novo.anoLancamento = p.anoLancamento;
    novo.classificacao = strdup(p.classificacao);
    novo.duracao = strdup(p.duracao);

    novo.tamCategorias = p.tamCategorias;
    if (novo.tamCategorias > 0) {
        novo.categorias = malloc(novo.tamCategorias * sizeof(char *));
        for (size_t i = 0; i < novo.tamCategorias; i++)
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

char *numeroParaMes(int n) {
    char *meses[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    if (n >= 1 && n <= 12) {
        char *resp = malloc(20);
        strcpy(resp, meses[n]);
        return resp;
    }
    return strdup("NaN");
}

char *intParaTexto(int num) {
    char *resp = malloc(12);
    sprintf(resp, "%d", num);
    return resp;
}

char *dataParaTexto(Data d) {
    char *saida = calloc(64, sizeof(char));
    char *mes = numeroParaMes(d.mes);
    char *dia = intParaTexto(d.dia);
    char *ano = intParaTexto(d.ano);

    strcat(saida, mes);
    strcat(saida, " ");
    strcat(saida, dia);
    strcat(saida, ", ");
    strcat(saida, ano);

    free(mes);
    free(dia);
    free(ano);

    return saida;
}

char *arrayParaTexto(char **array, size_t tam) {
    char *resp = calloc(255, sizeof(char));
    for (size_t i = 0; i < tam; i++) {
        strcat(resp, array[i]);
        if (i != tam - 1)
            strcat(resp, ", ");
    }
    return resp;
}

void mostrarPrograma(Programa *p) {
    char *sData;
    if (p->dataAdicao.mes && p->dataAdicao.dia && p->dataAdicao.ano) {
        sData = dataParaTexto(p->dataAdicao);
    } else {
        sData = strdup("NaN");
    }

    char *sElenco = (p->elenco != NULL) ? arrayParaTexto(p->elenco, p->tamElenco) : strdup("NaN");
    char *sCategorias = (p->categorias != NULL) ? arrayParaTexto(p->categorias, p->tamCategorias) : strdup("NaN");

    printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %d ## %s ## %s ## [%s] ##\n",
           p->id, p->titulo, p->tipo, p->diretor, sElenco, p->pais, sData, p->anoLancamento, p->classificacao, p->duracao, sCategorias);

    free(sData);
    free(sElenco);
    free(sCategorias);
}

char **separarCampos(char *linha) {
    int len = strlen(linha);
    char **campos = malloc(11 * sizeof(char *));
    int k = 0, l = 0;
    for (int i = 0; i < 11; i++) {
        campos[i] = calloc(1024, sizeof(char));
        strcpy(campos[i], "NaN");
    }
    for (int i = 0; i < len && k < 11; i++) {
        if (linha[i] != ',') {
            if (linha[i] == '"') {
                i++;
                while (linha[i] != '"') {
                    campos[k][l++] = linha[i++];
                }
            } else {
                campos[k][l++] = linha[i];
            }
        } else {
            campos[k][l] = '\0';
            l = 0;
            k++;
            while (linha[i + 1] == ',') {
                campos[k][l++] = 'N';
                campos[k][l++] = 'a';
                campos[k][l++] = 'N';
                campos[k][l] = '\0';
                i++;
                if (k < 11)
                    k++;
                l = 0;
            }
        }
    }
    return campos;
}

void definirId(Programa *p, char *campo) {
    p->id = strdup(campo);
}

void definirTipo(Programa *p, char *campo) {
    p->tipo = strdup(campo);
}

void definirTitulo(Programa *p, char *campo) {
    p->titulo = strdup(campo);
}

void definirDiretor(Programa *p, char *campo) {
    p->diretor = strdup(campo);
}

void definirElenco(Programa *p, char *campo) {
    if (strcmp(campo, "NaN") != 0 && strlen(campo) > 0) {
        int qtd = 1;
        int len = strlen(campo);
        for (int j = 0; j < len; j++)
            if (campo[j] == ',')
                qtd++;
        p->tamElenco = qtd;
        p->elenco = calloc(qtd, sizeof(char *));
        for (int j = 0; j < qtd; j++)
            p->elenco[j] = calloc(len, sizeof(char));
        for (int j = 0, k = 0, l = 0; j < len; j++) {
            if (campo[j] != ',') {
                p->elenco[k][l++] = campo[j];
            } else {
                p->elenco[k++][l] = '\0';
                l = 0;
                if (campo[j + 1] == ' ')
                    j++;
            }
        }
        size_t sTam = p->tamElenco;
        for (int j = 0; j < sTam - 1; j++) {
            int menor = j;
            for (int k = j + 1; k < sTam; k++) {
                if (strcmp(p->elenco[k], p->elenco[menor]) < 0)
                    menor = k;
            }
            char *aux = p->elenco[j];
            p->elenco[j] = p->elenco[menor];
            p->elenco[menor] = aux;
        }
    } else {
        p->tamElenco = 0;
        p->elenco = NULL;
    }
}

void definirPais(Programa *p, char *campo) {
    p->pais = strdup(campo);
}

void definirData(Programa *p, char *campo) {
    if (strcmp(campo, "NaN") != 0) {
        int len = strlen(campo);
        char cMes[len], cDia[len], cAno[len];
        int k;
        for (int j = 0; j < len; j++) {
            if (campo[j] != ' ') {
                cMes[j] = campo[j];
            } else {
                cMes[j] = '\0';
                k = j + 1;
                j = len;
            }
        }
        for (int j = k, l = 0; j < len; j++) {
            if (campo[j] != ',') {
                cDia[l++] = campo[j];
            } else {
                cDia[l] = '\0';
                k = j + 2;
                j = len;
            }
        }
        for (int j = k, l = 0; j < len; j++) {
            cAno[l++] = campo[j];
            if (j == len - 1)
                cAno[l] = '\0';
        }
        p->dataAdicao.mes = mesParaNumero(cMes);
        p->dataAdicao.dia = atoi(cDia);
        p->dataAdicao.ano = atoi(cAno);
    } else {
        p->dataAdicao.mes = 3;
        p->dataAdicao.dia = 1;
        p->dataAdicao.ano = 1900;
    }
}

void definirAnoLancamento(Programa *p, char *campo) {
    p->anoLancamento = atoi(campo);
}

void definirClassificacao(Programa *p, char *campo) {
    p->classificacao = strdup(campo);
}

void definirDuracao(Programa *p, char *campo) {
    p->duracao = strdup(campo);
}

void definirCategorias(Programa *p, char *campo) {
    if (strcmp(campo, "NaN") != 0) {
        int qtd = 1;
        int len = strlen(campo);
        for (int j = 0; j < len; j++)
            if (campo[j] == ',')
                qtd++;
        p->tamCategorias = qtd;
        p->categorias = malloc(qtd * sizeof(char *));
        for (int j = 0; j < qtd; j++)
            p->categorias[j] = malloc(len * sizeof(char));
        for (int j = 0, k = 0, l = 0; j < len; j++) {
            if (campo[j] != ',') {
                p->categorias[k][l++] = campo[j];
            } else {
                p->categorias[k++][l] = '\0';
                l = 0;
                if (campo[j + 1] == ' ')
                    j++;
            }
        }
        size_t sTam = p->tamCategorias;
        for (int j = 0; j < sTam - 1; j++) {
            int menor = j;
            for (int k = j + 1; k < sTam; k++) {
                if (strcmp(p->categorias[k], p->categorias[menor]) < 0)
                    menor = k;
            }
            char *aux = p->categorias[j];
            p->categorias[j] = p->categorias[menor];
            p->categorias[menor] = aux;
        }
    } else {
        p->tamCategorias = 0;
        p->categorias = NULL;
    }
}

void lerPrograma(Programa *p, char *linha) {
    char **campos = separarCampos(linha);
    definirId(p, campos[0]);
    definirTipo(p, campos[1]);
    definirTitulo(p, campos[2]);
    definirDiretor(p, campos[3]);
    definirElenco(p, campos[4]);
    definirPais(p, campos[5]);
    definirData(p, campos[6]);
    definirAnoLancamento(p, campos[7]);
    definirClassificacao(p, campos[8]);
    definirDuracao(p, campos[9]);
    definirCategorias(p, campos[10]);
    for (int i = 0; i < 11; i++)
        free(campos[i]);
    free(campos);
}

void liberarPrograma(Programa *p) {
    free(p->id);
    free(p->tipo);
    free(p->titulo);
    free(p->diretor);
    free(p->pais);
    free(p->classificacao);
    free(p->duracao);
    if (p->elenco) {
        for (size_t j = 0; j < p->tamElenco; j++)
            free(p->elenco[j]);
        free(p->elenco);
    }
    if (p->categorias) {
        for (size_t j = 0; j < p->tamCategorias; j++)
            free(p->categorias[j]);
        free(p->categorias);
    }
}

typedef struct Celula {
    Programa *elemento;
    struct Celula *proxima;
} Celula;

Celula *novaCelula() {
    Celula *tmp = malloc(sizeof(Celula));
    tmp->elemento = malloc(sizeof(Programa));
    tmp->proxima = NULL;
    return tmp;
}

Celula *novaCelulaComPrograma(Programa p) {
    Celula *tmp = malloc(sizeof(Celula));
    tmp->elemento = malloc(sizeof(Programa));
    *(tmp->elemento) = copiarPrograma(p);
    tmp->proxima = NULL;
    return tmp;
}

typedef struct {
    Celula *primeira;
    Celula *ultima;
} Lista;

Lista *novaLista() {
    Lista *tmp = malloc(sizeof(Lista));
    tmp->primeira = novaCelula();
    tmp->ultima = tmp->primeira;
    return tmp;
}

int tamanhoLista(Lista *lista) {
    int tam = 0;
    Celula *i;
    for (i = lista->primeira; i != lista->ultima; i = i->proxima, tam++);
    return tam;
}

void inserirInicio(Lista *lista, Programa p) {
    Celula *tmp = novaCelulaComPrograma(p);
    tmp->proxima = lista->primeira->proxima;
    lista->primeira->proxima = tmp;
    tmp = NULL;
}

void inserirFim(Lista *lista, Programa p) {
    Celula *tmp = novaCelulaComPrograma(p);
    lista->ultima->proxima = tmp;
    lista->ultima = tmp;
    tmp = NULL;
}

void inserirPosicao(Lista *lista, int pos, Programa p) {
    int tam = tamanhoLista(lista);
    if (pos < 0 || pos > tam) {
        errx(1, "Erro ao tentar inserir na posicao %d, tamanho da lista = %d\n", pos, tam);
    } else if (pos == 0) {
        inserirInicio(lista, p);
    } else if (pos == tam) {
        inserirFim(lista, p);
    } else {
        int j;
        Celula *i = lista->primeira;
        for (j = 0; j < pos; j++, i = i->proxima);
        Celula *tmp = novaCelulaComPrograma(p);
        tmp->proxima = i->proxima;
        i->proxima = tmp;
        tmp = i = NULL;
    }
}

Programa removerInicio(Lista *lista) {
    if (lista->primeira == lista->ultima) {
        errx(1, "Erro ao remover\n");
    }
    Celula *tmp = lista->primeira->proxima;
    Programa resp = copiarPrograma(*lista->primeira->proxima->elemento);
    lista->primeira->proxima = lista->primeira->proxima->proxima;
    tmp->proxima = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}

Programa removerFim(Lista *lista) {
    if (lista->primeira == lista->ultima) {
        errx(1, "Erro ao remover\n");
    }
    Celula *i;
    for (i = lista->primeira; i->proxima != lista->ultima; i = i->proxima);
    Celula *tmp = lista->ultima;
    Programa resp = copiarPrograma(*tmp->elemento);
    lista->ultima = i;
    i->proxima = NULL;
    free(tmp);
    i = tmp = NULL;
    return resp;
}

Programa removerPosicao(Lista *lista, int pos) {
    Programa resp;
    if (lista->primeira == lista->ultima) {
        errx(1, "Erro ao remover\n");
    }
    int tam = tamanhoLista(lista);
    if (pos < 0 || pos > tam) {
        errx(1, "Erro ao remover! %d não pode ser removido do tamanho %d\n", pos, tam);
    } else if (pos == 0) {
        resp = removerInicio(lista);
    } else if (pos == tam) {
        resp = removerFim(lista);
    } else {
        Celula *tmp;
        Celula *i = lista->primeira->proxima;
        for (int j = 0; j < pos - 1; j++, i = i->proxima);
        tmp = i->proxima;
        i->proxima = i->proxima->proxima;
        resp = copiarPrograma(*tmp->elemento);
        tmp->proxima = NULL;
        free(tmp);
        i = tmp = NULL;
    }
    return resp;
}

void mostrarLista(Lista *lista) {
    Celula *i;
    for (i = lista->primeira->proxima; i != NULL; i = i->proxima) {
        mostrarPrograma(i->elemento);
    }
}

void lerArquivo(Programa *);
void preencherListaInicial(Lista *, Programa *);
int lerId();
int lerPosicao();
void executarComando(char *, Lista *, Programa *);

int main() {
    Programa *programas = calloc(1368, sizeof(Programa));
    lerArquivo(programas);

    Lista *listaProgramas = novaLista();
    preencherListaInicial(listaProgramas, programas);

    int qtdOperacoes;
    scanf("%d", &qtdOperacoes);
    getchar();

    for (int i = 0; i < qtdOperacoes; i++) {
        char *op = malloc(255 * sizeof(char));
        scanf("%s", op);
        getchar();
        executarComando(op, listaProgramas, programas);
        free(op);
    }

    mostrarLista(listaProgramas);

    for (int i = 0; i < 1368; i++)
        liberarPrograma(programas + i);
    free(programas);

    return 0;
}

void lerArquivo(Programa *programas) {
    FILE *file = fopen("/tmp/disneyplus.csv", "r");
    char *linha = malloc(1024 * sizeof(char));
    while (fgetc(file) != '\n');
    for (int i = 0; i < 1368; i++) {
        fgets(linha, 1024, file);
        if (linha[strlen(linha) - 1] == '\n') linha[strlen(linha) - 1] = '\0';
        lerPrograma((programas + i), linha);
    }
    free(linha);
    fclose(file);
}

void preencherListaInicial(Lista *lista, Programa *programas) {
    char *entrada = malloc(255 * sizeof(char));
    scanf("%s", entrada);
    while (strcmp(entrada, "FIM") != 0) {
        int id = atoi((entrada + 1));
        inserirFim(lista, *(programas + (--id)));
        scanf("%s", entrada);
    }
    free(entrada);
}

int lerId() {
    char *id = malloc(255 * sizeof(char));
    scanf("%s", id);
    getchar();
    int resp = atoi(id + 1);
    free(id);
    return resp;
}

int lerPosicao() {
    int resp;
    scanf("%d", &resp);
    getchar();
    return resp;
}

void executarComando(char *op, Lista *listaProgramas, Programa *programas) {
    if (strcmp(op, "II") == 0) {
        int id = lerId();
        inserirInicio(listaProgramas, programas[--id]);
    } else if (strcmp(op, "IF") == 0) {
        int id = lerId();
        inserirFim(listaProgramas, programas[--id]);
    } else if (strcmp(op, "I*") == 0) {
        int pos = lerPosicao();
        int id = lerId();
        inserirPosicao(listaProgramas, pos, programas[--id]);
    } else if (strcmp(op, "RI") == 0) {
        Programa p = removerInicio(listaProgramas);
        printf("(R) %s\n", p.titulo);
        liberarPrograma(&p);
    } else if (strcmp(op, "RF") == 0) {
        Programa p = removerFim(listaProgramas);
        printf("(R) %s\n", p.titulo);
        liberarPrograma(&p);
    } else if (strcmp(op, "R*") == 0) {
        int pos = lerPosicao();
        Programa p = removerPosicao(listaProgramas, pos);
        printf("(R) %s\n", p.titulo);
        liberarPrograma(&p);
    }
}