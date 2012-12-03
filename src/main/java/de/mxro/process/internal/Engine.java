package de.mxro.process.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.mxro.process.ProcessListener;
import de.mxro.process.XProcess;

public class Engine {

	public static XProcess startProcess(final String command,
			final ProcessListener listener, final File folder) {

		final Process process;
		final String[] cmd = command.split(" ");
		try {
			process = Runtime.getRuntime().exec(cmd, null, folder);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}

		final OutputStream outputStream = process.getOutputStream();
		final InputStream inputStream = process.getInputStream();
		final InputStream errorStream = process.getErrorStream();

		return new XProcess() {

			@Override
			public void sendLine(final String line) {

			}

			@Override
			public void destory() {

			}
		};

	}

}
