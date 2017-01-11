package isep.web.sakila.webapi.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.StoreRepository;
import isep.web.sakila.jpa.entities.Store;
import isep.web.sakila.webapi.model.StoreWO;

@Service("storeService")
@Transactional
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	private static final Log log = LogFactory.getLog(StoreServiceImpl.class);

	@Override
	public List<StoreWO> findAllStores() {
		List<StoreWO> store = new LinkedList<StoreWO>();

		for (Store stores : storeRepository.findAll()) {
			store.add(new StoreWO(stores));
			log.debug("Adding " + stores);
		}

		return store;
	}

	@Override
	public StoreWO findById(int id) {
		log.debug(String.format("Looking for store by Id %s", id));
		Store store = storeRepository.findOne(id);

		if (store != null) {
			return new StoreWO(store);
		}
		return null;
	}

	@Override
	public void saveStore(StoreWO storeWO) {
		Store store = new Store();
		store.setStoreId(storeWO.getStoreId());
		store.setStaff(storeWO.getStaff());
		store.setAddress(storeWO.getAddress());
		storeRepository.save(store);
	}

	@Override
	public void updateStore(StoreWO storeWO) {
		int id = storeWO.getStoreId();
		Store store = storeRepository.findOne(storeWO.getStoreId());
		store.setStoreId(storeWO.getStoreId());
		store.setStaff(storeWO.getStaff());
		store.setAddress(storeWO.getAddress());
		storeRepository.save(store);
	}

	@Override
	public void deleteStoreById(int id) {
		storeRepository.delete(id);
	}
}
