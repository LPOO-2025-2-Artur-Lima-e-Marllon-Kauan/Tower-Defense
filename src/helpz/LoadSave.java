package helpz;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import objects.PathPoint;

public class LoadSave {

    // Carrega o sprite atlas
    public static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res/spriteatlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    // Cria arquivo de teste
    public static void CreateFile() {
        File dir = new File(System.getProperty("user.dir"), "res");
        if (!dir.exists()) dir.mkdirs();

        File txtFile = new File(dir, "testTextFile.txt");
        try {
            txtFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cria um novo nível
    public static void CreateLevel(String name, int[] idArr) {
        File dir = new File(System.getProperty("user.dir"), "res");
        if (!dir.exists()) dir.mkdirs();

        File newLevel = new File(dir, name + ".txt");

        if (newLevel.exists()) {
            System.out.println("File: " + name + " already exists!");
        } else {
            try {
                newLevel.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Escreve IDs e start/end padrão
            WriteToFile(newLevel, idArr, new PathPoint(0, 0), new PathPoint(0, 0));
        }
    }

    // Salva o nível existente
    public static void SaveLevel(String name, int[][] idArr, PathPoint start, PathPoint end) {
        File dir = new File(System.getProperty("user.dir"), "res");
        File levelFile = new File(dir, name + ".txt");

        if (levelFile.exists()) {
            WriteToFile(levelFile, Utilz.TwoDto1DintArr(idArr), start, end);
        } else {
            System.out.println("File: " + name + " does not exist!");
        }
    }

    // Escreve IDs e pontos no arquivo
    private static void WriteToFile(File f, int[] idArr, PathPoint start, PathPoint end) {
        try (PrintWriter pw = new PrintWriter(f)) {
            for (int i : idArr) {
                pw.println(i);
            }
            // Start e End no final do arquivo
            pw.println(start.getxCord());
            pw.println(start.getyCord());
            pw.println(end.getxCord());
            pw.println(end.getyCord());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Lê todos os números de um arquivo
    private static ArrayList<Integer> ReadFromFile(File file) {
        ArrayList<Integer> list = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    list.add(Integer.parseInt(line));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Retorna os pontos start e end do nível
    public static ArrayList<PathPoint> GetLevelPathPoints(String name) {
        File dir = new File(System.getProperty("user.dir"), "res");
        File lvlFile = new File(dir, name + ".txt");
        ArrayList<PathPoint> points = new ArrayList<>();

        if (!lvlFile.exists()) {
            System.out.println("File: " + name + " does not exist! Using default points");
            points.add(new PathPoint(0, 0));
            points.add(new PathPoint(0, 0));
            return points;
        }

        ArrayList<Integer> list = ReadFromFile(lvlFile);

        if (list.size() >= 4) {
            int size = list.size();
            int startX = list.get(size - 4);
            int startY = list.get(size - 3);
            int endX = list.get(size - 2);
            int endY = list.get(size - 1);

            points.add(new PathPoint(startX, startY));
            points.add(new PathPoint(endX, endY));
        } else {
            System.out.println("⚠️ File " + name + " does not have enough points, using default");
            points.add(new PathPoint(0, 0));
            points.add(new PathPoint(0, 0));
        }

        return points;
    }

    // Retorna os dados do nível como matriz 2D
    public static int[][] GetLevelData(String name) {
        File dir = new File(System.getProperty("user.dir"), "res");
        File lvlFile = new File(dir, name + ".txt");

        if (!lvlFile.exists()) {
            System.out.println("File: " + name + " does not exist! Returning empty level");
            return new int[20][20]; // Retorna matriz vazia
        }

        ArrayList<Integer> list = ReadFromFile(lvlFile);
        return Utilz.ArrayListTo2Dint(list, 20, 20);
    }
}
