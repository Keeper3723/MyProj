package by.leha.exceptions.dtos;

import java.util.Date;

public record AppError(int status
, String message,
Date timestamp) {
}
