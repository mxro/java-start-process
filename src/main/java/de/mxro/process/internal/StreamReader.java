package de.mxro.process.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamReader {

	private final class WorkerThread extends Thread {
		private final StreamListener listener;
		private final InputStream stream;

		private WorkerThread(final StreamListener listener,
				final InputStream stream) {
			this.listener = listener;
			this.stream = stream;
		}

		@Override
		public void run() {
			final BufferedReader reader = new BufferedReader(
					new InputStreamReader(stream));
			try {
				String read;

				while (true) {
					while (!reader.ready()) {
						if (stop) {
							stopReader();
							return;
						}
					}

					if (stop) {
						stopReader();
						return;
					}
					System.out.println("start read");
					read = reader.readLine();
					System.out.println("stop read");
					if (read != null) {
						listener.onOutputLine(read);
					}
				}

			} catch (final IOException e) {
				listener.onError(e);
			}

		}

		private void stopReader() throws IOException {
			stream.close();
			stopped = true;
			listener.onClosed();
		}
	}

	private final Thread t;
	private volatile boolean stop = false;
	private volatile boolean stopped = false;

	public void stop() {

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
		this.t = new WorkerThread(listener, stream);
		this.t.start();
	}

}
