package OnlineStore_Josiah;
import java.sql.Date;

public class Items {

	private int itemId, authorId;
	private String title,content,description, seller;
	
	private Price price;
	
	public String getAuthorName() {
		return seller;
	}
	public void setAuthorName(String authorName) {
		this.seller = authorName;
	}
	protected Items(int itemId, String seller, String title, String content, String description, double amount,Date postDate) {
	
		this.seller = seller;
		this.itemId = itemId;
		this.title = title;
		this.content = content;
		this.description = description;
		price.setAmount(amount);
		price.setPostDate(postDate);
	}
	public int getId() {
		return itemId;
	}
	public void setId(int id) {
		this.itemId = id;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}


	
}
class Category{
	String [] categories;
	
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
		return postDate;
	}
	public void setPostDate(java.sql.Date postDate) {
		this.postDate = postDate;
	}
	private java.sql.Date postDate;
	
	
}
