package eins.entity;

public enum InvoiceStatus {
    DRAFT("В очікуванні"), CANCELED("Скасоване"), IN_WORK("В роботі"), DONE("Виконане");

    private String uaName;

    InvoiceStatus(String uaName) {
        this.uaName = uaName;
    }

    public String getUaName() {
        return uaName;
    }
}
