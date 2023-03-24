package com.damo.gestionDeStock.handlers;

import com.damo.gestionDeStock.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private Integer HttpCode;

    private ErrorCodes code;

    private String message;

    private List<String> errors = new ArrayList<>();
}
