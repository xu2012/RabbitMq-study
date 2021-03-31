import com.study.mq.RabbitMQApplication;
import com.study.mq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class RabbitTest {
    @Autowired
    private Sender sender;

    @Test
    public void send() {
        sender.sendBroadcast("send");
    }

    @Test
    public void derictA() {
        sender.derictA("derictA");
    }

    @Test
    public void derictB() {
        sender.derictB("derictB");
    }

    @Test
    public void derictNull() {
        sender.derictNull("null");
    }
}
