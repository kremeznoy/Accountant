package accountant.service;

import java.util.Set;

import accountant.models.db.ProfileDb;

public interface UserProfileService {
	Set<ProfileDb> findAll();
	ProfileDb findById(int id);
	ProfileDb findByType(String type);
}
