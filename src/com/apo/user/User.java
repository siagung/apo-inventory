package com.apo.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.apo.operator.DBOperator;

/**Contains static methods that can be used to repeatedly reference
 * the currently logged in user; use UserFacilitator (com.apo.facilitator) when interacting
 * with actual user tables
 * 
 * @author Kevin Panuelos
 *
 */
public class User {
	/**For use with getFullName(), last name displayed first**/
	public static final int LAST_NAME_FIRST = 10;
	/**For use with getFullName(), first name displayed first**/
	public static final int FIRST_NAME_FIRST = 11;
	
	/**The user type table in the database**/
	private static final String USER_TYPE_TABLE = "user_type";

	/**The user name of the employee**/
	private static String userName;
	/**The first name of the employee**/
	private static String firstName;
	/**The middle name of the employee**/
	private static String middleName;
	/**The last name of the employee**/
	private static String lastName;
	/**The user type of the employee**/
	private static UserType userType;
	
	/**Convenience method for setting user credentials
	 * 
	 * @param userName User name of the employee
	 * @param firstName First name of the employee
	 * @param middleName Middle name of the employee
	 * @param lastName Last name of the employee
	 * @param userType User type of the employee (use User.UserType)
	 */
	public static void setUserCredentials (String userName, String firstName, String middleName, String lastName, UserType userType) {
		if (userName != null) {
			User.setUserName(userName);
		}
		if (firstName != null) {
			User.setFirstName(firstName);
		}
		if (middleName != null) {
			User.setMiddleName(middleName);
		}
		if (lastName != null) {
			User.setLastName(lastName);
		}
		if (userType != null) {
			User.setUserType(userType);
		}
	}
	
	/**get the current user name of the current user
	 * 
	 * @return The user name of the current user
	 */
	public static String getUserName() {
		return userName;
	}

	/**set the user name of the current user
	 * 
	 * @param userName The user name to be set
	 */
	public static void setUserName(String userName) {
		User.userName = userName;
	}

	/**get the first name of the current user
	 * 
	 * @return the first name of the current user
	 */
	public static String getFirstName() {
		return firstName;
	}

	/**set the first name of the current user
	 * 
	 * @param firstName The first name of the user to be set
	 */
	public static void setFirstName(String firstName) {
		User.firstName = firstName;
	}

	/**get the middle name of the current user
	 * 
	 * @return the middle name of the current user
	 */
	public static String getMiddleName() {
		return middleName;
	}

	/**set the middle name of the current user
	 * 
	 * @param middleName the middle name of the user to be set
	 */
	public static void setMiddleName(String middleName) {
		User.middleName = middleName;
	}

	/**get the last name of the current user
	 * 
	 * @return the last name of the current user
	 */
	public static String getLastName() {
		return lastName;
	}

	/**set the last name of the current user
	 * 
	 * @param lastName the last name of the user to be set
	 */
	public static void setLastName(String lastName) {
		User.lastName = lastName;
	}

	/**get the user type of the current user
	 * 
	 * @return the user type of the current user
	 */
	public static UserType getUserType() {
		return userType;
	}

	/**set the user type of the current user
	 * 
	 * @param userType The user type of the current user to be set
	 */
	public static void setUserType(UserType userType) {
		User.userType = userType;
	}
	
	/**Check if a privilege applies to the current user's user type
	 * 
	 * @param privilege The privilege to be checked
	 * @param operator The DBOperator object used to check with the database
	 * @return whether the privilege is granted or not for the current user
	 * @throws SQLException the sql server may not be operational
	 */
	public static boolean checkPrivilege (Privilege privilege, DBOperator operator) throws SQLException {
		return checkPrivilege(privilege, userType, operator);
	}
	
	/**Get the user's name; setting middleName and lastName to false is pretty much like the
	 * getFirstName() method
	 * 
	 * @param precedence refer to the constants FIRST_NAME_FIRST and LAST_NAME_FIRST
	 * @param middleName whether the middle name is to be shown
	 * @param middleNameShortened if middleNameShortened is set to true, only appends the middle initial of the name; not used when middleName is false (put anything on this parameter if middleName is false)
	 * @param lastName whether the last name is to be shown
	 * @return first name, at the very least; if LAST_NAME_FIRST, last name is set to the String object first with an appended comma; if FIRST_NAME_FIRST, first name is placed first
	 */
	public static String getName (int precedence, boolean middleName, boolean middleNameShortened, boolean lastName) {
		StringBuilder name = new StringBuilder();
		if (precedence == User.FIRST_NAME_FIRST) {
			name.append(User.firstName);
			if (middleName) {
				if (middleNameShortened) {
					name.append(User.middleName.charAt(0) + ".");
				}
				else {
					name.append(User.middleName);
				}
			}
			if (lastName) {
				name.append(User.lastName);
			}			
		}
		else {
			if (lastName) {
				name.append(User.lastName).append(",");
			}
			if (middleName) {
				if (middleNameShortened) {
					name.append(User.middleName.charAt(0) + ".");
				}
				else {
					name.append(User.middleName);
				}
			}
			name.append(User.firstName);
		}
		return name.toString();
	}
	
	private static boolean checkPrivilege (Privilege privilege, UserType userType, DBOperator operator) throws SQLException {
		String where = "type_name = " + userType.getValue() + " AND " + "privilege_id = " + privilege.getValue();
		ResultSet queryResult = operator.query(false, null, USER_TYPE_TABLE, where, null, null, null);
		boolean privilegeToggle = queryResult.getBoolean("privilege_toggle");
		return privilegeToggle;
	}
	
	public static enum UserType {
		ADMINISTRATOR("Administrator"),
		MEDIUM("Medium"),
		REGULAR("Regular");
		
		private final String userType;
		UserType(String userType) {
			this.userType = userType;
		}
		
		public String getValue() {
			return userType;
		}
	}
	
	public static enum Privilege {
		ADD_EMPLOYEE(1), 
		UPDATE_EMPLOYEE(2),
		DELETE_EMPLOYEE(3),
		RESTORE_EMPLOYEE(4),
		PROMOTE_DEMOTE_EMPLOYEE(5),
		VIEW_EMPLOYEE(6),
		ADD_PRODUCT(7),
		UPDATE_PRODUCT(8),
		RESTORE_PRODUCT(9),
		DELETE_PRODUCT (10),
		VIEW_PRODUCT(11),
		ADD_PRODUCT_CATEGORY(12),
		UPDATE_PRODUCT_CATEGORY(13),
		DELETE_PRODUCT_CATEGORY(14),
		VIEW_PRODUCT_CATEGORY(15),
		ADD_SUPPLIER(16),
		UPDATE_SUPPLIER(17),
		RESTORE_SUPPLIER(18),
		DELETE_SUPPLIER(19),
		VIEW_SUPPLIER(20),
		VIEW_SUPPLIER_PRODUCT(21),
		ADD_EDIT_CUSTOMER(22),
		DELETE_CUSTOMER(23),
		RESTORE_CUSTOMER(24),
		VIEW_CUSTOMER(25),
		ADD_CUSTOMER_VISIT(26),
		EDIT_CUSTOMER_VISIT(27),
		DELETE_CUSTOMER_VISIT(28),
		ADD_ORDER(29),
		EDIT_ORDER(30),
		VIEW_ORDER(31),
		DELETE_ORDER(32),
		RESTORE_ORDER(33),
		ADD_PAYMENT(34),
		EDIT_PAYMENT(35),
		DELETE_PAYMENT(36),
		READ_ANNOUNCEMENT(37),
		POST_ANNOUNCEMENT(38),
		VIEW_CUSTOMER_HISTORY(39),
		VIEW_SALES_REPORT(40),
		VIEW_PRODUCT_PURCHASES(41),
		VIEW_STOCK_REPORT(42);
		
		private final int index;
		Privilege(int id) {
			this.index = id;
		}
		
		public int getValue() { return index; }
	}
	
}
