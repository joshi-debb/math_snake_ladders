package Practica1_IPC1_2S2021;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.*;

public class EscalerasMatematicas {

    // ========> VARIABLES GLOBALES <========

    //ESCANER
    static Scanner escribe = new Scanner(System.in);
    static int leer;
    static char leer2 = 0;

    //NUMERO AL AZAR
    static Random tool = new Random();

    //VARIABLES PARA TABLERO

    // INICIALIZACION Y LLENADO DEL TABLERO PRINCIPAL

    public static int [][] tablero1 = {
            {64,63,62,61,60,59,58,57},
            {49,50,51,52,53,54,55,56},
            {48,47,46,45,44,43,42,41},
            {33,34,35,36,37,38,39,40},
            {32,31,30,29,28,27,26,25},
            {17,18,19,20,21,22,23,24},
            {16,15,14,13,12,11,10,9},
            {1,2,3,4,5,6,7,8}
    };

    
    public static int  [][] tablero2 = new int [8][8];
    public static boolean [][] posPena = new boolean[8][8];
    static double[][] mi;
    static int posJugador, mover;
    static int count;
    static int count2 = 0;
    static int count3 = 0;
    static int count4 = 0;
    static int countImpresionPF = 0;
    static int countImpresionPM = 0;
    static int countImpresionPD = 0;
    static int countAcciones = 0;
    static String texto2 = "";

    //VARIABLES PARA OPERACIONES
    static double apoyo, aux, resultado;
    static double factor,fc1, fc2,fc3, fc4, det;
    static double ladoA, ladoB,ladoC;
    static double alfa, beta, gama;

    // ========> METODO MAIN <========

    public static void main(String[] args) {
        menu();
    }

    // ========> MENU DEL JUEGO <========
    public static void menu(){

        do {
            System.out.println("----------------------------------");
            System.out.println("BIENVENIDO A ESCALERAS MATEMATICAS");
            System.out.println("========= MENU PRINCIPAL =========");
            System.out.println("1. Iniciar el Juego");
            System.out.println("2. Retomar el Juego");
            System.out.println("3. Generar Reportes");
            System.out.println("4. Salir");
            System.out.println("----------------------------------");

            try {
                leer = escribe.nextInt();
            } catch (Exception e) {
                System.out.println("Por favor ingrese un valor numerico entre 1 y 4");
            }

            switch (leer) {

                case 1:

                    count2 = 0;
                    count3 = 0;
                    count4 = 0;

                    //LLENADO DE LA MATRIZ SECUNDARIA

                    for (int i = 0; i < tablero2.length; i++) {
                        int countpen = 0;
                        for (int j = 0; j < tablero2[i].length; j++) {
                            if (countpen < 4) {
                                tablero2[i][j] = tool.nextInt(2);
                                if (tablero2[i][j] == 1 ) {
                                    countpen++;
                                }
                            }
                        }
                    }

                    //LLENADO DE LAS UBIACIONES DEPENALIZACIONES

                    for (int i = 0; i < tablero2.length; i++) {
                        for (int j = 0; j < tablero2[i].length; j++) {
                            if (tablero2[i][j] == 1){
                                posPena[i][j] = true;
                            }else{
                                posPena[i][j] = false;
                            }
                        }
                    }
                    posJugador = 0;

                    do{

                        for (int i = 0; i < tablero1.length; i++) {
                            System.out.println("-----------------------------------------------------------------------------------------------");
                            for (int j = 0; j < tablero1[i].length; j++) {
                                if (posJugador != tablero1[i][j] && !posPena[i][j]) {
                                    System.out.print("|    " + tablero1[i][j] + "\t |");
                                }
                                if (posJugador == tablero1[i][j] && posPena[i][j]) {
                                    System.out.print("|    " + tablero1[i][j] + "#@" + "\t |");
                                    count = 1;
                                } else if (posJugador == tablero1[i][j]) {
                                    System.out.print("|    " + tablero1[i][j] + "@" + "\t |");
                                } else if (posPena[i][j]) {
                                    System.out.print("|    " + tablero1[i][j] + "#" + "\t |");
                                }
                            }
                            System.out.println();
                        }
                        System.out.println("-----------------------------------------------------------------------------------------------");

                        System.out.println(" =====>| Presiona 1 para lanzar el dado |<===== ");
                        System.out.println(" =====>| Presiona la tecla p para salir al menu |<===== ");

                        //llENADO E IMPRESION DE TIPO DE PENALIZACION SEGUN POSICION EN FILAS Y COLUMNAS

                        if (count == 1) {
                            if (posJugador >= tablero1[2][7] && posJugador <= tablero1[0][0]) {

                                if (count2 != 2) {
                                    aux = tool.nextInt(4);
                                }else{
                                    aux = 5;
                                }
                                if (aux == 1) {
                                    penalizacionD1(); count2++; countImpresionPD = 1;
                                    countAcciones++;
                                    texto2+=
                                            "  \t\t<tr>\n"+
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Dificil Opcion 1</td>\n"+
                                            "  \t\t<tr>\n";
                                } else if (aux == 2) {
                                    penalizacionD3(); count2++; countImpresionPD = 2;
                                    countAcciones++;
                                    texto2+=
                                            "  \t\t<tr>\n"+
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Dificil Opcion 2</td>\n"+
                                                    "  \t\t<tr>\n";
                                } else if(aux == 3) {
                                    penalizacionD2(); count2++; countImpresionPD = 3;
                                    countAcciones++;
                                    texto2+=
                                            "  \t\t<tr>\n"+
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Dificil Opcion 3</td>\n"+
                                                    "  \t\t<tr>\n";
                                }
                                if (aux == 5){
                                    //System.out.println("No habra mas penalizaciones Dificiles");
                                }

                            } else if (posJugador <= tablero1[3][7] && posJugador >= tablero1[5][0]) {
                                if (count3 != 2) {
                                    aux = tool.nextInt(4);
                                }else{
                                    aux = 5;
                                }
                                if (aux == 1) {
                                    penalizacionM1(); count3++; countImpresionPM = 1;
                                    countAcciones++;
                                    texto2+=
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Media Opcion 1</td>\n"+
                                                    "  \t\t<tr>\n";
                                } else if (aux == 2) {
                                    penalizacionM3(); count3++; countImpresionPM = 2;
                                    countAcciones++;
                                    texto2+=
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Media Opcion 2</td>\n"+
                                                    "  \t\t<tr>\n";
                                } else if(aux == 3) {
                                    penalizacionM2(); count3++; countImpresionPM = 3;
                                    countAcciones++;
                                    texto2+="  \t\t<tr>\n"+
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Media Opcion 3</td>\n"+
                                            "  \t\t<tr>\n";
                                }
                                if (aux == 5){
                                    //System.out.println("No habra mas penalizaciones Medias");
                                }
                            } else if (posJugador >= tablero1[7][0] && posJugador <= tablero1[6][0]) {
                                if (count4 != 2) {
                                    aux = tool.nextInt(4);
                                }else{
                                    aux = 5;
                                }
                                if (aux == 1) {
                                    penalizacionF1(); count4++;  countImpresionPF = 1;
                                    countAcciones++;
                                    texto2+=
                                            "  \t\t<tr>\n"+
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Facil Opcion 1</td>\n"+
                                                    "  \t\t<tr>\n";
                                } else if (aux == 2) {
                                    penalizacionF3(); count4++; countImpresionPF = 2;
                                    countAcciones++;
                                    texto2+=
                                            "  \t\t<tr>\n"+
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Facil Opcion 2</td>\n"+
                                                    "  \t\t<tr>\n";
                                } else if(aux == 3) {
                                    penalizacionF2(); count4++; countImpresionPF = 3;
                                    countAcciones++;
                                    texto2+=
                                            "  \t\t<tr>\n"+
                                            "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                    "    \t<td>Penalizacion Facil Opcion 3</td>\n"+
                                                    "  \t\t<tr>\n";
                                }
                                if (aux == 5){
                                    //System.out.println("No habra mas penalizaciones Faciles");
                                }
                            }
                            count = 0;
                        }
                        if (posJugador >= 64) {
                          posJugador = 64;
                        }
                        if (posJugador >= 64) {
                            System.out.println("Has ganado !");
                            menu();
                        }else{ dado(); }

                    }while (leer2 != 'p');

                    break;
            case 2:

                do{
                    for (int i = 0; i < tablero1.length; i++) {
                        System.out.println("-----------------------------------------------------------------------------------------------");
                        for (int j = 0; j < tablero1[i].length; j++) {
                            if (posJugador != tablero1[i][j] && !posPena[i][j]) {
                                System.out.print("|    " + tablero1[i][j] + "\t |");
                            }
                            if (posJugador == tablero1[i][j] && posPena[i][j]) {
                                System.out.print("|    " + tablero1[i][j] + "#@" + "\t |");
                                count = 1;
                            } else if (posJugador == tablero1[i][j]) {
                                System.out.print("|    " + tablero1[i][j] + "@" + "\t |");
                            } else if (posPena[i][j]) {
                                System.out.print("|    " + tablero1[i][j] + "#" + "\t |");
                            }
                        }
                        System.out.println();
                    }
                    System.out.println("-----------------------------------------------------------------------------------------------");

                    System.out.println(" =====>| Presiona 1 para lanzar el dado |<===== ");
                    System.out.println(" =====>| Presiona la tecla p para salir al menu |<===== ");

                    //llENADO E IMPRESION DE TIPO DE PENALIZACION SEGUN POSICION EN FILAS Y COLUMNAS

                    if (count == 1) {
                        if (posJugador >= tablero1[2][7] && posJugador <= tablero1[0][0]) {

                            if (count2 != 2) {
                                aux = tool.nextInt(4);
                            }else{
                                aux = 5;
                            }
                            if (aux == 1) {
                                penalizacionD1(); count2++; countImpresionPD = 1;
                                countAcciones++;
                                texto2+=
                                        "  \t\t<tr>\n"+
                                                "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                "    \t<td>Penalizacion Dificil Opcion 1</td>\n"+
                                                "  \t\t<tr>\n";
                            } else if (aux == 2) {
                                penalizacionD3(); count2++; countImpresionPD = 2;
                                countAcciones++;
                                texto2+=
                                        "  \t\t<tr>\n"+
                                                "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                "    \t<td>Penalizacion Dificil Opcion 2</td>\n"+
                                                "  \t\t<tr>\n";
                            } else if(aux == 3) {
                                penalizacionD2(); count2++; countImpresionPD = 3;
                                countAcciones++;
                                texto2+=
                                        "  \t\t<tr>\n"+
                                                "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                "    \t<td>Penalizacion Dificil Opcion 3</td>\n"+
                                                "  \t\t<tr>\n";
                            }
                            if (aux == 5){
                                //System.out.println("No habra mas penalizaciones Dificiles");
                            }

                        } else if (posJugador <= tablero1[3][7] && posJugador >= tablero1[5][0]) {
                            if (count3 != 2) {
                                aux = tool.nextInt(4);
                            }else{
                                aux = 5;
                            }
                            if (aux == 1) {
                                penalizacionM1(); count3++; countImpresionPM = 1;
                                countAcciones++;
                                texto2+=
                                        "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                "    \t<td>Penalizacion Media Opcion 1</td>\n"+
                                                "  \t\t<tr>\n";
                            } else if (aux == 2) {
                                penalizacionM3(); count3++; countImpresionPM = 2;
                                countAcciones++;
                                texto2+=
                                        "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                "    \t<td>Penalizacion Media Opcion 2</td>\n"+
                                                "  \t\t<tr>\n";
                            } else if(aux == 3) {
                                penalizacionM2(); count3++; countImpresionPM = 3;
                                countAcciones++;
                                texto2+="  \t\t<tr>\n"+
                                        "   \t\t<td>"+ countAcciones +"</td>\n"+
                                        "    \t<td>Penalizacion Media Opcion 3</td>\n"+
                                        "  \t\t<tr>\n";
                            }
                            if (aux == 5){
                                //System.out.println("No habra mas penalizaciones Medias");
                            }
                        } else if (posJugador >= tablero1[7][0] && posJugador <= tablero1[6][0]) {
                            if (count4 != 2) {
                                aux = tool.nextInt(4);
                            }else{
                                aux = 5;
                            }
                            if (aux == 1) {
                                penalizacionF1(); count4++;  countImpresionPF = 1;
                                countAcciones++;
                                texto2+=
                                        "  \t\t<tr>\n"+
                                                "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                "    \t<td>Penalizacion Facil Opcion 1</td>\n"+
                                                "  \t\t<tr>\n";
                            } else if (aux == 2) {
                                penalizacionF3(); count4++; countImpresionPF = 2;
                                countAcciones++;
                                texto2+=
                                        "  \t\t<tr>\n"+
                                                "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                "    \t<td>Penalizacion Facil Opcion 2</td>\n"+
                                                "  \t\t<tr>\n";
                            } else if(aux == 3) {
                                penalizacionF2(); count4++; countImpresionPF = 3;
                                countAcciones++;
                                texto2+=
                                        "  \t\t<tr>\n"+
                                                "   \t\t<td>"+ countAcciones +"</td>\n"+
                                                "    \t<td>Penalizacion Facil Opcion 3</td>\n"+
                                                "  \t\t<tr>\n";
                            }
                            if (aux == 5){
                                //System.out.println("No habra mas penalizaciones Faciles");
                            }
                        }
                        count = 0;
                    }

                    if (posJugador >= 64) {
                        posJugador = 64;
                    }

                    if (posJugador >= 64) {
                        System.out.println("Has ganado !");
                        menu();
                    }else{ dado();}

                }while (leer2 != 'p');

                break;
            case 3:
                generarReportes();
                generarReportes2();
                break;
            case 4:
                System.out.println("======> GAME OVER <======");
                break;
            default:
                System.out.println("Ingrese un numero entre 1 y 4");
                break;
            }
        } while (leer != 4);
    }

    // ========> Dado y movimiento del jugador <========
    public static void dado() {

        try {
            leer2 = escribe.next().charAt(0);
        } catch (Exception e) {}
        if (leer2 == '1') {
            mover = (int) (Math.random() * 4) + 2;

            countAcciones++;
            texto2+=
                    "  \t\t<tr>\n"+
                    "   \t\t<td>"+ countAcciones +"</td>\n"+
                            "    \t<td>Lanzar dado</td>\n"+
                            "  \t\t<tr>\n";

            System.out.println(" =====>| has sacado un: " + mover + " |<===== ");
            if (mover != 0) {
                posJugador = posJugador + mover;

                countAcciones++;
                texto2+=
                        "  \t\t<tr>\n"+
                        "   \t\t<td>"+ countAcciones +"</td>\n"+
                                "    \t<td>"+ "Posicion del jugador es: "+posJugador+"</td>\n"+
                        "  \t\t<tr>\n";
            }
            System.out.println(" =====>| Tu posicion actual es: " + posJugador + " |<===== ");
        }
    }

    // ========> PENALIZACIONES <========
    public static void penalizacionD1() {

        System.out.println("Has caido en una penalizacion Dificil");
        System.out.println("En la casilla: " + posJugador);

        double[][] ma1 = {
                {5,10,1,3},
                {9,14,2,6},
                {7,8,15,3},
                {6,8,9,2},
        };
        double[][] mb1 = {
                {5,13,9,4},
                {1,9,6,3},
                {8,11,69,33},
                {25,6,7,4},
        };

        //---------------PRIMERA OPERACION------------------

        System.out.println("=============================================================================");
        System.out.println("Opcion 1 -> Nivel de dificultad Dificil");
        System.out.println("Realice la sieguiente operacion: Matriz A / Matriz B");

        System.out.println("Matriz A: ");
        for (int i = 0; i < ma1.length; i++) {
            for (int j = 0; j < ma1[i].length; j++) {
                System.out.print("| " + ma1[i][j] + " | \t");
            }
            System.out.println();
        }
        System.out.println("Matriz B: ");
        for (int i = 0; i < mb1.length; i++) {
            for (int j = 0; j < mb1[i].length; j++) {
                System.out.print("| " + mb1[i][j] + " | \t");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("SOLUCION: Matriz A / Matriz B");

        System.out.println("En primer lugar se debe calcular el deterimnante de la matriz denominador: ");
        System.out.println("NOTA: Si el determinante es cero, la operacion A/B no tiene solucion. ");


        //CALCULO DEL DETERMINANTE
        factor = ((mb1[1][1] * (mb1[2][2] * mb1[3][3] - mb1[3][2] * mb1[2][3]))
                + (-1 * mb1[1][2] * (mb1[2][1] * mb1[3][3] - mb1[3][1] * mb1[2][3]))
                + (mb1[1][3] * (mb1[2][1] * mb1[3][2] - mb1[3][1] * mb1[2][2])));
        fc1 = factor * mb1[0][0];

        factor = ((mb1[1][0] * (mb1[2][2] * mb1[3][3] - mb1[3][2] * mb1[2][3]))
                + (-1 * mb1[1][2] * (mb1[2][0] * mb1[3][3] - mb1[3][0] * mb1[2][3]))
                + (mb1[1][3] * (mb1[2][0] * mb1[3][2] - mb1[3][0] * mb1[2][2])));
        fc2 = factor * (-1 * mb1[0][1]);

        factor = ((mb1[1][0] * (mb1[2][1] * mb1[3][3] - mb1[3][1] * mb1[2][3]))
                + (-1 * mb1[1][1] * (mb1[2][0] * mb1[3][3] - mb1[3][0] * mb1[2][3]))
                + (mb1[1][3] * (mb1[2][0] * mb1[3][1] - mb1[3][0] * mb1[2][1])));
        fc3 = factor * mb1[0][2];

        factor = ((mb1[1][0] * (mb1[2][1] * mb1[3][2] - mb1[3][1] * mb1[2][2]))
                + (-1 * mb1[1][1] * (mb1[2][0] * mb1[3][2] - mb1[3][0] * mb1[2][2]))
                + (mb1[1][2] * (mb1[2][0] * mb1[3][1] - mb1[3][0] * mb1[2][1])));
        fc4 = factor * (-1 * mb1[0][3]);

        det = Math.round((fc1 + fc2 + fc3 + fc4) * 1000.0) / 1000.0;

        System.out.println("Determinante de la matriz B es: " + det);

        if (det != 0) {

            System.out.println("Como el determinante es distinto de cero la matriz Inversa existe ");
            System.out.println("procedemos con el metodo de solucion de Gauss Jordan");

            //Calculo de la matriz inversa por el metodo de gauss

            mi = new double[4][4];          //matriz inversa

            System.out.println("Matriz B: ");
            for (int i = 0; i < mb1.length; i++) {
                for (int j = 0; j < mb1[i].length; j++) {
                    System.out.print(mb1[i][j] + " \t");
                }
                System.out.println();
            }
            //System.out.println("Definimos la matriz identidad de las mismas dimensiones que nuestra matriz original");
            //Matriz identidad
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    mi[i][j] = 0;
                    if (i == j) {
                        mi[i][j] = 1;
                    }
                }
            }

            System.out.println("Matriz Identidad: ");
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    System.out.print(mi[i][j] + " \t");
                }
                System.out.println();
            }
            //REDUCCION POR RENGLONES
            for (int i = 0; i < 4; i++) {
                apoyo = mb1[i][i]; //estoy en la diagonal
                //convertir el apoyo a 1 y aplicar la operacion sobre toda la fila
                for (int k = 0; k < 4; k++) {
                    mb1[i][k] = mb1[i][k] / apoyo; //convertir el apoyo en 1
                    mi[i][k] = mi[i][k] / apoyo;   //aplicar la misma operacon en la matriz indentidad
                }
                for (int j = 0; j < 4; j++) {
                    if (i != j) { //no estoy en la diagonal
                        aux = mb1[j][i]; //aux va a ser el valor actual de la casilla [i][j] para luego ser utilizado
                        for (int k = 0; k < 4; k++) {
                            mb1[j][k] = mb1[j][k] - aux * mb1[i][k]; //convertir el valor en cero
                            mi[j][k] = mi[j][k] - aux * mi[i][k];       //aplicar la misma operacion a la matriz identidad
                        }
                    }
                }
            }

            System.out.println("Por el metodo de reduccion por renglones obtenemos la matriz B Inversa: ");
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    mi[i][j] = Math.round(mi[i][j] * 1000.0) / 1000.0;
                    System.out.print(" | " + mi[i][j] + "    \t|");
                }
                System.out.println();
            }

            System.out.println("Realizando la operacion: Matriz A * Matriz B (Inversa) ");
            double[][] mtp = new double[4][4];

            //multiplicacion de matrices A*B^-1
            for (int i = 0; i < 4; i++) {   //recorrido de las filas de A
                for (int j = 0; j < 4; j++) {  //Recorrido de las columnas de B^-1
                    mtp[i][j] = 0;
                    for (int k = 0; k < 4; k++) { //recorrido cada elemento de cada fila
                        mtp[i][j] = mtp[i][j] + ma1[i][k] * mi[k][j];  //llenado de filas y columnas
                    }
                }
            }

            System.out.println("Obtenemos la Matriz A/B: ");
            for (int i = 0; i < mtp.length; i++) {
                for (int j = 0; j < mtp[i].length; j++) {
                    mtp[i][j] = Math.round(mtp[i][j] * 1000.0) / 1000.0;
                    System.out.print("| " + mtp[i][j] + " | \t");
                }
                System.out.println();
            }

        } else {
            System.out.println("El determinante no existe, por lo que la operacion no puede realizarse");
        }

    }

    public static void penalizacionD2() {

        System.out.println("Has caido en una penalizacion Dificil");
        System.out.println("En la casilla: " + posJugador);

        double[][] ma2 = {
                {1,12,9,8},
                {7,6,3,2},
                {0,5,6,14},
                {6,9,6,10},
        };
        double[][] mb2 = {
                {8,19,20,4},
                {12,33,6,8},
                {4,5,9,7},
                {8,22,14,6},
        };

        //-------------SEGUNDA OPERACION----------------

        System.out.println("=============================================================================");
        System.out.println("Opcion 2 -> Nivel de dificultad Dificil");
        System.out.println("Realice la sieguiente operacion: Matriz A / Matriz B");

        System.out.println("Matriz A: ");
        for (int i = 0; i < ma2.length; i++) {
            for (int j = 0; j < ma2[i].length; j++) {
                System.out.print("| " + ma2[i][j] + " | \t");
            }
            System.out.println();
        }
        System.out.println("Matriz B: ");
        for (int i = 0; i < mb2.length; i++) {
            for (int j = 0; j < mb2[i].length; j++) {
                System.out.print("| " + mb2[i][j] + " | \t");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("SOLUCION: Matriz A / Matriz B");

        System.out.println("En primer lugar se debe calcular el deterimnante de la matriz denominador: ");
        System.out.println("NOTA: Si el determinante es cero, la operacion A/B no tiene solucion. ");


        //CALCULO DEL DETERMINANTE
        factor = ((mb2[1][1] * (mb2[2][2] * mb2[3][3] - mb2[3][2] * mb2[2][3]))
                + (-1 * mb2[1][2] * (mb2[2][1] * mb2[3][3] - mb2[3][1] * mb2[2][3]))
                + (mb2[1][3] * (mb2[2][1] * mb2[3][2] - mb2[3][1] * mb2[2][2])));
        fc1 = factor * mb2[0][0];

        factor = ((mb2[1][0] * (mb2[2][2] * mb2[3][3] - mb2[3][2] * mb2[2][3]))
                + (-1 * mb2[1][2] * (mb2[2][0] * mb2[3][3] - mb2[3][0] * mb2[2][3]))
                + (mb2[1][3] * (mb2[2][0] * mb2[3][2] - mb2[3][0] * mb2[2][2])));
        fc2 = factor * (-1 * mb2[0][1]);

        factor = ((mb2[1][0] * (mb2[2][1] * mb2[3][3] - mb2[3][1] * mb2[2][3]))
                + (-1 * mb2[1][1] * (mb2[2][0] * mb2[3][3] - mb2[3][0] * mb2[2][3]))
                + (mb2[1][3] * (mb2[2][0] * mb2[3][1] - mb2[3][0] * mb2[2][1])));
        fc3 = factor * mb2[0][2];

        factor = ((mb2[1][0] * (mb2[2][1] * mb2[3][2] - mb2[3][1] * mb2[2][2]))
                + (-1 * mb2[1][1] * (mb2[2][0] * mb2[3][2] - mb2[3][0] * mb2[2][2]))
                + (mb2[1][2] * (mb2[2][0] * mb2[3][1] - mb2[3][0] * mb2[2][1])));
        fc4 = factor * (-1 * mb2[0][3]);

        det = Math.round((fc1 + fc2 + fc3 + fc4) * 1000.0) / 1000.0;

        System.out.println("Determinante de la matriz B es: " + det);

        if (det != 0) {

            System.out.println("Como el determinante es distinto de cero la matriz Inversa existe ");
            System.out.println("procedemos con el metodo de solucion de Gauss Jordan");

            //Calculo de la matriz inversa por el metodo de gauss

            mi = new double[4][4];          //matriz inversa

            System.out.println("Matriz B: ");
            for (int i = 0; i < mb2.length; i++) {
                for (int j = 0; j < mb2[i].length; j++) {
                    System.out.print(mb2[i][j] + " \t");
                }
                System.out.println();
            }
            //System.out.println("Definimos la matriz identidad de las mismas dimensiones que nuestra matriz original");
            //Matriz identidad
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    mi[i][j] = 0;
                    if (i == j) {
                        mi[i][j] = 1;
                    }
                }
            }

            System.out.println("Matriz Identidad: ");
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    System.out.print(mi[i][j] + " \t");
                }
                System.out.println();
            }
            //REDUCCION POR RENGLONES
            for (int i = 0; i < 4; i++) {
                apoyo = mb2[i][i];
                //convertir el apoyo a 1 y aplicar la operacion sobre toda la fila
                for (int k = 0; k < 4; k++) {
                    mb2[i][k] = mb2[i][k] / apoyo;
                    mi[i][k] = mi[i][k] / apoyo;
                }
                for (int j = 0; j < 4; j++) {
                    if (i != j) {
                        aux = mb2[j][i];
                        for (int k = 0; k < 4; k++) {
                            mb2[j][k] = mb2[j][k] - aux * mb2[i][k];
                            mi[j][k] = mi[j][k] - aux * mi[i][k];
                        }
                    }
                }
            }

            System.out.println("Por el metodo de reduccion por renglones obtenemos la matriz B Inversa: ");
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    mi[i][j] = Math.round(mi[i][j] * 1000.0) / 1000.0;
                    System.out.print(" | " + mi[i][j] + "    \t|");
                }
                System.out.println();
            }

            System.out.println("Realizando la operacion: Matriz A * Matriz B (Inversa) ");
            double[][] mtp = new double[4][4];

            //multiplicacion de matrices A*B^-1
            for (int i = 0; i < 4; i++) {   //recorrido de las filas de A
                for (int j = 0; j < 4; j++) {  //Recorrido de las columnas de B^-1
                    mtp[i][j] = 0;
                    for (int k = 0; k < 4; k++) { //recorrido cada elemento de cada fila
                        mtp[i][j] = mtp[i][j] + ma2[i][k] * mi[k][j];  //llenado de filas y columnas
                    }
                }
            }

            System.out.println("Obtenemos la Matriz A/B: ");
            for (int i = 0; i < mtp.length; i++) {
                for (int j = 0; j < mtp[i].length; j++) {
                    mtp[i][j] = Math.round(mtp[i][j] * 1000.0) / 1000.0;
                    System.out.print("| " + mtp[i][j] + " | \t");
                }
                System.out.println();
            }

        } else {
            System.out.println("El determinante no existe, por lo que la operacion no puede realizarse");
        }

    }

    public static void penalizacionD3() {

        System.out.println("Has caido en una penalizacion Dificil");
        System.out.println("En la casilla: " + posJugador);

        double[][] ma3 = {
                {5,9,14,5},
                {6,0,5,3},
                {1,14,68,8},
                {7,5,3,9},
        };
        double[][] mb3 = {
                {0,9,7,19},
                {2,30,5,48},
                {1,31,2,5},
                {15,8,4,3},
        };

        //-------------TERCERA OPERACION----------------

        System.out.println("=============================================================================");
        System.out.println("Opcion 3 -> Nivel de dificultad Dificil");
        System.out.println("Realice la sieguiente operacion: Matriz A / Matriz B");

        System.out.println("Matriz A: ");
        for (int i = 0; i < ma3.length; i++) {
            for (int j = 0; j < ma3[i].length; j++) {
                System.out.print("| " + ma3[i][j] + " | \t");
            }
            System.out.println();
        }
        System.out.println("Matriz B: ");
        for (int i = 0; i < mb3.length; i++) {
            for (int j = 0; j < mb3[i].length; j++) {
                System.out.print("| " + mb3[i][j] + " | \t");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("SOLUCION: Matriz A / Matriz B");

        System.out.println("En primer lugar se debe calcular el deterimnante de la matriz denominador: ");
        System.out.println("NOTA: Si el determinante es cero, la operacion A/B no tiene solucion. ");


        //CALCULO DEL DETERMINANTE
        factor = ((mb3[1][1] * (mb3[2][2] * mb3[3][3] - mb3[3][2] * mb3[2][3]))
                + (-1 * mb3[1][2] * (mb3[2][1] * mb3[3][3] - mb3[3][1] * mb3[2][3]))
                + (mb3[1][3] * (mb3[2][1] * mb3[3][2] - mb3[3][1] * mb3[2][2])));
        fc1 = factor * mb3[0][0];

        factor = ((mb3[1][0] * (mb3[2][2] * mb3[3][3] - mb3[3][2] * mb3[2][3]))
                + (-1 * mb3[1][2] * (mb3[2][0] * mb3[3][3] - mb3[3][0] * mb3[2][3]))
                + (mb3[1][3] * (mb3[2][0] * mb3[3][2] - mb3[3][0] * mb3[2][2])));
        fc2 = factor * (-1 * mb3[0][1]);

        factor = ((mb3[1][0] * (mb3[2][1] * mb3[3][3] - mb3[3][1] * mb3[2][3]))
                + (-1 * mb3[1][1] * (mb3[2][0] * mb3[3][3] - mb3[3][0] * mb3[2][3]))
                + (mb3[1][3] * (mb3[2][0] * mb3[3][1] - mb3[3][0] * mb3[2][1])));
        fc3 = factor * mb3[0][2];

        factor = ((mb3[1][0] * (mb3[2][1] * mb3[3][2] - mb3[3][1] * mb3[2][2]))
                + (-1 * mb3[1][1] * (mb3[2][0] * mb3[3][2] - mb3[3][0] * mb3[2][2]))
                + (mb3[1][2] * (mb3[2][0] * mb3[3][1] - mb3[3][0] * mb3[2][1])));
        fc4 = factor * (-1 * mb3[0][3]);

        det = Math.round((fc1 + fc2 + fc3 + fc4) * 1000.0) / 1000.0;

        System.out.println("Determinante de la matriz B es: " + det);

        if (det != 0) {

            System.out.println("Como el determinante es distinto de cero la matriz Inversa existe ");
            System.out.println("procedemos con el metodo de solucion de Gauss Jordan");

            //Calculo de la matriz inversa por el metodo de gauss

            mi = new double[4][4];          //matriz inversa

            System.out.println("Matriz B: ");
            for (int i = 0; i < mb3.length; i++) {
                for (int j = 0; j < mb3[i].length; j++) {
                    System.out.print(mb3[i][j] + " \t");
                }
                System.out.println();
            }
            //System.out.println("Definimos la matriz identidad de las mismas dimensiones que nuestra matriz original");
            //Matriz identidad
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    mi[i][j] = 0;
                    if (i == j) {
                        mi[i][j] = 1;
                    }
                }
            }

            System.out.println("Matriz Identidad: ");
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    System.out.print(mi[i][j] + " \t");
                }
                System.out.println();
            }
            //REDUCCION POR RENGLONES
            for (int i = 0; i < 4; i++) {
                apoyo = mb3[i][i];
                //convertir el apoyo a 1 y aplicar la operacion sobre toda la fila
                for (int k = 0; k < 4; k++) {
                    mb3[i][k] = mb3[i][k] / apoyo;
                    mi[i][k] = mi[i][k] / apoyo;
                }
                for (int j = 0; j < 4; j++) {
                    if (i != j) {
                        aux = mb3[j][i];
                        for (int k = 0; k < 4; k++) {
                            mb3[j][k] = mb3[j][k] - aux * mb3[i][k];
                            mi[j][k] = mi[j][k] - aux * mi[i][k];
                        }
                    }
                }
            }

            System.out.println("Por el metodo de reduccion por renglones obtenemos la matriz B Inversa: ");
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    mi[i][j] = Math.round(mi[i][j] * 1000.0) / 1000.0;
                    System.out.print(" | " + mi[i][j] + "    \t|");
                }
                System.out.println();
            }

            System.out.println("Realizando la operacion: Matriz A * Matriz B (Inversa) ");
            double[][] mtp = new double[4][4];

            //multiplicacion de matrices A*B^-1
            for (int i = 0; i < 4; i++) {   //recorrido de las filas de A
                for (int j = 0; j < 4; j++) {  //Recorrido de las columnas de B^-1
                    mtp[i][j] = 0;
                    for (int k = 0; k < 4; k++) { //recorrido cada elemento de cada fila
                        mtp[i][j] = mtp[i][j] + ma3[i][k] * mi[k][j];  //llenado de filas y columnas
                    }
                }
            }

            System.out.println("Obtenemos la Matriz A/B: ");
            for (int i = 0; i < mtp.length; i++) {
                for (int j = 0; j < mtp[i].length; j++) {
                    mtp[i][j] = Math.round(mtp[i][j] * 1000.0) / 1000.0;
                    System.out.print("| " + mtp[i][j] + " | \t");
                }
                System.out.println();
            }

        } else {
            System.out.println("El determinante no existe, por lo que la operacion no puede realizarse");
        }
    }

    public static void penalizacionM1() {

        System.out.println("Has caido en una penalizacion Media");
        System.out.println("En la casilla: " + posJugador);

        double[][] ma4 = {
                {7,48,5,0,1},
                {57,8,4,6,14},
                {0,5,6,78,15},
                {21,14,8,19,54},
                {32,20,26,47,12},
        };
        double[][] mb4 = {
                {9,5,2,1,8},
                {4,2,3,47,8},
                {48,55,32,19,6},
                {7,56,32,14,8},
                {32,87,0,1,7},
        };

        //---------------PRIMERA OPERACION------------------

        System.out.println("=============================================================================");
        System.out.println("Opcion 1 -> Nivel de dificultad Media");
        System.out.println("Realice la sieguiente operacion: Matriz A + Matriz B");

        System.out.println("Matriz A: ");
        for (int i = 0; i < ma4.length; i++) {
            for (int j = 0; j < ma4[i].length; j++) {
                System.out.print("| " + ma4[i][j] + " | \t");
            }
            System.out.println();
        }

        System.out.println("Matriz B: ");
        for (int i = 0; i < mb4.length; i++) {
            for (int j = 0; j < mb4[i].length; j++) {
                System.out.print("| " + mb4[i][j] + " | \t");
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("SOLUCION: Matriz A + Matriz B");


        double[][] suma = new double[5][5];
        for (int i = 0; i < ma4.length; i++) {
            for (int j = 0; j < ma4[i].length; j++) {
                suma[i][j] = ma4[i][j] + mb4[i][j];
            }
        }
        for (int i = 0; i < suma.length; i++) {
            for (int j = 0; j < suma[i].length; j++) {
                System.out.print("| " + suma[i][j] + " | \t");
            }
            System.out.println();
        }
    }

    public static void penalizacionM2() {

        System.out.println("Has caido en una penalizacion Media");
        System.out.println("En la casilla: " + posJugador);

        double[][] ma5 = {
                {4,9,7,45,18},
                {7,51,26,8,38},
                {48,26,37,21,19},
                {1,0,6,8,1},
                {2,19,55,25,15},
        };

        double[][] mb5 = {
                {0,2,15,1,66},
                {21,48,62,7,33},
                {4,88,0,68,4},
                {25,18,24,7,55},
                {24,15,36,5,98},
        };

        //---------------SEGUNDA OPERACION------------------

        System.out.println("=============================================================================");
        System.out.println("Opcion 2 -> Nivel de dificultad Media");
        System.out.println("Realice la sieguiente operacion: Matriz A + Matriz B");

        System.out.println("Matriz A: ");
        for (int i = 0; i < ma5.length; i++) {
            for (int j = 0; j < ma5[i].length; j++) {
                System.out.print("| " + ma5[i][j] + " | \t");
            }
            System.out.println();
        }

        System.out.println("Matriz B: ");
        for (int i = 0; i < mb5.length; i++) {
            for (int j = 0; j < mb5[i].length; j++) {
                System.out.print("| " + mb5[i][j] + " | \t");
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("SOLUCION: Matriz A + Matriz B");


        double[][] suma2 = new double[5][5];
        for (int i = 0; i < ma5.length; i++) {
            for (int j = 0; j < ma5[i].length; j++) {
                suma2[i][j] = ma5[i][j] + mb5[i][j];
            }
        }
        for (int i = 0; i < suma2.length; i++) {
            for (int j = 0; j < suma2[i].length; j++) {
                System.out.print("| " + suma2[i][j] + " | \t");
            }
            System.out.println();
        }


    }

    public static void penalizacionM3() {

        System.out.println("Has caido en una penalizacion Media");
        System.out.println("En la casilla: " + posJugador);

        double[][] ma6 = {
                {0,1,15,5,36},
                {1,78,65,32,4},
                {48,66,39,0,55},
                {14,98,63,20,15},
                {11,39,84,7,1},
        };

        double[][] mb6 = {
                {78,25,66,48,98},
                {0,45,2,3,1},
                {2,9,14,10,20},
                {35,87,65,2,32},
                {25,8,4,9,39},
        };

        System.out.println("=============================================================================");
        System.out.println("Opcion 3 -> Nivel de dificultad Media");
        System.out.println("Realice la sieguiente operacion: Matriz A + Matriz B");

        System.out.println("Matriz A: ");
        for (int i = 0; i < ma6.length; i++) {
            for (int j = 0; j < ma6[i].length; j++) {
                System.out.print("| " + ma6[i][j] + " | \t");
            }
            System.out.println();
        }

        System.out.println("Matriz B: ");
        for (int i = 0; i < mb6.length; i++) {
            for (int j = 0; j < mb6[i].length; j++) {
                System.out.print("| " + mb6[i][j] + " | \t");
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("SOLUCION: Matriz A + Matriz B");

        double[][] suma3 = new double[5][5];

        for (int i = 0; i < ma6.length; i++) {
            for (int j = 0; j < ma6[i].length; j++) {
                suma3[i][j] = ma6[i][j] + mb6[i][j];
            }
        }
        for (int i = 0; i < suma3.length; i++) {
            for (int j = 0; j < suma3[i].length; j++) {
                System.out.print("| " + suma3[i][j] + " | \t");
            }
            System.out.println();
        }
    }

    public static void penalizacionF1() {
        System.out.println("Has caido en una penalizacion Facil");
        System.out.println("En la casilla: " + posJugador);

        //---------------PRIMERA OPERACION------------------

        System.out.println("=============================================================================");
        System.out.println("Opcion 1 -> Nivel de dificultad Facil");
        System.out.println("Segun los siguientes datos: Lado A = 15, Lado C = 20, Angulo Alfa = 25 grados");
        System.out.println("Calcule lo siguiente: Lado B = ? , Angulo Beta = ? , Angulo Gama = ? ");
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("SOLUCION: ");

        ladoA = 15.0;
        ladoC = 20.0;
        alfa = 25.0;

        //Calculo del lado B
        aux = ((ladoA * ladoA + ladoC * ladoC) - (2 * ladoA * ladoC * cos(Math.toRadians(alfa))));
        ladoB = Math.sqrt(aux);
        resultado = Math.round(ladoB * 1000.0) / 1000.0;
        System.out.println("El Lado B es: " + resultado);

        //Calculo del angulo beta
        aux = (((ladoA * ladoA) - (ladoB * ladoB) - (ladoC * ladoC)) / (-(2 * ladoB * ladoC)));
        beta = Math.toDegrees(acos(aux));
        resultado = Math.round(beta * 1000.0) / 1000.0;
        System.out.println("El angulo Beta es: " + resultado);

        //Calculo del angulo gama
        aux = (((ladoC * ladoC) - (ladoA * ladoA) - (ladoB * ladoB)) / (-(2 * ladoA * ladoB)));
        gama = Math.toDegrees(acos(aux));
        resultado = Math.round(gama * 1000.0) / 1000.0;
        System.out.println("El agnulo Gama es: " + resultado);
        System.out.println("=============================================================================");


    }

    public static void penalizacionF2() {
        System.out.println("Has caido en una penalizacion Facil");
        System.out.println("En la casilla: " + posJugador);

        //---------------SEGUNDA OPERACION------------------

        System.out.println("=============================================================================");
        System.out.println("Opcion 2 -> Nivel de dificultad Facil");
        System.out.println("Segun los siguientes datos: Lado B = 10, Lado C = 25, Angulo Beta = 30 grados");
        System.out.println("Calcule lo siguiente: Lado A = ? , Angulo Alfa = ? , Angulo Gama = ? ");
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("SOLUCION: ");

        ladoB = 10.0;
        ladoC = 25.0;
        beta = 30.0;

        //Calculo del lado A
        aux = ((ladoB * ladoB + ladoC * ladoC) - (2 * ladoB * ladoC * cos(Math.toRadians(beta))));
        ladoA = Math.sqrt(aux);
        resultado = Math.round(ladoA * 1000.0) / 1000.0;
        System.out.println("El Lado A es: " + resultado);

        //Calculo del angulo alfa
        aux = (((ladoB * ladoB) - (ladoA * ladoA) - (ladoC * ladoC)) / (-(2 * ladoA * ladoC)));
        alfa = Math.toDegrees(acos(aux));
        resultado = Math.round(alfa * 1000.0) / 1000.0;
        System.out.println("El angulo Alfa es: " + resultado);

        //Calculo del angulo gama
        aux = (((ladoC * ladoC) - (ladoA * ladoA) - (ladoB * ladoB)) / (-(2 * ladoA * ladoB)));
        gama = Math.toDegrees(acos(aux));
        resultado = Math.round(gama * 1000.0) / 1000.0;
        System.out.println("El agnulo Gama es: " + resultado);
        System.out.println("=============================================================================");

    }

    public static void penalizacionF3() {
        System.out.println("Has caido en una penalizacion Facil");
        System.out.println("En la casilla: " + posJugador);
        //---------------TERCERA OPERACION------------------

        System.out.println("=============================================================================");
        System.out.println("Opcion 3 -> Nivel de dificultad Facil");
        System.out.println("Segun los siguientes datos: Lado A = 18, Lado B = 25, Angulo Gama = 30 grados");
        System.out.println("Calcule lo siguiente: Lado C = ? , Angulo Alfa = ? , Angulo Beta = ? ");
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("SOLUCION: ");

        ladoA = 18.0;
        ladoB = 25.0;
        gama = 30.0;

        //Calculo del lado C
        aux = ((ladoA * ladoA + ladoB * ladoB) - (2 * ladoA * ladoB * cos(Math.toRadians(gama))));
        ladoC = Math.sqrt(aux);
        resultado = Math.round(ladoC * 1000.0) / 1000.0;
        System.out.println("El Lado C es: " + resultado);

        //Calculo del angulo alfa
        aux = (((ladoB * ladoB) - (ladoA * ladoA) - (ladoC * ladoC)) / (-(2 * ladoA * ladoC)));
        alfa = Math.toDegrees(acos(aux));
        resultado = Math.round(alfa * 1000.0) / 1000.0;
        System.out.println("El angulo Alfa es: " + resultado);

        //Calculo del angulo beta
        aux = (((ladoA * ladoA) - (ladoB * ladoB) - (ladoC * ladoC)) / (-(2 * ladoB * ladoC)));
        beta = Math.toDegrees(acos(aux));
        resultado = Math.round(beta * 1000.0) / 1000.0;
        System.out.println("El agnulo Beta es: " + resultado);
        System.out.println("=============================================================================");

    }

    public static void generarReportes() {

        System.out.println("Ingrese la ruta donde desea guardar su reporte");
        String ruta = new Scanner(System.in).nextLine();


        FileWriter fichero = null;
        PrintWriter pw = null;

        String texto = "";

        texto += " <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<title>Reporte 1</title>\n" +
                "\n" +
                "\t<style type=\"text/css\">\n" +
                "\n" +
                "\tbody{\n" +
                "\t\tmargin: 10;\n" +
                "\t\tfont-family: Papyrus;\n" +
                "\t\tbackground-color: #cfd4d1;\n" +
                "\n" +
                "\t}\n" +
                "\t.toppage{\n" +
                "\t\toverflow: hidden;\n" +
                "\t\tbackground-color:#eb492d ;\n" +
                "\t\ttext-align: center;\n" +
                "\t\tfont-family: Papyrus;\n" +
                "\t}\n" +
                "\n" +
                "\t.tabla{\n" +
                "\n" +
                "\t\tborder-collapse: collapse;\n" +
                "\t\twidows: 50%;\n" +
                "\t\ttext-align: left;\n" +
                "\t\tfont-family: Papyrus;\n" +
                "\t}\n" +
                "\n" +
                "\tth, td{\n" +
                "\n" +
                "\t\tfont-family: Papyrus;\n" +
                "\t\tborder: 1px solid #000;\n" +
                "\t\ttext-align: center;\n" +
                "\t\tpadding: 8px;\n" +
                "\t}\n" +
                "\t\t\n" +
                "\t</style>\n" +
                "\t}\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "\t<div class=\"toppage\" align=\"center\">\n" +
                "\t\t<br><br>\n" +
                "\t\t<h1>Reporte 1 \"Escaleras Matematicas\"</h1>\n" +
                "\t\t<p>Reporte de Operaciones Matematicas</p>\n" +
                "\n" +
                "\t</div><br><br>\n";

                do {

                texto+=
                "\n" +
                "<div class=\"tabla\" align=\"center\" style=\"text_align: center;\">\n" +
                "\t<table align=\"center\">\n" +
                "\t\t<tr>\n" +

                "\t\t<th  colspan = \"2\" style=\"background-color: #404035; color: white;\">Tipo de penalizacion:</th>\n" +
                "\t\t<th  colspan = \"2\" style=\"background-color: #404035; color: #00fc37;\">Facil</th>\n" +
                "\t\t</tr>\n" +
                "\n" +
                "  \t\t<tr>\n" +
                "\n" +
                "    \t<th> Datos a utilizar: </th>\n" +
                "    \t<th> Procedimiento: </th>\n" +
                "    \t<th> Resultado: </th>\n" +
                "\n" +
                " \t\t</tr>\n" +
                "\n" +
                "  \t\t<tr>\n" +
                "\n";

                    if(countImpresionPF == 1) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: Lado A = 15, Lado C = 20, Angulo Alfa = 25 grados \n "
                                + "Calcule lo siguiente: Lado B = ? , Angulo Beta = ? , Angulo Gama = ? " + "</td>\n";
                    }else if(countImpresionPF == 2) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: Lado B = 10, Lado C = 22, Angulo Beta = 30 grados \t\n "
                                + "Calcule lo siguiente: Lado A = ? , Angulo Alfa = ? , Angulo Gama = ? " + "</td>\t\n";
                    }else if(countImpresionPF == 3) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: Lado A = 18, Lado B = 25, Angulo Gama = 30 grados \t\n "
                                + "Calcule lo siguiente: Lado C = ? , Angulo Alfa = ? , Angulo Beta = ? " + "</td>\t\n";
                    }

                    if(countImpresionPF == 1) {
                        texto += "    \t<td>" +" lado B = Raiz( Lado A^2 + Lado C^2 - 2*lado A * lado C * cos (Alfa) ) \t\n"+
                                " Angulo Beta = Cos^-1 (Lado A^2 - Lado B^2 - Lado C^2 / -2*lado B * Lado C) " +
                                " Angulo Gama = Cos^-1 (Lado C^2 - Lado A^2 - Lado B^2 / -2*lado A * Lado B)" + " </td>\t\n";
                    }else if(countImpresionPF == 2) {
                        texto += "    \t<td>" + " lado A = Raiz( Lado B^2 + Lado C^2 - 2*lado B * lado C * cos (Beta) ) \t\n" +
                                " Angulo Alfa = Cos^-1 (Lado B^2 - Lado A^2 - Lado C^2 / -2*lado A * Lado C) " +
                                " Angulo Gama = Cos^-1 (Lado C^2 - Lado A^2 - Lado B^2 / -2*lado A * Lado B)" + " </td>\t\n";
                    }else if(countImpresionPF == 3) {
                        texto += "    \t<td>" + " lado C = Raiz( Lado A^2 + Lado B^2 - 2*lado A * lado B * cos (Gama) ) \t\n" +
                                " Angulo Alfa = Cos^-1 (Lado B^2 - Lado A^2 - Lado C^2 / -2*lado A * Lado C) " +
                                " Angulo Beta = Cos^-1 (Lado A^2 - Lado B^2 - Lado C^2 / -2*lado B * Lado C)" + " </td>\t\n";
                    }

                    if (countImpresionPF == 1) {
                        texto += "    \t<td>" + "Lado B = 9.012, Angulo Beta = 44.703, Angulo Gama = 110.297"+  "</td>\n";
                    }else if (countImpresionPF == 2) {
                        texto += "    \t<td>" + "Lado A = 17.088, Angulo Alfa = 17.014, Angulo Gama = 132.986"+  "</td>\n";
                    }else if (countImpresionPF == 3) {
                        texto += "    \t<td>" + "Lado C = 13.022, Angulo Alfa = 106.28, Angulo Beta = 43.720"+  "</td>\n";
                    }

                        countImpresionPF++;

                }while (countImpresionPF <= 2);

                texto+=
                "\n" +
                "  \t\t</tr>\n" +
                "\t</table>\n" +
                "</div>\n";

                do {

                texto+=
                "\n" +
                "<div class=\"tabla\" align=\"center\" style=\"text_align: center;\">\n" +
                "\t<table align=\"center\">\n" +
                "\t\t<tr>\n" +
                "\t\t<th  colspan = \"2\" style=\"background-color: #404035; color: white;\">Tipo de penalizacion:</th>\n" +
                "\t\t<th  colspan = \"2\" style=\"background-color: #404035; color: #00fc37;\">Media</th>\n" +
                "\t\t</tr>\n" +
                "\n" +
                "  \t\t<tr>\n" +
                "\n" +
                "    \t<th> Datos a utilizar: </th>\n" +
                "    \t<th> Procedimiento: </th>\n" +
                "    \t<th> Resultado: </th>\n" +
                "\n" +
                " \t\t</tr>\n" +
                "\n" +
                "  \t\t<tr>\n" +
                "\n";

                    if(countImpresionPM == 1) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: \n" +
                                "        Matriz A = {\n" +
                                "                {7,48,5,0,1},\n" +
                                "                {57,8,4,6,14},\n" +
                                "                {0,5,6,78,15},\n" +
                                "                {21,14,8,19,54},\n" +
                                "                {32,20,26,47,12},\n" +
                                "        };\n" +
                                "        Matriz B = {\n" +
                                "                {9,5,2,1,8},\n" +
                                "                {4,2,3,47,8},\n" +
                                "                {48,55,32,19,6},\n" +
                                "                {7,56,32,14,8},\n" +
                                "                {32,87,0,1,7},\n" +
                                "        };"
                                + "Calcule lo siguiente: Matriz A + Matriz B" + "</td>\n";
                    }else if(countImpresionPM == 2) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: \n" +
                                "Matriz A = {\n" +
                                "                {4,19,5,0,1},\n" +
                                "                {57,8,4,6,14},\n" +
                                "                {0,5,6,78,15},\n" +
                                "                {21,14,8,19,54},\n" +
                                "                {32,20,26,47,12},\n" +
                                "        };\n" +
                                "        Matriz B = {\n" +
                                "                {9,5,2,1,8},\n" +
                                "                {4,2,3,47,8},\n" +
                                "                {48,55,32,19,6},\n" +
                                "                {7,56,32,14,8},\n" +
                                "                {32,87,0,1,7},\n" +
                                "        };\n "
                                + "Calcule lo siguiente: Matriz A + Matriz B" + "</td>\n";
                    }else if(countImpresionPM == 3) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: \n" +
                                "        Matriz A = {\n" +
                                "                {0,1,15,5,36},\n" +
                                "                {1,78,65,32,4},\n" +
                                "                {48,66,39,0,55},\n" +
                                "                {14,98,63,20,15},\n" +
                                "                {11,39,84,7,1},\n" +
                                "        };\n" +
                                "\n" +
                                "        Matriz B = {\n" +
                                "                {78,25,66,48,98},\n" +
                                "                {0,45,2,3,1},\n" +
                                "                {2,9,14,10,20},\n" +
                                "                {35,87,65,2,32},\n" +
                                "                {25,8,4,9,39},\n" +
                                "        };"
                                + "Calcule lo siguiente: Matriz A + Matriz B" + "</td>\n";
                    }

                    if(countImpresionPM == 1) {
                        texto += "    \t<td>" +" \n" +
                                "Para poder sumar o restar matrices, éstas deben tener el mismo número de filas y de columnas. " +
                                "Se procede sumando cada termino de la matriz A con usu respectivo en la matriz B " + " </td>\t\n";
                    }else if(countImpresionPM == 2) {
                        texto += "    \t<td>" +" \n" +
                                "Para poder sumar o restar matrices, éstas deben tener el mismo número de filas y de columnas. " +
                                "Se procede sumando cada termino de la matriz A con usu respectivo en la matriz B " + " </td>\t\n";
                    }else if(countImpresionPM == 3) {
                        texto += "    \t<td>" +" \n" +
                                "Para poder sumar o restar matrices, éstas deben tener el mismo número de filas y de columnas. " +
                                "Se procede sumando cada termino de la matriz A con usu respectivo en la matriz B " + " </td>\t\n";
                    }

                    if (countImpresionPM == 1) {
                        texto += "    \t<td>" + "   Matriz A + Matriz B = {\n" +
                                "                {16,53,7,1,9},\n" +
                                "                {61,10,7,53,22},\n" +
                                "                {48,60,38,97,21},\n" +
                                "                {28,70,40,33,62},\n" +
                                "                {64,107,26,48,19},\n" +
                                "        };          "+  "</td>\n";
                    }else if (countImpresionPM == 2) {
                        texto += "    \t<td>" + "   Matriz A + Matriz B = {\n" +
                                "                {4,11,22,46,84},\n" +
                                "                {28,99,88,15,71},\n" +
                                "                {52,114,37,89,23},\n" +
                                "                {26,18,30,15,56},\n" +
                                "                {26,34,91,30,113},\n" +
                                "        };          "+  "</td>\n";
                    }else if (countImpresionPM == 3) {
                        texto += "    \t<td>" + "   Matriz A + Matriz B = {\n" +
                                "                {78,26,81,53,134},\n" +
                                "                {1,123,67,35,5},\n" +
                                "                {50,75,53,10,75},\n" +
                                "                {49,185,128,22,47},\n" +
                                "                {36,47,88,16,40},\n" +
                                "        };          "+  "</td>\n";
                    }
                            countImpresionPM++;
                      }while (countImpresionPM <= 2);

                texto+=
                "\n" +
                "  \t\t</tr>\n" +
                "\t</table>\n" +
                "</div>\n" ;


                do {

                texto+=
                "\n" +
                "<div class=\"tabla\" align=\"center\" style=\"text_align: center;\">\n" +
                "\t<table align=\"center\">\n" +
                "\t\t<tr>\n" +
                "\t\t<th  colspan = \"2\" style=\"background-color: #404035; color: white;\">Tipo de penalizacion:</th>\n" +
                "\t\t<th  colspan = \"2\" style=\"background-color: #404035; color: #00fc37;\">Dificil</th>\n" +
                "\t\t</tr>\n" +
                "\n" +
                "  \t\t<tr>\n" +
                "\n" +
                "    \t<th> Datos a utilizar: </th>\n" +
                "    \t<th> Procedimiento: </th>\n" +
                "    \t<th> Resultado: </th>\n" +
                "\n" +
                " \t\t</tr>\n" +
                "\n" +
                "  \t\t<tr>\n" +
                "\n";

                    if(countImpresionPD == 1) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: \n" +
                                "        Matriz A = {\n" +
                                "                {5,10,1,3},\n" +
                                "                {9,14,2,6},\n" +
                                "                {7,8,15,3},\n" +
                                "                {6,8,9,2},\n" +
                                "        };\n" +
                                "        Matriz B = {\n" +
                                "                {5,13,9,4},\n" +
                                "                {1,9,6,3},\n" +
                                "                {8,11,69,33},\n" +
                                "                {25,6,7,4},\n" +
                                "        };"
                                + "Calcule lo siguiente: Matriz A / Matriz B" + "</td>\n";
                    }else if(countImpresionPD == 2) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: \n" +
                                "        Matriz A = {\n" +
                                "                {1,12,9,8},\n" +
                                "                {7,6,3,2},\n" +
                                "                {0,5,6,14},\n" +
                                "                {6,9,6,10},\n" +
                                "        };\n" +
                                "        Matriz B = {\n" +
                                "                {8,19,20,4},\n" +
                                "                {12,33,6,8},\n" +
                                "                {4,5,9,7},\n" +
                                "                {8,22,14,6},\n" +
                                "        };"
                                + "Calcule lo siguiente: Matriz A / Matriz B" + "</td>\n";
                    }else if(countImpresionPD == 3) {
                        texto += "   \t\t<td>"+"Segun los siguientes datos: \n" +
                                "        Matriz A = {\n" +
                                "                {5,9,14,5},\n" +
                                "                {6,0,5,3},\n" +
                                "                {1,14,68,8},\n" +
                                "                {7,5,3,9},\n" +
                                "        };\n" +
                                "        Matriz B = {\n" +
                                "                {0,9,7,19},\n" +
                                "                {2,30,5,48},\n" +
                                "                {1,31,2,5},\n" +
                                "                {15,8,4,3},\n" +
                                "        };"
                                + "Calcule lo siguiente: Matriz A / Matriz B" + "</td>\n";
                    }

                    if(countImpresionPD == 1) {
                        texto += "    \t<td>" +" \n" +
                                "Para poder dividir matrices, éstas deben tener el determinante distinto de cero. " +
                                "En este caso el determinante de la matriz B es: 7672.00 por lo que tiene solucion" +
                                "Se procede a hacer la inversa de la matriz B por el metodo de Jordan, " +
                                "Por ultimo se multiplica la matriz A por la inversa de la matriz B " + " </td>\t\n";
                    }else if(countImpresionPD == 2) {
                        texto += "    \t<td>" +" \n" +
                                "Para poder dividir matrices, éstas deben tener el determinante distinto de cero. " +
                                "En este caso el determinante de la matriz B es: -3120.0 por lo que tiene solucion" +
                                "Se procede a hacer la inversa de la matriz B por el metodo de Jordan, " +
                                "Por ultimo se multiplica la matriz A por la inversa de la matriz B " + " </td>\t\n";
                    }else if(countImpresionPD == 3) {
                        texto += "    \t<td>" +" \n" +
                                "Para poder dividir matrices, éstas deben tener el determinante distinto de cero. " +
                                "En este caso el determinante de la matriz B es: -104955.0 por lo que tiene solucion" +
                                "Se procede a hacer la inversa de la matriz B por el metodo de Jordan, " +
                                "Por ultimo se multiplica la matriz A por la inversa de la matriz B " + " </td>\t\n";
                    }

                    if (countImpresionPD == 1) {
                        texto += "    \t<td>" + " Matriz A / Matriz B = {\n" +
                                "                {-3.912,6.386,-0.111,0.77},\n" +
                                "                {-8.027,12.319,-0.154,1.534},\n" +
                                "                {7.61,-9.704,0.159,-0.898},\n" +
                                "                {4.379,-5.215,0.056,-0.439},\n" +
                                "        };"+  "</td>\n";
                    }else if (countImpresionPD == 2) {
                        texto += "    \t<td>" + " Matriz A / Matriz B = {\n" +
                                "                {-2.946,-1.675,-0.075,5.60},\n" +
                                "                {2.574,1.769,0.921,-4.818},\n" +
                                "                {-3.889,-1.81,1.102,6.033},\n" +
                                "                {-0.231,0.388,1.36,-0.297},\n" +
                                "        }; "+  "</td>\n";
                    }else if (countImpresionPD == 3) {
                        texto += "    \t<td>" + " Matriz A / Matriz B = La operacion" +
                                "no ha podido realizarse debido a que la matriz B no tiene inversa" +
                                "segun el metodo de Gauss Jordan, debido a que cuenta con un creo en " +
                                "su posicion [0][0], cualquier numero operado con cero es cero o no existe" +
                                "por lo tanto la operacion no puede resolverse."+  "</td>\n";
                    }

                    countImpresionPD++;
                    }while (countImpresionPD <= 2);

                    texto+=
                "\n" +
                "  \t\t</tr>\n" +
                "\t</table>\n" +
                "</div>\n"+
                "\n" +
                "</body>\n" +
                "</html>";

        try {
            fichero = new FileWriter(ruta + "\\Reporte1.html");
            pw = new PrintWriter(fichero);

            pw.println(texto);

            System.out.printf("Su reporte 1 se ha generado en la ruta: " + ruta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void generarReportes2() {

        System.out.println("Ingrese la ruta donde desea guardar su reporte 2");
        String ruta = new Scanner(System.in).nextLine();


        FileWriter fichero = null;
        PrintWriter pw = null;

        texto2 = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<title>Reporte 2</title>\n" +
                "\n" +
                "\t<style type=\"text/css\">\n" +
                "\n" +
                "\tbody{\n" +
                "\t\tmargin: 10;\n" +
                "\t\tfont-family: Papyrus;\n" +
                "\t\tbackground-color: #cfd4d1;\n" +
                "\n" +
                "\t}\n" +
                "\t.toppage{\n" +
                "\t\toverflow: hidden;\n" +
                "\t\tbackground-color:#eb492d ;\n" +
                "\t\ttext-align: center;\n" +
                "\t\tfont-family: Papyrus;\n" +
                "\t}\n" +
                "\n" +
                "\t.tabla{\n" +
                "\n" +
                "\t\tborder-collapse: collapse;\n" +
                "\t\twidows: 50%;\n" +
                "\t\ttext-align: left;\n" +
                "\t\tfont-family: Papyrus;\n" +
                "\t}\n" +
                "\n" +
                "\tth, td{\n" +
                "\n" +
                "\t\tfont-family: Papyrus;\n" +
                "\t\tborder: 1px solid #000;\n" +
                "\t\ttext-align: center;\n" +
                "\t\tpadding: 8px;\n" +
                "\t}\n" +
                "\t\t\n" +
                "\t</style>\n" +
                "\t}\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "\t<div class=\"toppage\" align=\"center\">\n" +
                "\t\t<br><br>\n" +
                "\t\t<h1>Reporte 2 \"Escaleras Matematicas\"</h1>\n" +
                "\t\t<p>Bitacora de acciones</p>\n" +
                "\n" +
                "\t</div><br><br>\n" +
                "\n" +
                "<div class=\"tabla\" align=\"center\" style=\"text_align: center;\">\n" +
                "\t<table align=\"center\">\n" +
                "\t\t<tr>\n" +
                "\t\t<th  colspan = \"2\" style=\"background-color: #404035; color: white;\">Recuento general de acciones</th>\n" +
                "\n" +
                "\t\t<tr>\n" +
                "    \t<th> No. </th>\n" +
                "    \t<th> Accion: </th>\n" +
                " \t\t</tr>\n" +
                "\n"+

                texto2;

                texto2 +=
                "\n" +
                "\t</table>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        try {
            fichero = new FileWriter(ruta + "\\Reporte2.html");
            pw = new PrintWriter(fichero);

            pw.println(texto2);



            System.out.printf("Su reporte 2 se ha generado en la ruta: " + ruta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}


