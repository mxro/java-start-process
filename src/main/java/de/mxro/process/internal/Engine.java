package de.mxro.process.internal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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

		new StreamReader(inputStream, new StreamListener() {

			@Override
			public void onOutputLine(final String line) {
				listener.onOutputLine(line);
			}

			@Override
			public void onError(final Throwable t) {
				listener.onError(new Exception(
						"Error while reading standard output", t));
			}

			@Override
			public void onClosed() {
				listener.onOutputClosed();
			}
		});

		new StreamReader(errorStream, new StreamListener() {

			@Override
			public void onOutputLine(final String line) {
				listener.onErrorLine(line);
			}

			@Override
			public void onError(final Throwable t) {
				listener.onError(new Exception(
						"Error while reading error output", t));
			}

			@Override
			public void onClosed() {

			}
		});

		return new XProcess() {

			@Override
			public synchronized void sendLine(final String line) {
				final BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(outputStream));
				try {
					writer.append(line);
				} catch (final IOException e) {
					listener.onError(e);
				}
			}

			@Override
			public void destory() {
				process.destroy();
			}
		};

	}

}
