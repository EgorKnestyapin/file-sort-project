package com.example.file_sort_project.service;

/**
 * Сервис для работы с файлами.
 */
public interface FileService {


    /**
     * Получить N-ное максимальное число из файла формата xlsx.
     *
     * @param path Путь к локальному файлу
     * @param N    Порядковый номер максимального числа
     * @return N-ное максимальное число
     */
    int findNMaxNumberFromXlsxFile(String path, int N);
}
