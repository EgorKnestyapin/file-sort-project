package com.example.file_sort_project.service.impl;

import com.example.file_sort_project.exception.FileException;
import com.example.file_sort_project.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.file_sort_project.configuration.ApplicationConstant.INVALID_FILE_PATH_EXCEPTION_MESSAGE;
import static com.example.file_sort_project.configuration.ApplicationConstant.READ_FILE_EXCEPTION_MESSAGE;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Override
    public int findNMaxNumberFromXlsxFile(String path, int N) {
        log.info("Поиск {} максимального числа из файла", N);
        Sheet sheet = getRows(path);
        Set<Integer> numberSet = new HashSet<>();


        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                numberSet.add((int) cell.getNumericCellValue());
            }
        }

        List<Integer> numberList = new ArrayList<>(numberSet);
        log.info("Быстрая сортировка чисел: {}", numberList);
        quickSort(numberList, 0, numberList.size() - 1);
        log.info("Список чисел после сортировки: {}", numberList);

        if (N > numberList.size() || N <= 0) {
            throw new IllegalArgumentException("Некорректное число N для поиска N-ного максимального значения");
        }

        return numberList.get(numberList.size() - N);
    }

    private static Sheet getRows(String path) {
        FileInputStream file;
        Workbook workbook;

        log.info("Получение локального файла по названию пути: {}", path);
        try {
            file = new FileInputStream(path);
        } catch (IOException e) {
            throw new FileException(INVALID_FILE_PATH_EXCEPTION_MESSAGE);
        }

        log.info("Чтение данных из файла формата xlsx");
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new FileException(HttpStatus.BAD_GATEWAY, READ_FILE_EXCEPTION_MESSAGE);
        }
        return workbook.getSheetAt(0);
    }

    public static void quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);

            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}
