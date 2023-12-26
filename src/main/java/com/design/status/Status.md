상태 패턴 (Status Pattern) 
---
#### 내부 상태가 바뀜에 따라 객체의 행동이 바뀔수 있도록 해주는 패턴


--- 
### 상태패턴 적용예시 I

> 💡 고객 상태정보와 관련하여 패턴을 적용해보자 

- 휴면고객 : 장기간 카드를 사용하지 않아 카드시스템을 이용할 수 없는 고객
- 일반고객 : 일반적인 카드시스템을 이용할 수있는 고객 
- VIP고객 : 카드사용율이 높아 포인트 및 페이백 등 추가 혜택을 받는 고객 

#### 소스코드 
```JAVA 
// State 인터페이스
// 모든 상태 클래스에서 사용할 인터페이스
public interface CustomerState {
    void createCard(); 
    void useCard();    
    void receiveBenefits(); 
	void upgrade(Customer customer); // 상태 레퍼런스 전달  
}
```

```JAVA 
// 휴면고객 상태클래스
public class DormantCustomer implements CustomerState {

    @Override
    public void createCard() {
        System.out.println("휴면고객은 카드를 만들수없습니다.");
    }

    @Override
    public void useCard() {
        System.out.println("휴면고객은 카드를 사용할 수 없습니다");
    }

    @Override
    public void receiveBenefits() {
        System.out.println("휴면고객은 혜택을 받을 수 없습니다.");
    }

    @Override
	public void upgrade(Customer customer) {
		customer.setState(new RegularCustomer());
	}
}


// 일반고객 상태클래스
public class RegularCustomer implements CustomerState {
    @Override
    public void createCard() {
        System.out.println("일반고객은 카드를 만들 수 있습니다. ");
    }

    @Override
    public void useCard() {
        System.out.println("일반고객은 카드를 사용할 수 있습니다.");
    }

    @Override
    public void receiveBenefits() {
        System.out.println("추가적인 혜택이 없습니다.");
    }

    @Override
	public void upgrade(Customer customer) {
		customer.setState(new VipCustomer());
	}
}


// VIP고객 상태클래스
public class VipCustomer implements CustomerState {

    @Override
    public void createCard() {
        System.out.println("VIP고객은 카드를 만들수있습니다.");
    }

    @Override
    public void useCard() {
        System.out.println("VIP고객은 카드를 사용할 수 있습니다.");
    }

    @Override
    public void receiveBenefits() {
        System.out.println("추가적인 할인 혜택을 받을수있습니다.");
    }

	@Override
	public void upgrade(Customer customer) {
		System.out.println("더이상 업그레이드 할수없습니다.");
	}
}
```

```JAVA 
// 고객 클래스 (컨택스트 객체)
// 객체의 내부 상태를 관리하고 상태가 변경될 때마다 해당 상태에 따른 행동을 수행하는 것 
public class Customer {
    private CustomerState currentState;

    public Customer() {
        this.currentState = new RegularCustomer();
    }

    // 상태 변경 메서드
    public void setState(CustomerState newState) {
        this.currentState = newState;
    }

    public void createCard() {
        currentState.createCard();
    }

    public void useCard() {
        currentState.useCard();
    }

    public void receiveBenefits() {
        currentState.receiveBenefits();
    }

	public void upgrade() {
        currentState.upgrade(this);
    }
}

```
```JAVA 
//메인클래스 
public class Main {
    public static void main(String[] args) {

		//일반고객
        Customer customer = new Customer();
        customer.createCard();
        customer.useCard();
        customer.receiveBenefits();

        // VIP로 상태 변경
     	customer.upgrade();
	 	customer.useCard();
        customer.receiveBenefits();

        // 휴면 상태로 상태 변경
        customer.setState(new DormantCustomer());
        customer.useCard();
        customer.receiveBenefits();
    }
}
```

