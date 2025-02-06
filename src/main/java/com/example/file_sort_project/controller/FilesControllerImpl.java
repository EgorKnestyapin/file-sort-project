package com.example.file_sort_project.controller;

import com.example.file_sort_project.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FilesControllerImpl implements FilesController {

    private final FileService fileService;

    @Override
    @GetMapping("/N-max-number")
    public ResponseEntity<Integer> getNMaxNumber(@RequestParam String path, @RequestParam int N) {
        return ResponseEntity.ok(fileService.findNMaxNumberFromXlsxFile(path, N));
    }
}
