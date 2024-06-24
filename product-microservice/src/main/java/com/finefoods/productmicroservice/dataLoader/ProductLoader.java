package com.finefoods.productmicroservice.dataLoader;

import com.finefoods.productmicroservice.model.Product;
import com.finefoods.productmicroservice.repository.ProductRepository;
import com.finefoods.productmicroservice.service.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Component
public class ProductLoader implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ProductServiceImp productServiceImp;

    public String getWorkingDir() throws IOException {
        return new File(".").getCanonicalPath();
    }
    String fileDir = "/product-microservice/src/main/java/com/finefoods/productmicroservice/dataLoader/productImages/";

    @Override
    public void run(String... args) throws Exception {
        String path = getWorkingDir();

        if (productRepository.findProductByProductId((long) 1) == null) {
            Product product = Product.builder()
                    .productId(1L)
                    .brand("Arz")
                    .productName("Coconut Macaroons")
                    .description("Soft and chewy on the inside, crisp and golden on the outside")
                    .category("sweets")
                    .tags("sweets, cookies, coconut,")
                    .size("500")
                    .unit("g")
                    .cost(10.99)
                    .price(15.00)
                    .currentPrice(12.99)
                    .isTaxed(true)
                    .skuCode("4501")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20.00)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir + "1.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 2) == null) {
            Product product = Product.builder()
                    .productId(2L)
                    .brand("Arz")
                    .productName("Rosemary Sticks")
                    .description("Rosemary sticks with sea salt")
                    .category("sweets")
                    .tags("sweets, cookies, rosemary, sticks, nuts-free")
                    .size("400")
                    .unit("g")
                    .cost(10.99)
                    .price(15.00)
                    .currentPrice(12.99)
                    .isTaxed(true)
                    .skuCode("4502")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20.00)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "2.jpeg");
            productServiceImp.imageLoader(productImage,product);


        }
        if (productRepository.findProductByProductId((long) 3) == null) {
            Product product = Product.builder()
                    .productId(3L)
                    .brand("Arz")
                    .productName("Rainbow Cookies")
                    .description("Vanilla cookies dipped in colorful sparkles  ")
                    .category("sweets")
                    .tags("sweets, cookies, rainbow , nuts-free")
                    .size("500")
                    .unit("g")
                    .cost(10.99)
                    .price(15.00)
                    .currentPrice(12.99)
                    .isTaxed(true)
                    .skuCode("4503")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "3.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 4) == null) {
            Product product = Product.builder()
                    .productId(4L)
                    .brand("Arz")
                    .productName("Vanilla Chocolate Cookies")
                    .description("Vanilla cookies dipped in chocolate ")
                    .category("sweets")
                    .tags("sweets, cookies, chocolate , nuts-free")
                    .size("500")
                    .unit("g")
                    .cost(10.99)
                    .price(15.00)
                    .currentPrice(12.99)
                    .isTaxed(true)
                    .skuCode("4504")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "4.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 5) == null) {
            Product product = Product.builder()
                    .productId(5L)
                    .brand("Arz")
                    .productName("Filo Bites")
                    .description("Sesame squares with a hint of anis")
                    .category("sweets")
                    .tags("sweets, cookies, anis ")
                    .size("500")
                    .unit("g")
                    .cost(10.99)
                    .price(15.00)
                    .currentPrice(12.99)
                    .isTaxed(true)
                    .skuCode("4505")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "5.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 6) == null) {
            Product product = Product.builder()
                    .productId(6L)
                    .brand("Arz")
                    .productName("Date Cookies")
                    .description("Delicious cookies filled with sweet and chewy dates")
                    .category("sweets")
                    .tags("sweets, cookies, dates, nuts-free ")
                    .size("500")
                    .unit("g")
                    .cost(10.99)
                    .price(10.00)
                    .currentPrice(8.99)
                    .isTaxed(true)
                    .skuCode("4506")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "6.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(path + fileDir+ "6-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
        }
        if (productRepository.findProductByProductId((long) 7) == null) {
            Product product = Product.builder()
                    .productId(7L)
                    .brand("Arz")
                    .productName("Tuxedo Cake")
                    .description("A delicious cake with layers of chocolate and vanilla, topped with white chocolate icing and white chocolate curls ")
                    .category("sweets")
                    .tags("sweets, cakes, chocolate, white , tuxedo , nuts-free")
                    .size("10")
                    .unit("inches")
                    .cost(40.00)
                    .price(50.00)
                    .currentPrice(50.00)
                    .isTaxed(true)
                    .skuCode("4507")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "7.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 8) == null) {
            Product product = Product.builder()
                    .productId(8L)
                    .brand("Arz")
                    .productName("Strawberry Shortcake")
                    .description("layers of fluffy vanilla cake, stuffed with fresh strawberries, and whipped cream ")
                    .category("sweets")
                    .tags("sweets, cakes, strawberry, white , shortcake , nuts-free")
                    .size("10")
                    .unit("inches")
                    .cost(40.00)
                    .price(50.00)
                    .currentPrice(50.00)
                    .isTaxed(true)
                    .skuCode("4508")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "8.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 9) == null) {
            Product product = Product.builder()
                    .productId(9L)
                    .brand("Arz")
                    .productName("Double Chocolate Cake")
                    .description("chocolate cake infused with rich cocoa flavor and topped with a velvety chocolate frosting, and dark and milk chocolate curls for an extra dose of decadence.")
                    .category("sweets")
                    .tags("sweets, cakes, chocolate, nuts-free")
                    .size("10")
                    .unit("inches")
                    .cost(40.00)
                    .price(50.00)
                    .currentPrice(50.00)
                    .isTaxed(true)
                    .skuCode("4509")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "9.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 10) == null) {
            Product product = Product.builder()
                    .productId(10L)
                    .brand("Arz")
                    .productName("Hazelnut Cake")
                    .description("A delicious cake with hazelnut flavor, stuffed with hazelnut crumb and topped with creamy hazelnut frosting")
                    .category("sweets")
                    .tags("sweets, cakes, hazelnut")
                    .size("8")
                    .unit("inches")
                    .cost(30.00)
                    .price(40.00)
                    .currentPrice(40.00)
                    .isTaxed(true)
                    .skuCode("4510")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "10.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 11) == null) {
            Product product = Product.builder()
                    .productId(11L)
                    .brand("Arz")
                    .productName("Triple Chocolate Cake")
                    .description("A rich chocolate cake featuring layers of moist chocolate sponge with chocolate buttercream filling")
                    .category("sweets")
                    .tags("sweets, cakes, chocolate, nuts-free")
                    .size("8")
                    .unit("inches")
                    .cost(30.00)
                    .price(40.00)
                    .currentPrice(40.00)
                    .isTaxed(true)
                    .skuCode("4511")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "11.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 12) == null) {
            Product product = Product.builder()
                    .productId(12L)
                    .brand("Arz")
                    .productName("Fruit Tart")
                    .description("Buttery pastry crust filled with luscious custard and topped with fresh seasonal fruits")
                    .category("sweets")
                    .tags("sweets, cakes, fruits, custard, pastry, nuts-free")
                    .size("8")
                    .unit("inches")
                    .cost(30.00)
                    .price(40.00)
                    .currentPrice(40.00)
                    .isTaxed(true)
                    .skuCode("4512")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "12.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 13) == null) {
            Product product = Product.builder()
                    .productId(13L)
                    .brand("Arz")
                    .productName("Gourmet Baklava")
                    .description("layers of flaky phyllo pastry, rich nuts, and sweet honey syrup")
                    .category("sweets")
                    .tags("sweets, baklava, nuts,")
                    .size("800")
                    .unit("g")
                    .cost(15.99)
                    .price(12.00)
                    .currentPrice(15.99)
                    .isTaxed(true)
                    .skuCode("4513")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "13.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 14) == null) {
            Product product = Product.builder()
                    .productId(14L)
                    .brand("Arz")
                    .productName("Marble Cake")
                    .description("cake with swirls of vanilla and chocolate, offering a perfect blend of flavors in every slice")
                    .category("sweets")
                    .tags("sweets, cakes, chocolate, vanilla, nuts-free")
                    .size("800")
                    .unit("g")
                    .cost(8.00)
                    .price(11.99)
                    .currentPrice(9.99)
                    .isTaxed(true)
                    .skuCode("4514")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "14.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 15) == null) {
            Product product = Product.builder()
                    .productId(15L)
                    .brand("Arz")
                    .productName("Cashew Fingers")
                    .description("crispy delights packed with roasted cashews, a touch of sweetness, and a satisfying crunch.")
                    .category("sweets")
                    .tags("baklava, cakes, chocolate, vanilla")
                    .size("200")
                    .unit("g")
                    .cost(5)
                    .price(6.99)
                    .currentPrice(6.99)
                    .isTaxed(true)
                    .skuCode("4515")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "15.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 16) == null) {
            Product product = Product.builder()
                    .productId(16L)
                    .brand("Arz")
                    .productName("Hazelnut Bites")
                    .description("mini bites blending the flavors of traditional baklava with the irresistible crunch of hazelnuts")
                    .category("sweets")
                    .tags("baklava, cakes, chocolate, vanilla")
                    .size("200")
                    .unit("g")
                    .cost(5)
                    .price(6.99)
                    .currentPrice(6.99)
                    .isTaxed(true)
                    .skuCode("4516")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "16.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 17) == null) {
            Product product = Product.builder()
                    .productId(17L)
                    .brand("Arz")
                    .productName("Walnut Bites")
                    .description("mini bites blending the flavors of traditional baklava with the irresistible crunch of walnuts")
                    .category("sweets")
                    .tags("baklava, cakes, chocolate, vanilla")
                    .size("200")
                    .unit("g")
                    .cost(5)
                    .price(6.99)
                    .currentPrice(6.99)
                    .isTaxed(true)
                    .skuCode("4517")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "17.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 18) == null) {
            Product product = Product.builder()
                    .productId(18L)
                    .brand("Arz")
                    .productName("Fancy Baklava")
                    .description("layers of flaky phyllo pastry, rich nuts, and sweet honey syrup")
                    .category("sweets")
                    .tags("sweets, baklava, nuts,")
                    .size("800")
                    .unit("g")
                    .cost(15.99)
                    .price(12.00)
                    .currentPrice(15.99)
                    .isTaxed(true)
                    .skuCode("4518")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "18.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 19) == null) {
            Product product = Product.builder()
                    .productId(19L)
                    .brand("Arz")
                    .productName("Cheese Pizza")
                    .description("gooey melted cheese on a crisp golden crust")
                    .category("cafe")
                    .tags("pizza, cheese, cafe,nuts-free")
                    .size("100")
                    .unit("g")
                    .cost(3.99)
                    .price(4.99)
                    .currentPrice(4.99)
                    .isTaxed(true)
                    .skuCode("4519")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "19.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 20) == null) {
            Product product = Product.builder()
                    .productId(20L)
                    .brand("Arz")
                    .productName("Muhammara Pizza")
                    .description("tomato with hint of red chilli papers sauce on a crisp golden crust")
                    .category("cafe")
                    .tags("pizza, spicy, cafe, tomato sauce, vegan ,nuts-free, non-dairy")
                    .size("100")
                    .unit("g")
                    .cost(2.99)
                    .price(3.99)
                    .currentPrice(3.99)
                    .isTaxed(true)
                    .skuCode("4520")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "20.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 21) == null) {
            Product product = Product.builder()
                    .productId(21L)
                    .brand("Arz")
                    .productName("Zaatar & Cheese Pizza")
                    .description("The perfect harmony of thyme, sesame, and sumac mixed with olive oil, generously sprinkled over gooey cheese on a crisp golden crust, creating a tantalizing zaatar and cheese pizza")
                    .category("cafe")
                    .tags("pizza, cafe, cheese, zataar")
                    .size("100")
                    .unit("g")
                    .cost(2.99)
                    .price(3.99)
                    .currentPrice(3.99)
                    .isTaxed(true)
                    .skuCode("4521")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "21.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 22) == null) {
            Product product = Product.builder()
                    .productId(22L)
                    .brand("Arz")
                    .productName("Zaatar Pizza")
                    .description("The perfect harmony of thyme, sesame, and sumac mixed with olive oil on a crisp golden crust")
                    .category("cafe")
                    .tags("pizza, cafe, zataar, vegan, non-dairy")
                    .size("100")
                    .unit("g")
                    .cost(2.99)
                    .price(3.99)
                    .currentPrice(3.99)
                    .isTaxed(true)
                    .skuCode("4522")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "22.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 23) == null) {
            Product product = Product.builder()
                    .productId(23L)
                    .brand("Arz")
                    .productName("Olives and Cheese Pizza")
                    .description("gooey melted cheese, topped with lebanese green olives on a crisp golden crust")
                    .category("cafe")
                    .tags("pizza, cafe, olives")
                    .size("100")
                    .unit("g")
                    .cost(2.99)
                    .price(3.99)
                    .currentPrice(3.99)
                    .isTaxed(true)
                    .skuCode("4523")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "23.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 24) == null) {
            Product product = Product.builder()
                    .productId(24L)
                    .brand("Arz")
                    .productName("Zaatar Croissant")
                    .description("Flaky buttery croissant filled with a savory blend of zaatar")
                    .category("cafe")
                    .tags("croissant, cafe, zaatar")
                    .size("50")
                    .unit("g")
                    .cost(1.49)
                    .price(2.99)
                    .currentPrice(2.99)
                    .isTaxed(true)
                    .skuCode("4524")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "24.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 25) == null) {
            Product product = Product.builder()
                    .productId(25L)
                    .brand("Arz")
                    .productName("Cheese Croissant")
                    .description("Flaky buttery croissant filled with a rich, melty cheese")
                    .category("cafe")
                    .tags("croissant, cafe, cheese")
                    .size("50")
                    .unit("g")
                    .cost(1.99)
                    .price(3.49)
                    .currentPrice(3.49)
                    .isTaxed(true)
                    .skuCode("4525")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "25.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 26) == null) {
            Product product = Product.builder()
                    .productId(26L)
                    .brand("Arz")
                    .productName("Meat Kibbeh Sanieh")
                    .description("homemade-style kibbeh, stuffed with seasoned ground beef, conveniently prepared for heating and enjoying, offering a delicious taste of traditional Lebanese cuisine ")
                    .category("frozen")
                    .tags("frozen, meat, kibbeh")
                    .size("500")
                    .unit("g")
                    .cost(13.00)
                    .price(15.99)
                    .currentPrice(15.99)
                    .isTaxed(true)
                    .skuCode("4526")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "26.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 27) == null) {
            Product product = Product.builder()
                    .productId(27L)
                    .brand("Arz")
                    .productName("Beef Shawarma")
                    .description("frozen beef shawarma, seasoned to perfection and ready to heat up for a mouthwatering taste of Middle Eastern cuisine at home")
                    .category("frozen")
                    .tags("frozen, meat, shawarma, nuts-free")
                    .size("500")
                    .unit("g")
                    .cost(13.00)
                    .price(15.99)
                    .currentPrice(15.99)
                    .isTaxed(true)
                    .skuCode("4527")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "27.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 28) == null) {
            Product product = Product.builder()
                    .productId(28L)
                    .brand("Arz")
                    .productName("Chicken Shawarma")
                    .description("frozen chicken shawarma, seasoned to perfection and ready to heat up for a mouthwatering taste of Middle Eastern cuisine at home")
                    .category("frozen")
                    .tags("frozen, meat, chicken, shawarma, nuts-free")
                    .size("500")
                    .unit("g")
                    .cost(13.00)
                    .price(15.99)
                    .currentPrice(15.99)
                    .isTaxed(true)
                    .skuCode("4528")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "28.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 29) == null) {
            Product product = Product.builder()
                    .productId(29L)
                    .brand("Arz")
                    .productName("Meat Kibbeh ")
                    .description("homemade-style kibbeh, stuffed with seasoned ground beef, conveniently prepared for frying and enjoying, offering a delicious taste of traditional Lebanese cuisine ")
                    .category("frozen")
                    .tags("frozen, meat, kibbeh ")
                    .size("20")
                    .unit("pc")
                    .cost(13.00)
                    .price(15.99)
                    .currentPrice(15.99)
                    .isTaxed(true)
                    .skuCode("4529")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "29.jpeg");
            productServiceImp.imageLoader(productImage,product);

        }
        if (productRepository.findProductByProductId((long) 30) == null) {
            Product product = Product.builder()
                    .productId(30L)
                    .brand("Arz")
                    .productName("Spinach & Cheese Mini Pies")
                    .description("frozen spinach and cheese mini pies, bursting with savory flavors and flaky pastry, perfect for a quick and delicious snack or appetizer")
                    .category("frozen")
                    .tags("frozen, pies, mini, cheese, spinach, nuts-free")
                    .size("18")
                    .unit("pc")
                    .cost(7.00)
                    .price(9.99)
                    .currentPrice(9.99)
                    .isTaxed(true)
                    .skuCode("4530")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "30.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 31) == null) {
            Product product = Product.builder()
                    .productId(31L)
                    .brand("Arz")
                    .productName("Phyllo Dough")
                    .description("Layer phyllo dough with butter or oil to create flaky pastry sheets for sweet and savory dishes.")
                    .category("frozen")
                    .tags("frozen, dough")
                    .size("454")
                    .unit("g")
                    .cost(1.50)
                    .price(2.99)
                    .currentPrice(1.99)
                    .isTaxed(true)
                    .skuCode("4531")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "31.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 32) == null) {
            Product product = Product.builder()
                    .productId(32L)
                    .brand("Arz")
                    .productName("Tahina")
                    .description("creamy paste made from 100% pure sesame seed")
                    .category("grocery")
                    .tags("grocery, tahini, sesame, vegan, non-dairy")
                    .size("907")
                    .unit("g")
                    .cost(8.00)
                    .price(10.99)
                    .currentPrice(10.99)
                    .isTaxed(false)
                    .skuCode("4532")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "32.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(fileDir+ "32-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(fileDir+ "32-2.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 33) == null) {
            Product product = Product.builder()
                    .productId(33L)
                    .brand("Arz")
                    .productName("Halawa Regular")
                    .description("sweet, dense confection made from sesame paste and sugar, enjoyed for its rich flavor and unique texture")
                    .category("grocery")
                    .tags("grocery, halawa, halva, sesame, vegan, non-dairy")
                    .size("907")
                    .unit("g")
                    .cost(3.00)
                    .price(4.99)
                    .currentPrice(4.99)
                    .isTaxed(true)
                    .skuCode("4533")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "33.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 34) == null) {
            Product product = Product.builder()
                    .productId(34L)
                    .brand("Arz")
                    .productName("Halawa Pistachio")
                    .description("sweet, dense confection made from sesame paste and sugar,pistachio added, enjoyed for its rich flavor and unique texture")
                    .category("grocery")
                    .tags("grocery, halawa, halva, sesame, vegan, non-dairy")
                    .size("907")
                    .unit("g")
                    .cost(4.00)
                    .price(5.99)
                    .currentPrice(5.99)
                    .isTaxed(true)
                    .skuCode("4534")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "34.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 35) == null) {
            Product product = Product.builder()
                    .productId(35L)
                    .brand("Arz")
                    .productName("Green Olives")
                    .description("Hand-picked from Lebanon, seasoned with thyme and lemon.")
                    .category("grocery")
                    .tags("grocery, olives, green, vegan, organic, nuts-free, non-dairy")
                    .size("1")
                    .unit("L")
                    .cost(4.00)
                    .price(5.99)
                    .currentPrice(5.99)
                    .isTaxed(false)
                    .skuCode("4535")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "35.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(fileDir+ "35-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(fileDir+ "35-2.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 36) == null) {
            Product product = Product.builder()
                    .productId(36L)
                    .brand("Arz")
                    .productName("Black Olives")
                    .description("Hand-picked from Lebanon, seasoned with thyme and vinegar.")
                    .category("grocery")
                    .tags("grocery, olives, black, vegan, organic, nuts-free, non-dairy")
                    .size("1")
                    .unit("L")
                    .cost(4.00)
                    .price(5.99)
                    .currentPrice(5.99)
                    .isTaxed(false)
                    .skuCode("4536")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "36.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(fileDir+ "36-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(fileDir+ "36-2.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 37) == null) {
            Product product = Product.builder()
                    .productId(37L)
                    .brand("Arz")
                    .productName("Green Olives")
                    .description("Hand-picked from Lebanon, seasoned with thyme and lemon.")
                    .category("grocery")
                    .tags("grocery, olives, green, vegan, organic, nuts-free, non-dairy")
                    .size("3")
                    .unit("L")
                    .cost(10.00)
                    .price(12.99)
                    .currentPrice(12.99)
                    .isTaxed(false)
                    .skuCode("4537")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "37.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(fileDir+ "35-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(fileDir+ "35-2.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 38) == null) {
            Product product = Product.builder()
                    .productId(38L)
                    .brand("Arz")
                    .productName("Black Olives")
                    .description("Hand-picked from Lebanon, seasoned with thyme and vinegar.")
                    .category("grocery")
                    .tags("grocery, olives, black, vegan, organic, nuts-free,non-dairy")
                    .size("3")
                    .unit("L")
                    .cost(10.00)
                    .price(12.99)
                    .currentPrice(12.99)
                    .isTaxed(false)
                    .skuCode("4538")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "38.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(fileDir+ "36-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(fileDir+ "36-2.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 39) == null) {
            Product product = Product.builder()
                    .productId(39L)
                    .brand("Arz")
                    .productName("Pickled Turnips")
                    .description("pickled turnips, vibrant with a pink hue from beets, offering crispness and flavor in every bite ")
                    .category("grocery")
                    .tags("grocery, turnips, pickles, vegan, organic, nuts-free, non-dairy")
                    .size("1")
                    .unit("kg")
                    .cost(3.00)
                    .price(4.99)
                    .currentPrice(4.99)
                    .isTaxed(false)
                    .skuCode("4539")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "39.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(fileDir+ "39-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(fileDir+ "39-2.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 40) == null) {
            Product product = Product.builder()
                    .productId(40L)
                    .brand("Arz")
                    .productName("Pickled Cucumbers")
                    .description("Crisp and tangy pickled cucumbers, preserved in a flavorful vinegar brine")
                    .category("grocery")
                    .tags("grocery, cucmbers, pickles, vegan, organic, nuts-free, non-dairy")
                    .size("1")
                    .unit("kg")
                    .cost(3.00)
                    .price(4.99)
                    .currentPrice(4.99)
                    .isTaxed(false)
                    .skuCode("4540")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "40.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 41) == null) {
            Product product = Product.builder()
                    .productId(41L)
                    .brand("La Tortilla Factory")
                    .productName(" Gluten Free Tortillas")
                    .description("Thin circular unleavened flatbread")
                    .category("bread")
                    .tags("grocery, bread, gluten-free, vegan, organic, nuts-free, non-dairy")
                    .size("240")
                    .unit("g")
                    .cost(7.00)
                    .price(4.99)
                    .currentPrice(7.99)
                    .isTaxed(false)
                    .skuCode("4541")
                    .upcCode("828696023345")
                    .vendor("La Tortilla Factory")
                    .points(30)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "41.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(fileDir+ "41-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
        }
        if (productRepository.findProductByProductId((long) 42) == null) {
            Product product = Product.builder()
                    .productId(42L)
                    .brand("Catch Of The Day")
                    .productName("Pacific Salmon Fillets")
                    .description("A mild flavoured fish with a flaky, moist texture")
                    .category("grocery")
                    .tags("grocery, frozen, shellfish, nuts-free, non-dairy")
                    .size("680")
                    .unit("g")
                    .cost(7.00)
                    .price(12.99)
                    .currentPrice(12.99)
                    .isTaxed(false)
                    .skuCode("4542")
                    .upcCode("828696023345")
                    .vendor("La Tortilla Factory")
                    .points(10)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "42.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage2 = new File(fileDir+ "42-1.jpeg");
            productServiceImp.imageLoader(productImage2,product);
        }
        if (productRepository.findProductByProductId((long) 43) == null) {
            Product product = Product.builder()
                    .productId(43L)
                    .brand("Arz")
                    .productName("Fruit Salad Bowl")
                    .description("mix of seasonal cut fruits")
                    .category("produce")
                    .tags("nuts-free, non-dairy,vegan")
                    .size("750")
                    .unit("g")
                    .cost(9.00)
                    .price(14.99)
                    .currentPrice(14.99)
                    .isTaxed(false)
                    .skuCode("4543")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(40)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "43.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 44) == null) {
            Product product = Product.builder()
                    .productId(44L)
                    .brand("Arz")
                    .productName("Mango Salad Bowl")
                    .description("spinach, cherry tomatoes topped with mangoes and raisins")
                    .category("produce")
                    .tags("nuts-free, non-dairy,vegan")
                    .size("450")
                    .unit("g")
                    .cost(9.00)
                    .price(10.99)
                    .currentPrice(10.99)
                    .isTaxed(false)
                    .skuCode("4544")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(40)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "44.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 45) == null) {
            Product product = Product.builder()
                    .productId(45L)
                    .brand("Arz")
                    .productName("Sandwich Pita Bread")
                    .description("lebanese style pita bread")
                    .category("bread")
                    .tags("nuts-free, non-dairy,vegan")
                    .size("258")
                    .unit("g")
                    .cost(0.50)
                    .price(0.99)
                    .currentPrice(0.99)
                    .isTaxed(false)
                    .skuCode("4545")
                    .upcCode("828696023345")
                    .vendor("bakery")
                    .points(40)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "45.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 46) == null) {
            Product product = Product.builder()
                    .productId(46L)
                    .brand("Arz")
                    .productName("Tabouleh Salad")
                    .description("lebanese style salad, made with parsley, tomatoes, bulgurs and salad dressing ")
                    .category("deli")
                    .tags("nuts-free, non-dairy,vegan")
                    .size("1")
                    .unit("kg")
                    .cost(17.50)
                    .price(19.99)
                    .currentPrice(19.99)
                    .isTaxed(false)
                    .skuCode("4546")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(50)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "46.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 47) == null) {
            Product product = Product.builder()
                    .productId(47L)
                    .brand("Arz")
                    .productName("Olives Salad")
                    .description("cut olives with oil and spices")
                    .category("deli")
                    .tags("nuts-free, non-dairy, vegan")
                    .size("1")
                    .unit("kg")
                    .cost(17.50)
                    .price(21.99)
                    .currentPrice(21.99)
                    .isTaxed(false)
                    .skuCode("4547")
                    .upcCode("828696023345")
                    .vendor("kitchen")
                    .points(60)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "47.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 48) == null) {
            Product product = Product.builder()
                    .productId(48L)
                    .brand("Arz")
                    .productName("Chicken Legs")
                    .description("Halal chicken legs")
                    .category("meat")
                    .tags("raw")
                    .size("1")
                    .unit("kg")
                    .cost(3.50)
                    .price(4.99)
                    .currentPrice(4.99)
                    .isTaxed(false)
                    .skuCode("4548")
                    .upcCode("828696023345")
                    .vendor("meat section")
                    .points(60)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "48.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 49) == null) {
            Product product = Product.builder()
                    .productId(49L)
                    .brand("Arz")
                    .productName("Extra Lean Ground Beef")
                    .description("Halal meat")
                    .category("meat")
                    .tags("raw")
                    .size("1")
                    .unit("kg")
                    .cost(10.50)
                    .price(12.99)
                    .currentPrice(12.99)
                    .isTaxed(false)
                    .skuCode("4549")
                    .upcCode("828696023345")
                    .vendor("meat section")
                    .points(60)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "49.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
        if (productRepository.findProductByProductId((long) 50) == null) {
            Product product = Product.builder()
                    .productId(50L)
                    .brand("Arz")
                    .productName("Flat Chicken Family Platter")
                    .description("1 Chicken, Rice 500g or Spicy Potatoes 500g With Chef Selected Salad & Garlic Sauce 4oz.")
                    .category("catering")
                    .tags("meat")
                    .size("")
                    .unit("")
                    .cost(20.50)
                    .price(24.99)
                    .currentPrice(24.99)
                    .isTaxed(true)
                    .skuCode("4550")
                    .upcCode("828696023345")
                    .vendor("cafe")
                    .points(100)
                    .build();
            productRepository.save(product);
            File productImage = new File(fileDir+ "50.jpeg");
            productServiceImp.imageLoader(productImage,product);
        }
    }
}
