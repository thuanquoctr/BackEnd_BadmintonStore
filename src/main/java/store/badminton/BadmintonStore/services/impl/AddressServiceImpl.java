package store.badminton.BadmintonStore.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.badminton.BadmintonStore.entities.Address;
import store.badminton.BadmintonStore.payloads.AddressDto;
import store.badminton.BadmintonStore.repositories.AddressRepo;
import store.badminton.BadmintonStore.services.AddressService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepo addressRepo;
    private ModelMapper modelMapper;

    @Autowired
    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
        modelMapper = new ModelMapper();
    }

    @Override
    public AddressDto addAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        addressRepo.save(address);
        return toAddressDto(address);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        return null;
    }

    @Override
    public AddressDto getAddressById(long id) {
        return toAddressDto(addressRepo.getById((int) id));
    }

    @Override
    public AddressDto deleteAddress(long id) {
        return null;
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepo.findAll();
        List<AddressDto> addressDtos = addresses.stream().map(address -> this.toAddressDto(address)).collect(Collectors.toList());
        return addressDtos;
    }

    private Address toAddress(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }

    private AddressDto toAddressDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }
}
