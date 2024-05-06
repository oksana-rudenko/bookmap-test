package org.bookmap;

import org.bookmap.service.FileService;

public class Main {
    private static final String INPUT_DATA = "input.txt";
    private static final String OUTPUT_DATA = "output.txt";
    private static final int ZERO_POSITION = 0;
    private static final int ONE_POSITION = 1;
    private static final int TWO_POSITION = 2;

    public static void main(String[] args) {
        FileService fileService = new FileService();
        String dataFromFile = fileService.readDataFromFile(INPUT_DATA);
        String dataForOutput = dataListForOutput(dataFromFile);
        fileService.writeDataToFile(OUTPUT_DATA, dataForOutput);
    }

    private static String dataListForOutput(String dataFromFile) {
        StringBuilder dataForOutput = new StringBuilder();
        String[] splitedDataFromFile = dataFromFile.split("\r\n");
        String stringOfLetters = splitedDataFromFile[ONE_POSITION];
        int amountOfRow = Integer.parseInt(splitedDataFromFile[ZERO_POSITION].split(" ")[1]);
        for (int i = 2; i < 2 + amountOfRow; i++) {
            String[] queryContent = splitedDataFromFile[i].split(" ");
            int start = Integer.parseInt(queryContent[ZERO_POSITION]) - 1;
            int a = start, b = start;
            int l = Integer.parseInt(queryContent[ONE_POSITION]);
            int indexOfLetter = a + Integer.parseInt(queryContent[TWO_POSITION]) - 1;
            char charAtIndex = stringOfLetters.charAt(indexOfLetter);
            int aOccurence = 1, bOccurence = 0;
            while (b < l){
                if (a < indexOfLetter) {
                    if (stringOfLetters.charAt(a) == charAtIndex) {
                        aOccurence++;
                    }
                    a++;
                }
                if (aOccurence > bOccurence) {
                    if (stringOfLetters.charAt(b) != charAtIndex) {
                        bOccurence++;
                    }
                    b++;
                }
                if (bOccurence == aOccurence && a == indexOfLetter) {
                    dataForOutput.append(b - start).append("\r\n");
                    break;
                }
                if (b == l && bOccurence < aOccurence) {
                    dataForOutput.append("-1").append("\r\n");
                }
            }
        }
        return dataForOutput.toString().trim();
    }
}
