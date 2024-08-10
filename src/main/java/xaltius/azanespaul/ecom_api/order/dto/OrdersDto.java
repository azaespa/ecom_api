package xaltius.azanespaul.ecom_api.order.dto;

public record OrdersDto(int ordersId,
                        String transactionId,
                        String dateOrdered,
                        String customerName,
                        String customerAddress,
                        String customerEmail,
                        String customerMobile,
                        String productName,
                        String productImageUrl,
                        String sellerName
                        ){

}
