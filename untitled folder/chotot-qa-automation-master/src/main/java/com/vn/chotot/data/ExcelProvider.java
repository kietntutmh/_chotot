package com.vn.chotot.data;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExcelProvider {
    private XSSFWorkbook excelWBook;
    private XSSFSheet excelWSheet;
    private XSSFCell cell;
    private int rowNumber;
    private int columnNumber;

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int pRowNumber) {
        rowNumber = pRowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int pColumnNumber) {
        columnNumber = pColumnNumber;
    }

    public Object[][] getTableArrayByRow(String excelFile, String sheetName, int rowIndex) {
        // Open the Excel file
        try (FileInputStream ExcelFile = new FileInputStream(excelFile)) {
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            int totalCols = excelWSheet.getRow(0).getPhysicalNumberOfCells();
            String[][] tabArray = new String[1][totalCols];
            int ci = 0;
            int cj = 0;
            for (int i = 0; i < totalCols; i++, cj++) {
                tabArray[ci][cj] = getCellData(rowIndex - 1, i);
            }
            return tabArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] getTableArray(String excelFile, String sheetName, int startRowIndex) {
        // Open the Excel file
        try (FileInputStream ExcelFile = new FileInputStream(excelFile)) {
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            int totalCols = excelWSheet.getRow(0).getPhysicalNumberOfCells();
            int totalRows = excelWSheet.getLastRowNum() + 1;
            String[][] tabArray = new String[totalRows][totalCols];
            int ci = 0;
            int cj;
            for (int i = startRowIndex - 1; i < totalRows; i++, ci++) {
                cj = 0;
                for (int j = 0; j < totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                }
            }
            return tabArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getExcelFileSheet(String excelFile, String sheetName) {
        // Open the Excel file
        try (FileInputStream ExcelFile = new FileInputStream(excelFile)) {
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCellData(int RowNum, int ColNum) {
        cell = excelWSheet.getRow(RowNum).getCell(ColNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public List<String> getRowData(int RowNum) {
        List<String> listCellData = new ArrayList<>();
        XSSFRow rowData = excelWSheet.getRow(RowNum);
        int cellNumber = rowData.getPhysicalNumberOfCells();
        for (int i = 0; i < cellNumber; i++) {
            try {
                listCellData.add(rowData.getCell(i).getStringCellValue());
            } catch (IllegalStateException ex) {
                String strValue = String.valueOf(rowData.getCell(i).getNumericCellValue());
                listCellData.add(strValue);
            } catch (NullPointerException nulE) {
                System.out.println("Null value at cell " + i);
            }
        }
        return listCellData;
    }

    public void setCellData(String excelFile, int RowNum, int ColNum, String value) {
        try {
            XSSFRow row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
            }
            cell.setCellValue(value);
            ClassLoader cLoader = ExcelProvider.class.getClassLoader();
            String filePath =
                    new String(Files.readAllBytes(Paths.get(cLoader.getResource(excelFile).toURI())));
            FileOutputStream fileOut = new FileOutputStream(filePath);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getColumnData(int ColumnNum) {
        List<String> listColumnData = new ArrayList<>();
        int totalRow = excelWSheet.getLastRowNum();
        for (int i = 1; i < totalRow; i++) {
            String columnData = "";
            try {
                columnData = getCellData(i, columnNumber);
            } catch (NullPointerException ex) { }
            listColumnData.add(columnData);
        }
        return listColumnData;
    }
}
