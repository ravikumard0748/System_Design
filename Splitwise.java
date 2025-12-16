import java.util.*;

interface SplitStrategy {
    Map<User, Double> getShares(double totalAmount, List<User> users);
}

class User {
    public final String name;
    public double balance = 0.0;

    public User(String name) {
        this.name = name;
    }
}

class Expense {
    private final User paidBy;
    private final double amount;
    private final List<User> sharers;
    private final SplitStrategy strategy;

    private Expense(Builder builder) {
        this.paidBy = builder.paidBy;
        this.amount = builder.amount;
        this.sharers = new ArrayList<>(builder.sharers);
        this.strategy = builder.strategy;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public List<User> getSharers() {
        return new ArrayList<>(sharers);
    }

    public SplitStrategy getStrategy() {
        return strategy;
    }

    public static class Builder {
        private User paidBy;
        private double amount;
        private final List<User> sharers = new ArrayList<>();
        private SplitStrategy strategy;

        public Builder paidBy(User paidBy) {
            this.paidBy = paidBy;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder addSharer(User sharer) {
            this.sharers.add(sharer);
            return this;
        }

        public Builder sharers(List<User> sharers) {
            this.sharers.addAll(sharers);
            return this;
        }

        public Builder strategy(SplitStrategy strategy) {
            this.strategy = strategy;
            return this;
        }

        public Expense build() {
            if (paidBy == null || amount <= 0 || sharers.isEmpty() || strategy == null) {
                throw new IllegalArgumentException("Invalid expense parameters");
            }
            return new Expense(this);
        }
    }
}

class EqualSplitStrategy implements SplitStrategy {
    @Override
    public Map<User, Double> getShares(double totalAmount, List<User> users) {
        Map<User, Double> shares = new HashMap<>();
        if (users.isEmpty()) return shares;

        double share = totalAmount / users.size();
        for (User user : users) {
            shares.put(user, share);
        }
        return shares;
    }
}

public class Splitwise {

    private final List<User> users = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();

    public void addUser(String name) {
        if (getUser(name) != null) {
            throw new IllegalArgumentException("User already exists: " + name);
        }
        users.add(new User(name));
    }

    public User getUser(String name) {
        return users.stream()
                .filter(u -> u.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public void addExpense(User paidBy, double amount, List<User> sharers, SplitStrategy strategy) {
        Expense expense = new Expense.Builder()
                .paidBy(paidBy)
                .amount(amount)
                .sharers(sharers)
                .strategy(strategy)
                .build();

        expenses.add(expense);

        paidBy.balance += amount; 
        Map<User, Double> shares = strategy.getShares(amount, sharers);

        for (Map.Entry<User, Double> entry : shares.entrySet()) {
            User user = entry.getKey();
            double userShare = entry.getValue();
            user.balance -= userShare;
        }
    }

    public String getBalances() {
        StringBuilder sb = new StringBuilder("Current Balances:\n");

        users.stream()
                .sorted((u1, u2) -> Double.compare(u2.balance, u1.balance))
                .forEach(u -> sb.append(u.name)
                        .append(": $")
                        .append(String.format("%.2f", u.balance))
                        .append("\n"));

        return sb.toString();
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    public static void main(String[] args) {
        Splitwise app = new Splitwise();

        app.addUser("Ravi");
        app.addUser("Kumar");
        app.addUser("Mani");

        User ravi = app.getUser("Ravi");
        User kumar = app.getUser("Kumar");
        User mani = app.getUser("Mani");

        List<User> sharers = Arrays.asList(ravi, kumar, mani);

        app.addExpense(ravi, 900, sharers, new EqualSplitStrategy());

        System.out.println(app.getBalances());
    }
}
