package com.pridepoints.api.utilities.download;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
public class DownloadCSV<T> {
    public void exportToCSV(List<T> entidade, String nomeDoArquivo) throws IOException {
        try (FileWriter csvWriter = new FileWriter(nomeDoArquivo)) {
            if (!entidade.isEmpty()) {
                // Obtenha os nomes das colunas a partir da primeira linha (um objeto da lista)
                T firstItem = entidade.get(0);
                Field[] fields = firstItem.getClass().getDeclaredFields();

                // Escreva os nomes das colunas como cabeçalho
                for (Field field : fields) {
                    csvWriter.append(field.getName());
                    csvWriter.append(";");
                }
                csvWriter.append("\n");

                // Escreva os dados
                for (T coluna : entidade) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object value;
                        try {
                            value = field.get(coluna);
                        } catch (IllegalAccessException e) {
                            value = "";
                        }
                        csvWriter.append(value.toString());
                        csvWriter.append(",");
                    }
                    csvWriter.append("\n");
                }
            }
        }
    }
}
