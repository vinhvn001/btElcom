package elcom.ex1.librarybooks.message.rabbitmq.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class WorkerConfig {

    @Bean("worker_queue")
    public Queue initWorkerQueue(){
        return new Queue("worker_queue");
    }

    private static class ReceiverConfig {
        @Bean
        public WorkerReceiver workerReceiver1() {
            return new WorkerReceiver(1);
        }
        @Bean
        public WorkerReceiver workerReceiver2() {
            return new WorkerReceiver(2);
        }
        @Bean
        public WorkerReceiver workerReceiver3() {
            return new WorkerReceiver(3);
        }
        @Bean
        public WorkerReceiver workerReceiver4(){
            return new WorkerReceiver(4);}
    }

    @Bean
    public WorkerSender workerSender() {
        return new WorkerSender();
    }
}
