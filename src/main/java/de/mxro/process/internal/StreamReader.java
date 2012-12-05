package de.mxro.process.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamReader {

	// private final InputStream stream;
	// private final StreamListener listener;
	private final Thread t;
	private volatile boolean stop = false;
	private volatile boolean stopped = false;

	public void stop() {
		System.out.println("stop");
		if (stopped) {
			return;
		}
		stop = true;
		while (!stopped) {
			Thread.yield();
		}
	}

	public StreamReader(final InputStream stream, final StreamListener listener) {
		super();
		// this.stream = stream;
		// this.listener = listener;
		this.t = new Thread() {

			@Override
			public void run() {
				final BufferedReader reader = new BufferedReader(
						new InputStreamReader(stream));
				try {
					String read;
					System.out.println("start reading");
					while (!reader.ready()) {
						if (stop) {
							stream.close();
							stopped = true;
							listener.onClosed();
							return;
						}
					}
					while ((read = reader.readLine()) != null && !stop) {
						System.out.println("read");
						listener.onOutputLine(read);
					}
					System.out.println("done");
					stream.close();
					stopped = true;
					listener.onClosed();
				} catch (final IOException e) {
					listener.onError(e);
				}

			}

		};
		this.t.start();
	}

}
