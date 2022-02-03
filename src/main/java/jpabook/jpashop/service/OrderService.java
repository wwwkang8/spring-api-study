package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     * */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //직접 생성자 사용해서 set으로 사용할 수 있지만, 그러면 로직이 이쪽저쪽에서 사용해서 관리 어려움

        //주문 저장
        orderRepository.save(order);

        return order.getId();

    }

    /*
    * 취소
    * */
    @Transactional
    public void canCelOrder(Long orderId){
        // 주문 엔티티 조회 후 취소
        Order order = orderRepository.findOne(orderId);

        //주문 취소
        order.cancel();
    }

    // 검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll();
//    }


}
