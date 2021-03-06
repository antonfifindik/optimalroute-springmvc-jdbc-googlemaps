package com.optimalroute.app.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optimalroute.app.interfaces.IAddressService;
import com.optimalroute.app.interfaces.IOrderService;
import com.optimalroute.app.objects.Address;
import com.optimalroute.app.objects.OrderForCourier;
import com.optimalroute.app.objects.OrderType;

@Controller
public class MapController {

	@Autowired
	private IAddressService addressService;
	@Autowired
	private IOrderService orderService;
	private ArrayList<OrderForCourier> ordersForCourierList;

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		if (HomeController.getCurrentAccount() != null) {

			ordersForCourierList = new ArrayList<>();
			orderService.findAllOrders().stream().filter(o -> o.getCourier().getId() == HomeController.getCurrentAccount().getCourier().getId() && !o.isStatus()).forEach(o -> {
				OrderForCourier ofcSender = new OrderForCourier();
				ofcSender.setIdOrder(o.getId());
				ofcSender.setAddress(o.getSenderAddress());
				ofcSender.setOrderType(OrderType.SENDER);
				ofcSender.setClient(o.getSender());

				OrderForCourier ofcRecipient = new OrderForCourier();
				ofcRecipient.setIdOrder(o.getId());
				ofcRecipient.setAddress(o.getRecipientAddress());
				ofcRecipient.setOrderType(OrderType.RECIPIENT);
				ofcRecipient.setClient(o.getRecipient());

				ordersForCourierList.add(ofcSender);
				ordersForCourierList.add(ofcRecipient);
			});
		}

		ArrayList<Address> expulsionList = new ArrayList<>();
		List<Address> addressesList = addressService.findAllAddresses();

		for (OrderForCourier ofc : ordersForCourierList)
			expulsionList.add(ofc.getAddress());

		addressesList.removeAll(expulsionList);

		model.addAttribute("ordersForCourierList", ordersForCourierList);
		model.addAttribute("account", HomeController.getCurrentAccount());
		model.addAttribute("addressesList", addressesList);

		return "map";
	}

	@RequestMapping(value = "/getNewAddressAll", method = RequestMethod.GET)
	public @ResponseBody String[] getAllAddresses() {

		String[] result = { "Вінниця, Батозька, 21", "Вінниця, Пирогова, 358", "Вінниця, Соборна, 10", "Вінниця, Ігоря Костецького, 12", "Вінниця, Воїнів-Інтернаціоналістів, 3", "Вінниця, Келецька, 105", "Вінниця, Зодчих, 28", "Вінниця, Бучми, 126",
				"Вінниця, Блока, 3" };

		return result;
	}

	@RequestMapping(value = "/getTenAddressesByCourier", method = RequestMethod.GET)
	public @ResponseBody String[] getTenAddressesByCourier() {

		String[] result = new String[(ordersForCourierList.size() > 10 ? 50 : ordersForCourierList.size() * 5)];

		for (int i = 0, j = 0; i < (ordersForCourierList.size() > 10 ? 50 : ordersForCourierList.size() * 5); i += 5, j++) {
			result[i] = String.valueOf(ordersForCourierList.get(j).getIdOrder());
			result[i + 1] = ordersForCourierList.get(j).getAddress().getCity() + ", " + ordersForCourierList.get(j).getAddress().getStreet() + ", " + ordersForCourierList.get(j).getAddress().getHouseNumber();
			result[i + 2] = String.valueOf(ordersForCourierList.get(j).getOrderType());
			result[i + 3] = ordersForCourierList.get(j).getAddress().toString();
			result[i + 4] = ordersForCourierList.get(j).getClient().getShortName();
		}

		return result;
	}

}
