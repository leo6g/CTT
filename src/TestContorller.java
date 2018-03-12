import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContorller {
	private final String helloTemplate = "你好，%s";
	private final AtomicLong counter = new AtomicLong();
	@RequestMapping("/sayHello")
	@ResponseBody
	public Greeting sayHello(@RequestParam(value="name",defaultValue="world") String name) {
		return new Greeting(counter.incrementAndGet(),String.format(helloTemplate, name));
	}
}
