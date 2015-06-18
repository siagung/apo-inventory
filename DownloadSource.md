# Introduction #

For this project, we are using **Git** instead of Mercurial. They're pretty similar, except that **Git** is faster overall. This high-level guide won't be very detailed, but it will nonetheless be a bit of help in setting up Google Code with Eclipse.


# EGit #

We're using [Eclipse](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/junom2) as the IDE of choice for this project. Apparently the newest release (Juno) has bulit-in Git support, but I'll pretend that you, like me, have a Pre-Juno version of Eclipse.

To get EGit, go to Eclipse, press the Help menu, and select _Install New Software..._. On the _Work With..._ text box, copy and paste "http://download.eclipse.org/egit/updates" without quotation marks. Press Enter/Return.

There will be packages there. Select Eclipse Git Team Provider, agree to the terms, and click Finish. It will install the software and ask you to restart Eclipse afterwards.

On your workspace, look at the panel below the coding area. (The panel where command line/system.out prints are typically shown) Look for a tab labeled Git Repositories. There will be a few buttons along the right side. Hover over them to view tooltips and look for **Clone a Git repository and add a clone to this view" and click on it.**

A window will open. Go to Google Code, look for this project and click **Source**. By default the _Checkout_ view is selected. Copy the URL under **Option 1** minus the `git clone` into the URI field of Eclipse. Check for any spaces and erase them.

Under authentication, change %40 to @ and put in your Google Code password. This can be found on your Google Code **Profile** (check top right side of the Google Code page). On the **Profile** page click **Settings**. Copy the Google Code password there to the password field in Eclipse.

In Eclipse, click Next and select the Master branch (or if any other branches are available, you may choose to select them as well). Next browse for a folder in your computer where you want to put in the project files. Click Finish and you should have the accompanying project ready to go.

It should look like this:
![http://apo-inventory.googlecode.com/git/Git%20setup%201.png](http://apo-inventory.googlecode.com/git/Git%20setup%201.png)

# Committing, Pushing, Pulling #
## Commit ##
When committing, go to the Git Repositories tab on the lower part of the Eclipse workspace and right click the repository (in this case, it's _apo-inventory_ or whatever you named it), and select _Commit..._. Create a message and confirm the Commit.

## Push ##
Pushing means sending your changes to Google Code where other users can Pull your changes. On the Git Repositories tab, right-click the repository again and select _Push to Upstream..._ or _Push..._.

In the case of _Push..._, you'll have to configure where you're uploading the code, which is probably already supplied, so no biggie. Just click _Finish_.

## Pull ##
Pulling means you're getting updates from other people's Pushes to Google Code. Just go to Git Repositories, right-click the repository again and click _Pull from Upstream..._ or just _Pull..._.

# Branching #
Branching means you want to create new code separate from the "official" code. It's like getting a copy of the master branch and isolating it as a sandbox for your tinkering pleasure. You can do anything you want with it without affecting the Master branch.

If you actually do something substantial, other users can choose to merge your branch with the official branch.

## Creating a Branch ##
I won't cover merging for now, but creating a branch with Eclipse is also a matter of navigating to Git Repositories on the panel below the coding space and right-clicking the correct repository. Hover over _Switch to..._ and select _New Branch..._.

You're on your own to decide what to fill up on the forms.