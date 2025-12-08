class Account {

    BigDecimal balance = 0.0
    
    void deposit(BigDecimal amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative")
        }
        balance += amount
    }
    
    void deposit(List<BigDecimal> amounts) {
        for (amount in amounts) {
            try {
                // Try to deposit the current amount
                deposit(amount)
                println "Success: Deposited $amount"
            } catch (IllegalArgumentException e) {
                // Catch the specific error, print it, and keep looping
                println "Skipped invalid amount ($amount): ${e.message}"
            }
        }
    }
}

Account account = new Account()

account.deposit(350)
account.deposit(-500)
account.deposit([100, 40, -5])