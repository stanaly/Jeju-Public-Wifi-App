package com.jejuPublicWifi.Explore.controllers;

import com.jejuPublicWifi.Explore.models.Wifi;
import com.jejuPublicWifi.Explore.repository.WifiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wifi")
public class WifiController {

    @Autowired
    WifiRepository WifiRepository;

    @GetMapping("/")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Wifi>> getAllWifi(@RequestParam(required = false) String addressDetail) {
        try {
            List<Wifi> Wifis = new ArrayList<Wifi>();

            if (addressDetail == null)
                Wifis.addAll(WifiRepository.findAll());
            else
                Wifis.addAll(WifiRepository.findByAddressDetailContaining(addressDetail));

            if (Wifis.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Wifis, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Wifi> getWifiById(@PathVariable("id") Long id) {
        Optional<Wifi> WifiData = WifiRepository.findById(id);

        return WifiData.map(wifi -> new ResponseEntity<>(wifi, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/")
    public ResponseEntity<Wifi> createWifi(@RequestBody Wifi wifi) {
        try {
            Wifi _Wifi = WifiRepository.save(new Wifi(wifi.getMacAddress(), wifi.getApGroupName(), wifi.getInstallLocationDetail(), wifi.getCategory(), wifi.getCategoryDetail(), wifi.getAddressDong(), wifi.getAddressDetail(), wifi.getLatitude(), wifi.getLongitude(), wifi.getScore(), wifi.getCongestion()));
            return new ResponseEntity<>(_Wifi, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* // Update Wifi by id
    @PutMapping("/{id}")
    public ResponseEntity<Wifi> updateWifi(@PathVariable("id") String id, @RequestBody Wifi Wifi) {
        Optional<Wifi> WifiData = WifiRepository.findById(id);

        if (WifiData.isPresent()) {
            Wifi _Wifi = WifiData.get();
            _Wifi.setTitle(Wifi.getTitle());
            _Wifi.setDescription(Wifi.getDescription());
            _Wifi.setPublished(Wifi.isPublished());
            return new ResponseEntity<>(WifiRepository.save(_Wifi), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWifi(@PathVariable("id") Long id) {
        try {
            WifiRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


   /* Delete All Wifis
    @DeleteMapping("/Wifis")
    public ResponseEntity<HttpStatus> deleteAllWifis() {
        try {
            WifiRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */


    /* // Find by published
    @GetMapping("/Wifis/published")
    public ResponseEntity<List<Wifi>> findByPublished() {
        try {
            List<Wifi> Wifis = WifiRepository.findByPublished(true);

            if (Wifis.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Wifis, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

}
