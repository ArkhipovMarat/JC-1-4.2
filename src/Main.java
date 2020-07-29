import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(1, 1, 1, 1);
        GameProgress gameProgress2 = new GameProgress(2, 2, 2, 2);
        GameProgress gameProgress3 = new GameProgress(3, 3, 3, 3);

        String[] gamesaves = {"C://Games/savedgames/save1.dat",
                "C://Games/savedgames/save2.dat",
                "C://Games/savedgames/save3.dat"};

        saveGameProgress("C://Games/savedgames/save1.dat", gameProgress1);
        saveGameProgress("C://Games/savedgames/save2.dat", gameProgress2);
        saveGameProgress("C://Games/savedgames/save3.dat", gameProgress3);

        zipFiles("C://Games/savedgames", gamesaves);

        deleteSaves(gamesaves);
    }


    public static void saveGameProgress(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String filePath, String[] gameSaves) {
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("C://Games/savedgames/saves.zip"))) {
                for (String gamesave : gameSaves) {
                    FileInputStream fis = new FileInputStream(gamesave);
                    ZipEntry entry = new ZipEntry(gamesave);
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[(fis.available())];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void deleteSaves (String[] gameSaves) {
        for (String gamesave : gameSaves) {
            File file = new File (gamesave);
            if (file.exists()) file.delete();
        }
    }


}
