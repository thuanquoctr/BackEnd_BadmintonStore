package store.badminton.BadmintonStore.services;

import store.badminton.BadmintonStore.payloads.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto addAddress(AddressDto addressDto);
    AddressDto updateAddress(AddressDto addressDto);
    AddressDto getAddressById(long id);
    AddressDto deleteAddress(long id);
    List<AddressDto> getAllAddresses();
}
