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
            deposit(amount)
        }
    }
}

Account account = new Account()

account.deposit(350)

try {
    account.deposit(-500)
} catch (IllegalArgumentException e) {
    println e.message
}

println account.balance

try {
    account.deposit([100, 40, -5])
} catch (IllegalArgumentException e) {
    println e.message
}

println account.balance