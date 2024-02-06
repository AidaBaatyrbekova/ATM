package ATM.DAO;

import ATM.Model.UserAccount;

public class AccountDao extends UserAccount {

    private UserAccount[] accountDao;

    public UserAccount[] getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(UserAccount[] accountDao) {
        this.accountDao = accountDao;
    }
}
