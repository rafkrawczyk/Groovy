class GroupBy {
    static void main(String[] args) {
        def people = [
            [name: 'Alice', role: 'Developer'],
            [name: 'Bob', role: 'Manager'],
            [name: 'Carol', role: 'Developer'],
            [name: 'Dave', role: 'Marketing'],
            [name: 'Eve', role: 'Manager'],
        ]
        
        def peopleByRole = people.groupBy { it.role }
        
        peopleByRole.each {role, list ->
            println "$role : ${list.name}"
        }
        
        def numbers = [1, 2, 3, 4, 5, 6, 7]
        def byParity = numbers.groupBy { it % 2 == 0 ? 'Even' : 'Odd' }
        
        println byParity
    }
}