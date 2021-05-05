package cn.zzy.library_web.entity;

public class Account {
    private int accountId;
    private String accountName;
    private String accountPass;
    private int accountRole;

    public Account(int accountId, String accountName, String accountPass, int accountRole) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountPass = accountPass;
        this.accountRole = accountRole;
    }

    public Account() {
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountPass(String accountPass) {
        this.accountPass = accountPass;
    }

    public void setAccountRole(int accountRole) {
        this.accountRole = accountRole;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountPass() {
        return accountPass;
    }

    public int getAccountRole() {
        return accountRole;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", accountPass='" + accountPass + '\'' +
                ", accountRole=" + accountRole +
                '}';
    }
}
