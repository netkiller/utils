# Queue example

```
Queue --> IPO --> Queue

```

```java
package cn.netkiller.test;

import java.util.LinkedList;
import java.util.Queue;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.input.QueueInput;
import cn.netkiller.ipo.output.QueueOutput;
import cn.netkiller.ipo.process.string.StringReplace;
import cn.netkiller.ipo.Process;

public class Test {

	public static void main(String[] args) {

		Queue<Object> iQueue = new LinkedList<Object>();
		Queue<Object> oQueue = new LinkedList<Object>();
		iQueue.offer("Neo");
		iQueue.offer("Jam");
		Input input = new Input(new QueueInput(iQueue));
		Output output = new Output(new QueueOutput(oQueue));
		Process process = new Process();
		process.add(new StringReplace("Neo", "Netkiller "));

		InputProcessOutput ipo = new InputProcessOutput("Queue");
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);
		ipo.launch();

		System.out.println(oQueue.toString());
	}

}
```

输出信息

```
15:26:52.791 [main] DEBUG cn.netkiller.ipo.Input - Open cn.netkiller.ipo.input.QueueInput
15:26:52.795 [main] DEBUG cn.netkiller.ipo.Output - Open cn.netkiller.ipo.output.QueueOutput
15:26:52.795 [main] DEBUG cn.netkiller.ipo.Process - Open cn.netkiller.ipo.process.string.StringReplace
15:26:52.795 [main] DEBUG cn.netkiller.ipo.InputProcessOutput - ==================== Begin ====================
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Input - cn.netkiller.ipo.input.QueueInput Neo
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Process - cn.netkiller.ipo.process.string.StringReplace Netkiller 
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Output - Netkiller 
15:26:52.796 [main] DEBUG cn.netkiller.ipo.InputProcessOutput - ---------- Queue ----------
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Input - cn.netkiller.ipo.input.QueueInput Jam
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Process - cn.netkiller.ipo.process.string.StringReplace Jam
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Output - Jam
15:26:52.796 [main] DEBUG cn.netkiller.ipo.InputProcessOutput - ---------- Queue ----------
15:26:52.796 [main] DEBUG cn.netkiller.ipo.InputProcessOutput - ---------- Queue ----------
15:26:52.796 [main] DEBUG cn.netkiller.ipo.InputProcessOutput - ==================== End ====================
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Process - Close cn.netkiller.ipo.process.string.StringReplace
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Output - Close cn.netkiller.ipo.output.QueueOutput
15:26:52.796 [main] DEBUG cn.netkiller.ipo.Input - Close cn.netkiller.ipo.input.QueueInput
[Netkiller , Jam]
```