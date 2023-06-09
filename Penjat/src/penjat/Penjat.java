/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author David
 */
public class Penjat {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        final char[][] estatPenjatInicial
                = {
                {' ', ' ', ' ', ' ', '_', '_', '_', '_', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', '_', '_', '|', '_', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'|', ' ', ' ', ' ', ' ', '|', '_', '_', '_', '_', '_', ' '},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '|'}
        };

        final String[] paraules = {"patata", "armari", "bicicleta",
                "advocat", "ascensor", "astronauta", "autopista",
                "avinguda", "bigoti", "carretera", "castanya",
                "cervell", "civada", "cultura", "dentista", "esquena",
                "estrella", "formatge", "gendre", "genoll",
                "infermera", "internet", "maduixa", "malaltia",
                "maluc", "mandarina", "maquinista", "motocicleta",
                "nebot", "pastanaga", "patinet", "perruqueria",
                "pissarra", "professor", "quadrat", "taronja",
                "tramvia", "trapezi", "tricicle", "violeta"};

        final int MAXINTENTS = 8;

        // Estat gràfic del joc durant la partida
        char[][] estatPenjat
                = new char[estatPenjatInicial.length][estatPenjatInicial[0].length];

        // Inicialitzar el dibuix del penjat
        inicialitzarEstatPenjat(estatPenjatInicial, estatPenjat);

        // Seleccionar la paraula aleatòriament
        int index = (int) (Math.random() * paraules.length);
        String paraulaSecreta = paraules[index];

        // Convertir la paraula a un array de caràcters
        char[] paraulaSecretaChars = paraulaSecreta.toCharArray();
        int totalEncerts = 0, totalErrors = 0;

        // Estructura de dades (array) per saber quines lletres portem
        // encertades
        boolean[] lletresEncertades = new boolean[paraulaSecretaChars.length];

        // Llistat de lletres que hem introduït
        String lletresIntroduides = "";


        do {

            // Mostrar Penjat
            mostrarEstatPenjat(estatPenjat);

            // Mostrar enigma
            mostrarParaula(paraulaSecreta, lletresEncertades);

            // Mostrar lletres
            mostrarLletresIntroduides(lletresIntroduides);

            // Demana lletra al usuari
            String lletraStr = demanarLletra(lletresIntroduides);
            char lletra = lletraStr.charAt(0);

            // Comprovar si la lletra introduïda pertany a la paraula
            boolean encertada = false;
            for (int i = 0; i < paraulaSecretaChars.length; i++) {
                if (paraulaSecretaChars[i] == lletra) {
                    lletresEncertades[i] = true;
                    totalEncerts++;
                    encertada = true;
                }
            }
            // Actualitzar el dibuix del penjat si la lletra no es correcta
            if (!encertada) {
                totalErrors++;
                actualitzarEstatPenjat(estatPenjat, totalErrors);
            }

            // Afegir la lletra al llistat de lletres introduïdes
            lletresIntroduides += lletra;

        } while (totalEncerts < paraulaSecreta.length() && totalErrors < MAXINTENTS);

// Mostrar missatge si la partida ha acabat
        if (totalEncerts == paraulaSecreta.length()) {
            System.out.println("Felicitats, has guanyat");
            System.out.println("La paraula era: " + paraulaSecreta);
        } else if (totalErrors == MAXINTENTS) {
            netejaPantalla();
            mostrarEstatPenjat(estatPenjat);
            System.out.println("OOOOOoooohhhh! Has perdut!!");
            System.out.println("La paraula era: " + paraulaSecreta);
        }
    }

    static void mostrarEstatPenjat(char[][] estat) {

        for (char[] fila : estat) {
            for (char valor : fila) {
                System.out.print(valor);
            }
            System.out.println("");
        }

    }

    static void inicialitzarEstatPenjat(char[][] estatPenjatIni, char[][] estat) {

        for (int i = 0; i < estatPenjatIni.length; ++i) {
            for (int j = 0; j < estatPenjatIni[0].length; ++j) {
                estat[i][j] = estatPenjatIni[i][j];
            }
        }

    }

    static void mostrarParaula(String paraula, boolean[] encertades) {
        System.out.print("Paraula: ");
        for (int i = 0; i < paraula.length(); i++) {
            char lletra = paraula.charAt(i);
            if (encertades[i]) {
                System.out.print(lletra);
            } else {
                System.out.print("*");
            }
            System.out.print(" ");
        }
        System.out.println("");
    }

    static void mostrarLletresIntroduides(String lletres) {
        System.out.print("Lletres: ");
        for (int i = 0; i < lletres.length(); i++) {
            System.out.print(lletres.charAt(i));
            if (i < lletres.length() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }

    static String demanarLletra(String lletres) {

        Scanner sc = new Scanner(System.in);
        String lletraStr;
        do {
            System.out.print("Introdueix una lletra: ");
            lletraStr = sc.nextLine().toLowerCase();
            if (lletraStr.length() != 1) {
                System.out.println("Has d'introduir una sola lletra.");
            } else if (lletres.contains(lletraStr)) {
                System.out.println("Aquesta lletra ja ha estat introduïda.");
            } else if (!Character.isLetter(lletraStr.charAt(0))) {
                System.out.println("Has d'introduir una lletra.");
            }
        } while (lletraStr.length() != 1 || lletres.contains(lletraStr) || !Character.isLetter(lletraStr.charAt(0)));
        return lletraStr;

    }

    public static boolean existeixLletra(char lletra, String paraulaSecreta) {
        for (int i = 0; i < paraulaSecreta.length(); i++) {
            if (lletra == paraulaSecreta.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    static void actualitzarEstatPenjat(char[][] estat, int errors) {
        // Actualitza l'estat del penjat segons els intents fallits
        switch (errors) {
            case 1:
                estat[1][8] = '|';
                break;
            case 2:
                estat[2][8] = 'o';
                break;
            case 3:
                estat[3][8] = '|';
                break;
            case 4:
                estat[3][7] = '/';
                break;
            case 5:
                estat[3][9] = '\\';
                break;
            case 6:
                estat[4][8] = '|';
                break;
            case 7:
                estat[5][7] = '/';
                break;
            case 8:
                estat[5][9] = '\\';
                break;
        }

    }

    static void netejaPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {}

    }

}
