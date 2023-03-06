package de.hsaugsburg.oosd.artx.initialization;

import de.hsaugsburg.oosd.artx.models.Category;
import de.hsaugsburg.oosd.artx.models.Product;
import de.hsaugsburg.oosd.artx.models.User;
import de.hsaugsburg.oosd.artx.repositories.RoleRepository;
import de.hsaugsburg.oosd.artx.services.CategoryService;
import de.hsaugsburg.oosd.artx.services.ProductService;
import de.hsaugsburg.oosd.artx.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This initializes the test data (e.g. test users, products,...)
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final UserService userService;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ProductService productService, CategoryService categoryService, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void initialize() {

        // Test users
        userService.save(new User(1l, "test", "test@test.de", passwordEncoder.encode("test"), Arrays.asList(roleRepository.findRoleByName("ROLE_ADMIN")), new ArrayList<>()));
        userService.save(new User(2l, "test", "andyschmid3@gmail.com", passwordEncoder.encode("test"), Arrays.asList(roleRepository.findRoleByName("ROLE_ADMIN")), new ArrayList<>()));
        userService.save(new User(10l, "test", "erich.seifert@hs-augsburg.de", passwordEncoder.encode("test"), Arrays.asList(roleRepository.findRoleByName("ROLE_ADMIN")), new ArrayList<>()));

        // Test categories
        Category malerei = new Category(Category.CategoryType.MALEREI, "Malerei");
        Category plastik = new Category(Category.CategoryType.PLASTIK, "Plastik");
        Category grafik = new Category(Category.CategoryType.GRAFIK, "Grafik");
        Category fluegelaltar = new Category(Category.CategoryType.FLUEGELALTAR, "Fluegelaltar");
        Category cgi = new Category(Category.CategoryType.CGI, "CGI");
        categoryService.save(malerei);
        categoryService.save(plastik);
        categoryService.save(grafik);
        categoryService.save(fluegelaltar);
        categoryService.save(cgi);

        // Test products
        productService.save(new Product("Maria mit Kind und einem Mönch", "Parmigianino", new BigDecimal(10000.00), "1530", malerei, "Parmigianinos Madonnen zeichnen sich durch Grazie, Eleganz und ideale Schönheit aus.", "/images/products/pic_product_1.jpg"));
        productService.save(new Product("Bildnis des Pfalzgrafen Christian III. von Birkenfeld-Bischweiler-Rappoltstein", "Hyacinthe Rigaud", new BigDecimal(999.99), "1743", malerei, "Dieses Gemälde wurde 1901 aus Privatbesitz durch Ankauf erworben.", "/images/products/pic_product_2.jpg"));
        productService.save(new Product("Gerüst eines Neubaues", "Paul Klee", new BigDecimal(5600.00), "1930", malerei, "Dieses Gemälde wurde 1963 als Ankauf von Dr. Fritz und Dr. Peter Nathan, Zuerich erworben.", "/images/products/pic_product_3.jpg"));
        productService.save(new Product("Bildnis Gött", "Paul Roloff", new BigDecimal(4000.00), "1933", malerei, "Dieses Gemölde wurde 1937 erworben als Ankauf durch den Staatsminister des Inneren.", "/images/products/pic_product_4.jpg"));
        productService.save(new Product("König Max I. Joseph", "Herr Deutsch", new BigDecimal(3500.00), "1806", malerei, "1964 durch das Bayerische Staatsministerium des Inneren übertragen.", "/images/products/pic_product_5.jpg"));
        productService.save(new Product("Abendmahl", "Guglielmus Paludanus", new BigDecimal(2199.99), "1560", plastik, "Erworben 1807 als Säkularisationsgut aus der Dominikanerkirche Augsburg.", "/images/products/pic_product_6.jpg"));
        productService.save(new Product("Gegen den Wind Schreitender", "Adolf Hölzel", new BigDecimal(1000.00), "1925", grafik, "1964 als Schenkung von Sofie und Emanuel Fohn erworben.", "/images/products/pic_product_7.jpg"));
        productService.save(new Product("Die Madonna Tempi", "Raffael", new BigDecimal(3000.00), "1507", malerei, "1829 für König Ludwig I. aus dem Hause Tempi in Florenz erworben.", "/images/products/pic_product_8.jpg"));
        productService.save(new Product("Kirchliche Szene", "Raffael", new BigDecimal(10000.00), "1722", grafik, "Dieses Gemälde stellt eine kirchliche Szene dar.", "/images/products/pic_product_9.jpg"));
        productService.save(new Product("Kauernde", "Auguste Rodin", new BigDecimal(80000.00), "1880", plastik, "1912 als Schenkung im Rahmen der Tschudi-Spende erworben.", "/images/products/pic_product_10.jpg"));
        productService.save(new Product("Tirol", "Franz Marc", new BigDecimal(4999.99), "1914", malerei, "1949 als Ankauf von Maria Marc, Benediktbeuern erworben.", "/images/products/pic_product_11.jpg"));
        productService.save(new Product("Jenenser Student", "Ferdinand Hodler", new BigDecimal(550.00), "1908", malerei, "1912 als Schenkung von Friedrich Wilhelm Freiherr von Bissing im Rahmen der Tschudi-Spende erworben.", "/images/products/pic_product_12.jpg"));
        productService.save(new Product("Double Loop", "Lászlo Moholy-Nagy", new BigDecimal(1299.99), "1946", plastik, "Dieses Gemälde zeigt transparenten Kunststoff, thermisch verformt.", "/images/products/pic_product_13.jpg"));
        productService.save(new Product("La Bouteille de Bordeaux", "Juan Gris", new BigDecimal(40000.00), "1915", malerei, "1971 als Vermächtnis von Theodor und Woty Werner erworben.", "/images/products/pic_product_14.jpg"));
        productService.save(new Product("Hausaltärchen Kleiner Dom", "Kölnisch", new BigDecimal(5000.00), "1370", fluegelaltar, "Dieses Gemälde ist bestückt mit plastischer Verkündigungsgruppe (Schrein), Szenen aus der Kindheit Jesu und Heiligen Flügelaußenseiten: Verkündigung.", "/images/products/pic_product_15.jpg"));
        productService.save(new Product("Reliquienaltärchen mit Verkündigung", "Kölnisch", new BigDecimal(1250.00), "1349", fluegelaltar, "Das Gemälde zeigt die Geburt Christi, Marienkrönung, Taufe Christi und nicht zugehöriger Elfenbeinstatuette der Muttergottes Flügelaußenseite.", "/images/products/pic_product_16.jpg"));
        productService.save(new Product("A 3D render of an astronaut walking in a green desert", "DallE", new BigDecimal(100.00), "2022", cgi, "Dieses Bild wurde von einer KI erstellt. Der Titel ist das Einzige, was ein Mensch zusteuerte...", "/images/products/pic_product_17.jpg"));
        productService.save(new Product("Merkur in Herse verliebt", "Jan Böckhorst", new BigDecimal(12340.00), "1655", malerei, "Jan Boeckhorst, Merkur in Herse verliebt, um 1655, Bayerische Staatsgemäldesammlungen - Staatsgalerie im Neuen Schloss Schleißheim", "/images/products/pic_product_18.jpg"));
    }

    @Override
    public void run(String... args) {
        initialize();
    }
}
