package org.example.mst;

import java.util.*;

public class AlgoritmoPrim {

    public static void ejecutarPrim(int numVertices, List<List<Arista>> adyacencia, int nodoInicio) {
        boolean[] visitado = new boolean[numVertices];
        PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>();
        List<Arista> mst = new ArrayList<>();
        int costoTotal = 0;

        // Empezamos desde el nodo de inicio
        visitado[nodoInicio] = true;
        colaPrioridad.addAll(adyacencia.get(nodoInicio));

        while (!colaPrioridad.isEmpty() && mst.size() < numVertices - 1) {
            Arista aristaActual = colaPrioridad.poll();
            int nodoDestino = aristaActual.destino;

            // Si el destino ya fue visitado, ignoramos para evitar ciclos
            if (visitado[nodoDestino]) continue;

            // Incluimos la arista al MST
            visitado[nodoDestino] = true;
            mst.add(aristaActual);
            costoTotal += aristaActual.peso;

            // Agregamos las nuevas aristas adyacentes a la cola de prioridad
            for (Arista arista : adyacencia.get(nodoDestino)) {
                if (!visitado[arista.destino]) {
                    colaPrioridad.add(arista);
                }
            }
        }

        // Imprimir resultados
        System.out.println("=== RESULTADO: ALGORITMO DE PRIM ===");
        for (Arista a : mst) {
            System.out.println("Arista: (" + a.origen + " - " + a.destino + ") | Peso: " + a.peso);
        }
        System.out.println("Costo Total del MST: " + costoTotal + "\n");
    }
}
