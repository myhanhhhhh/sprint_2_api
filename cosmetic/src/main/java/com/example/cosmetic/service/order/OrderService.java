package com.example.cosmetic.service.order;

import com.example.cosmetic.dto.ICartDto;
import com.example.cosmetic.dto.IOrderDetailDto;
import com.example.cosmetic.model.order.Order;
import com.example.cosmetic.model.order.OrderDetail;
import com.example.cosmetic.model.product.Product;
import com.example.cosmetic.model.user.AppUser;
import com.example.cosmetic.repository.IAppUserRepository;
import com.example.cosmetic.repository.ICartRepository;
import com.example.cosmetic.repository.IOrderRepository;
import com.example.cosmetic.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void createOrder(Integer idUser) throws Exception {
        AppUser appUser = appUserRepository.findById(idUser).orElse(null);
        if (appUser == null) {
            throw new Exception("Khong tim thay user");
        }
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        Order order = new Order();
        order.setDateOfOrder(String.valueOf(localDate));
        order.setTimeOfOrder(String.valueOf(localTime));
        order.setTotalMoney(0.0);
        order.setAppUser(appUser);
        order.setPaymentStatus(0);
        orderRepository.save(order);
    }

    @Override
    public void createOrderDetail(Integer idUser) throws Exception {
        Order order = orderRepository.findOrderById(idUser);
        List<ICartDto> cartDto = cartRepository.getAllCart(idUser);
        if (order == null) {
            throw new Exception("khong tim thay order");
        }
        for (ICartDto cart : cartDto) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findById(cart.getIdProduct()).orElse(null);
            if (product == null) {
                throw new Exception("Không tìm thấy sản phẩm");
            }
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setProduct(product);
            orderDetail.setPriceOfOrder(cart.getPrice());
            orderDetail.setOrder(order);
            product.setQuantity(product.getQuantity()- orderDetail.getQuantity());
            Integer isOrderDetailCreated = orderRepository.createOrderDetail(orderDetail);
            if (isOrderDetailCreated > 0) {
                Integer quantityOfOrderAfterPayment = product.getQuantity();
                productRepository.updateQuantityOfProduct(product.getId(), quantityOfOrderAfterPayment);
            } else {
                orderRepository.deleteById(order.getId());
            }
        }
        cartRepository.deleteCartByIdUser(idUser);
    }

    @Override
    public void updateTotalMoney(Integer idUser) throws Exception {
        System.out.println("_-------------------------");
        Order order = orderRepository.findOrderById(idUser);
        System.out.println("_-------------------------");
        System.out.println("------------" + order);
        List<IOrderDetailDto> orderDetailDtos = orderRepository.findOrderDetailById(order.getId());
        if (orderDetailDtos.isEmpty()) {
            throw new Exception("khong tim thay order chi tiet");
        }
        double total = 0;
        for (IOrderDetailDto orderDetailDto : orderDetailDtos) {
            total += orderDetailDto.getQuantity() * orderDetailDto.getPriceOfProduct();
        }
        orderRepository.updateTotalMoney(total * 8 / 100, order.getId());
    }

}
