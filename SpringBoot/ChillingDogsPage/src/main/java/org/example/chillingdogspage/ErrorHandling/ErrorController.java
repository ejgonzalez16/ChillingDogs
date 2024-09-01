package org.example.chillingdogspage.ErrorHandling;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ClientNotFoundException.class)
    public String errorCliente(Model model, ClientNotFoundException ex){
        model.addAttribute("cedula", ex.getCedula());
        return "cliente_error";
    }
}
