템플릿메소드 패턴 (template Method Pattern) 
---
#### 알고리즘의 골격을 메소드로 정의한 패턴, 알고리즘의 일부 단계를 서브클래스에서 구현함. 구조를 유지하며 서브클래스에서 재정의 할 수 있음

### 필요상황
- 유지보수 향상 
한 클래스에 알고리즘이 구조가 집중되어있어 일부 구현만 서브 클래스에 의존할 수 있음 (공통된 부분 구조화)



### 템플릿메소드 패턴 적용예제1
💡카드발급 과정을 템플릿메소드 패턴으로 만들어보자 


```JAVA
abstract class CardIssuingTemplate {

    public final void issueCard() {
        selectCardProduct(); //카드 상품
        customizeCard(); //카드 디자인유형, 후불교통카드 기능 등 상세선택
        if(isCreditProuct()){
            rateCredit(); //신용평가
        }
        createCard(); //카드생성
    }

    protected boolean isCreditProuct() {
        return true;
    }

    protected abstract void selectCardProduct();
    protected abstract void customizeCard();
    protected void rateCredit() {
        System.out.println("발급기준 확인");

    }

    protected void createCard() {
        System.out.println("카드 생성");
    }
}

class CreditCardIssuing extends CardIssuingTemplate {

    @Override
    protected void selectCardProduct() {
        System.out.println("신용카드발급 > 상품선택");
    }

    @Override
    protected void customizeCard() {
        System.out.println("신용카드 기능선택");
        // 카드 한도와 혜택 설정 로직 구현
    }
}

class CheckCardIssuing extends CardIssuingTemplate {

    @Override
    protected void selectCardProduct() {
        System.out.println("체크카드발급 > 상품선택");
    }

    @Override
    protected void customizeCard() {
        System.out.println("체크카드 기능선택");
    }

    
    @Override
    protected boolean isCreditProuct() {
        // 후불교통기능이 있는 체크카드만 신용평가 대상이 됨
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        CardIssuingTemplate creditCardIssuing = new CreditCardIssuing();
        CardIssuingTemplate checkCardIssuing = new CheckCardIssuing();

        creditCardIssuing.issueCard();
        System.out.println("---------------------");
        checkCardIssuing.issueCard();
    }
}
```