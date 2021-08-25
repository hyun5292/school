package pcop.dto;

import java.sql.SQLException;

public class Food_Stock_DTO {
	private String FS_PRODUCT;
	private String FS_PRICE;
	
	public Food_Stock_DTO(String fS_PRODUCT, String fS_PRICE) {
		super();
		FS_PRODUCT = fS_PRODUCT;
		FS_PRICE = fS_PRICE;
	}
	
	public Food_Stock_DTO() {
		
	}

	public String getFS_PRODUCT() {
		return FS_PRODUCT;
	}

	public void setFS_PRODUCT(String fS_PRODUCT) {
		FS_PRODUCT = fS_PRODUCT;
	}

	public String getFS_PRICE() {
		return FS_PRICE;
	}

	public void setFS_PRICE(String fS_PRICE) {
		FS_PRICE = fS_PRICE;
	}

}
