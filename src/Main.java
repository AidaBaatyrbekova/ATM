import ATM.Service.Impl.AccountServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);

        AccountServiceImpl accountService = new AccountServiceImpl();

        System.out.print("Доброго времени суток!\n" +
                "Введите ваше имя ---> ");
        String name = scannerString.nextLine();
        System.out.print("Введите фамилию ---> ");
        String lastName = scannerString.nextLine();
        accountService.singUp(name, lastName);

        System.out.print("\nВведите ваши реквизиты для дальнейших действий\n" +
                "Номер карты ---> ");
        String cardName = scannerInt.nextLine();
        System.out.print("пинкод ---> ");
        String pinCode = scannerInt.nextLine();
        accountService.singIn(cardName, pinCode);


    }
}
