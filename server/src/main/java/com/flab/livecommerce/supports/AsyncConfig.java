package com.flab.livecommerce.supports;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/*
비동기 처리를 위한 별도의 스레드풀 생성
: @Async 어노테이션을 사용하면 Default로 SimpleAsyncTaskExecutor가 생성되어 사용된다.
그러나 이는 어떤 스레드도 재사용하지 않고 호출마다 새로운 스레드를 생성하기 때문에 요청이 많은
경우, 성능이 저하된다. 따라서, 새로운 스레드 풀을 생성하고 이를 프로젝트 특성에 맞게 튜닝한 후
빈으로 등록한다.
TASK_CORE_POOL_SIZE: 기본적으로 실행을 대기하고 있는 스레드 수
TASK_MAX_POOL_SIZE: 최대로 생성할 수 있는 스레드 수
TASK_QUEUE_CAPACITY: 요청받은 작업을 저장할 최대 갯수(이 크기보다 많은 작업이 큐에 들어와야 poolSize가 증가한다.)
THREAD_NAME_PREFIX: spring이 생성하는 스레드의 접두사

적정 스레드 개수 = CPU 코어 수 * (1 + 대기시간/작업시간) -> CPU * 2로 최대 쓰레드 수 지정을 한다.
 */

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    private static final Logger log = LoggerFactory.getLogger(AsyncConfig.class);
    private static final int TASK_QUEUE_CAPACITY = 50;
    private static final int KEEP_ALIVE_SECOND = 60;
    private static final String THREAD_NAME_PREFIX = "AsyncExecutor-";

    @Override
    public Executor getAsyncExecutor() {
        var taskExecutor = new ThreadPoolTaskExecutor();
        int processors = Runtime.getRuntime().availableProcessors();
        log.info("processors count {}", processors);

        taskExecutor.setCorePoolSize(processors);
        taskExecutor.setMaxPoolSize(processors * 2);
        taskExecutor.setQueueCapacity(TASK_QUEUE_CAPACITY);
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECOND);
        taskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        taskExecutor.initialize();

        return taskExecutor;
    }

}
