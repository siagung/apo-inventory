**Contents**


# Introduction #

As a team consisting of different people, there can be no such thing as an imposition of a coding style, because coding is a reflection of one's brain. That said, there should at least be a few things to remember before you code that will be beneficial for everyone involved when testing starts.

# Ground Rules #
## New Classes ##
When creating a new class, always:
  * Use the new Log class. It is flexible enough to support a few coding styles.<br>
<blockquote>What it does is that it prints out a debug/error message to console. It requires an identifier, called a TAG, before a message.<br><br>
While the Log class requires you to specify a TAG, you can also use the Object or Class itself to let the Log class handle everything for you.<br>
</blockquote><ul><li>If you've specified a TAG String variable, you can just either use<br>
<blockquote><code>Log.debug(TAG, message)</code> or <code>Log.error(TAG, message)</code>
</blockquote></li><li>If you don't want to specify a TAG variable, you can just use the instance of the class<br>
<blockquote><code>Log.debug(this, message)</code> or <code>Log.error(this, message)</code>
</blockquote></li><li>If you, for some reason, use the .class or .getClass() methods, you can also roll that way<br>
<blockquote><code>Log.debug(this.getClass(), message)</code> or <code>Log.debug(ClassName.class, message)</code></blockquote></li></ul></li></ul>

<blockquote>String TAGs are more flexible and readable, since they can also hold more user-friendly descriptors. Alternatively, using class instances or class names are more granular and help trace the source class (complete with package hierarchy).</blockquote>

<ul><li>Assess whether functionality you're including in a class already exists in another class.<br>
<ul><li>If you found general-purpose utility methods from another class and you want it transferred to a dedicated class, let the author of that class know!</li></ul></li></ul>

<h2>Editing Classes</h2>
If you think you have a better implementation of an existing class, remember to <b>always create a new branch first</b>. While the wonders of version control allow easy reverting when things go wrong, <i>always think about how you can save all the hassle</i>.<br>
<br>
If your implementation works and the team agrees that it's better, your branch can be merged later with the main branch. <i>It's not like we're using Subversion or anything.</i>

<h2>Committing</h2>
In your machine, you must commit if you've changed something, and make your commits count. Or at the very least, commit after every "milestone" of your code (eg, a method was implemented). This is helpful when you choose to redo a part of your code, and then it, for whatever reason, breaks a lot of stuff (although we generally try to avoid writing code that does that :D).<br>
<br>
Remember to put a message. Not only is this because it's a requirement, but it is also generally good practice, especially if you are going to revert in the future.<br>
<br>
<h2>Pushing</h2>
Committing is different from Pushing... sort of. Think of Committing as saving a new local copy to your machine. Think of Pushing as uploading your code to the cloud, ie, here. Your commits are uploaded as well, and therefore your messages are too.<br>
<br>
<h2>Comments Policy</h2>
Always put comments. Preferably in Javadoc syntax, since we're working in Java.<br>
<br>
As a refresher, every method, variable, and class can have a description written before their signatures. Javadoc starts with / and ends with /. If you create a method signature (and/or implementation), you can rely on your IDE to supply the proper details (like @param, @return, @throws, etc.) automatically.<br>
<br>
For example:<br>
<pre><code>/** Establishes a connection to server; doesn't store user name and password, just server URL **/<br>
public class Server {<br>
...<br>
        /** establishes a connection to the MySQL server<br>
	 * <br>
	 * @param username The username to the MySQL Server<br>
	 * @param password The password to the MySQL Server<br>
	 * @param url The URL could be over IP, etc. Follow: [host][,failoverhost...][:port]/[database][?propertyName1][=propertyValue1][&amp;propertyName2][=propertyValue2]...; leaving the field null will establish the connection to localhost<br>
	 * @param dbName The name of the database to be accessed; leaving this null only connects to the server<br>
	 * @throws DatabaseNotFoundException the database could not be found<br>
	 * @throws SQLException the database has incorrect data<br>
	 */<br>
	public Server (String username, String password, String url, String dbName) throws DatabaseNotFoundException, SQLException {<br>
		// implementation here				<br>
	}<br>
}<br>
</code></pre>

Aside from this, any methods with a complex implementation can have methods within the implementation. It's not only for everyone else, but for yourself. How would you be able to improve it later on if you chose to?<br>
<br>
<h2>Wiki Updates</h2>
If you have a great idea, and you've implemented it or wish to implement it, explain what you're thinking about in an article. It doesn't have to be extremely comprehensive, although that helps. People using your classes will be able to understand them better this way.<br>
<br>
If there are also issues that need a lot more elaboration, you better make a Wiki of it as well.<br>
<br>
Additionally, you should probably check back here occasionally. These ground rules will either change or have something added. Happy coding!