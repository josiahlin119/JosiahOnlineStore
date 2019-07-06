package OnlineStore_Josiah;
import java.sql.Date;

public class Items {

	private int itemId;
	private String title,descriptions, sellerId;
	private String category;
	private Price price = new Price();
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	protected Items(int itemId, String sellerId, String title, String descriptions, double amount,Date postTime,String category) {
	
		this.sellerId = sellerId;
		this.itemId = itemId;
		this.title = title;
		this.category = category;
		this.descriptions = descriptions;
		price.setAmount(amount);
		price.setPostDate(postTime);
	}
	public static Items createItemsInDAO(int itemId, String sellerId, String title, String descriptions, double amount,Date postTime,String category) {
		return new Items(itemId,sellerId,title, descriptions, amount,postTime,category);
		
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


	
}

class Price{
	double amount;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public java.sql.Date getPostDate() {
		return postTime;
	}
	public void setPostDate(java.sql.Date postTIme) {
		this.postTime = postTIme;
	}
	private java.sql.Date postTime;
	
	
}
