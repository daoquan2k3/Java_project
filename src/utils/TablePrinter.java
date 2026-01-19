package utils;

import java.util.List;

public class TablePrinter {
    public static <T> void printTable(String title, String[] headers, int[] columnWidths, List<T> data, RowMapper<T> mapper) {
        System.out.println("\n--- " + title.toUpperCase() + " ---");

        //Tạo đường kẻ ngang dựa trên độ rộng cột
        StringBuilder lineBuilder = new StringBuilder("+");
        for (int width : columnWidths) {
            lineBuilder.append("-".repeat(width + 2)).append("+");
        }
        String line = lineBuilder.toString();

        //Tạo định dạng dòng (ví dụ: | %-5s | %-20s |)
        StringBuilder formatBuilder = new StringBuilder("|");
        for (int width : columnWidths) {
            formatBuilder.append(" %-").append(width).append("s |");
        }
        String format = formatBuilder.toString() + "\n";

        System.out.println(line);
        System.out.printf(format, (Object[]) headers);
        System.out.println(line);

        for (T item : data) {
            System.out.printf(format, mapper.mapRow(item));
        }
        System.out.println(line);
    }

    @FunctionalInterface
    public interface RowMapper<T> {
        Object[] mapRow(T item);
    }
}
