package org.example.mst;

import java.util.*;

public class AlgoritmoKruskal {

    // Estructura Union-Find para detectar ciclos
    static class UnionFind {
        int[] padre;
        int[] rango;

        public UnionFind(int n) {
            padre = new int[n];
            rango = new int[n];
            for (int i = 0; i < n; i++) {
                padre[i] = i;
                rango[i] = 0;
            }
        }

        public int encontrar(int i) {
            if (padre[i] != i) {
                padre[i] = encontrar(padre[i]); // Compresión de ruta
            }
            return padre[i];
        }

        public boolean unir(int i, int j) {
            int raizI = encontrar(i);
            int raizJ = encontrar(j);

            if (raizI == raizJ) return false; // Están en el mismo conjunto (formaría ciclo)

            if (rango[raizI] < rango[raizJ]) {
                padre[raizI] = raizJ;
            } else if (rango[raizI] > rango[raizJ]) {
                padre[raizJ] = raizI;
            } else {
                padre[raizJ] = raizI;
                rango[raizI]++;
            }
            return true;
        }
    }

    public static void ejecutarKruskal(int numVertices, List<Arista> listaAristas) {
        // 1. Ordenar todas las aristas por peso creciente
        Collections.sort(listaAristas);

        UnionFind uf = new UnionFind(numVertices);
        List<Arista> mst = new ArrayList<>();
        int costoTotal = 0;

        // 2. Iterar sobre aristas ordenadas y agregarlas si no forman ciclos
        for (Arista arista : listaAristas) {
            if (uf.unir(arista.origen, arista.destino)) {
                mst.add(arista);
                costoTotal += arista.peso;
            }
            if (mst.size() == numVertices - 1) break; // Ya tenemos todas las aristas necesarias
        }

        // Imprimir resultados
        System.out.println("=== RESULTADO: ALGORITMO DE KRUSKAL ===");
        for (Arista a : mst) {
            System.out.println("Arista: (" + a.origen + " - " + a.destino + ") | Peso: " + a.peso);
        }
        System.out.println("Costo Total del MST: " + costoTotal + "\n");
    }
}
