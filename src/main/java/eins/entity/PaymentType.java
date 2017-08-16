package eins.entity;

public enum PaymentType {
    CASH("готівка при отримані в офісі"),
    CASH_ON_DELIVERY("при отриманні на \"Новій пошті\""),
    CASHLESS("по перерахунку");

    private String uaName;

    PaymentType(String uaName) {
        this.uaName = uaName;
    }

    public String getUaName() {
        return uaName;
    }
}
