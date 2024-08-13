package store.badminton.BadmintonStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.badminton.BadmintonStore.payloads.AddressDto;
import store.badminton.BadmintonStore.services.AddressService;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable int id) {
        AddressDto addressDto = addressService.getAddressById(id);
        return addressDto != null ? ResponseEntity.ok(addressDto) : ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addressDtos = addressService.getAllAddresses();
        return addressDtos != null ? ResponseEntity.ok(addressDtos) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.addAddress(addressDto));
    }

}
