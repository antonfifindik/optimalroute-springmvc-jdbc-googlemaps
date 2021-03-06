package com.optimalroute.app.interfaces;

import java.util.List;

import com.optimalroute.app.objects.Address;

public interface IAddressService {

	void insert(Address address);

	Address selectAddressById(int id);

	void delete(Address address);

	void delete(int id);

	void update(Address address);

	List<Address> findAllAddresses();
}
