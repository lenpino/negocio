package cl.altair.utiles.generales;
import javax.mail.*;

public class SMTPAuthenticator extends Authenticator
{
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication("lenpino@gmail.com", "m13d2n");
    }
}
