package de.mxro.process;

import de.mxro.process.internal.Engine;

public class Spawn {

	public static Process process(final String command,
			final ProcessListener listener) {
		return Engine.startProcess(command, listener);
	}

}
