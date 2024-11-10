package org.example.chillingdogspage.Servicio;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.example.chillingdogspage.Entidad.Email;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public CreateEmailResponse enviarEmail(Email email) {
        Resend resend = new Resend("re_VkJ7tQaC_Dt7Ztrcutro6KwNYZ5Gansmb");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Chilling Dogs <onboarding@resend.dev>")
                .to(email.getCorreo())
                .subject("Cita agendada con Chilling Dogs por " + email.getPelidutoName() + "!")
                .html("<p>Buen día, hemos agendado tu cita para el " + email.getFecha() + "!</p><br>Esperamos que con esto " + email.getPelidutoName() + " se recupere prontamente<br>Muchas Gracias por confiar en Chilling Dogs, la mejor veterinaria del mundo, " + email.getNombre() + " " + email.getApellido() + "<br>Att:Chilling Dogs!")
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
            return data;
        } catch (ResendException e) {
            e.printStackTrace();
        }
        return null;
    }
}
