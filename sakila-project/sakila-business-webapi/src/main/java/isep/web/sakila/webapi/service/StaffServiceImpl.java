package isep.web.sakila.webapi.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import isep.web.sakila.dao.repositories.StaffRepository;
import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.webapi.model.StaffWO;

public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffRepository staffRepository;

	private static final Log log = LogFactory.getLog(StaffServiceImpl.class);

	@Override
	public List<StaffWO> findAllStaffs() {
		List<StaffWO> staffs = new LinkedList<StaffWO>();

		for (Staff staff : staffRepository.findAll()) {
			staffs.add(new StaffWO(staff));
			log.debug("Adding " + staff);
		}
		System.out.println(staffs.size());

		return staffs;
	}

	@Override
	public Staff signInStaff2(String username, String password) {
		return staffRepository.findByUsernameByPassword(username, password);
	}

}
