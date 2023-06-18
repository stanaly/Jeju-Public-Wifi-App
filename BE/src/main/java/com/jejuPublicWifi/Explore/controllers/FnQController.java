
package com.jejuPublicWifi.Explore.controllers;

import com.jejuPublicWifi.Explore.models.FnQ;
import com.jejuPublicWifi.Explore.repository.FnQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/fnq")
public class FnQController {

    @Autowired
    FnQRepository FnQRepository;

    @GetMapping("/")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<FnQ>> getAllFnQ() {
        try {

            List<FnQ> FnQs = new ArrayList<FnQ>(FnQRepository.findAll());

            if (FnQs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(FnQs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/")
    public ResponseEntity<FnQ> createFnQ(@RequestBody FnQ fnq) {
        try {
            FnQ _FnQ = FnQRepository.save(new FnQ(fnq.getQuestion(),fnq.getAnswer()));
            return new ResponseEntity<>(_FnQ, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFnQ(@PathVariable("id") Long id) {
        try {
            FnQRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /* // Update FnQ by id
    @PutMapping("/{id}")
    public ResponseEntity<FnQ> updateFnQ(@PathVariable("id") String id, @RequestBody FnQ FnQ) {
        Optional<FnQ> FnQData = FnQRepository.findById(id);

        if (FnQData.isPresent()) {
            FnQ _FnQ = FnQData.get();
            _FnQ.setTitle(FnQ.getTitle());
            _FnQ.setDescription(FnQ.getDescription());
            _FnQ.setPublished(FnQ.isPublished());
            return new ResponseEntity<>(FnQRepository.save(_FnQ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     */


   /* Delete All FnQs
    @DeleteMapping("/FnQs")
    public ResponseEntity<HttpStatus> deleteAllFnQs() {
        try {
            FnQRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */


    /* // Find by published
    @GetMapping("/FnQs/published")
    public ResponseEntity<List<FnQ>> findByPublished() {
        try {
            List<FnQ> FnQs = FnQRepository.findByPublished(true);

            if (FnQs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(FnQs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

}
