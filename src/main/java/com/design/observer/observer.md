옵저버 패턴 (Observer Pattern) 
---
### 객체의 상태값이 변경되면 그 객체의 의존하는 다른 객체에게 연락이 가고 자동으로 갱신되는 패턴

![image](https://github.com/miyeon48/study-design-pattern/assets/17865304/a757a754-5a70-4ebc-b532-93d5bb1c8135)

### 필요 상황
✔ 객체의 상태변화에 대한 처리가 필요할 때

✔ 이벤트 처리 및 핸들링이 필요할 때

✔ 통신

분산 시스템에서 효과적인 통신? -> 출판 구독 패턴도 많이 사용됨


### 옵저버 vs 출판-구독 패턴
> 작성예정

### 옵저버 패턴 적용예제

> 스프링 프레임워크 이벤트 동작원리 

- ApplicationListener ▶ Observer
- ApplicationEventPublisher ▶ Subject

1. 옵저버 생성 (리스너 등록) 
```
@Component
public class ObserverListenerA implements ApplicationListener<AppEvent> {
    
    @Override
    public void onApplicationEvent(AppEvent event) {
        System.out.println(event.getName() + " Listener A");
    }
}
```
```
@Component
public class ObserverListenerB implements ApplicationListener<AppEvent> {
    
    @Override
    public void onApplicationEvent(AppEvent event) {
        System.out.println(event.getName() + " Listener B");
    }
}
```

2. 정보 갱신

```
@Component
public class AppRunner implements ApplicationRunner {
 
    @Autowired
    ApplicationEventPublisher eventPublisher;
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventPublisher.publishEvent(new AppEvent(this, "Observer"));        
    }
}

```
