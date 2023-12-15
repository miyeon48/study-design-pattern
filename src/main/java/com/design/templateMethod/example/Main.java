package com.design.templateMethod.example;

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