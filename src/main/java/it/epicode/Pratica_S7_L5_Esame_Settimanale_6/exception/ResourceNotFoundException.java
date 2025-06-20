package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
