# The Short Version #
From the database, the raw SQL commands are wrapped as methods in _DBOperator_. The _Server_ is required to instantiate _DBOperator_ and its subclasses because the SQL server needs to be connected to.

The _RevisableDBOperator_ mostly accommodates the different design of the database. This is passed to a Facilitator object that is subclassed for each table.

# The Long Version #
Here's the thing: we have a custom-designed database that does loose **revisioning**. This means deleting doesn't mean deleting, it's not a straightforward affair. Reverting edits is easy and comparable.

For the user, this all sounds pretty awesome, but from an implementation standpoint, there is a bit of issue.

## Implementation: The Bigger Picture ##
In the grand scheme of things, coding a desktop application that connects to an SQL powered RDBMS will require quite a bit of abstraction to prevent coding hell. Coding hell means directly manipulating SQL statements from Java, which isn't exactly the best way to approach the connection between the two realms.

As such, the approach to developing the database will, from a high-level standpoint, be as follows.
![http://apo-inventory.googlecode.com/git-history/a1703657531263f08734300cd1d1508de462cdbd/ClassOverview1.png](http://apo-inventory.googlecode.com/git-history/a1703657531263f08734300cd1d1508de462cdbd/ClassOverview1.png)

### JDBC ###
For the data and data interactions, the MySQL database will be connected to the Java app via JDBC. JDBC also contains operations for statements, connections, et al. Making JDBC less of an error prone coding experience is a database wrapper class that I made in INTRODB.

### Database Wrapper Class ###
This wrapper class crafts direct methods for common database operations like query, insert, delete and update. Some of the method parameters will still involve some raw SQL syntax, but for the most part, it curbs hours of undetectable errors and SQLExceptions.

Even with the methods in place, it's still going to be a repetitive time coding query operations to retrieve the latest information for an entry in a table. This is why a subclass of the wrapper class should exist, which will be divulged later on.

### App Logic ###
There will be a standardized interface/abstract class that will need to have its methods implemented by subclasses, which correspond to the tables of the database.

These subclasses will be one of the least re-usable components of the program and are very dependent on the table's design (unless we're just talking about changing the UI of the current application). They will also be the final bridge between the user interface and the database operations.

### User Interface Classes ###
These will interact with the App Logic classes and ultimately use them as the sources of the underlying models the graphical components will display.

## Implementation: The Reality ##
In reality, these classes will be implemented with different names and perhaps a bit of a deviation from what is ideal, but the idea remains the same.

![http://apo-inventory.googlecode.com/git-history/a1703657531263f08734300cd1d1508de462cdbd/ClassOverview2.png](http://apo-inventory.googlecode.com/git-history/a1703657531263f08734300cd1d1508de462cdbd/ClassOverview2.png)

### Server and DBOperator ###
The _Server_ class would serve as the connector to the SQL Server (via JDBC). The _DBOperator_ would interface with the Server class. Its constructor would create a Server connection, thus the DBOperator would send out a login attempt to the SQL server. Authenticating the login would be possible via the DBOperator, where the connection status could be checked.

Since we're working with a revisable database, we'll need a subclass of _DBOperator_. Before we even delve on it, let's see what things we do at the database design level, so as to be able to explain why _RevisableDBOperator_ acts, and is coded, like it does.

### Database Design Level ###
From the database design level, columns _revisionId_, _head_ and _deleted_ are added to the main tables. This helps keep track of data that can be revised.

_Revising_ in this context means a copy of an item in a table is made every time an alteration is made. The _deleted_ column is a marker for when the user deletes an item, and the _head_ column is a marker for the official version of an item.

![http://apo-inventory.googlecode.com/git-history/cba90dd10e9a096cae9fdc861e2fc9d38841a7fa/RevisableDataTable.png](http://apo-inventory.googlecode.com/git-history/cba90dd10e9a096cae9fdc861e2fc9d38841a7fa/RevisableDataTable.png)

So to help curb the unwieldy chaos of making raw SQL statements that reference these columns over and over and over, these features have been abstracted into methods that can be accessed through the _RevisableDBOperator_ class.

### RevisableDBOperator ###
The _RevisableDBOperator_ class isn't perfect. It doesn't address tables that reference the main tables' identifiers. It only does main operations for the main tables. This means they work best on tables that contain the _revisionId_, _head_ and _deleted_ columns.

For linked columns, they'll have to be worked around using _DBOperator_'s methods in the app logic aspect of the program. This may be potentially messy, but still better than pure raw SQL.

_**Announcement:** I'll try implementing a standard method to avoid the workarounds. I think I have an idea how to do this. Stay tuned in the 'facilitator' branch._

### Facilitator ###
If a database can be operated by an operator, a facilitator can "facilitate" the transfer of data between the operator and interface displayed to the user. This represents the app logic, although they are not necessarily the listeners for buttons and components in the UI.

At the time of writing, even I am not sure how to facilitate the interaction between data and the UI, which is why I've made a branch of the code that you can pull, if you wish.

Anyway, _Facilitator_ is an abstract class that contains the _RevisableDBOperator_ object. As _Facilitator_ can contain a _RevisableDBOperator_, working around the class's limitations can be done within _Facilitator_, which are the most database-dependent classes, along with the UI classes.

_Facilitator_ is meant to be subclassed. These subclasses, by design, should ideally contain the column labels of a table, maybe even their indices (which may not always be a reliable design choice), and should also be named according to the table it's representing.

Methods that address or retrieve something else that is unique to the table, or any other tables referencing it, can be added on the coder's own volition.

### UI Classes ###
Ideally, reusable JPanel objects should be available for repeated use, for the interface is envisioned to be uniform a lot of the time. A mock-up by DJ can be found below, and can reflect what most of the features look like to the user.

![http://apo-inventory.googlecode.com/git/2-X%20Employee%20list.png](http://apo-inventory.googlecode.com/git/2-X%20Employee%20list.png)

# Let's Do This! #
Let's hope we get this finished early with this design. Let's go, team.