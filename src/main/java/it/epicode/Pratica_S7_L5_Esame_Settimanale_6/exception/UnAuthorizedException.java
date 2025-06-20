package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String message) {
        super(message);
    }
}
