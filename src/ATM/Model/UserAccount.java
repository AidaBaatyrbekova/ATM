package ATM.Model;
public class UserAccount {

    private String name;
    private String LastName;
    private String cardName;
    private String pinCode;
    private int balance;

    public UserAccount() {
    }

    public UserAccount(String name, String lastName, String cardName, String pinCode, int balance) {
        this.name = name;
        this.LastName = lastName;
        this.cardName = cardName;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", cardName='" + cardName + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", balance=" + balance +
                '}';
    }
}
