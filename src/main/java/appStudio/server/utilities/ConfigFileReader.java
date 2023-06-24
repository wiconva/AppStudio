package appStudio.server.utilities;

import java.io.*;

public class ConfigFileReader {
    /*Clase para Listar todos los archivos dentro del proyecto, leer el archivo indicado y devolver el valor
    * para la clave dada. */
    private File file = null;

    /*Method: para devolver el valor de la clave buscada dentro de un archivo en función del nombre del archivo y
    * la llave objetivo.*/
    public String getKeyValue(String fileName, String key){
        String line = null;
        findPathFile(new File(System.getProperty("user.dir")),fileName);
        if(this.file != null) {
            try {
                FileReader fr = new FileReader(this.file);
                BufferedReader br = new BufferedReader(fr);

                while ((line = br.readLine()) != null) {
                    if (line.split("===")[0].contains(key)) {
                        line = line.split("===")[1];
                        return line;
                    }
                }
                System.out.println("Entry no find.");
            } catch (FileNotFoundException e) {
                System.out.println("Somethings go wrong with the file reader.");
            } catch (IOException e) {
                System.out.println("Somethings go wrong when read the file.");
            }
        }else {
            System.out.println("File not Exists.");
        }
        return null;
    }

    /*Método para listar todos los archivos y asignar el path del archivo objetivo a la variable file
    * de la clase según la carpeta y nombre de archivo especificado.*/
    private void findPathFile(File folder, String fileName){
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                if (fileName.equals(file.getName())){
                    this.file = file;
                }
            } else {
                findPathFile(file,fileName);
            }
        }
    }
}
