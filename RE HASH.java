import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(1090);
        int tamanho = 100;
        int limite = 90;
        int[] tabela = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = -1;
        }

        int[] valores = new int[tamanho];
        int[] colisoes = new int[tamanho];
        int inseridos = 0;
        int rejeitados = 0;
        int totalColisoes = 0;

        for (int i = 0; i < 100; i++) {
            int valor = random.nextInt(100001);

            if (inseridos < limite) {
                int pos = valor % tamanho;
                int cont = 0;

                while (tabela[pos] != -1) {
                    cont++;
                    pos = (pos + 1) % tamanho;
                }

                tabela[pos] = valor;
                valores[inseridos] = valor;
                colisoes[inseridos] = cont;
                totalColisoes = totalColisoes + cont;
                inseridos++;
            } else {
                rejeitados++;
            }
        }

        System.out.println("Inseridos: " + inseridos);
        System.out.println("Rejeitados (ocupacao de 90%): " + rejeitados);
        System.out.println("Total de colisoes: " + totalColisoes);

        int[] topValor = new int[3];
        int[] topColisoes = new int[3];
        boolean[] usado = new boolean[tamanho];

        for (int k = 0; k < 3; k++) {
            int melhor = -1;
            for (int j = 0; j < inseridos; j++) {
                if (!usado[j]) {
                    if (melhor == -1 || colisoes[j] > colisoes[melhor]) {
                        melhor = j;
                    }
                }
            }
            usado[melhor] = true;
            topValor[k] = valores[melhor];
            topColisoes[k] = colisoes[melhor];
        }

        System.out.println("Tres elementos que mais colidiram:");
        for (int k = 0; k < 3; k++) {
            System.out.println(topValor[k] + " (" + topColisoes[k] + " colisoes)");
        }

        System.out.print("Posicoes livres: ");
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] == -1) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.println("Busca:");
        for (int k = 0; k < 3; k++) {
            int pos = topValor[k] % tamanho;
            int passos = 1;

            while (tabela[pos] != topValor[k] && tabela[pos] != -1) {
                pos = (pos + 1) % tamanho;
                passos++;
            }

            if (tabela[pos] == topValor[k]) {
                System.out.println(topValor[k] + " encontrado na posicao " + pos + " em " + passos + " acessos");
            } else {
                System.out.println(topValor[k] + " nao encontrado");
            }
        }
    }
}
