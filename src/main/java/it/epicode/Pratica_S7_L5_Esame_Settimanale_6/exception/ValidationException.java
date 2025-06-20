package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
