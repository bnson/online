// A class to represent a product
public class Product {

    // A static field to store the next id for a product
    private static int nextId = 1;

    // A field to store the id of the product
    private int id;

    // A field to store the name of the product
    // The name can only contain alphanumeric characters
    private String name;

    // A field to store the price of the product
    private double price;

    // A field to store the availability of the product
    // The availability can be either "available" or "not available"
    private String availability;

    // A field to store the times of selling the product
    // The times can only be "morning", "noon", or "evening"
    // The product can be sold multiple times in a day
    private String[] times;

    // A constructor to create a product object with given values
    public Product(String name, double price, String availability, String[] times) {
        // Assign the next id to the id field and increment the next id
        this.id = nextId++;

        // Validate the name of the product
        if (name.matches("[a-zA-Z0-9]+")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name");
        }

        // Validate the price of the product
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Invalid price");
        }

        // Validate the availability of the product
        if (availability.equals("available") || availability.equals("not available")) {
            this.availability = availability;
        } else {
            throw new IllegalArgumentException("Invalid availability");
        }

        // Validate the times of selling the product
        for (String time : times) {
            if (time.equals("morning") || time.equals("noon") || time.equals("evening")) {
                continue;
            } else {
                throw new IllegalArgumentException("Invalid time");
            }
        }
        this.times = times;
    }

    // A method to display the product in a table format
    public void display() {
        //System.out.println("+----+----------------+----------------+----------------+----------------+");
        //System.out.println("| Id | Name           | Price          | Availability   | Times          |");
        System.out.println("+----+----------------+----------------+----------------+----------------+");
        System.out.printf("| %-2d | %-14s | %-14.2f | %-14s | %-14s |\n", id, name, price, availability, String.join(", ", times));
        System.out.println("+----+----------------+----------------+----------------+----------------+");
    }

    // A public method to get the name of the product
    public String getName() {
        return name;
    }

    // A public method to set the name of the product
    public void setName(String name) {
        // Validate the name of the product
        if (name.matches("[a-zA-Z0-9]+")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    // A public method to get the price of the product
    public double getPrice() {
        return price;
    }

    // A public method to set the price of the product
    public void setPrice(double price) {
        // Validate the price of the product
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Invalid price");
        }
    }

    // A public method to get the availability of the product
    public String getAvailability() {
        return availability;
    }

    // A public method to set the availability of the product
    public void setAvailability(String availability) {
        // Validate the availability of the product
        if (availability.equals("available") || availability.equals("not available")) {
            this.availability = availability;
        } else {
            throw new IllegalArgumentException("Invalid availability");
        }
    }

// A public method to get the times of selling the product
    public String[] getTimes() {
        return times;
    }

    // A public method to set the times of selling the product
    public void setTimes(String[] times) {
        // Validate the times of selling the product
        for (String time : times) {
            if (time.equals("morning") || time.equals("noon") || time.equals("evening")) {
                continue;
            } else {
                throw new IllegalArgumentException("Invalid time");
            }
        }
        this.times = times;
    }

    // A public method to get the id of the product
    public int getId() {
        return id;
    }
}


// A class to represent a food item that inherits from the product class
public class Food extends Product {
    // A field to store whether the food is vegetarian or not

    private boolean vegetarian;

    // A constructor to create a food object with given values
    public Food(String name, double price, String availability, String[] times, boolean vegetarian) {
        // Call the constructor of the superclass (product) with the given values
        super(name, price, availability, times);

        // Assign the value of vegetarian to the field
        this.vegetarian = vegetarian;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    // A method to display the food item in a table format
    // This method overrides the display method of the superclass (product)
    @Override
    public void display() {
        System.out.println("+----+----------------+----------------+----------------+----------------+----------------+");
        System.out.println("| Id | Name           | Price          | Availability   | Times          | Vegetarian     |");
        System.out.println("+----+----------------+----------------+----------------+----------------+----------------+");
        System.out.printf("| %-2d | %-14s | %-14.2f | %-14s | %-14s | %-14s |\n", getId(), getName(), getPrice(), getAvailability(), String.join(", ", getTimes()), isVegetarian() ? "Yes" : "No");
        System.out.println("+----+----------------+----------------+----------------+----------------+----------------+");
    }

}


// A class to represent a drink item that inherits from the product class
public class Drink extends Product {
    // A field to store whether the drink has ice or not

    private boolean ice;

    // A constructor to create a drink object with given values
    public Drink(String name, double price, String availability, String[] times, boolean ice) {
        // Call the constructor of the superclass (product) with the given values
        super(name, price, availability, times);

        // Assign the value of ice to the field
        this.ice = ice;
    }

    public boolean isIce() {
        return ice;
    }

    public void setIce(boolean ice) {
        this.ice = ice;
    }

    // A method to display the drink item in a table format
    // This method overrides the display method of the superclass (product)
    @Override
    public void display() {
        System.out.println("+----+----------------+----------------+----------------+----------------+----------------+");
        System.out.println("| Id | Name           | Price          | Availability   | Times          | Ice            |");
        System.out.println("+----+----------------+----------------+----------------+----------------+----------------+");
        System.out.printf("| %-2d | %-14s | %-14.2f | %-14s | %-14s | %-14s |\n", getId(), getName(), getPrice(), getAvailability(), String.join(", ", getTimes()), isIce() ? "Yes" : "No");
        System.out.println("+----+----------------+----------------+----------------+----------------+----------------+");
    }
}


public class ProductManager {
    // A field to store a list of products

    private final ArrayList<Product> products;

    // A constructor to create a product manager object with an empty list
    public ProductManager() {
        products = new ArrayList<>();

        // Create an array of food data with 10 rows and 5 columns
        Object[][] dataFoods = {
            {"Bánh mì", 20000, "available", new String[]{"morning", "noon"}, false},
            {"Phở", 30000, "available", new String[]{"morning", "noon"}, false},
            {"Cơm tấm", 25000, "available", new String[]{"noon", "evening"}, false},
            {"Bánh xèo", 40000, "available", new String[]{"evening"}, false},
            {"Bún bò Huế", 35000, "available", new String[]{"noon"}, false},
            {"Gỏi cuốn", 15000, "available", new String[]{"noon"}, true},
            {"Bánh cuốn", 30000, "not available", new String[]{"morning"}, false},
            {"Chè", 10000, "available", new String[]{"evening"}, true},
            {"Bánh tráng trộn", 15000, "available", new String[]{"evening"}, false},
            {"Bánh flan", 10000, "not available", new String[]{"noon"}, true}
        };

        // Create an array of drink data with 10 rows and 5 columns
        Object[][] dataDrinks = {
            {"Trà sữa", 30000, "available", new String[]{"noon", "evening"}, true},
            {"Cà phê sữa đá", 20000, "available", new String[]{"morning"}, true},
            {"Nước mía", 10000, "available", new String[]{"morning", "noon"}, false},
            {"Sinh tố bơ", 25000, "not available", new String[]{"noon"}, true},
            {"Soda chanh", 15000, "available", new String[]{"evening"}, true},
            {"Nước cam ép", 20000, "not available", new String[]{"morning"}, false},
            {"Nước dừa", 15000, "available", new String[]{"noon"}, false},
            {"Nước chanh mật ong", 20000, "available", new String[]{"evening"}, false},
            {"Sữa chua nếp cẩm", 25000, "available", new String[]{"noon"}, true},
            {"Sinh tố dâu", 25000, "not available", new String[]{"noon"}, true},
            {"Trà đào", 20000, "available", new String[]{"evening"}, true}
        };

        // Loop through each row in the dataFoods array
        for (Object[] dataFood : dataFoods) {
            // Extract the values of name, price, availability, times, and vegetarian from the row
            String name = (String) dataFood[0];
            double price = (double) dataFood[1];
            String availability = (String) dataFood[2];
            String[] times = (String[]) dataFood[3];
            boolean vegetarian = (boolean) dataFood[4];

            // Add a food item to the list of products with the extracted values
            addFood(name, price, availability, times, vegetarian);
        }

        // Loop through each row in the dataDrinks array
        for (Object[] dataDrink : dataDrinks) {
            // Extract the values of name, price, availability, times, and ice from the row
            String name = (String) dataDrink[0];
            double price = (double) dataDrink[1];
            String availability = (String) dataDrink[2];
            String[] times = (String[]) dataDrink[3];
            boolean ice = (boolean) dataDrink[4];

            // Add a drink item to the list of products with the extracted values
            addDrink(name, price, availability, times, ice);
        }

    }

    // A method to display the products in the list
    // The method prioritizes food items before drink items
    public void displayProducts() {
        // Loop through each product in the list
        for (Product product : products) {
            // Check if the product is a food item
            if (product instanceof Food) {
                // Display the food item
                product.display();
            }
        }

        // Loop through each product in the list again
        for (Product product : products) {
            // Check if the product is a drink item
            if (product instanceof Drink) {
                // Display the drink item
                product.display();
            }
        }
    }

    // A method to add a food item to the list of products
    public void addFood(String name, double price, String availability, String[] times, boolean vegetarian) {
        // Create a food object with the given values
        Food food = new Food(name, price, availability, times, vegetarian);

        // Add the food object to the list of products
        products.add(food);
    }

    // A method to add a drink item to the list of products
    public void addDrink(String name, double price, String availability, String[] times, boolean ice) {
        // Create a drink object with the given values
        Drink drink = new Drink(name, price, availability, times, ice);

        // Add the drink object to the list of products
        products.add(drink);
    }

// A method to add a product from the command line
    public void addProductFromCommandLine() {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // A variable to store the type of product (food or drink)
        String type = "";

        // A loop to validate the type of product
        while (true) {
            // Ask the user for the type of product (food or drink)
            System.out.println("Enter the type of product (food or drink):");
            type = scanner.nextLine();

            // Check if the type of product is food or drink
            if (type.equals("food") || type.equals("drink")) {
                // Valid type of product
                break;
            } else {
                // Invalid type of product
                System.out.println("Invalid type of product. Please enter food or drink.");
            }
        }

        // A variable to store the name of the product
        String name = "";

        // A loop to validate the name of the product
        while (true) {
            // Ask the user for the name of the product
            System.out.println("Enter the name of the product:");
            name = scanner.nextLine();

            // Check if the name of the product is alphanumeric
            if (name.matches("[a-zA-Z0-9]+")) {
                // Valid name of product
                break;
            } else {
                // Invalid name of product
                System.out.println("Invalid name of product. Please enter only alphanumeric characters.");
            }
        }

        // A variable to store the price of the product
        double price = 0;

        // A loop to validate the price of the product
        while (true) {
            // Ask the user for the price of the product
            System.out.println("Enter the price of the product:");
            try {
                price = Double.parseDouble(scanner.nextLine());
                // Check if the price of the product is positive
                if (price > 0) {
                    // Valid price of product
                    break;
                } else {
                    // Invalid price of product
                    System.out.println("Invalid price of product. Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                // Invalid input format
                System.out.println("Invalid input format. Please enter a number.");
            }
        }

        // A variable to store the availability of the product
        String availability = "";

        // A loop to validate the availability of the product
        while (true) {
            // Ask the user for the availability of the product (available or not available)
            System.out.println("Enter the availability of the product (available or not available):");
            availability = scanner.nextLine();

            // Check if the availability of the product is available or not available
            if (availability.equals("available") || availability.equals("not available")) {
                // Valid availability of product
                break;
            } else {
                // Invalid availability of product
                System.out.println("Invalid availability of product. Please enter available or not available.");
            }
        }

        // A variable to store an array of times of selling the product
        String[] times = null;

        // A loop to validate the times of selling the product
        while (true) {
            // Ask the user for the times of selling the product (morning, noon, or evening) separated by commas
            System.out.println("Enter the times of selling the product (morning, noon, or evening) separated by commas:");
            times = scanner.nextLine().split(",");

            // A flag to indicate whether all times are valid or not
            boolean validTimes = true;

            // Loop through each time in the array and check if it is morning, noon, or evening
            for (String time : times) {
                if (time.equals("morning") || time.equals("noon") || time.equals("evening")) {
                    continue;
                } else {
                    validTimes = false;
                    break;
                }
            }

            // Check if all times are valid or not
            if (validTimes) {
                // Valid times of selling products
                break;
            } else {
                // Invalid times of selling products
                System.out.println("Invalid times of selling products. Please enter morning, noon, or evening separated by commas.");
            }
        }

        // Check if the type of product is food or drink
        if (type.equals("food")) {
            // A variable to store whether the food is vegetarian or not
            boolean vegetarian = false;

            // A loop to validate whether the food is vegetarian or not
            while (true) {
                // Ask the user if the food is vegetarian (yes or no)
                System.out.println("Is the food vegetarian (yes or no)?");
                String answer = scanner.nextLine();

                // Check if the answer is yes or no
                if (answer.equals("yes")) {
                    // The food is vegetarian
                    vegetarian = true;
                    break;
                } else if (answer.equals("no")) {
                    // The food is not vegetarian
                    vegetarian = false;
                    break;
                } else {
                    // Invalid answer
                    System.out.println("Invalid answer. Please enter yes or no.");
                }
            }

            // Add a food item to the list of products with the given values
            addFood(name, price, availability, times, vegetarian);
        } else if (type.equals("drink")) {
            // A variable to store whether the drink has ice or not
            boolean ice = false;

            // A loop to validate whether the drink has ice or not
            while (true) {
                // Ask the user if the drink has ice (yes or no)
                System.out.println("Does the drink have ice (yes or no)?");
                String answer = scanner.nextLine();

                // Check if the answer is yes or no
                if (answer.equals("yes")) {
                    // The drink has ice
                    ice = true;
                    break;
                } else if (answer.equals("no")) {
                    // The drink does not have ice
                    ice = false;
                    break;
                } else {
                    // Invalid answer
                    System.out.println("Invalid answer. Please enter yes or no.");
                }
            }

            // Add a drink item to the list of products with the given values
            addDrink(name, price, availability, times, ice);
        } else {
            // Invalid type of product
            System.out.println("Invalid type of product.");
        }
    }

// A method to delete a product from the list by id
    public void deleteProduct() {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // A variable to store the id of the product to delete
        int id = 0;

        // A loop to validate the id of the product
        while (true) {
            // Ask the user for the id of the product to delete
            System.out.println("Enter the id of the product to delete:");
            try {
                // Parse the user input as an integer
                id = Integer.parseInt(scanner.nextLine());

                // Break out of the loop if no exception is thrown
                break;
            } catch (NumberFormatException e) {
                // Invalid input format
                System.out.println("Invalid input format. Please enter a number.");
            }
        }

        // Loop through each product in the list
        for (int i = 0; i < products.size(); i++) {
            // Get the product at the current index
            Product product = products.get(i);

            // Check if the product has the same id as the given id
            if (product.getId() == id) {
                // Remove the product from the list
                products.remove(i);

                // Break out of the loop
                break;
            }
        }
    }

}
