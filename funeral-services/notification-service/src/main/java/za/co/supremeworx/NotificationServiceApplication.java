package za.co.supremeworx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics="notificationTopic")
	public void handleNotification() {
		
	}
}
