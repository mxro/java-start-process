package de.mxro.process;

/**
 * A wrapper for {@link java.lang.Process}.
 * 
 * @author <a href="http://www.mxro.de/">Max Rohde</a>
 * 
 */
public interface Process {

	/**
	 * Try to destroy the process
	 */
	public void destory();

}
