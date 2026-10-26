import java.util.Random;

class No {
    int valor;
    No proximo;

    No(int valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

public class Main {
    public static void main(String[] args) {
        Random random = new Random(1090);
        No[] tabela = new No[100];

        int ultimo1 = -1;
        int ultimo2 = -1;
        int ultimo3 = -1;

        for (int i = 0; i < 1000; i++) {
            int valor = random.nextInt(100001);
            int posicao = valor % 100;

            No novo = new No(valor);

            if (tabela[posicao] != null) {
                ultimo1 = ultimo2;
                ultimo2 = ultimo3;
                ultimo3 = valor;
            }

            novo.proximo = tabela[posicao];
            tabela[posicao] = novo;
        }

        int maior = 0;
        int posicaoMaior = -1;
        int listas = 0;
        int vazias = 0;

        for (int i = 0; i < 100; i++) {
            int tamanho = 0;
            No atual = tabela[i];

            while (atual != null) {
                tamanho++;
                atual = atual.proximo;
            }

            if (tamanho == 0) {
                vazias++;
            }
            if (tamanho > 1) {
                listas++;
            }
            if (tamanho > maior) {
                maior = tamanho;
                posicaoMaior = i;
            }
        }

        System.out.println("Maior lista encadeada (posicao " + posicaoMaior + ", tamanho " + maior + "):");
        No atual = tabela[posicaoMaior];
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();

        System.out.println("Listas encadeadas geradas: " + listas);
        System.out.println("Posicoes vazias: " + vazias);
        System.out.println("Tres ultimos valores inseridos em listas: " + ultimo1 + ", " + ultimo2 + ", " + ultimo3);
    }
}
