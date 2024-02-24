package net.dashboard.projectstatproduct.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
@Builder
public class Product {

    @Id
	private String id;

	private String productName;

    private Long idProduct;

	private String categoryProduct;

	private double unitPrice;

	private Date expiredDate;

}
