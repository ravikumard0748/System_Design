// 1) Product Interfaces
interface Notification {
    void send(String message);
}

class NormalSMS implements Notification {
    public void send(String message) { System.out.println("Normal SMS: " + message); }
}

class NormalEmail implements Notification {
    public void send(String message) { System.out.println("Normal Email: " + message); }
}

class NormalPush implements Notification {
    public void send(String message) { System.out.println("Normal Push: " + message); }
}

class UrgentSMS implements Notification {
    public void send(String message) { System.out.println("URGENT SMS: " + message); }
}

class UrgentEmail implements Notification {
    public void send(String message) { System.out.println("URGENT Email: " + message); }
}

class UrgentPush implements Notification {
    public void send(String message) { System.out.println("URGENT Push: " + message); }
}

interface NotificationFactory {
    Notification createSMS();
    Notification createEmail();
    Notification createPush();
}

class NormalNotificationFactory implements NotificationFactory {
    public Notification createSMS() { return new NormalSMS(); }
    public Notification createEmail() { return new NormalEmail(); }
    public Notification createPush() { return new NormalPush(); }
}

class UrgentNotificationFactory implements NotificationFactory {
    public Notification createSMS() { return new UrgentSMS(); }
    public Notification createEmail() { return new UrgentEmail(); }
    public Notification createPush() { return new UrgentPush(); }
}

// 7) CLIENT CODE
public class Main {
    public static void main(String[] args) {

        // Choose Notification Family: NORMAL or URGENT
        NotificationFactory factory = new UrgentNotificationFactory(); // change to NormalNotificationFactory()

        // Get only products from that family
        Notification sms = factory.createSMS();
        Notification email = factory.createEmail();

        sms.send("Server is down!");
        email.send("Fix immediately!");
    }
}
