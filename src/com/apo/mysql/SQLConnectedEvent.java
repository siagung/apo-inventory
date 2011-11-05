package com.apo.mysql;

import java.util.EventObject;

/**Event for when the connection to the SQL server has been established**/
public class SQLConnectedEvent extends EventObject {

	/**Create a new SQLConnectedEvent
	 * 
	 * @param source The source of the event
	 */
	public SQLConnectedEvent(Object source) {
		super(source);
	}

}
