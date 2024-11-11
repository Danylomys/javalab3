public class Client {
    private final String name;
    private final long Id;
    private double balance;


    public Client(String name, long Id, double balance) {
        this.name = name;
        this.Id = Id;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return Id;
    }

    public double getBalance() {
        return balance;
    }

    public void decrementBalance(double balance) {
        this.balance -= balance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", Id=" + Id +
                ", balance=" + balance +
                '}';
    }
}

