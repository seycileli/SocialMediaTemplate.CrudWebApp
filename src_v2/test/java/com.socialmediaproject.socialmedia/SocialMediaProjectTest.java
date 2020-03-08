package com.casestudyproject.casestudy;

import com.casestudyproject.dao.UserProfileDao;
import com.casestudyproject.entities.UserProfile;
import com.casestudyproject.services.UserServices;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
class CasestudyApplicationTests {

	UserProfile userProfile = new UserProfile();
	UserServices userServices = new UserServices();

	public CasestudyApplicationTests(UserProfile userProfile, UserServices userServices) {
		this.userProfile = userProfile;
		this.userServices = userServices;

		/**
		 * Seeing if below class can be retrieved
		 */

		userServices.getUserProfile(userProfile);

		userServices.updateUser(new UserProfile(1, "",
				"", "", "", "", userProfile.getJoinDate()));

		//shouldn't be able to update ID number, supposed to be auto-generated

		userServices.deleteUser(1);

		userServices.searchByEmail(userProfile.getEmail());

		userServices.isUserValid(userProfile.getEmail(), userProfile.getPassword());

		userServices.createUser(new UserProfile(1, "",
				"", "", "", "", userProfile.getJoinDate()));

		//testing userServices

	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public UserServices getUserServices() {
		return userServices;
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	@Override
	public String toString() {
		return "CasestudyApplicationTests{" +
				"userProfile=" + userProfile +
				", userServices=" + userServices +
				'}';
	}


}
