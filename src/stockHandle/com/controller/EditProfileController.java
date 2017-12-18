package stockHandle.com.controller;

import stockHandle.com.connection.EditProfileDAO;
import stockHandle.com.model.UserBean;

public class EditProfileController {
  public void editProfile(UserBean ub)
  {
	  EditProfileDAO editProfileDAO = new EditProfileDAO();
	  editProfileDAO.editUserProfile(ub);
  }
}
