class Account {
    
    BigDecimal balance = 0.0
    String type
    
    BigDecimal deposit(BigDecimal addition) {
    this.balance += addition
    return balance
    }
    
    BigDecimal withdraw(BigDecimal withdrawal) {
    this.balance -= withdrawal
    return balance
    }
    
    BigDecimal plus(Account otherAccount) {
        return this.balance + otherAccount.balance
    }
}

Account checking = new Account(type:"Checking")
checking.deposit(100.00)
Account savings = new Account(type:"Savings")
savings.deposit(50.00)

println checking.balance
println savings.balance

BigDecimal total = checking + savings
println total