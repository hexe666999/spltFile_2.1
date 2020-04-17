package ru.performanceLab;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        long m = System.currentTimeMillis();

        if (args.length != 2) {
            System.out.println("Введено неправильное количество аргументов.");
        } else {
            splitFile(new File("main.log.2014-11-17.TXT"), args[0], Integer.parseInt(args[1]));
        }
        System.out.println((double) (System.currentTimeMillis() - m));
    }

    private static void splitFile(File file, String fileName, int rowCount) {


        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            for (int countFiles = 0; ; countFiles++) {
                System.out.println("Запись файла №" + countFiles + ".");
                String name = fileName + countFiles + ".txt";
                String line;
                StringBuilder strBuffer = new StringBuilder();
                try {
                    for (int i = 0; i < rowCount; i++) {
                        if ((line = reader.readLine()) != null) {
                            strBuffer.append(line).append(" \r\n");
                        } else {
                            FileWriter writer = new FileWriter(name, false);
                            writer.write(strBuffer.toString());
                            writer.flush();
                            writer.close();
                            System.out.println("Запись закончена");
                            return;
                        }
                    }
                    FileWriter writer = new FileWriter(name, false);
                    writer.write(strBuffer.toString());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}