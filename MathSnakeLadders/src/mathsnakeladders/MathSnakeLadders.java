package mathsnakeladders;

import static java.lang.Math.*;
import java.util.Random;
import java.util.Scanner;

public class MathSnakeLadders {

    //scanner & inputs
    static Scanner scanner = new Scanner(System.in);
    static char read = 0;

    //random tool
    static Random randomTool = new Random();

    //board vars
    public static int[][] mainBoard = new int[8][8]; //main board
    public static int[][] secondaryBoard = new int[8][8]; //sec board
    public static boolean[][] penaltyBoard = new boolean[8][8]; //logic penalty locations board

    //player vars
    static int playerPositon, move;
    static boolean penalized;

    //penalty counters
    static int easyPenaltyCounter = 0;
    static int mediumPenaltyCounter = 0;
    static int hardPenaltyCounter = 0;

    //operations vars
    static double pivot, aux, result;
    static double factor, fc1, fc2, fc3, fc4, det;
    static double sideA, sideB, sideC;
    static double alpha, beta, gamma;

    public static void main(String[] args) {
        menu();
    }

    //game menu
    public static void menu() {

        do {
            System.out.println("----------------------------------");
            System.out.println("==== MATH SNAKE LADDERS =====");
            System.out.println("========= MAIN MENU =========");
            System.out.println("1. Play");
            System.out.println("2. Resume");
            System.out.println("3. Exit");
            System.out.println("----------------------------------");

            read = scanner.next().charAt(0);

            switch (read) {
                case '1':
                    modInit();
                    play();
                    break;
                case '2':
                    play();
                    break;
                case '3':
                    System.out.println("======> GAME OVER <======");
                    break;
                default:
                    System.out.println("Insert a number between 1 & 3");
                    break;
            }
        } while (read != '3');
    }

    public static void modInit() {
        //restart counters
        easyPenaltyCounter = 0;
        mediumPenaltyCounter = 0;
        hardPenaltyCounter = 0;

        playerPositon = 0;  //restart player position

        //fill main board
        int counter = 64;
        for (int i = 0; i < mainBoard.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < mainBoard[i].length; j++) {
                    mainBoard[i][j] = counter--;
                }
            } else {
                for (int j = mainBoard[i].length - 1; j >= 0; j--) {
                    mainBoard[i][j] = counter--;
                }
            }
        }

        //fill secondary board
        for (int i = 0; i < secondaryBoard.length; i++) {
            int countpen = 0;
            for (int j = 0; j < secondaryBoard[i].length; j++) {
                if (countpen < 4) {
                    secondaryBoard[i][j] = randomTool.nextInt(2);
                    if (secondaryBoard[i][j] == 1) {
                        countpen++;
                    }
                }
            }
        }

        //fill penalty board
        for (int i = 0; i < secondaryBoard.length; i++) {
            for (int j = 0; j < secondaryBoard[i].length; j++) {
                if (secondaryBoard[i][j] == 1) {
                    penaltyBoard[i][j] = true;
                } else {
                    penaltyBoard[i][j] = false;
                }
            }
        }

    }

    public static void play() {
        do {
            for (int i = 0; i < mainBoard.length; i++) {
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                for (int j = 0; j < mainBoard[i].length; j++) {
                    if (playerPositon != mainBoard[i][j] && !penaltyBoard[i][j]) {
                        System.out.print("[\t" + mainBoard[i][j] + "\t]");
                    }
                    if (playerPositon == mainBoard[i][j] && penaltyBoard[i][j]) {
                        System.out.print("[\t" + mainBoard[i][j] + "#@" + "\t]");
                        penalized = true;
                    } else if (playerPositon == mainBoard[i][j]) {
                        System.out.print("[\t" + mainBoard[i][j] + "@" + "\t]");
                    } else if (penaltyBoard[i][j]) {
                        System.out.print("[\t" + mainBoard[i][j] + "#" + "\t]");
                    }
                }
                System.out.println();
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

            //if player is penalized check type and sort penalty
            if (penalized) {
                if (playerPositon >= mainBoard[2][7] && playerPositon <= mainBoard[0][0]) {

                    if (hardPenaltyCounter <= 2) {
                        aux = randomTool.nextInt(3) + 1;

                        if (aux == 1) {
                            hardPenalty(1);
                            hardPenaltyCounter += 1;
                        } else if (aux == 2) {
                            hardPenalty(3);
                            hardPenaltyCounter += 1;
                        } else if (aux == 3) {
                            hardPenalty(2);
                            hardPenaltyCounter += 1;
                        }
                    } else {
                        System.out.println("There will be no more hard penalties.");
                    }

                } else if (playerPositon <= mainBoard[3][7] && playerPositon >= mainBoard[5][0]) {
                    if (mediumPenaltyCounter <= 2) {
                        aux = randomTool.nextInt(3) + 1;

                        if (aux == 1) {
                            mediumPenalty(1);
                            mediumPenaltyCounter += 1;
                        } else if (aux == 2) {
                            mediumPenalty(3);
                            mediumPenaltyCounter += 1;
                        } else if (aux == 3) {
                            mediumPenalty(2);
                            mediumPenaltyCounter += 1;
                        }
                    } else {
                        System.out.println("There will be no more medium penalties.");
                    }
                } else if (playerPositon >= mainBoard[7][0] && playerPositon <= mainBoard[6][0]) {
                    if (easyPenaltyCounter != 2) {
                        aux = randomTool.nextInt(3) + 1;

                        if (aux == 1) {
                            easyPenalty1();
                            easyPenaltyCounter += 1;
                        } else if (aux == 2) {
                            easyPenalty3();
                            easyPenaltyCounter += 1;
                        } else if (aux == 3) {
                            easyPenalty2();
                            easyPenaltyCounter += 1;
                        }
                    } else {
                        System.out.println("There will be no more easy penalties.");
                    }
                }
                penalized = false;
            }

            if (playerPositon >= 64) {
                playerPositon = 64;
                System.out.println("You have won !");
                menu();
            } else {
                dice();
            }

        } while (read != 'p');
    }

    //dice & player movement
    public static void dice() {
        System.out.println();
        System.out.println(" > press '1' to roll the die");
        System.out.println(" > press 'p' to return to main menu");

        read = scanner.next().charAt(0);

        if (read == '1') {
            move = (int) (Math.random() * 4) + 2;

            System.out.println(" > you rolled a: " + move);
            if (move != 0) {
                playerPositon = playerPositon + move;
            }
            System.out.println(" > your current position is: " + playerPositon);
        }
        System.out.println();
    }

    //penaltys
    public static void hardPenalty(int type) {
        System.out.println();
        System.out.println("=============================================================================");
        System.out.println("You have landed on a Hard penalty");
        System.out.println("on the square: " + playerPositon);

        double[][] ma = new double[4][4];
        double[][] mb = new double[4][4];

        if (type == 1) {
            System.out.println();
            System.out.println("=============================================================================");
            System.out.println("Option 1 -> Difficulty level: Hard");
            System.out.println("Perform the following operation: Matrix A / Matrix B");

            ma = new double[][]{
                {5, 10, 1, 3},
                {9, 14, 2, 6},
                {7, 8, 15, 3},
                {6, 8, 9, 2}
            };
            mb = new double[][]{
                {5, 13, 9, 4},
                {1, 9, 6, 3},
                {8, 11, 69, 33},
                {25, 6, 7, 4}
            };
        } else if (type == 2) {
            System.out.println();
            System.out.println("=============================================================================");
            System.out.println("Option 2 -> Difficulty level: Hard");
            System.out.println("Perform the following operation: Matrix A / Matrix B");

            ma = new double[][]{
                {1, 12, 9, 8},
                {7, 6, 3, 2},
                {0, 5, 6, 14},
                {6, 9, 6, 10}
            };

            mb = new double[][]{
                {8, 19, 20, 4},
                {12, 33, 6, 8},
                {4, 5, 9, 7},
                {8, 22, 14, 6}
            };

        } else if (type == 3) {
            System.out.println();
            System.out.println("=============================================================================");
            System.out.println("Option 3 -> Difficulty level: Hard");
            System.out.println("Perform the following operation: Matrix A / Matrix B");

            ma = new double[][]{
                {5, 9, 14, 5},
                {6, 0, 5, 3},
                {1, 14, 68, 8},
                {7, 5, 3, 9}
            };

            mb = new double[][]{
                {0, 9, 7, 19},
                {2, 30, 5, 48},
                {1, 31, 2, 5},
                {15, 8, 4, 3}
            };
        }
        
        System.out.println();
        //print matrix A
        System.out.println("Matrix A: ");
        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma[i].length; j++) {
                System.out.print("|\t" + ma[i][j] + "\t| ");
            }
            System.out.println();
        }

        //print matrix B
        System.out.println("Matrix B: ");
        for (int i = 0; i < mb.length; i++) {
            for (int j = 0; j < mb[i].length; j++) {
                System.out.print("|\t" + mb[i][j] + "\t| ");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("SOLUTION: Matrix A / Matrix B");
        System.out.println();
        System.out.println("First, you must calculate the determinant of the denominator matrix");
        System.out.println("NOTE: If the determinant is zero, the operation A/B has no solution");
        System.out.println();

        //determinant calc
        factor = ((mb[1][1] * (mb[2][2] * mb[3][3] - mb[3][2] * mb[2][3]))
                + (-1 * mb[1][2] * (mb[2][1] * mb[3][3] - mb[3][1] * mb[2][3]))
                + (mb[1][3] * (mb[2][1] * mb[3][2] - mb[3][1] * mb[2][2])));
        fc1 = factor * mb[0][0];

        factor = ((mb[1][0] * (mb[2][2] * mb[3][3] - mb[3][2] * mb[2][3]))
                + (-1 * mb[1][2] * (mb[2][0] * mb[3][3] - mb[3][0] * mb[2][3]))
                + (mb[1][3] * (mb[2][0] * mb[3][2] - mb[3][0] * mb[2][2])));
        fc2 = factor * (-1 * mb[0][1]);

        factor = ((mb[1][0] * (mb[2][1] * mb[3][3] - mb[3][1] * mb[2][3]))
                + (-1 * mb[1][1] * (mb[2][0] * mb[3][3] - mb[3][0] * mb[2][3]))
                + (mb[1][3] * (mb[2][0] * mb[3][1] - mb[3][0] * mb[2][1])));
        fc3 = factor * mb[0][2];

        factor = ((mb[1][0] * (mb[2][1] * mb[3][2] - mb[3][1] * mb[2][2]))
                + (-1 * mb[1][1] * (mb[2][0] * mb[3][2] - mb[3][0] * mb[2][2]))
                + (mb[1][2] * (mb[2][0] * mb[3][1] - mb[3][0] * mb[2][1])));
        fc4 = factor * (-1 * mb[0][3]);

        det = Math.round((fc1 + fc2 + fc3 + fc4) * 1000.0) / 1000.0;

        System.out.println("The determinant of matrix B is: " + det);
        System.out.println();

        if (det != 0) {

            System.out.println("As the determinant is nonzero, the inverse matrix exists");
            System.out.println("We proceed with the Gauss-Jordan elimination method");
            System.out.println();

            //Calculation of the identity matrix
            double[][] mi = new double[4][4];  //inverse matrix

            System.out.println("Matriz B: ");
            for (int i = 0; i < mb.length; i++) {
                for (int j = 0; j < mb[i].length; j++) {
                    System.out.print("|\t" + mb[i][j] + "\t| ");
                }
                System.out.println();
            }

            //Indentity Matrix
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    mi[i][j] = 0;
                    if (i == j) {
                        mi[i][j] = 1;
                    }
                }
            }
            
            System.out.println();
            System.out.println("Identity matrix: ");
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    System.out.print("|\t" + mi[i][j] + "\t| ");
                }
                System.out.println();
            }

            //row reduction
            for (int i = 0; i < 4; i++) {
                pivot = mb[i][i]; //I am on the diagonal
                //Convert the pivot to 1 and apply the operation to the entire row
                for (int k = 0; k < 4; k++) {
                    mb[i][k] = mb[i][k] / pivot; //convert the pivot to 1
                    mi[i][k] = mi[i][k] / pivot; //apply the same operation to the identity matrix
                }
                for (int j = 0; j < 4; j++) {
                    if (i != j) { //not on the diagonal
                        aux = mb[j][i]; //aux will be the current value of the [i][j] cell to be used later
                        for (int k = 0; k < 4; k++) {
                            mb[j][k] = mb[j][k] - aux * mb[i][k]; //convert the value to zero
                            mi[j][k] = mi[j][k] - aux * mi[i][k]; //apply the same operation to the identity matrix
                        }
                    }
                }
            }
            
            System.out.println();
            System.out.println("Using the row reduction method, we obtain the inverse matrix B: ");
            for (int i = 0; i < mi.length; i++) {
                for (int j = 0; j < mi[i].length; j++) {
                    mi[i][j] = Math.round(mi[i][j] * 1000.0) / 1000.0;
                    System.out.print("|\t" + mi[i][j] + "\t| ");
                }
                System.out.println();
            }
            
            System.out.println();
            System.out.println("Performing the operation: Matrix A * Matrix B (Inverse) ");
            double[][] mtp = new double[4][4];

            //multiplying matrices A*B^-1
            for (int i = 0; i < 4; i++) { //iterate through the rows of A
                for (int j = 0; j < 4; j++) { //Iterate through the columns of B^-1
                    mtp[i][j] = 0;
                    for (int k = 0; k < 4; k++) { //iterate through each element of each row
                        mtp[i][j] = mtp[i][j] + ma[i][k] * mi[k][j]; //filling rows and columns
                    }
                }
            }
            
            System.out.println();
            System.out.println("We obtain the Matrix A/B: ");
            for (int i = 0; i < mtp.length; i++) {
                for (int j = 0; j < mtp[i].length; j++) {
                    mtp[i][j] = Math.round(mtp[i][j] * 1000.0) / 1000.0;
                    System.out.print("|\t" + mtp[i][j] + "\t| ");
                }
                System.out.println();
            }

        } else {
            System.out.println("The determinant does not exist, so the operation cannot be performed");
        }
    }

    public static void mediumPenalty(int type) {
        System.out.println();
        System.out.println("=============================================================================");
        System.out.println("You have landed on a Medium penalty");
        System.out.println("On the square: " + playerPositon);

        double[][] ma = new double[5][5];
        double[][] mb = new double[5][5];

        if (type == 1) {

            System.out.println("=============================================================================");
            System.out.println("Option 1 -> Medium Difficulty Level");
            System.out.println("Perform the following operation: Matrix A + Matrix B");
            System.out.println();

            ma = new double[][]{
                {7, 48, 5, 0, 1},
                {57, 8, 4, 6, 14},
                {0, 5, 6, 78, 15},
                {21, 14, 8, 19, 54},
                {32, 20, 26, 47, 12}
            };
            mb = new double[][]{
                {9, 5, 2, 1, 8},
                {4, 2, 3, 47, 8},
                {48, 55, 32, 19, 6},
                {7, 56, 32, 14, 8},
                {32, 87, 0, 1, 7}
            };
        } else if (type == 2) {

            System.out.println("=============================================================================");
            System.out.println("Option 2 -> Medium Difficulty Level");
            System.out.println("Perform the following operation: Matrix A + Matrix B");
            System.out.println();

            ma = new double[][]{
                {4, 9, 7, 45, 18},
                {7, 51, 26, 8, 38},
                {48, 26, 37, 21, 19},
                {1, 0, 6, 8, 1},
                {2, 19, 55, 25, 15}
            };

            mb = new double[][]{
                {0, 2, 15, 1, 66},
                {21, 48, 62, 7, 33},
                {4, 88, 0, 68, 4},
                {25, 18, 24, 7, 55},
                {24, 15, 36, 5, 98}
            };

        } else if (type == 3) {

            System.out.println("=============================================================================");
            System.out.println("Option 3 -> Medium Difficulty Level");
            System.out.println("Perform the following operation: Matrix A + Matrix B");
            System.out.println();

            ma = new double[][]{
                {0, 1, 15, 5, 36},
                {1, 78, 65, 32, 4},
                {48, 66, 39, 0, 55},
                {14, 98, 63, 20, 15},
                {11, 39, 84, 7, 1}
            };

            mb = new double[][]{
                {78, 25, 66, 48, 98},
                {0, 45, 2, 3, 1},
                {2, 9, 14, 10, 20},
                {35, 87, 65, 2, 32},
                {25, 8, 4, 9, 39}
            };
        }

        //print Matrix A
        System.out.println("Matrix A: ");
        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma[i].length; j++) {
                System.out.print("|\t" + ma[i][j] + "\t| ");
            }
            System.out.println();
        }
        
        //print Matrix B
        System.out.println();
        System.out.println("Matrix B: ");
        for (int i = 0; i < mb.length; i++) {
            for (int j = 0; j < mb[i].length; j++) {
                System.out.print("|\t" + mb[i][j] + "\t| ");
            }
            System.out.println();
        }
        
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("SOLUTION: Matrix A + Matrix B");

        double[][] sum = new double[5][5];
        for (int i = 0; i < ma.length; i++) {
            for (int j = 0; j < ma[i].length; j++) {
                sum[i][j] = ma[i][j] + mb[i][j];
            }
        }
        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum[i].length; j++) {
                System.out.print("|\t" + sum[i][j] + "\t| ");
            }
            System.out.println();
        }
    }

    public static void easyPenalty1() {
        System.out.println();
        System.out.println("=============================================================================");
        System.out.println("You have landed on an Easy penalty");
        System.out.println("On the square: " + playerPositon);

        //---------------FIRST OPERATION------------------
        System.out.println("=============================================================================");
        System.out.println("Option 1 -> Easy Difficulty Level");
        System.out.println("According to the following data: Side A = 15, Side C = 20, Angle Alpha = 25 degrees");
        System.out.println("Calculate the following: Side B = ?, Angle Beta = ?, Angle Gamma = ?");
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("SOLUTION: ");

        sideA = 15.0;
        sideC = 20.0;
        alpha = 25.0;

        //Calculation of side B
        aux = ((sideA * sideA + sideC * sideC) - (2 * sideA * sideC * cos(Math.toRadians(alpha))));
        sideB = Math.sqrt(aux);
        result = Math.round(sideB * 1000.0) / 1000.0;
        System.out.println("Side B is: " + result);

        //Calculation of angle beta
        aux = (((sideA * sideA) - (sideB * sideB) - (sideC * sideC)) / (-(2 * sideB * sideC)));
        beta = Math.toDegrees(acos(aux));
        result = Math.round(beta * 1000.0) / 1000.0;
        System.out.println("Angle Beta is: " + result);

        //Calculation of angle gamma
        aux = (((sideC * sideC) - (sideA * sideA) - (sideB * sideB)) / (-(2 * sideA * sideB)));
        gamma = Math.toDegrees(acos(aux));
        result = Math.round(gamma * 1000.0) / 1000.0;
        System.out.println("Angle Gamma is: " + result);
        System.out.println("=============================================================================");
    }

    public static void easyPenalty2() {
        System.out.println();
        System.out.println("=============================================================================");
        System.out.println("You have landed on an Easy penalty");
        System.out.println("On the square: " + playerPositon);

        //---------------SECOND OPERATION------------------
        System.out.println("=============================================================================");
        System.out.println("Option 2 -> Easy Difficulty Level");
        System.out.println("According to the following data: Side B = 10, Side C = 25, Angle Beta = 30 degrees");
        System.out.println("Calculate the following: Side A = ?, Angle Alpha = ?, Angle Gamma = ?");
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("SOLUTION: ");

        sideB = 10.0;
        sideC = 25.0;
        beta = 30.0;

        //Calculation of side A
        aux = ((sideB * sideB + sideC * sideC) - (2 * sideB * sideC * cos(Math.toRadians(beta))));
        sideA = Math.sqrt(aux);
        result = Math.round(sideA * 1000.0) / 1000.0;
        System.out.println("Side A is: " + result);

        //Calculation of angle alpha
        aux = (((sideB * sideB) - (sideA * sideA) - (sideC * sideC)) / (-(2 * sideA * sideC)));
        alpha = Math.toDegrees(acos(aux));
        result = Math.round(alpha * 1000.0) / 1000.0;
        System.out.println("Angle Alpha is: " + result);

        //Calculation of angle gamma
        aux = (((sideC * sideC) - (sideA * sideA) - (sideB * sideB)) / (-(2 * sideA * sideB)));
        gamma = Math.toDegrees(acos(aux));
        result = Math.round(gamma * 1000.0) / 1000.0;
        System.out.println("Angle Gamma is: " + result);
        System.out.println("=============================================================================");
    }

    public static void easyPenalty3() {
        System.out.println();
        System.out.println("=============================================================================");
        System.out.println("You have landed on an Easy penalty");
        System.out.println("On the square: " + playerPositon);
        //---------------THIRD OPERATION------------------

        System.out.println("=============================================================================");
        System.out.println("Option 3 -> Easy Difficulty Level");
        System.out.println("According to the following data: Side A = 18, Side B = 25, Angle Gamma = 30 degrees");
        System.out.println("Calculate the following: Side C = ?, Angle Alpha = ?, Angle Beta = ?");
        System.out.println("-----------------------------------------------------------------------------");

        System.out.println("SOLUTION: ");

        sideA = 18.0;
        sideB = 25.0;
        gamma = 30.0;

        //Calculation of side C
        aux = ((sideA * sideA + sideB * sideB) - (2 * sideA * sideB * cos(Math.toRadians(gamma))));
        sideC = Math.sqrt(aux);
        result = Math.round(sideC * 1000.0) / 1000.0;
        System.out.println("Side C is: " + result);

        //Calculation of angle alpha
        aux = (((sideB * sideB) - (sideA * sideA) - (sideC * sideC)) / (-(2 * sideA * sideC)));
        alpha = Math.toDegrees(acos(aux));
        result = Math.round(alpha * 1000.0) / 1000.0;
        System.out.println("Angle Alpha is: " + result);

        //Calculation of angle beta
        aux = (((sideA * sideA) - (sideB * sideB) - (sideC * sideC)) / (-(2 * sideB * sideC)));
        beta = Math.toDegrees(acos(aux));
        result = Math.round(beta * 1000.0) / 1000.0;
        System.out.println("Angle Beta is: " + result);
        System.out.println("=============================================================================");
    }

}
