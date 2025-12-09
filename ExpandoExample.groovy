println "--- Part 1: The ExpandoExample Class ---"

def myExpando = new Expando()

myExpando.firstName = "Tony"
myExpando.lastName = "Stark"
myExpando.heroName = "Iron Man"

myExpando.fly = { ->
    return "$delegate.heroName is flying high!"
}

myExpando.shootLaser = { powerLevel ->
    return "Shooting laser with $powerLevel% power!"
}

println "Property: ${myExpando.firstName} ${myExpando.lastName}"
println "Method 1: ${myExpando.fly()}"
println "Method 2: ${myExpando.shootLaser(85)}"

println "\n--- Part 2: Metaclass (Expando behavior on Classes) ---"

class Robot {
    String name
}

Robot.metaClass.manufacturer = "Mexico Factory No.12"

Robot.metaClass.speak = { message ->
    return "Robot '$delegate.name' says: ${message.toUpperCase()}"
}

def bender = new Robot(name: "Bender")

println "Original Property: ${bender.name}"
println "Injected Property: ${bender.manufacturer}"
println "Injected Method: ${bender.speak('Bite my shiny metal ass!')}"

def r2d2 = new Robot(name: "R2D2")
println "Second Robot: ${r2d2.speak('Beep boop')}"