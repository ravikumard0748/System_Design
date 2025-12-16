// NotificationDemo.java
public class notification {
    public static void main(String[] args) {
        Notification email = NotificationFactory.create(NotificationType.EMAIL);
        Notification sms = NotificationFactory.create(NotificationType.SMS);
        Notification push = NotificationFactory.create(NotificationType.PUSH);

        Notification decoratedEmail = new LoggingDecorator(
                                        new EncryptionDecorator(email));

        Notification decoratedSms = new RetryDecorator(
                                      new LoggingDecorator(sms), 3);

        Notification decoratedPush = new SchedulingDecorator(
                                       new LoggingDecorator(push), "2025-12-11 10:00");

        decoratedEmail.send("Hello via Email!");
        decoratedSms.send("Hello via SMS!");
        decoratedPush.send("Hello via Push!");
    }

    public interface Notification {
        void send(String message);
    }

    public static class EmailNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("[Email] sending: " + message);
        }
    }

    public static class SMSNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("[SMS] sending: " + message);
        }
    }

    public static class PushNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("[Push] sending: " + message);
        }
    }

    public enum NotificationType {
        EMAIL, SMS, PUSH
    }

    public static class NotificationFactory {
        public static Notification create(NotificationType type) {
            switch (type) {
                case EMAIL: return new EmailNotification();
                case SMS:   return new SMSNotification();
                case PUSH:  return new PushNotification();
                default:    throw new IllegalArgumentException("Unknown type");
            }
        }
    }

    public static abstract class NotificationDecorator implements Notification {
        protected final Notification wrapped;
        protected NotificationDecorator(Notification wrapped) {
            this.wrapped = wrapped;
        }
    }

    public static class LoggingDecorator extends NotificationDecorator {
        public LoggingDecorator(Notification wrapped) {
            super(wrapped);
        }
        @Override
        public void send(String message) {
            log(message);
            wrapped.send(message);
        }
        private void log(String message) {
            System.out.println("[Log] about to send: \"" + message + "\" via " + wrapped.getClass().getSimpleName());
        }
    }

    public static class EncryptionDecorator extends NotificationDecorator {
        public EncryptionDecorator(Notification wrapped) {
            super(wrapped);
        }
        @Override
        public void send(String message) {
            String encrypted = encrypt(message);
            wrapped.send(encrypted);
        }
        private String encrypt(String message) {
            StringBuilder sb = new StringBuilder();
            for (char c : message.toCharArray()) sb.append((char)(c + 1));
            return "[enc]" + sb.toString();
        }
    }

    public static class RetryDecorator extends NotificationDecorator {
        private final int maxAttempts;
        public RetryDecorator(Notification wrapped, int maxAttempts) {
            super(wrapped);
            this.maxAttempts = Math.max(1, maxAttempts);
        }
        @Override
        public void send(String message) {
            int attempt = 0;
            while (attempt < maxAttempts) {
                attempt++;
                try {
                    wrapped.send(message);
                    System.out.println("[Retry] success on attempt " + attempt);
                    return;
                } catch (RuntimeException e) {
                    System.out.println("[Retry] attempt " + attempt + " failed: " + e.getMessage());
                    if (attempt >= maxAttempts) throw e;
                }
            }
        }
    }

    public static class SchedulingDecorator extends NotificationDecorator {
        private final String scheduledAt;
        public SchedulingDecorator(Notification wrapped, String scheduledAt) {
            super(wrapped);
            this.scheduledAt = scheduledAt;
        }
        @Override
        public void send(String message) {
            System.out.println("[Schedule] scheduled at " + scheduledAt + " (simulated)");
            wrapped.send(message);
        }
    }
}
