package lemonadestand;

import java.util.Scanner;

import lemonadestand.dao.CustomerDAO;
import lemonadestand.dao.LemonadeDAO;
import lemonadestand.dao.LemonadeStandDAO;
import lemonadestand.dao.OrderDAO;
import lemonadestand.entity.Customer;
import lemonadestand.entity.Lemonade;
import lemonadestand.entity.Order;

public class Application {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
//
		System.out.println("Welcome to our lemonade stand!");
		System.out.println("To make an order please provide name and phone number");

		String name = scanner.nextLine();

		System.out.println("Hi" + name + ", nice to meet you! \n Please provide your phone number.");

		String phoneNumber = scanner.nextLine();

		System.out.println("Awesome! We captured your phone nnumber as "
				+ phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3") + ".");
		System.out.println("Is that correct?");

		String validation = "";

		do {
			if (validation.equals("N")) {
				System.out.println("Please re-enter your phone number");
				validation = scanner.nextLine();
				System.out.println(
						"Now we have " + phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3") + ".");
			}
			System.out.println("Please enter Y for yes or N for no.");
			validation = scanner.nextLine();
		} while (!validation.equals("Y"));

		System.out.println("Great! Let's get started on your order!");

		CustomerDAO customerDAO = new CustomerDAO();
		OrderDAO orderDAO = new OrderDAO();
		LemonadeDAO lemonadeDAO = new LemonadeDAO();
		LemonadeStandDAO lemonadeStandDAO = new LemonadeStandDAO();

		Customer customer = customerDAO.create(new Customer(name, phoneNumber));
		Order order = orderDAO.createOrder(new Order(customer, lemonadeStandDAO.getLemonadeStand(1)));

		if (order != null) {
			System.out.println("How many lemonades would you like to order?");

			for (int numberOfLemonades = scanner.nextInt(),
					currentLemonade = 1; numberOfLemonades > 0; numberOfLemonades--, currentLemonade++) {
				System.out.println(
						"How much lemon juice would you like for lemonade" + currentLemonade + "? (in ounces)");
				double lemonJuice = scanner.nextDouble();
				System.out.println("How much water would you like? (in cups)");
				double water = scanner.nextDouble();
				System.out.println("How much sugar would you like? (in cups)");
				double sugar = scanner.nextDouble();
				System.out.println("How many ice cubes would you like?");
				int iceCubes = scanner.nextInt();
				order.addLemonade(lemonadeDAO.create(new Lemonade(lemonJuice, water, sugar, iceCubes, order)));
				orderDAO.updateOrder(order);
			}
////		System.out.println(order);
//
//		File file = new File("./orders");
//
//		File[] files = file.listFiles();
//		System.out.println(file.isDirectory());

//		FileOutputStream fileOutputStream = null;
//		ObjectOutputStream objectOutputStream = null;
//		try {
//			fileOutputStream = new FileOutputStream(file + "/order" + (files.length + 1) + ".txt");
//			objectOutputStream = new ObjectOutputStream(fileOutputStream);
//
//			objectOutputStream.writeObject(order);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Failed to create file. Please ensure orders directory exists");
//		} finally {
//			try {
//				if (fileOutputStream != null) {
//					fileOutputStream.close();
//				}
//				if (objectOutputStream != null) {
//					objectOutputStream.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//		objectMapper.writeValue(new File(file + "/order" + (files.length + 1) + ".json"), order);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Success! Your order total is: " + order.getTotal());

//		scanner.close();

//		Customer customer1 = new Customer("Will", "(000) 000-0000");
//		Customer customer2 = new Customer("Frank", "(999) 999-9999");
//		
//		Order order1 = new Order(customer1);
//		
//		order1.addLemonade(new Lemonade(1, .5, 1, 5));
//		order1.addLemonade(new Lemonade(1, .5, 1, 5));
//		order1.addLemonade(new Lemonade(1, .5, 1, 5));
//		order1.addLemonade(new Lemonade(1, .5, 1, 5));
//		order1.addLemonade(new Lemonade(1, .5, 1, 5));

//		System.out.println(order1.getTotal());

//		Order order2 = new Order(customer2);
//		
//		order2.addLemonade(new Lemonade(1, .25, 1, 5));
//		order2.addLemonade(new Lemonade(4, .5, 1, 7));
//		order2.addLemonade(new Lemonade(1, .5, 1, 9));
//		order2.addLemonade(new Lemonade(3, .75, 1, 1));
//		order2.addLemonade(new Lemonade(2, .5, 1, 5));

//		System.out.println(order2.getTotal());

//		order1.equals(order2);
//		
//		Box<Order> b1 = new Box<Order>(order1);
//		Box<Customer> b2 = new Box<Customer>(customer2);
//		
//		b1.setObj(order2);
//		
//		((Order) b1.getObj()).addLemonade(new Lemonade(2, 1.5, 1.75, 5));

//		System.out.println(b1.getObj());
//		System.out.println(b2.getObj());
//
//		b2.printValue(customer2, order2);

//		Box<Integer> b3 = new Box<Integer>(1);

//		LemonadeStand lemonadeStand1 = new LemonadeStand("Sarah Spectacular");
//		LemonadeStand lemonadeStand2 = new LemonadeStand("Chico Chaching");
//		
//		Map<LemonadeStand, List<Order>> lemonadeStandOrders = new HashMap<>();
//		List<Order> lemonadeStand1Orders = new ArrayList<>();
//		lemonadeStand1Orders.add(order1);
//		lemonadeStandOrders.put(lemonadeStand1, lemonadeStand1Orders);
//		lemonadeStandOrders.put(lemonadeStand1, Arrays.asList(new Order[] { order1, order2 }));

//		lemonadeStandOrders.put(lemonadeStand2, Arrays.asList(new Order[] { order2 }));
//		
//		System.out.println(lemonadeStandOrders.get(lemonadeStand1));

//		OrderDAO orderDAO = new OrderDAO();
//		
//		Order order = orderDAO.getOrder(4);
//		
//		System.out.println(order.getCustomer());
//		System.out.println(order.getLemonadeStand());
//		System.out.println(order.getTotal());

			// save data to database

			System.out.println("Success! Your order total is: " + order.getTotal());
		}
	}
}
