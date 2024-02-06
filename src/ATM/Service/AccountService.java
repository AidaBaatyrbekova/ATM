package ATM.Service;

public interface AccountService{
    public void singUp(String name, String lastName);
    public void singIn(String cardName, String pinCode);
    public void balance(String cardName, String pinCode);
    public void sendToFriend(String friendCardNumber);
    public void deposit(String cardName, String pinCode);
    public void withdrawMoney(int amount);
}
