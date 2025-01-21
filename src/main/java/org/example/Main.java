package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans la calculatrice !");
        System.out.print("Entrez le premier nombre : ");
        int num1 = scanner.nextInt();

        System.out.print("Entrez le deuxième nombre : ");
        int num2 = scanner.nextInt();

        int result = calculator.add(num1, num2);
        System.out.println("La somme de " + num1 + " et " + num2 + " est : " + result);
    }
}