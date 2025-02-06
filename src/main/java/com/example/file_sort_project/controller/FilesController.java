package com.example.file_sort_project.controller;

import com.example.file_sort_project.domain.response.SimpleMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Контроллер для работы с файлами", description = "File API version v1")
public interface FilesController {

    @Operation(summary = "Находит N-ное максимальное число из файла",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешное нахождение N-ного максимального числа",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Integer.class))),
                    @ApiResponse(responseCode = "400", description = "Невалидные входные значения",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SimpleMessage.class))),
            }
    )
    ResponseEntity<Integer> getNMaxNumber(String path, int N);
}
