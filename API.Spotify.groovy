import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets

class SpotifyDownloader {

    // ==========================================
    // CONFIGURATION - PASTE YOUR KEYS HERE
    // ==========================================
    static final String CLIENT_ID = "YOUR_CLIENT_ID"
    static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET"

    // ID for "Global Top 50" playlist - this is the standard for current hits
    static final String PLAYLIST_ID = "37i9dQZEVXbMDoHDwVN2tF"

    static void main(String[] args) {
        // Validation check
        if (CLIENT_ID == "YOUR_CLIENT_ID") {
            println "ERROR: You must fill in CLIENT_ID and CLIENT_SECRET in the code!"
            return
        }

        try {
            println ">>> 1. Fetching access token..."
            String token = getAccessToken()

            println ">>> 2. Fetching top charts (Global Top 50)..."
            def tracks = getPlaylistTracks(token, PLAYLIST_ID)

            println ">>> 3. Saving results to file..."
            saveToFile(tracks)

        } catch (Exception e) {
            println "An error occurred: ${e.message}"
            e.printStackTrace()
        }
    }

    // Method for Authorization (OAuth 2.0 Client Credentials Flow)
    static String getAccessToken() {
        def client = HttpClient.newHttpClient()

        // Encode keys to Base64 format as required by Spotify API
        String auth = "${CLIENT_ID}:${CLIENT_SECRET}"
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8))

        // Build the POST request to get the token
        def request = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic $encodedAuth")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build()

        def response = client.send(request, HttpResponse.BodyHandlers.ofString())

        if (response.statusCode() != 200) {
            throw new RuntimeException("Authorization failed: ${response.body()}")
        }

        // Parse JSON response to extract the token
        def json = new JsonSlurper().parseText(response.body())
        return json.access_token
    }

    // Method to fetch tracks from a specific playlist
    static List getPlaylistTracks(String token, String playlistId) {
        def client = HttpClient.newHttpClient()

        // Build GET request for the playlist tracks
        def request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/playlists/$playlistId/tracks?limit=50"))
                .header("Authorization", "Bearer $token")
                .GET()
                .build()

        def response = client.send(request, HttpResponse.BodyHandlers.ofString())

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to fetch playlist: ${response.body()}")
        }

        def json = new JsonSlurper().parseText(response.body())

        // Map the complex JSON structure into a simple list of maps
        return json.items.collect { item ->
            def track = item.track
            [
                    title: track.name,
                    artist: track.artists[0].name,
                    album: track.album.name,
                    popularity: track.popularity,
                    // Some tracks might not have a preview URL
                    previewUrl: track.preview_url ?: "No preview available",
                    spotifyUrl: track.external_urls.spotify
            ]
        }
    }

    // Method to save the data to a text file
    static void saveToFile(List tracks) {
        File file = new File("popular_hits.txt")
        file.withWriter('UTF-8') { writer ->
            writer.writeLine("=== MOST POPULAR SONGS (Spotify Global 50) ===\n")

            tracks.eachWithIndex { t, index ->
                writer.writeLine("${index + 1}. ${t.artist} - ${t.title}")
                writer.writeLine("   Album: ${t.album} | Popularity: ${t.popularity}/100")
                writer.writeLine("   Link: ${t.spotifyUrl}")
                writer.writeLine("-" * 40)
            }
        }
        println "Success! Data saved to file: ${file.absolutePath}"
    }
}