class Person implements GroovyInterceptable {

    private Map<String, Object> personalCharacteristics = [:]

    @Override
    void setProperty(String propertyName, Object newValue) {
        System.out.println ">> INTERCEPTED: Setting property '$propertyName' to '$newValue'"
        personalCharacteristics[propertyName] = newValue
    }

    @Override
    Object getProperty(String propertyName) {
        System.out.println ">> INTERCEPTED: Getting property '$propertyName'"
        if (propertyName == 'personalCharacteristics') return personalCharacteristics
        return personalCharacteristics.containsKey(propertyName) ?
                personalCharacteristics[propertyName] : "N/A"
    }

    @Override
    Object invokeMethod(String methodName, Object args) {
        System.out.println ">> INTERCEPTED: Calling method '$methodName' with args '$args'"

        if (methodName == "speak") {
            return "Hello!"
        }

        if (methodName == "add") {
            def list = args as List
            return list.sum()
        }

        return "Method '$methodName' handled generically"
    }
}

def mark = new Person()

mark.hairColor = "Blonde"
mark.height = 190

println "Hair Color: ${mark.hairColor}"
println "Height: ${mark.height}"
println "Eye Color: ${mark.eyeColor}"

println "Result 1: " + mark.speak()
println "Result 2: " + mark.add(10, 20, 30)
println "Result 3: " + mark.travel()
