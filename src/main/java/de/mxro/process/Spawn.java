package de.mxro.process;

import java.io.File;

import de.mxro.process.internal.Engine;

public class Spawn {

	/**
	 * Start a new process.
	 * 
	 * @param command
	 * @param listener
	 * @param folder
	 * @return
	 */
	public static XProcess process(final String command,
			final ProcessListener listener, final File folder) {
		return Engine.startProcess(command, listener, folder);
	}

}
