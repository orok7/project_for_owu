package eins.service.interfaces;

public interface MailService {
    void sendMailRecPass(String email, String pass, double min);
    public void sendNewOrder(String email, String subject, String text);

}
