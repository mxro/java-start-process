java-start-process
==================

**Important** This project has moved to [javadelight/delight-java-start-process](https://github.com/javadelight/delight-java-start-process) **Important**

Wrapper to make work with Runtime.getRuntime() easier and less error-prone.

## Why

Starting another program from within a Java application is easy to do. Just provide the path to the application to the method Runtime.exec() 
and the JVM will launch the specified application. However, as with so many supposedly simple tasks in software development, 
the devil lies in the detail. It is all too easy to introduce bugs around a call to Runtime.exec(), especially when one wants 
to pass parameters or read the output of the launched application.

This library is designed to make the task of starting applications from Java programs easier.

## Usage

1. Link the project as Maven dependency or as JAR to your project (or just download and copy&paste the packages).
2. Start external process in your Java application.


### Starting a process synchronously

    Spawn.runCommand(new String[] { "/bin/bash", "-c",
                    "vmstat -s -S M | egrep -ie 'memory|swap' | grep 'free memory'" },
                            null);

### Starting a process asynchronously

    final String cmd = "C:\\Windows\\System32\\NETSTAT.exe -ab";
	final File folder = new File("C:\\Windows\\System32\\");

	Spawn.startProcess(cmd, folder, new ProcessListener() {

		@Override
		public void onOutputLine(final String line) {
			System.out.println(line);
		}

		@Override
		public void onOutputClosed() {
			System.out.println("process completed");
		}

		@Override
		public void onErrorLine(final String line) {

		}

		@Override
		public void onError(final Throwable t) {
			throw new RuntimeException(t);
		}
	});

	Thread.sleep(600);

## Maven Dependency

#### Artifact

	<groupId>de.mxro.process</groupId>
	<artifactId>java-start-process</artifactId>
	<version>[0.0.1,)</version>

#### Repository

    <repositories>
        <repository>
            <id>onedb Releases</id>
            <url>http://dl.dropbox.com/u/957046/onedb/mvn-releases</url>
        </repository>
    </repositories>

## Resources

[When Runtime.exec() won't](http://www.javaworld.com/jw-12-2000/jw-1229-traps.html?page=4)

[Executing a Command Line Executable From Java](http://developer4life.blogspot.co.nz/2013/01/executing-command-line-executable-from.html)

[Five Common java.lang.Process Pitfalls](http://kylecartmell.com/?p=9)

[Apache Commons Exec](http://commons.apache.org/exec/)

[SO: Java Process with Input/Output Stream](http://stackoverflow.com/questions/3643939/java-process-with-input-output-stream)

[SO: Starting a process in Java?](http://stackoverflow.com/questions/3774432/starting-a-process-in-java)
