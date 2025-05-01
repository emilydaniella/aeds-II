import pandas as pd
import matplotlib.pyplot as plt

# Tamanhos de entrada analisados
tamanhos = [100, 1000, 10000, 100000]

# Função para ler dados de um algoritmo
def ler_dados(algoritmo):
    comps = []
    movs = []
    tempos = []

    for tam in tamanhos:
        nome_arquivo = f"{algoritmo}_{tam}.txt"
        with open(nome_arquivo, 'r') as f:
            linha = f.readline().strip()
            comp, mov, tempo = map(int, linha.split())
            comps.append(comp)
            movs.append(mov)
            tempos.append(tempo)

    return comps, movs, tempos

# Lista de algoritmos
algoritmos = ['bubble', 'selection', 'insertion', 'quicksort']

# Dicionários para armazenar os dados
dados_comparacoes = {}
dados_movimentacoes = {}
dados_tempos = {}

# Preencher os dados
for algoritmo in algoritmos:
    comps, movs, tempos = ler_dados(algoritmo)
    dados_comparacoes[algoritmo] = comps
    dados_movimentacoes[algoritmo] = movs
    dados_tempos[algoritmo] = tempos

# Função para gerar gráfico
def gerar_grafico(dados, titulo, ylabel):
    plt.figure(figsize=(10, 6))
    for algoritmo in algoritmos:
        plt.plot(tamanhos, dados[algoritmo], marker='o', label=algoritmo.capitalize())
    plt.title(titulo)
    plt.xlabel("Tamanho do vetor")
    plt.ylabel(ylabel)
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.show()

# Gerar os três gráficos
gerar_grafico(dados_comparacoes, "Número de Comparações", "Comparações")
gerar_grafico(dados_movimentacoes, "Número de Movimentações", "Movimentações")
gerar_grafico(dados_tempos, "Tempo de Execução (ms)", "Tempo (ms)")
