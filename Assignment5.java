import java.io.*;
import java.util.*;

public class Assignment5 {
    public static void main(String[] args) throws IOException {
        String file1 = "matrix1.txt";
        String file2 = "matrix2.txt";
        int[][] matrix1 = readMatrixFromFile(file1);
        int[][] matrix2 = readMatrixFromFile(file2);
        int[][] result = multiplyMatrices(matrix1, matrix2);
        writeMatrixToFile("matrix3.txt", result);
    }

    private static int[][] readMatrixFromFile(String filename) throws IOException {
        List<int[]> matrix = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                matrix.add(Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray());
            }
        }
        return matrix.toArray(new int[0][]);
    }

    private static void writeMatrixToFile(String filename, int[][] matrix) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int[] row : matrix) {
                for (int val : row) {
                    bw.write(val + " ");
                }
                bw.newLine();
            }
        }
    }

    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length, cols1 = matrix1[0].length;
        int rows2 = matrix2.length, cols2 = matrix2[0].length;
        if (cols1 != rows2) throw new IllegalArgumentException("Matrices cannot be multiplied");

        int[][] result = new int[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
}