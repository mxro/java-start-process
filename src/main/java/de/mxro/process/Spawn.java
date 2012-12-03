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
	public static XProcess startProcess(final String command,
			final File folder, final ProcessListener listener) {
		return Engine.startProcess(command, listener, folder);
	}

}
