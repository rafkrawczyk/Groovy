
import java.time.LocalDateTime

// ======================================================
// 1. EXTENSIONS (1.seconds)
// ======================================================
Integer.metaClass.getSeconds = { -> return delegate * 1000 }
Integer.metaClass.getMinutes = { -> return delegate * 60 * 1000 }

// ======================================================
// 2. THE DSL CLASSES
// ======================================================

// This class handles the final step: ".after"
class TimerAction {
    String who
    String taskName

    def getAfter() {
        return { long delayInMillis ->
            def triggerTime = LocalDateTime.now().plusNanos(delayInMillis * 1000000)

            println "--- SYSTEM LOG ---"
            println "Scheduling task for user: $who"
            println "Task: $taskName"
            println "Triggering at: $triggerTime"

            Thread.sleep(delayInMillis)

            println "\n!!! ALARM !!!"
            println "Hey $who, don't forget to: $taskName"
            println "================"
        }
    }
}

// This class handles the middle step: the task string
class TaskCapture {
    String who

    // In Groovy, if an object is followed by a String literal (like "Check Oven"),
    // the .call() method is invoked automatically.
    def call(String taskName) {
        return new TimerAction(who: who, taskName: taskName)
    }
}

// ======================================================
// 3. EXECUTION SETUP
// ======================================================

println "Starting Groovy Script...\n"

// Define 'me' so it can be passed as an argument
def me = "Rafal"

// Define 'remind' as a closure.
def remind = { who ->
    return new TaskCapture(who: who)
}

// ======================================================
// 4. THE COMMAND
// ======================================================

remind(me).call("Check the oven").after(2.seconds)

println "\nScript finished."