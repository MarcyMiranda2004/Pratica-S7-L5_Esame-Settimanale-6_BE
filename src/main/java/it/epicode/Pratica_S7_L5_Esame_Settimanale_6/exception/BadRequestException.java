package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
