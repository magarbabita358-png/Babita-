package models;

public class Equipment extends InventoryItem {

    private String assetId;
    private String brand;
    private int warrantyMonths;
    private String category;

    public Equipment(String id, String name, boolean isAvailable,
                     String assetId, String brand, int warrantyMonths, String category) {

        super(id, name, isAvailable);
        this.assetId = assetId;
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
        this.category = category;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getItemType() {
        return "Equipment";
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Asset ID: " + assetId +
               ", Brand: " + brand +
               ", Warranty (months): " + warrantyMonths +
               ", Category: " + category;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Equipment other = (Equipment) obj;

        return assetId.equals(other.assetId);
    }
}
