package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.controller;

import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto.LoginDto;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.dto.UserDto;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception.NotFoundException;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.exception.ValidationException;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.model.User;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.service.AuthService;
import it.epicode.Pratica_S7_L5_Esame_Settimanale_6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody @Validated UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, e) -> s + e));
        }

        return userService.saveUser(userDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult)
            throws ValidationException, NotFoundException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s, e) -> s + e));
        }
        return authService.login(loginDto);
    }
}

