class AnyVsEvery {
    static void main(String[] args) {
        def langs = ["Groovy", "Java", "Kotlin", "Go"]
        
        boolean hasShortName = langs.any { it.length() < 3 }
        
        println "List: $langs"
        println "Is there any word shorter than 3 chars? $hasShortName"
        
        boolean allCapitalized = langs.every { Character.isUpperCase(it.charAt(0)) }
        
        boolean allLong = langs.every { it.length() > 3 }
        
        println "Is EVERY word capitalized? $allCapitalized"
        println "Is EVERY word longer than 3 chars? $allLong"
    }
}