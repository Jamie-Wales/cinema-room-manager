package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        menu(generateSeats());
    }


    public static void menu(String[][] seats) {
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a tickets");
            System.out.println("3. Statistics");
            System.out.println("4. Calculate profit");
            System.out.println("0. Exit");
            int input = scanner.nextInt();


            switch (input) {
                case 1:
                    print(seats);
                    break;
                case 2:
                    tickets(seats);
                    break;
                case 3:
                    statistics(seats);
                    break;
                case 4:
                    calculateProfit(seats);
                    break;
                case 0:
                    return;
                default:
                    System.out.print("Please enter valid input.");
                    break;
            }

        } while (condition);


    }


    public static void print(String[][] seats) {
        System.out.println("Cinema:");
        for (int i = 0; i < seats.length; i++) {
            if (i != 0) {
                System.out.println();
            }
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
        }

    }

    public static String[][] generateSeats() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int sizeY = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int sizeX = scanner.nextInt();
        String[][] seats = new String[sizeY + 1][sizeX + 1];

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (j == 0 && i == 0) {
                    seats[i][j] = " ";
                } else if (i == 0) {
                    seats[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    seats[i][j] = Integer.toString(i);
                } else {
                    seats[i][j] = "S";
                }
            }
        }

        return seats;

    }

    public static void calculateProfit(String[][] seats) {
        int y = seats.length - 1;
        int x = seats[y].length - 1;
        int total = y * x;
        if (total < 60) {
            System.out.println("Total profit:");
            System.out.println("$" + (total * 10));
        } else {
            double row1 = y / 2;
            double row2;
            double profit = row1 * x * 10;
            if (total % 2 != 0) {
                row2 = y / 2 + 1;
            } else {
                row2 = y / 2;
            }
            profit += y * x * 8;
            System.out.println("Total profit:");
            System.out.print("$" + profit);

        }
    }


    public static void tickets(String[][] seats) {
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;

        while (condition) {
            System.out.println();
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();
            System.out.println();

            int sizeY = seats.length - 1;
            int sizeX = seats[sizeY].length - 1;

            if (sizeY < rowNumber || sizeX < seatNumber || seatNumber < 0 || rowNumber < 0) {
                System.out.println("Wrong input!");
                continue;
            }

            if ("B".equals(seats[rowNumber][seatNumber])) {
                System.out.println("That ticket has already been purchased!");
            } else {
                seats[rowNumber][seatNumber] = "B";
                print(seats);
                int total = sizeY * sizeX;
                if (total < 60) {
                    System.out.println();
                    System.out.println("Ticket price:");
                    System.out.println("$10");
                    break;
                } else {
                    int row1 = sizeY / 2;
                    System.out.println();
                    System.out.print("Ticket price:");
                    System.out.println(rowNumber > row1 ? "$8" : "$10");
                    break;
                }

            }
        }

    }


    public static void statistics(String[][] seats) {

        int y = seats.length - 1;
        int x = seats[y].length - 1;
        int row1Count = 0;
        int row2Count = 0;
        int row1 = y / 2;
        int total = y * x;


        if (total < 60) {
            int income = total * 10;
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    if ("B".equals(seats[i][j]) && i > row1) {
                        row2Count++;
                    } else if ("B".equals(seats[i][j]) && i < row1) {
                        row1Count++;
                    }
                }
            }

            double percentage = (row1Count + row2Count) / total * 100;

            System.out.printf("Number of purchased tickets %d \n", row1Count + row2Count);
            System.out.printf("Percentage %.2f%% \n", percentage);
            System.out.printf("Current income $%d \n", row1Count * 10 + row2Count * 8);
            System.out.printf("Total income $%d \n", income);
        } else {
            int row2;
            int income = row1 * x * 10;

            if (total % 2 != 0) {
                row2 = y / 2 + 1;


            } else {
                row2 = y / 2;

            }

            income += row2 * x * 8;

            for (int i = 1; i <= y; i++) {
                for (int j = 1; j <= x; j++) {
                    if ("B".equals(seats[i][j]) && i > row1) {
                        row2Count++;
                    } else if ("B".equals(seats[i][j]) && i <= row1) {
                        row1Count++;
                    }
                }
            }

            double percentage;
            if (row1Count + row2Count == 0) {
                percentage = 0;
            } else {
                percentage = (row1Count + row2Count) * 100.0 / total;
            }
            System.out.printf("Number of purchased tickets %d \n", row1Count + row2Count);
            System.out.printf("Percentage %.2f%% \n", percentage);
            System.out.printf("Current income $%d \n", row1Count * 10 + row2Count * 8);
            System.out.printf("Total income $%d \n", income);


        }

    }
}