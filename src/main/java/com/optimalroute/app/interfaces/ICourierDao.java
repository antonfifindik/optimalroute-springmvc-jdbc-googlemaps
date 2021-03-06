package com.optimalroute.app.interfaces;

import java.util.List;

import com.optimalroute.app.objects.Courier;

public interface ICourierDao {

	void insert(Courier courier);

	Courier selectCourierById(int id);

	void delete(Courier courier);

	void delete(int id);

	void update(Courier courier);

	List<Courier> findAllCouriers();

}
