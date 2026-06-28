package expresiones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // Entrada de datos
    private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        menu();
    }

    public static void menu() throws IOException {

        // Variables
        AnalizadorExpresion analizador = new AnalizadorExpresion();
        int opcion;
        String expresion;
        boolean resultado;

        do {
            System.out.println("\nMenú de análisis de expresiones aritméticas");
            System.out.println("1. Ingresar y validar expresión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(entrada.readLine());

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la expresión aritmética: ");
                    expresion = entrada.readLine();

                    resultado = analizador.validar(expresion);

                    if (resultado) {
                        System.out.println("La expresión es válida.");
                    } else {
                        System.out.println("La expresión es inválida.");
                    }

                    System.out.println("\nDetalle del análisis:");
                    System.out.println(analizador.getDetalle());
                    break;

                case 2:
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        } while (opcion != 2);
    }
}