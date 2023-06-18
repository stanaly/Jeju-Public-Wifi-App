
package com.jejuPublicWifi.Explore.controllers;

import com.jejuPublicWifi.Explore.models.Review;
import com.jejuPublicWifi.Explore.models.User;
import com.jejuPublicWifi.Explore.models.Wifi;
import com.jejuPublicWifi.Explore.payload.response.ReviewResponse;
import com.jejuPublicWifi.Explore.repository.ReviewRepository;
import com.jejuPublicWifi.Explore.repository.UserRepository;
import com.jejuPublicWifi.Explore.repository.WifiRepository;
import com.jejuPublicWifi.Explore.security.jwt.JwtUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UserRepository UserRepository;
    @Autowired
    WifiRepository WifiRepository;
    @Autowired
    ReviewRepository ReviewRepository;

    @GetMapping("/")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Review>> getAllReview() {
        try {

            List<Review> Reviews = new ArrayList<Review>(ReviewRepository.findAll());

            if (Reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/wifi/{wifiId}")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Review> getReviewByWifiID(@PathVariable("wifiId") Long wifiId) {
        Wifi wifi = WifiRepository.findById(wifiId).orElseThrow(() -> new RuntimeException("Error: Wifi is not found."));
        Optional<Review> ReviewData = ReviewRepository.findByWifi(wifi);

        return ReviewData.map(Review -> new ResponseEntity<>(Review, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Review> getReviewByUserId(@PathVariable("userId") Long userId) {

        User user = UserRepository.findById(userId).orElseThrow(() -> new RuntimeException("Error: User is not found."));
        Optional<Review> ReviewData = ReviewRepository.findByUser(user);

        return ReviewData.map(Review -> new ResponseEntity<>(Review, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Review> createReview(HttpServletRequest request, @RequestBody Review review, @RequestBody Wifi wifi) throws ServletException, IOException {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        } catch (IOException e) {
            // Handle the exception
        }
        String body = requestBody.toString();

        String jwt = jwtUtils.getJwtFromCookies(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            try {
                User user = UserRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User is not found."));
                Review _Review = ReviewRepository.save(new Review(review.getDate(),review.getScore(), review.getDetail(), user, wifi));
                return new ResponseEntity<>(_Review, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable("id") Long id) {
        try {
            ReviewRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    /* // Update Review by id
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable("id") String id, @RequestBody Review Review) {
        Optional<Review> ReviewData = ReviewRepository.findById(id);

        if (ReviewData.isPresent()) {
            Review _Review = ReviewData.get();
            _Review.setTitle(Review.getTitle());
            _Review.setDescription(Review.getDescription());
            _Review.setPublished(Review.isPublished());
            return new ResponseEntity<>(ReviewRepository.save(_Review), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     */


   /* Delete All Reviews
    @DeleteMapping("/Reviews")
    public ResponseEntity<HttpStatus> deleteAllReviews() {
        try {
            ReviewRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */


    /* // Find by published
    @GetMapping("/Reviews/published")
    public ResponseEntity<List<Review>> findByPublished() {
        try {
            List<Review> Reviews = ReviewRepository.findByPublished(true);

            if (Reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

}
