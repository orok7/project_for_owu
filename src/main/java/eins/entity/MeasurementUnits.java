package eins.entity;

public enum MeasurementUnits {
    SHT("шт."), PAC("уп."), KG("кг");

    private String uaName;

    MeasurementUnits(String uaName) {
        this.uaName = uaName;
    }

    public String getUaName() {
        return uaName;
    }
}
