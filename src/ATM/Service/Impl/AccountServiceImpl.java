package ATM.Service.Impl;

import ATM.DAO.AccountDao;
import ATM.Model.UserAccount;
import ATM.Service.AccountService;

import java.util.Random;
import java.util.Scanner;

public class AccountServiceImpl implements AccountService {

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private final Scanner scannerInt = new Scanner(System.in);
    protected AccountDao accountDao = new AccountDao();
    protected UserAccount newAccount = null;

    @Override
    public void singUp(String name, String lastName) {

        int cardName = random.nextInt(1000000, 99999999);
        int pinCode = random.nextInt(1000, 9999);

        System.out.println("\nЗдравствуйте " + lastName + " " + name + "!\n" +
                "Ваш аккаунт успешно зарегистрирован!\n" +
                "Ваши реквизиты:\n" +
                "Номер карты: " + cardName + '\n' +
                "Пинкод: " + pinCode);

        UserAccount[] userAccountsArray = new UserAccount[4];

        for (int i = 0; i < userAccountsArray.length; i++) {
            if (userAccountsArray[i] == null) {

                UserAccount userAccount = new UserAccount("sami", "U", "00000000", "0000", 0);
                UserAccount userAccount2 = new UserAccount("lola", "K", "12345678", "0066", 0);
                UserAccount userAccount1 = new UserAccount(name, lastName, Integer.toString(cardName), Integer.toString(pinCode), 0);
                userAccountsArray[i] = userAccount;
                userAccountsArray[i + 1] = userAccount1;
                userAccountsArray[i + 2] = userAccount2;
                accountDao.setAccountDao(userAccountsArray);
                return;
            }
        }
    }

    @Override
    public void singIn(String cardName, String pinCode) {

        for (UserAccount account : accountDao.getAccountDao()) {
            if (account != null && cardName.equals(account.getCardName()) && pinCode.equals(account.getPinCode())) {
                newAccount = account;
                break;
            }
        }

        if (newAccount != null && cardName.equals(newAccount.getCardName()) && pinCode.equals(newAccount.getPinCode())) {
            System.out.println("\nДанные совпали!");

            int action = 1;

            while (action != 0) {
                System.out.println("""
                                                
                        Выбирите действие:
                        1. Проверить баланс
                        2. Внести дипозит
                        3. Перевести деньги
                        4. Снять деньги
                        0. Выйти с программы
                                                
                        """);

                action = scannerInt.nextInt();

                switch (action) {
                    case 1:
                        balance(cardName, pinCode);
                        break;
                    case 2:
                        deposit(newAccount.getCardName(), newAccount.getPinCode());
                        break;
                    case 3:
                        sendToFriend(newAccount.getCardName());
                        break;
                    case 4:
                        withdrawMoney(newAccount.getBalance());
                        break;
                }
            }
        } else {
            System.out.println("\nДанные не верны! ");
        }
    }
    @Override
    public void balance(String cardName, String pinCode) {

        for (UserAccount account : accountDao.getAccountDao()) {
            if (account != null && cardName.equals(account.getCardName()) && pinCode.equals(account.getPinCode())) {
                newAccount = account;
                break;
            }
        }

        if (newAccount != null && cardName.equals(newAccount.getCardName()) && pinCode.equals(newAccount.getPinCode())) {
            System.out.println("\nДобрый день, " + newAccount.getName() + "!");
            System.out.println("Информация вашего счета: ");
            System.out.println("Ваш баланс состовляет: " + newAccount.getBalance() + " рублей\n");
        } else {
            System.out.println("\nДанные не верны! ");
            return;
        }
        return;
    }

    @Override
    public void sendToFriend(String friendCardNumber) {

        System.out.print("\nВведите номер карты ---> ");
        String cardNumber = scanner.nextLine();
        System.out.print("Введите пин код карты ---> ");
        String pinCode = scanner.nextLine();

        for (UserAccount account : accountDao.getAccountDao()) {
            if (account != null && cardNumber.equals(account.getCardName()) && pinCode.equals(account.getPinCode())) {
                newAccount = account;
            }
        }
        if (newAccount != null && cardNumber.equals(newAccount.getCardName()) && pinCode.equals(newAccount.getPinCode())) {
            System.out.println("\nДанные верны!");
            System.out.print("\nВведите номер карты друга --- > ");
            String cardNumberFriend = scanner.nextLine();

            UserAccount friend = null;

            for (UserAccount accountFriend : accountDao.getAccountDao()) {
                if (accountFriend != null && cardNumberFriend.equals(accountFriend.getCardName())) {
                    friend = accountFriend;

                    System.out.print("Введите сумму перевода ---> ");
                    int sum = scannerInt.nextInt();

                    friend.setBalance(friend.getBalance() + sum);
                    newAccount.setBalance(newAccount.getBalance() - sum);

                    System.out.println("Вы успешно перевели деньги на номер " + cardNumberFriend +
                            "\nC вашего счета списалось " + sum + " рублей" +
                            "\nВаш баланс после операции --- > " + newAccount.getBalance() + "рублей" +
                            "\nБаланс друга после операции ---> " + friend.getBalance() + "рублей");
                }
            }
        } else {
            System.out.println("ERROR");
        }


    }
    @Override
    public void deposit(String cardName, String pinCode) {

        System.out.print("\nВведите номер карты ---> ");
        cardName = scanner.nextLine();
        System.out.print("Введите пин код карты ---> ");
        pinCode = scanner.nextLine();

        for (UserAccount account : accountDao.getAccountDao()) {
            if (account != null && cardName.equals(account.getCardName()) && pinCode.equals(account.getPinCode())) {
                newAccount = account;
            }
        }
        if (newAccount != null && cardName.equals(newAccount.getCardName()) && pinCode.equals(newAccount.getPinCode())) {
            System.out.println("\nДанные верны!");

            System.out.print("На какой cчет перевести?\n" + "---> ");
            String userCardNum = scanner.nextLine();

            System.out.print("Введите сумму взноса ---> ");
            int sum = scannerInt.nextInt();

            newAccount.setBalance(newAccount.getBalance() + sum);

            System.out.println("Вы успешно перевели деньги на номер " + userCardNum +
                    "\nВы внесли " + sum + " рублей" +
                    "\nБаланс после операции --- > " + newAccount.getBalance() + "рублей");
        } else {
            System.out.println("error");
        }
    }

    @Override
    public void withdrawMoney(int amount) {

        System.out.print("\nВведите номер карты ---> ");
        String cardName = scanner.nextLine();
        System.out.print("Введите пин код ---> ");
        String pinCode = scanner.nextLine();

        for (UserAccount account : accountDao.getAccountDao()) {
            if (account != null && cardName.equals(account.getCardName()) && pinCode.equals(account.getPinCode())) {
                newAccount = account;
            }
        }

        if (newAccount != null && cardName.equals(newAccount.getCardName()) && pinCode.equals(newAccount.getPinCode())) {
            System.out.println("\nДанные верны!");

            System.out.print("Введите сумму которую хоите вывести ---> ");
            amount = scannerInt.nextInt();

            if(amount > newAccount.getBalance()){
                System.out.println("У вас недостаточно средств! ");
                return;
            }

            if (amount % 5000 == 0) {
                System.out.println("Вывести " + amount + " по 5к руб " + amount / 5000 + " штук ---> 1");
            }
            if (amount % 2000 == 0) {
                System.out.println("Вывести " + amount + " по 2000 руб " + amount / 2000 + " штук ---> 2");
            }
            if (amount % 1000 == 0) {
                System.out.println("Вывести " + amount + " по 1000 руб " + amount / 1000 + " штук ---> 3");
            }
            if (amount % 500 == 0) {
                System.out.println("Вывести " + amount + " по 500 руб " + amount / 500 + " штук ---> 4");
            }
            if (amount % 200 == 0) {
                System.out.println("Вывести " + amount + " по 200 руб " + amount / 200 + " штук ---> 5");
            }
            if (amount % 100 == 0) {
                System.out.println("Вывести " + amount + " по 100 руб " + amount / 100 + " штук ---> 6");
            }
            if (amount % 50 == 0) {
                System.out.println("Вывести " + amount + " по 50 руб " + amount / 50 + " штук ---> 7");
            }
            if (amount % 10 == 0) {
                System.out.println("Вывести " + amount + " по 10 руб " + amount / 10 + " штук ---> 8");
            }

            System.out.println("\nВыберите способ вывода денег ---> ");
            int num = scannerInt.nextInt();


            System.out.println("Вы успешно сняли " + amount + " рублей" +
                    "\nВаш баланс до операции: " + newAccount.getBalance());

            switch (num) {
                case 1:
                    newAccount.setBalance(newAccount.getBalance() - amount);
                case 2:
                    newAccount.setBalance(newAccount.getBalance() - amount);
                case 3:
                    newAccount.setBalance(newAccount.getBalance() - amount);
                case 4:
                    newAccount.setBalance(newAccount.getBalance() - amount);
                case 5:
                    newAccount.setBalance(newAccount.getBalance() - amount);
                case 6:
                    newAccount.setBalance(newAccount.getBalance() - amount);
                case 7:
                    newAccount.setBalance(newAccount.getBalance() - amount);
                case 8:
                    newAccount.setBalance(newAccount.getBalance() - amount);
            }
            System.out.println("Ваш баланс после операции: " + newAccount.getBalance() + " рублей");

        } else {
            System.out.println("error");
        }
    }
}


