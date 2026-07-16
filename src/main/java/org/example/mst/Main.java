package org.example.mst;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int numVertices = 6; // Vértices numerados del 0 al 5

        // Grafo para Prim (Lista de adyacencia)
        List<List<Arista>> adyacencia = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adyacencia.add(new ArrayList<>());
        }

        // Grafo para Kruskal (Lista de aristas simple)
        List<Arista> listaAristas = new ArrayList<>();

        // Función auxiliar para agregar aristas no dirigidas (en ambas direcciones)
        agregarArista(adyacencia, listaAristas, 0, 1, 4);
        agregarArista(adyacencia, listaAristas, 0, 2, 3);
        agregarArista(adyacencia, listaAristas, 1, 2, 1);
        agregarArista(adyacencia, listaAristas, 1, 3, 2);
        agregarArista(adyacencia, listaAristas, 2, 3, 4);
        agregarArista(adyacencia, listaAristas, 3, 4, 2);
        agregarArista(adyacencia, listaAristas, 4, 5, 6);

        // Ejecutar los algoritmos
        AlgoritmoPrim.ejecutarPrim(numVertices, adyacencia, 0);
        AlgoritmoKruskal.ejecutarKruskal(numVertices, listaAristas);
    }

    private static void agregarArista(List<List<Arista>> adyacencia, List<Arista> aristas, int u, int v, int peso) {
        // Para Prim (Bidireccional por ser no dirigido)
        adyacencia.get(u).add(new Arista(u, v, peso));
        adyacencia.get(v).add(new Arista(v, u, peso));
        // Para Kruskal (Solo se necesita una vez)
        aristas.add(new Arista(u, v, peso));
    }
}
