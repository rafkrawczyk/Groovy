class NumberHelper {
    static int timesThree(Integer input) {
        return input * 3
    }
}

use(NumberHelper) {
    println 376.timesThree()
}

// Outside the block, the method does not exist
try {
    println 376.timesThree()
} catch (MissingMethodException e) {
    println "Error: method 'timesThree doesn't exist outside of category!"
}