package Twitter

// ==========================================
// Usage Example
// ==========================================

// 1. Create a tweet with hashtags and mentions
Twitter.Tweet myTweet = new Tweet("@groovyUser", "Learning #Groovy is fun! Thanks @ApacheFoundation for the cool language. #coding")

// 2. Interact with it
myTweet.like()
myTweet.like()
myTweet.retweet()

// 3. Print the state
println myTweet

// 4. Test the Bonus Logic
println "--- Metadata ---"
println "Hashtags found: ${myTweet.getHashtags()}"
println "Mentions found: ${myTweet.getMentions()}"

/*
Expected Output:
[Current Date] @groovyUser: Learning #Groovy is fun! Thanks @ApacheFoundation for the cool language. #coding (Likes: 2, RTs: 1)
--- Metadata ---
Hashtags found: [#Groovy, #coding]
Mentions found: [@ApacheFoundation]
*/

