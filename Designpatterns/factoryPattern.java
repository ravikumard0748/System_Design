
interface Notification {
    void send(String message);
}

// Step 2: Multiple Implementations
class SMSNotification implements Notification {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class EmailNotification implements Notification {
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

class PushNotification implements Notification {
    public void send(String message) {
        System.out.println("Sending Push Notification: " + message);
    }
}

class NotificationFactory {
    public static Notification getNotification(String type) {
        if (type.equalsIgnoreCase("SMS")) 
            return new SMSNotification();
        else if (type.equalsIgnoreCase("EMAIL")) 
            return new EmailNotification();
        else if (type.equalsIgnoreCase("PUSH")) 
            return new PushNotification();
        return null;
    }
}

public class  factoryPattern{
    public static void main(String[] args) {
        Notification notif = NotificationFactory.getNotification("EMAIL");
        notif.send("Hello, User! Welcome to Factory Pattern.");
    }
}
