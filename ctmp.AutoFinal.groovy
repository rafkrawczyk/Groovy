import groovy.transform.AutoFinal

@AutoFinal
class PriceCalculator {

    // Valid Method: We read the parameters, but we don't change them
    BigDecimal calculateTotal(BigDecimal price, int quantity) {
        return price * quantity
    }

    // Invalid Method: If you uncomment the line below,
    // the code will NOT compile.
    void updatePrice(BigDecimal price) {
        // price = price + 25.50

        println "Price is: $price"
    }
}

// ---Usage ---

def calc = new PriceCalculator()
println "Total: " + calc.calculateTotal(21.30, 3)