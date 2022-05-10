package br.cefsa.edu.leo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Leo {

    private static String inicio = "<!DOCTYPE html><html><body><svg height=\"2160\" width=\"3840\">";
    private static String fim = "</svg></body></html>";
    private static String conteudo = "";

    private static int x1;
    private static int y1;
    private static int x2;
    private static int y2;


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("A gramática aceita apenas os caracteres L");
        Boolean repete = false;

        do {

            x1 = 0;
            y1 = 0;
            x2 = 0;
            y2 = 0;

            System.out.println("local do arquivo:");
            String caminho = System.console().readLine();

            Path path = Paths.get(caminho);
            List<String> lista = Files.readAllLines(path, StandardCharsets.UTF_8);

            if (lista.isEmpty()) {
                System.out.println("arquivo invalido.");
            } else {
                acertaTxt(lista);
                repete = false;
                criaArquivo();
                System.out.println("Arquivo ok.");
            }
        } while (repete);

        scanner.close();
    }

    static void acertaTxt(List<String> list) {
        for (String line : list) {
            line = line.trim();
            char[] caracteres = line.toUpperCase().toCharArray();

            for (char carac : caracteres) {
                if (carac != 'L') {
                    System.out.println("incorreto, favor arrumar.");
                }
            }
            gerar(line);
            System.out.println(line);
        }
    }

    private static void criaArquivo() {
        Path path = Paths.get("linha muito reta.html");
        String arquivoConteudo = inicio + conteudo + fim;
        byte[] bytes = arquivoConteudo.getBytes();

        try {
            Files.write(path, bytes);
        } catch (IOException exception) {
            System.out.println("ERROR 404, arquivo not found");
        }
    }

    private static void gerar(String aux) {
        int desloca = x2;
        for (char a : aux.toCharArray()) {
            if (a == 'L') {
                x1 = x2;
                y1 = y2;
                x2 += geraAleatorio();
                y2 += geraAleatorio();

                geraLinha();
            }

        }
    }

    private static int geraAleatorio() {
        Random random = new Random();
        return random.nextInt(100 - (-100 - 1) + -100);
    }

    private static void geraLinha() {
        String forma = "<line ";
        forma += "x1=\"" + x1 + "\" ";
        forma += "x2=\"" + x2 + "\" ";
        forma += "y1=\"" + y1 + "\" ";
        forma += "y2=\"" + y2 + "\" ";
        forma += "style=\"stroke:rgb(122, 255, 61);stroke-width:5\" />\"";
        conteudo += forma;
    }

}