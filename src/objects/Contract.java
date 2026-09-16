package objects;


public class Contract {

    private String contractId;
    private String propertyId;
    private String buyerId;
    private String sellerId;
    private String mostajerId;
    private int amount;
    private String contractType;

    public Contract(String contractId, String propertyId, String buyerId, String sellerId, String mostajerId, int amount, String contractType) {
        this.contractId = contractId;
        this.propertyId = propertyId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.mostajerId = mostajerId;
        this.amount = amount;
        this.contractType = contractType;
    }

    public String getContractId() {return contractId;}
    public String getPropertyId() {return propertyId;}
    public String getBuyerId() {return buyerId;}
    public String getSellerId() {return sellerId;}
    public String getMostajerId() {return mostajerId;}
    public long getAmount() {return amount;}
    public String getContractType() {return contractType;}

    public void setAmount(int amount) {this.amount = amount;}

    @Override
    public String toString(){
        return "Contract ID: "+contractId+
                "| Propety ID: "+propertyId+
                "| Buyer ID: "+buyerId+
                "| Price: "+amount;
    }
}
