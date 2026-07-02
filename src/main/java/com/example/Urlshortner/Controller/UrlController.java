package com.example.Urlshortner.Controller;

//import org.springframework.http.HttpStatus.FOUND;
import com.example.Urlshortner.model.UrlRequest;
import com.example.Urlshortner.Service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UrlController {

    private final UrlService urlService;

    // Constructor Injection
    public UrlController(UrlService urlService) {
        // TODO: Assign the injected UrlService to the class field
        this.urlService = urlService;
    }

    // POST: Create Short URL
    @PostMapping("/api/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody UrlRequest request) {

        // TODO: Call the service to generate the short code using the original URL from the request
        String shortCode = urlService.shortenUrl(request.getOriginalUrl());

        // TODO: Return the short code as a JSON map (Key: "shortCode", Value: variable shortCode)
        return ResponseEntity.ok(Collections.singletonMap("shortCode", shortCode));
    }

    // GET: Redirect to Original URL
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {

        // TODO: Retrieve the original URL from the service using the shortCode
        String originalUrl = urlService.getOriginalUrl(shortCode);

        // Check if the URL exists
        if (originalUrl == null) {
            // If URL not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // TODO: Create a Redirect Response (HTTP 302)
        // Hint: Use URI.create() to convert the string URL to a URI object
        return ResponseEntity.status(org.springframework.http.HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}


