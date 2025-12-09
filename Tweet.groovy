package Twitter
import java.time.LocalDateTime

class Tweet {
    String handle
    String content
    LocalDateTime createdAt
    int likes = 0
    int retweets = 0

    Tweet (String handle, String content) {
        if (content.length() > 280) {
            throw new IllegalArgumentException("Tweet is too long! (Maximum of 280 characters)")
        }

        this.handle = handle
        this.content = content
        this.createdAt = LocalDateTime.now()
    }

    void like() {
        likes++
    }

    void retweet() {
        retweets++
    }

    List<String> getHashtags() {
        return content.findAll(/#\w+/)
    }

    List<String> getMentions() {
        return content.findAll(/@\w+/)
    }

    String toString() {
        return "[$createdAt] $handle: $content (Likes: $likes, RTs: $retweets"
    }
}
