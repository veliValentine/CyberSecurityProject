LINK: <link to the repository>

From frontpage you may access each fault. 
Faults are:
1. Injection flaw
2. Broken Authentication
3. Broken Access Control
4. Cross-Site Scripting (XSS)
5. Insufficient Logging & Monitoring

FLAW 1: Injection flaw

Code is under InjectionController.java and injection.html.

Injection Flaw allows access to information by injecting unwanted SQL query. A way to protect your application from injection flaw is to parametrizes the query.

This application asks for a name and then shows the information related to that name. For now application only shows persons name. It is possible to inject SQL query to show all personnel.

We are suppose to show information under our name. You can use example name ‘Dave’. Now application allows us so that we can inject query that shows us all the names and possible the information with the name. 

How to reproduce:
Open application
Click Injection
Submit text:' OR '1'='1
See all the Data

How To Fix It
Under method getName we are requesting String n, where n is the name of our search. 
Simply add methods to check that n does not contain anything suspicious.
Following method checks that n can’t contain anything we do not want to 
String unWantedCharacters
If n contains any of unWantedCharacters
	Stop
Else
	Execute query


FLAW 2: Broken Authentication
Code is under following classes: BrokenAuthController.java, login.java and sigup.html

Broken Authentication is a vulnerability in the authentication system. Examples of authentication vulnerabilities, for example, are allowing default usernames/passwords, use of plain text or weakly hashed passwords. 
This application simply saves a new user with username and password after checking that username doesn't exist. Application doesn't use any kind of hash in a password. Passwords are saved in plain text. It also allows well known passwords. 

How to fix it
One can fix this flaw by two quite simple steps. 
First steps is to check created users password against well known passwords and demanding user to use more complex password. Pseudocode for checking well known passwords. 
	String password
	List wellKnownPasswords
	If wellKnownPasswords contains password
		Pick new password
	Else
		Create user

Second step would be to add some kind of salt to password reposition. This way passwords wouldn’t be in a plain text. 

FLAW 3: Broken Access Control
Code under BrokenAccessController.java and login.html
Broken access control means that user can bypass access control. One way to do this is by modifying the URL. We are going to demonstrate this by allowing a non admin user access admin only site. Simple way to protect your application from this specific flaw is to check credentials of user.
We have already two users. First one without admin privileges where username and password is user. Second user has admin privileges. Username and password is admin. 
Loginpage works in a way that it checks if user exist in a repository and then redirects it to “/admin” or “/user” pages according users credentials.
Site is supposed to allow only admin users to access site “/admin”. Now anyone with knowledge of “/admin” URL can access site.

How to fix it
One way to fix this flaw is to add authentication check. This means that users, who is trying to access admin site, credentials are checked before pages content is shown. A way to do this is that we add information about logged user by using springs Authentication tool. This gives us access to logged user and a way to check logged users credentials. Now we know if we should show the required site to user or not.

FLAW 4: Cross-Site Scripting (XSS)
Code under XSSController.java and xss.html
Cross-site scripting allows unvalidated user inputs that include HTML or scripts. This application shows stored XSS flaw. This allows users to save HTML or scripts for later view. 
Application works in a way that allows user to save comment. User can insert HTML or scripts. For example
Write: <h1>Hello</h1>
Click Submit
You can see that you inserted HTML H1 
How to fix it
Way to fix this is to sanitize comments content before saving it. This may be done manually by exiting any HTML or script content. More general way is to use frameworks that automatically escape XSS by design. (Instead of using th:utext under xss.html use th:text)

Flaw 5: Insufficient Logging & Monitoring
Code under login.html and BrokenAccessController.java
This flaw doesn’t inform developer about unruly logins. It also allows penetration testing. A way of fixing this flaw is to ensure that all login information is properly saved with user credentials. 
This application simply doesn’t save any information regarding login information. 
How to fix it
We can fix this flaw by saving information regarding logins. We may save failed login attempts and lock username after multiple failed login attempts. This information is needed to save to server in a way that we don’t lose it. One way to do this is to write a .txt file regarding necessary information. This includes at least login timestamp and used username/password. 
One way to lock username that has too many failed login attempts is to add counter to login.java and a boolean locked. Then attach a response to locked username login attempt. 
