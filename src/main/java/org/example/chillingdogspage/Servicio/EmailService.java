package org.example.chillingdogspage.Servicio;

import com.resend.services.emails.model.CreateEmailResponse;
import org.example.chillingdogspage.Entidad.Email;
import com.resend.*;

public interface EmailService {
    public CreateEmailResponse enviarEmail(Email email);
}
