package pcop.dto;

public class Food_Stock_DTO {
	private String FS_PRODUCT;
	private String FS_KIND;
	private int FS_PRICE;
	private int FS_SELL_PRICE;
	private int FS_SELL_COUNT;
	private int FS_NUM;
	private String FS_IMG;
	
		
	public Food_Stock_DTO(String fS_PRODUCT, String fS_KIND, int fS_PRICE, int fS_SELL_PRICE, int fS_SELL_COUNT,
			int fS_NUM, String fS_IMG) {
		super();
		FS_PRODUCT = fS_PRODUCT;
		FS_KIND = fS_KIND;
		FS_PRICE = fS_PRICE;
		FS_SELL_PRICE = fS_SELL_PRICE;
		FS_SELL_COUNT = fS_SELL_COUNT;
		FS_NUM = fS_NUM;
		FS_IMG = fS_IMG;
	}

	public Food_Stock_DTO() {
		
	}

	public String getFS_PRODUCT() {
		return FS_PRODUCT;
	}

	public void setFS_PRODUCT(String fS_PRODUCT) {
		FS_PRODUCT = fS_PRODUCT;
	}

	public String getFS_KIND() {
		return FS_KIND;
	}

	public void setFS_KIND(String fS_KIND) {
		FS_KIND = fS_KIND;
	}

	public int getFS_PRICE() {
		return FS_PRICE;
	}

	public void setFS_PRICE(int fS_PRICE) {
		FS_PRICE = fS_PRICE;
	}

	public int getFS_SELL_PRICE() {
		return FS_SELL_PRICE;
	}

	public void setFS_SELL_PRICE(int fS_SELL_PRICE) {
		FS_SELL_PRICE = fS_SELL_PRICE;
	}

	public int getFS_SELL_COUNT() {
		return FS_SELL_COUNT;
	}

	public void setFS_SELL_COUNT(int fS_SELL_COUNT) {
		FS_SELL_COUNT = fS_SELL_COUNT;
	}

	public int getFS_NUM() {
		return FS_NUM;
	}

	public void setFS_NUM(int fS_NUM) {
		FS_NUM = fS_NUM;
	}

	public String getFS_IMG() {
		return FS_IMG;
	}

	public void setFS_IMG(String fS_IMG) {
		FS_IMG = fS_IMG;
	}
	
	
}
