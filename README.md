# LiveCommerce

## 프로젝트 목표

---  
이커머스 기반 서비스를 개발하는 것이 목표


## 사용한 기술 & 개발 환경 

--- 

- JAVA11
- SpringBoot 2.7.2
- Gradle
- MySQL
- flyway
- JdbcTemplate
- Redis
- Docker


## Elastic APM Setting

--- 
**apm-server**

```dockerfile
docker-compose -f apm-docker-compose.yml up
```

**apm-agent options**   
```
-javaagent:./agent/elastic-apm-agent-1.34.0.jar
-Delastic.apm.service_name=Livecommerce
-Delastic.apm.server_url=http://localhost:8200
-Delastic.apm.application_packages=com.flab.*
-Delastic.apm.transaction_sample_rate=1
-Delastic.apm.enable_log_correlation=true
-Delastic.apm.span_frames_min_duration=1ms
-Delastic.apm.span_min_duration=0ms
-Delastic.apm.trace_methods_duration_threshold=1ms
-Delastic.apm.trace_methods=com.flab.*
```