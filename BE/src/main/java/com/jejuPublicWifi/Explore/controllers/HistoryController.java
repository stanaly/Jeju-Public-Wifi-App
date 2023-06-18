
package com.jejuPublicWifi.Explore.controllers;

import com.jejuPublicWifi.Explore.models.History;
import com.jejuPublicWifi.Explore.models.Review;
import com.jejuPublicWifi.Explore.models.User;
import com.jejuPublicWifi.Explore.models.Wifi;
import com.jejuPublicWifi.Explore.repository.HistoryRepository;
import com.jejuPublicWifi.Explore.repository.UserRepository;
import com.jejuPublicWifi.Explore.repository.WifiRepository;
import com.jejuPublicWifi.Explore.security.jwt.JwtUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.boot.model.source.spi.HibernateTypeSource;
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
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UserRepository UserRepository;
    @Autowired
    WifiRepository WifiRepository;
    @Autowired
    HistoryRepository HistoryRepository;

    @GetMapping("/")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<History>> getAllHistory() {
        try {

            List<History> Histories = new ArrayList<History>(HistoryRepository.findAll());

            if (Histories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Histories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/wifi/{wifiId}")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<History> getHistoryByWifiID(@PathVariable("wifiId") Long wifiId) {
        Wifi wifi = WifiRepository.findById(wifiId).orElseThrow(() -> new IllegalArgumentException("Invalid wifi Id:" + wifiId));
        Optional<History> HistoryData = HistoryRepository.findByWifi(wifi);

        return HistoryData.map(History -> new ResponseEntity<>(History, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<History> getHistoryByUserId(@PathVariable("userId") Long userId) {

        User user = UserRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        Optional<History> HistoryData = HistoryRepository.findByUser(user);

        return HistoryData.map(History -> new ResponseEntity<>(History, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/")
    public ResponseEntity<History> createHistory(HttpServletRequest request, @RequestBody History history, @RequestBody Wifi wifi) throws ServletException, IOException {
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
                History _History = HistoryRepository.save(new History(history.getDate(), user, wifi));
                return new ResponseEntity<>(_History, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteHistory(@PathVariable("id") Long id) {
        try {
            HistoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /* // Update History by id
    @PutMapping("/{id}")
    public ResponseEntity<History> updateHistory(@PathVariable("id") String id, @RequestBody History History) {
        Optional<History> HistoryData = HistoryRepository.findById(id);

        if (HistoryData.isPresent()) {
            History _History = HistoryData.get();
            _History.setTitle(History.getTitle());
            _History.setDescription(History.getDescription());
            _History.setPublished(History.isPublished());
            return new ResponseEntity<>(HistoryRepository.save(_History), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     */


   /* Delete All Histories
    @DeleteMapping("/Histories")
    public ResponseEntity<HttpStatus> deleteAllHistories() {
        try {
            HistoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */


    /* // Find by published
    @GetMapping("/Histories/published")
    public ResponseEntity<List<History>> findByPublished() {
        try {
            List<History> Histories = HistoryRepository.findByPublished(true);

            if (Histories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Histories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

}
