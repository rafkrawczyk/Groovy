class FindVsFindAll {
    static void main(String[] args) {
        def numbers = [1, 3, 4, 5, 8, 9, 10, 1]

        def firstEven = numbers.find { it % 2 == 0 }
        
        println "Original list: $numbers"
        println "First even number: $firstEven"
        println "Return type: ${firstEven.getClass().name}"
        
        def allEvens = numbers.findAll { it % 2 == 0 }
        println "All evens: $allEvens"
        println "Return type: ${allEvens.getClass().name}"
    }
}