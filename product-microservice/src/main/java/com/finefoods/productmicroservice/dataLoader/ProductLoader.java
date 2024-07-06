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
                    .brand("JOSEDOR")
                    .productName("KNIT MINI DRESS")
                    .description(" a sleeveless, red knit garment with a delicate pattern throughout")
                    .category("dress")
                    .gender("women")
                    .color("red")
                    .tags("red, knit, sleeveless, short")
                    .cost(19.99)
                    .price(49.99)
                    .currentPrice(49.99)
                    .isTaxed(true)
                    .skuCode("4501")
                    .upcCode("828696023345")
                    .vendor("france")
                    .points(20.00)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir + "1.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage1 = new File(path + fileDir + "1-1.jpeg");
            productServiceImp.imageLoader(productImage1,product);
            File productImage2 = new File(path + fileDir + "1-2.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(path + fileDir + "1-3.jpeg");
            productServiceImp.imageLoader(productImage3,product);

        }
        if (productRepository.findProductByProductId((long) 2) == null) {
            Product product = Product.builder()
                    .productId(2L)
                    .brand("JOSEDOR")
                    .productName("PIPING KNIT MINI DRESS")
                    .description("sleeveless, white mini dress with black trim")
                    .category("dress")
                    .tags("white, black, knit, trim, short")
                    .gender("women")
                    .color("white")
                    .cost(18.99)
                    .price(29.99)
                    .currentPrice(29.99)
                    .isTaxed(true)
                    .skuCode("4502")
                    .upcCode("828696023345")
                    .vendor("france")
                    .points(20.00)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "2.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage1 = new File(path + fileDir+ "2-1.jpeg");
            productServiceImp.imageLoader(productImage1,product);
            File productImage2 = new File(path + fileDir+ "2-2.jpeg");
            productServiceImp.imageLoader(productImage2,product);

        }
        if (productRepository.findProductByProductId((long) 3) == null) {
            Product product = Product.builder()
                    .productId(3L)
                    .brand("JOSEDOR")
                    .productName("MINI DRESS ZW COLLECTION")
                    .description("a sleeveless, red mini dress with a high neckline and a simple, streamlined silhouette")
                    .category("dresses")
                    .tags("red, dress, short , nuts-free")
                    .gender("women")
                    .color("red")
                    .cost(24.99)
                    .price(53.99)
                    .currentPrice(53.99)
                    .isTaxed(true)
                    .skuCode("4503")
                    .upcCode("828696023345")
                    .vendor("france")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "3.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage1 = new File(path + fileDir+ "3-1.jpeg");
            productServiceImp.imageLoader(productImage1,product);
            File productImage2 = new File(path + fileDir+ "3-2.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(path + fileDir+ "3-3.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 4) == null) {
            Product product = Product.builder()
                    .productId(4L)
                    .brand("JOSEDOR")
                    .productName("TIED GINGHAM DRESS")
                    .description("V-neck mini dress with adjustable spaghetti straps. Elastic smocked fabric")
                    .category("dresses")
                    .tags("straps, short, white , yellow")
                    .gender("women")
                    .color("yellow")
                    .cost(10.99)
                    .price(39.99)
                    .currentPrice(39.99)
                    .isTaxed(true)
                    .skuCode("4504")
                    .upcCode("828696023345")
                    .vendor("france")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "4.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage1 = new File(path + fileDir+ "4-1.jpeg");
            productServiceImp.imageLoader(productImage1,product);
            File productImage2 = new File(path + fileDir+ "4-2.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(path + fileDir+ "4-3.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 5) == null) {
            Product product = Product.builder()
                    .productId(5L)
                    .brand("JOSEDOR")
                    .productName("BALLOON SLEEVE DRESS")
                    .description("Mini dress made of cotton. Round neck with below-the-elbow length balloon sleeves")
                    .category("dresses")
                    .tags("orange, balloon, short")
                    .gender("women")
                    .color("orange")
                    .cost(19.99)
                    .price(45.99)
                    .currentPrice(45.99)
                    .isTaxed(true)
                    .skuCode("4505")
                    .upcCode("828696023345")
                    .vendor("france")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir+ "5.jpeg");
            productServiceImp.imageLoader(productImage,product);
            File productImage1 = new File(path + fileDir+ "5-1.jpeg");
            productServiceImp.imageLoader(productImage1,product);
            File productImage2 = new File(path + fileDir+ "5-2.jpeg");
            productServiceImp.imageLoader(productImage2,product);
            File productImage3 = new File(path + fileDir+ "5-3.jpeg");
            productServiceImp.imageLoader(productImage3,product);
        }
        if (productRepository.findProductByProductId((long) 6) == null) {
            Product product = Product.builder()
                    .productId(6L)
                    .brand("ZARA")
                    .productName("RUFFLED MIDI DRESS")
                    .description("Midi dress crafted from lightweight fabric. V-neckline and ruffled trim detailing.")
                    .category("dresses")
                    .tags("blue, midi, ruffled")
                    .gender("women")
                    .color("blue")
                    .cost(29.99)
                    .price(59.99)
                    .currentPrice(59.99)
                    .isTaxed(true)
                    .skuCode("6021")
                    .upcCode("729847566829")
                    .vendor("Spain")
                    .points(25)
                    .build();
            productRepository.save(product);
            File productImage = new File(path + fileDir + "6.jpeg");
            productServiceImp.imageLoader(productImage, product);
            File productImage1 = new File(path + fileDir + "6-1.jpeg");
            productServiceImp.imageLoader(productImage1, product);
        }
        if (productRepository.findProductByProductId((long) 7) == null) {
            Product product = Product.builder()
                    .productId(7L)
                    .brand("ZARA")
                    .productName("FLORAL PRINT DRESS")
                    .description("Flowy dress with a floral print. High neckline and long sleeves with elastic cuffs.")
                    .category("dresses")
                    .tags("floral, long sleeve, flowy")
                    .gender("women")
                    .color("green")
                    .cost(34.99)
                    .price(69.99)
                    .currentPrice(69.99)
                    .isTaxed(true)
                    .skuCode("7542")
                    .upcCode("830175694857")
                    .vendor("Spain")
                    .points(30)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "7.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "7-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 8) == null) {
            Product product = Product.builder()
                    .productId(8L)
                    .brand("ZARA")
                    .productName("LACE MIDI DRESS")
                    .description("Elegant lace midi dress with a round neckline and short sleeves. Fully lined.")
                    .category("dresses")
                    .tags("lace, midi, elegant")
                    .gender("women")
                    .color("black")
                    .cost(39.99)
                    .price(79.99)
                    .currentPrice(79.99)
                    .isTaxed(true)
                    .skuCode("8963")
                    .upcCode("912345678901")
                    .vendor("Spain")
                    .points(35)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "8.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "8-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }
        if (productRepository.findProductByProductId((long) 9) == null) {
            Product product = Product.builder()
                    .productId(9L)
                    .brand("ZARA")
                    .productName("SATIN SLIP DRESS")
                    .description("Satin slip dress with a V-neckline and spaghetti straps. Bias-cut for a flattering fit.")
                    .category("dresses")
                    .tags("satin, slip, bias-cut")
                    .gender("women")
                    .color("pink")
                    .cost(49.99)
                    .price(99.99)
                    .currentPrice(99.99)
                    .isTaxed(true)
                    .skuCode("7321")
                    .upcCode("856924701234")
                    .vendor("Spain")
                    .points(40)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "9.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "9-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }
        if (productRepository.findProductByProductId((long) 10) == null) {
            Product product = Product.builder()
                    .productId(10L)
                    .brand("ZARA")
                    .productName("KNIT MIDI DRESS")
                    .description("Knit midi dress with a mock neckline and long sleeves. Ribbed knit pattern throughout.")
                    .category("dresses")
                    .tags("knit, midi, mock neckline")
                    .gender("women")
                    .color("gray")
                    .cost(39.99)
                    .price(79.99)
                    .currentPrice(79.99)
                    .isTaxed(true)
                    .skuCode("6134")
                    .upcCode("745612309876")
                    .vendor("Spain")
                    .points(35)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "10.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "10-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }
        if (productRepository.findProductByProductId((long) 11) == null) {
            Product product = Product.builder()
                    .productId(11L)
                    .brand("ZARA")
                    .productName("SLIM FIT SHIRT")
                    .description("Slim fit shirt made from cotton with a button-down collar and long sleeves.")
                    .category("shirts")
                    .tags("slim fit, cotton, button-down")
                    .gender("men")
                    .color("white")
                    .cost(29.99)
                    .price(59.99)
                    .currentPrice(59.99)
                    .isTaxed(true)
                    .skuCode("7123")
                    .upcCode("820495632145")
                    .vendor("Spain")
                    .points(25)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "11.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "11-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }
        if (productRepository.findProductByProductId((long) 12) == null) {
            Product product = Product.builder()
                    .productId(12L)
                    .brand("ZARA")
                    .productName("DENIM JACKET")
                    .description("Classic denim jacket with chest pockets and metal buttons. Adjustable tabs at sides.")
                    .category("jackets")
                    .tags("denim, jacket, blue")
                    .gender("men")
                    .color("blue")
                    .cost(49.99)
                    .price(99.99)
                    .currentPrice(99.99)
                    .isTaxed(true)
                    .skuCode("8345")
                    .upcCode("725431098765")
                    .vendor("Spain")
                    .points(40)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "12.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "12-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 13) == null) {
            Product product = Product.builder()
                    .productId(13L)
                    .brand("ZARA")
                    .productName("TEXTURED BLAZER")
                    .description("Textured blazer with notch lapels and two-button closure. Chest and flap pockets.")
                    .category("blazers")
                    .tags("textured, blazer, formal")
                    .gender("men")
                    .color("gray")
                    .cost(79.99)
                    .price(159.99)
                    .currentPrice(159.99)
                    .isTaxed(true)
                    .skuCode("9765")
                    .upcCode("603187654321")
                    .vendor("Spain")
                    .points(55)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "13.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "13-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 14) == null) {
            Product product = Product.builder()
                    .productId(14L)
                    .brand("ZARA")
                    .productName("SLIM CHINOS")
                    .description("Slim fit chino trousers made from stretch cotton fabric. Belt loops and side pockets.")
                    .category("pants")
                    .tags("slim fit, chinos, stretch")
                    .gender("men")
                    .color("khaki")
                    .cost(39.99)
                    .price(79.99)
                    .currentPrice(79.99)
                    .isTaxed(true)
                    .skuCode("5432")
                    .upcCode("812309876543")
                    .vendor("Spain")
                    .points(35)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "14.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "14-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 15) == null) {
            Product product = Product.builder()
                    .productId(15L)
                    .brand("ZARA")
                    .productName("COTTON POLO SHIRT")
                    .description("Classic cotton polo shirt with short sleeves and ribbed collar and cuffs.")
                    .category("shirts")
                    .tags("cotton, polo, classic")
                    .gender("men")
                    .color("navy")
                    .cost(24.99)
                    .price(49.99)
                    .currentPrice(49.99)
                    .isTaxed(true)
                    .skuCode("7564")
                    .upcCode("945612378905")
                    .vendor("Spain")
                    .points(22)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "15.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "15-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 16) == null) {
            Product product = Product.builder()
                    .productId(16L)
                    .brand("ZARA")
                    .productName("SUEDE BOMBER JACKET")
                    .description("Suede bomber jacket with a stand-up collar and zip-up front. Ribbed trim.")
                    .category("jackets")
                    .tags("suede, bomber, zip-up")
                    .gender("men")
                    .color("brown")
                    .cost(89.99)
                    .price(179.99)
                    .currentPrice(179.99)
                    .isTaxed(true)
                    .skuCode("6854")
                    .upcCode("702348765432")
                    .vendor("Spain")
                    .points(60)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "16.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "16-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 17) == null) {
            Product product = Product.builder()
                    .productId(17L)
                    .brand("ZARA")
                    .productName("WOOL BLEND COAT")
                    .description("Wool blend coat with notched lapels and button closure. Side pockets.")
                    .category("coats")
                    .tags("wool blend, coat, classic")
                    .gender("men")
                    .color("gray")
                    .cost(129.99)
                    .price(259.99)
                    .currentPrice(259.99)
                    .isTaxed(true)
                    .skuCode("7932")
                    .upcCode("837456210987")
                    .vendor("Spain")
                    .points(80)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "17.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "17-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 18) == null) {
            Product product = Product.builder()
                    .productId(18L)
                    .brand("ZARA")
                    .productName("CASHMERE SWEATER")
                    .description("Cashmere sweater with a round neckline and long sleeves. Ribbed trim at cuffs and hem.")
                    .category("sweaters")
                    .tags("cashmere, sweater, round neckline")
                    .gender("men")
                    .color("black")
                    .cost(69.99)
                    .price(139.99)
                    .currentPrice(139.99)
                    .isTaxed(true)
                    .skuCode("6201")
                    .upcCode("704938567812")
                    .vendor("Spain")
                    .points(50)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "18.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "18-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }
        if (productRepository.findProductByProductId((long) 19) == null) {
            Product product = Product.builder()
                    .productId(19L)
                    .brand("ZARA")
                    .productName("LINEN BLEND SHIRT")
                    .description("Linen blend shirt with a mandarin collar and long sleeves. Button-up front.")
                    .category("shirts")
                    .tags("linen blend, shirt, mandarin collar")
                    .gender("men")
                    .color("white")
                    .cost(34.99)
                    .price(69.99)
                    .currentPrice(69.99)
                    .isTaxed(true)
                    .skuCode("5046")
                    .upcCode("812345670123")
                    .vendor("Spain")
                    .points(30)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "19.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "19-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 20) == null) {
            Product product = Product.builder()
                    .productId(20L)
                    .brand("ZARA")
                    .productName("SLIM FIT SUIT")
                    .description("Slim fit suit with a two-button blazer and tailored trousers. Wool blend fabric.")
                    .category("suits")
                    .tags("slim fit, suit, wool blend")
                    .gender("men")
                    .color("navy")
                    .cost(149.99)
                    .price(299.99)
                    .currentPrice(299.99)
                    .isTaxed(true)
                    .skuCode("8532")
                    .upcCode("809763452189")
                    .vendor("Spain")
                    .points(90)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "20.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "20-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }
        if (productRepository.findProductByProductId((long) 21) == null) {
            Product product = Product.builder()
                    .productId(21L)
                    .brand("ZARA")
                    .productName("PRINTED T-SHIRT")
                    .description("Cotton t-shirt with a printed design on the front. Round neckline and short sleeves.")
                    .category("t-shirts")
                    .tags("printed, cotton, round neckline")
                    .gender("kids")
                    .color("white")
                    .cost(12.99)
                    .price(24.99)
                    .currentPrice(24.99)
                    .isTaxed(true)
                    .skuCode("7543")
                    .upcCode("812345670234")
                    .vendor("Spain")
                    .points(15)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "21.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "21-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 22) == null) {
            Product product = Product.builder()
                    .productId(22L)
                    .brand("ZARA")
                    .productName("DENIM SHORTS")
                    .description("Denim shorts with an elastic waistband and side pockets. Frayed hems.")
                    .category("shorts")
                    .tags("denim, shorts, elastic waistband")
                    .gender("kids")
                    .color("blue")
                    .cost(19.99)
                    .price(39.99)
                    .currentPrice(39.99)
                    .isTaxed(true)
                    .skuCode("6023")
                    .upcCode("729847566831")
                    .vendor("Spain")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "22.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "22-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 23) == null) {
            Product product = Product.builder()
                    .productId(23L)
                    .brand("ZARA")
                    .productName("PRINTED DRESS")
                    .description("Printed dress with a round neckline and short sleeves. Cotton fabric.")
                    .category("dresses")
                    .tags("printed, dress, cotton")
                    .gender("kids")
                    .color("pink")
                    .cost(24.99)
                    .price(49.99)
                    .currentPrice(49.99)
                    .isTaxed(true)
                    .skuCode("8321")
                    .upcCode("856924701238")
                    .vendor("Spain")
                    .points(25)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "23.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "23-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 24) == null) {
            Product product = Product.builder()
                    .productId(24L)
                    .brand("ZARA")
                    .productName("HOODED SWEATSHIRT")
                    .description("Hooded sweatshirt with a kangaroo pocket. Ribbed cuffs and hem. Soft cotton fabric.")
                    .category("sweatshirts")
                    .tags("hooded, sweatshirt, cotton")
                    .gender("kids")
                    .color("gray")
                    .cost(29.99)
                    .price(59.99)
                    .currentPrice(59.99)
                    .isTaxed(true)
                    .skuCode("9761")
                    .upcCode("603187654325")
                    .vendor("Spain")
                    .points(30)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "24.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "24-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 25) == null) {
            Product product = Product.builder()
                    .productId(25L)
                    .brand("ZARA")
                    .productName("CARGO PANTS")
                    .description("Cargo pants with multiple pockets and adjustable waistband. Cotton fabric.")
                    .category("pants")
                    .tags("cargo, pants, cotton")
                    .gender("kids")
                    .color("khaki")
                    .cost(34.99)
                    .price(69.99)
                    .currentPrice(69.99)
                    .isTaxed(true)
                    .skuCode("5435")
                    .upcCode("812309876546")
                    .vendor("Spain")
                    .points(35)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "25.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "25-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 26) == null) {
            Product product = Product.builder()
                    .productId(26L)
                    .brand("ZARA")
                    .productName("PRINTED SHIRT")
                    .description("Printed shirt with a button-up front and long sleeves. Linen fabric.")
                    .category("shirts")
                    .tags("printed, shirt, linen")
                    .gender("kids")
                    .color("blue")
                    .cost(19.99)
                    .price(39.99)
                    .currentPrice(39.99)
                    .isTaxed(true)
                    .skuCode("7546")
                    .upcCode("812345670236")
                    .vendor("Spain")
                    .points(20)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "26.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "26-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 27) == null) {
            Product product = Product.builder()
                    .productId(27L)
                    .brand("ZARA")
                    .productName("SWEATPANTS")
                    .description("Sweatpants with an elastic waistband and side pockets. Soft fleece interior.")
                    .category("pants")
                    .tags("sweatpants, fleece, elastic waistband")
                    .gender("kids")
                    .color("gray")
                    .cost(24.99)
                    .price(49.99)
                    .currentPrice(49.99)
                    .isTaxed(true)
                    .skuCode("8323")
                    .upcCode("856924701240")
                    .vendor("Spain")
                    .points(25)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "27.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "27-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 28) == null) {
            Product product = Product.builder()
                    .productId(28L)
                    .brand("ZARA")
                    .productName("PATTERNED JUMPSUIT")
                    .description("Patterned jumpsuit with short sleeves and a round neckline. Elastic waistband.")
                    .category("jumpsuits")
                    .tags("patterned, jumpsuit, elastic waistband")
                    .gender("kids")
                    .color("yellow")
                    .cost(29.99)
                    .price(59.99)
                    .currentPrice(59.99)
                    .isTaxed(true)
                    .skuCode("9763")
                    .upcCode("603187654327")
                    .vendor("Spain")
                    .points(30)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "28.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "28-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 29) == null) {
            Product product = Product.builder()
                    .productId(29L)
                    .brand("ZARA")
                    .productName("HOODED PUFFER JACKET")
                    .description("Hooded puffer jacket with front zip closure. Side pockets and elastic cuffs.")
                    .category("jackets")
                    .tags("hooded, puffer, elastic cuffs")
                    .gender("kids")
                    .color("blue")
                    .cost(49.99)
                    .price(99.99)
                    .currentPrice(99.99)
                    .isTaxed(true)
                    .skuCode("7561")
                    .upcCode("945612378909")
                    .vendor("Spain")
                    .points(40)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "29.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "29-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }

        if (productRepository.findProductByProductId((long) 30) == null) {
            Product product = Product.builder()
                    .productId(30L)
                    .brand("ZARA")
                    .productName("EMBROIDERED DRESS")
                    .description("Embroidered dress with short sleeves and a round neckline. Cotton fabric.")
                    .category("dresses")
                    .tags("embroidered, dress, cotton")
                    .gender("kids")
                    .color("white")
                    .cost(24.99)
                    .price(49.99)
                    .currentPrice(49.99)
                    .isTaxed(true)
                    .skuCode("6203")
                    .upcCode("704938567814")
                    .vendor("Spain")
                    .points(25)
                    .build();
            productRepository.save(product);
            File productImage1 = new File(path + fileDir + "30.jpeg");
            productServiceImp.imageLoader(productImage1, product);
            File productImage2 = new File(path + fileDir + "30-1.jpeg");
            productServiceImp.imageLoader(productImage2, product);
        }
    }
}
