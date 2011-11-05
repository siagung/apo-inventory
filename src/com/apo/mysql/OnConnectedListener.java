package com.apo.mysql;

import java.util.EventObject;

/**Listener for when the connection to the SQL Server has been established**/
public interface OnConnectedListener {
	/**When connection has been confirmed, what do you do?
	 * 
	 * @param e
	 */
	public void onConnectionConfirmed(EventObject e);
}
