싱글톤 패턴 (Singleton Pattern) 
---
#### 클래스 인스턴스를 하나만 만들고, 그 인스턴스로의 접근을 제공하는 패턴


### 필요상황
- 인스턴스가 한개만 필요한 상황 
- ex. 쓰레드 풀, DB커넥션풀, 캐시, 그 외 설정 등등


### 일반적인 싱글톤의 구현
```JAVA
public class ChocolateBoiler {
	private static ChocolateBoiler uniqueInstance;
  
	private ChocolateBoiler() {}
  
	public static ChocolateBoiler getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ChocolateBoiler();
		}
		return uniqueInstance;
	}
}
```
--- 
### 멀티쓰레드 환경에서의 싱글톤 구현
멀티 스레드 환경에서 두 개 이상의 스레드가 인스턴스를 호출하는 과정에서 서로 다른 두 개의 인스턴스가 만들어 지는 상황이 발생할 수 있음

💡 <해결방법1> synchronized 키워드 사용
- 한 스레드가 메소드 사용을 끝내기 전까지 다른 스레드는 대기하게 하는 키워드

```JAVA
public class ChocolateBoiler {
	private static ChocolateBoiler uniqueInstance;
  
	private ChocolateBoiler() {}
  
	public static synchronized ChocolateBoiler getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ChocolateBoiler();
		}
		return uniqueInstance;
	}
}
```

💡 <해결방법2> 정적 초기화 시 인스턴스를 만듬
- 클래스가 로딩될 떄 JVM에서 SINGLETON의 하나뿐인 인스턴스를 생성해줌 
- 정적(static) 변수는 클래스가 로드될 때 메모리에 할당되며, 프로그램이 종료될 때까지 유지됨


```JAVA
public class ChocolateBoiler {
	private static ChocolateBoiler uniqueInstance  = new ChocolateBoiler ();
  
	private ChocolateBoiler() {}
  
	public static synchronized ChocolateBoiler getInstance() {
		return uniqueInstance;
	}
}
```

💡 <해결방법3> DCL (Double-checked-locking) 사용
- 인스턴스가 생성되어있는지 확인한 다음 생성되어있지 않았을 때만 동기화
- volatile 키워드 : 멀티스레드 환경에서 변수를 안전하게 읽기위해서 제공되는 키워드, 다른 쓰레드에서도 변경된 값으로 읽을 수 있게 함

```JAVA
public class ChocolateBoiler {
	private volatile static ChocolateBoiler uniqueInstance;
  
	private ChocolateBoiler() {}
  
	public static ChocolateBoiler getInstance() {
		if (uniqueInstance == null) {
            synchronized (Singleton.class){
			    if (uniqueInstance == null) {
                    uniqueInstance = new ChocolateBoiler();
                }
            }
		}
		return uniqueInstance;
	}
}
```

💡 <해결방법4> ENUM 사용
- 리플랙션, 직렬화, 역직렬화 등의 경우에는 private 멤버 접근을 허용하여 싱글톤에서 문제가 발생할 수 있음
- Enum은 자바에서 열거형을 나타내는 특수한 유형
- Enum은 JVM이 인스턴스를 생성, 관리, 보장하는 방식으로 동장함 
- Enum은 각 상수가 값이 할당되고 이후에는 변경되지않음 (final 키워드와 유사, 불변한 싱글톤)


```JAVA
public enum SingletonEnum {
    INSTANCE;

    private String data;

    SingletonEnum() {
        this.data = "Initial data";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}


public class Main {
    public static void main(String[] args) {
        // Enum 싱글톤 인스턴스 얻기
        SingletonEnum singleton = SingletonEnum.INSTANCE;

        // 데이터 출력
        System.out.println(singleton.getData());  // Initial data
    }
}
```


### <리플랙션, 직렬화, 역직렬화> 는 왜 싱글톤에서 문제가 될 수있을까? 
리플랙션(Reflection)
- 실행 중에 다른 객체의 정보를 얻어울 수있는 기능
- 클래스의 메타데이터를 검사하고 조작할 수 있는 기능을 제공, private 멤버를 포함한 모든 멤버에 접근할 수 있음

직렬화(Serialization)
- 객체의 상태를 저장하고 나중에 해당 상태를 복원하는 과정으로, 객체의 모든 멤버 변수를 저장하고 복원해야하기 때문에 , private 멤버에 대한 접근이 허용
