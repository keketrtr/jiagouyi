package com.sishuok.architecture1.ordermgr.queue;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sishuok.architecture1.cartmgr.service.ICartService;
import com.sishuok.architecture1.cartmgr.vo.CartModel;
import com.sishuok.architecture1.cartmgr.vo.CartQueryModel;
import com.sishuok.architecture1.ordermgr.service.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.service.IOrderService;
import com.sishuok.architecture1.ordermgr.vo.OrderDetailModel;
import com.sishuok.architecture1.ordermgr.vo.OrderModel;
import com.sishuok.architecture1.ordermgr.vo.OrderQueryModel;
import com.sishuok.architecture1.storemgr.service.IStoreService;
import com.sishuok.architecture1.storemgr.vo.StoreModel;
import com.sishuok.pageutil.Page;
import com.sishuok.util.format.DateFormatHelper;
@Service
public class QueueReceiver implements ServletContextListener {
	@Resource
	private ICartService cartService;
	@Resource
	private IOrderService orderService;
	@Resource
	private IOrderDetailService orderDetailService;
	@Resource
	private IStoreService storeService;
	
	public void acceptMsg() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.16.15.111:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("order-queue");
		MessageConsumer consumer = session.createConsumer(destination);
		
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage txtMsg = (TextMessage) message;
				int customerUuid;
				try {
					customerUuid = Integer.parseInt(txtMsg.getText());
					session.commit();
				} catch (NumberFormatException | JMSException e) {
					e.printStackTrace();
					throw new RuntimeException("can not receive message");
				}
				
				CartQueryModel cartQueryModel = new CartQueryModel();
				cartQueryModel.getPage().setNumPerPage(1000);
				cartQueryModel.setCustomerUuid(customerUuid);
				Page<CartModel> page = cartService.getByConditionPage(cartQueryModel);
				//2、
				float totalMoney = 0.0f;
				for(CartModel cartModel : page.getResult()){
					totalMoney += 10;//10元一件商品
				}
				
				OrderModel orderModel = new OrderModel();
				orderModel.setCustomerUuid(customerUuid);
				orderModel.setOrderTime(DateFormatHelper.date2str(System.currentTimeMillis()));
				orderModel.setSaveMoney(0.0F);
				orderModel.setTotalMoney(totalMoney);
				orderModel.setState(1);
				
				orderService.create(orderModel);
				
				OrderQueryModel orderQueryModel = new OrderQueryModel();
				orderQueryModel.setOrderTime(orderModel.getOrderTime());
				Page<OrderModel> orderPage = orderService.getByConditionPage(orderQueryModel);
				orderModel = orderPage.getResult().get(0);
				
				//3、
				for(CartModel cartModel : page.getResult()){
					OrderDetailModel orderDetailModel = new OrderDetailModel();
					orderDetailModel.setGoodsUuid(cartModel.getGoodUuid());
					orderDetailModel.setOrderNum(cartModel.getBuyNum());
					orderDetailModel.setPrice(10.0f);
					orderDetailModel.setMoney(orderDetailModel.getPrice()*orderDetailModel.getOrderNum());
					orderDetailModel.setSaveMoney(0.0f);
					orderDetailModel.setOrderUuid(orderModel.getUuid());
					
					orderDetailService.create(orderDetailModel);
					//4、
					StoreModel storeModel = storeService.getByGoodsUuid(cartModel.getGoodUuid());
					storeModel.setStoreNum(storeModel.getStoreNum()-orderDetailModel.getOrderNum());
					storeService.update(storeModel);
					//5、
					cartService.delete(cartModel.getUuid());
				}
			}
		});
		
//		session.close();
//		connection.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		QueueReceiver receiver = ctx.getBean(QueueReceiver.class);
		try {
			receiver.acceptMsg();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("activemq接收端没能正常启动");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
