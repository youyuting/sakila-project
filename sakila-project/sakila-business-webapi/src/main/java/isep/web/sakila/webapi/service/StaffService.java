package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.webapi.model.StaffWO;

public interface StaffService {

	List<StaffWO> findAllStaffs();

	Staff signInStaff2(String username, String password);

}
