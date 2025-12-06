import java.time.ZonedDateTime
import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)

class Tweet {

    String user
    ZonedDateTime time
    String content
    Integer likes
    Integer retweets
    List<String> hashtags
    
    Tweet(String user, String content) {
        this.user = user
        this.content = content
        this.time = ZonedDateTime.now()
        this.likes = 0
        this.retweets = 0
        this.hashtags = []
    }
    
    void like() {
        this.likes++
    }
    
    void retweet() {
        this.retweets++
    }
    
    void addHashtag(String tag) {
        if (!tag.startsWith("#")) {
            tag = "#" + tag
        }
        this.hashtags.add(tag)
    }
    
    void editTweet(String newContent) {
        this.content = newContent
    }
}

def tweet1 = new Tweet("@jenkinsAdmin", "Build failed again... checking logs.")
tweet1.addHashtag("devops")
tweet1.addHashtag("pain")
tweet1.like()
tweet1.like()

def tweet2 = new Tweet("@groovyUser", "I actually love closures, they are so powerful.")
tweet2.retweet()
tweet2.addHashtag("coding")

tweet2.editTweet("I actually love closures AND AST transformations.")

println "\n--- Printing Tweet Instances (using @ToString) ---"

println tweet1
println tweet2