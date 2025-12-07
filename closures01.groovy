myClosure = {
    6 + 14
}

void printResult(c) {
    println c.call()
}

printResult(myClosure)

println ("---------------------------")

def myList = ["Java", "Groovy", "Python", "Rust"]
myList.each { println it }

println ("---------------------------")

def myMap = [1:"Java", 2:"Groovy", 3:"Python", 4:"Rust"]
myMap.each { k, v ->
    println "Key: ${k}, Value: ${v}"
}

println ("---------------------------")

def logMessage = {
    String severity, Date time, String msg ->
    println "[${severity}] at ${time}: ${msg}"
}
    
def logError = logMessage.curry("ERROR")
def logInfo = logMessage.curry("INFO")

Date now = new Date()

logInfo(now, "Application started successfully.")
logInfo(now, "Connected to database.")
logError(now, "Connection timeout!")
logError(now, "Database missing!")