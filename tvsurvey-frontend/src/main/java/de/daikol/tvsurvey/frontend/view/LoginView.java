package de.daikol.tvsurvey.frontend.view;

/**
 * The View for the login.
 */
public interface LoginView {
    
    /**
     * Der Name der View.
     */
    String NAME = "login";
    
	/**
	 * Diese Methode wird aufgerufen sobald der Login scheitert.
	 *
	 */
	void showLoginFailed();

	/**
	 * Dieses Interface stellt einen Listener dar. Dieser Listener wird aufgerufen, sobald der Login erfolgen soll.
	 *
	 * @author Marcel Becker (becker.marcel@postdirekt.de)
	 * @version $Revision: 254 $
	 *
	 */
	interface LoginViewListener {
	    
		/**
		 * Diese Methode wird verwendet um sich einzuloggen.
		 *
		 * @param user Der Benutzername.
		 * @param pass Das Passwort.
		 */
		void login(String user, String pass);
	}
}
