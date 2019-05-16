# IPO Framework (Input Process Output Framework)

![image](docs/images/IPO.png)

## Maven

```
	<repositories>
		<repository>
			<id>jitpack.io</id>
			<url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependencies>
		<dependency>
			<groupId>com.github.netkiller</groupId>
			<artifactId>ipo</artifactId>
			<version>1.0.1</version>
		</dependency>
	</dependencies>
	
```

## Demo

```java
	
	Input input = new Input();
	// StdinInput stdin = new StdinInput();
	input.add(new StdinInput());
	// input.add(new FileInput(file.getURI().getPath()));
	
	Output output = new Output();
	output.add(new StdoutOutput());
	
	Process process = new Process();
	process.add(new Replace("Hello", "Netkiller "));
	process.add(new Replace("Neo", "<Neo>"));
	process.add(new Replace("Tom", "[Tom]"));
	 
	InputProcessOutput ipo = new InputProcessOutput();
	
	ipo.setInput(input);
	ipo.setProcess(process);
	ipo.setOutput(output);
	ipo.launch();

```