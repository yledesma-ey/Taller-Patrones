package org.ey.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class StringFileReader {
    /**Devuelve el texto contenido en un archivo.
     */
    static String getFileAsString(String path){
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Archivo no encontrado");
        }

    }
}
