package trade.dolcecosmetics.app.data.repository

import trade.dolcecosmetics.app.R
import trade.dolcecosmetics.app.data.model.Product
import trade.dolcecosmetics.app.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Radiance Serum",
            description = "A powerful Vitamin C brightening serum enriched with hyaluronic acid. Visibly reduces dark spots and boosts luminosity for a radiant, glass-skin glow. Suitable for all skin types.",
            category = ProductCategory.SKINCARE,
            price = 48.00,
            imageRes = R.drawable.img_serum,
        ),
        Product(
            id = 2,
            title = "Velvet Moisturizer",
            description = "A rich, deeply hydrating cream formulated with shea butter and botanical extracts. Melts into skin leaving a velvety finish that lasts all day. Perfect for dry and combination skin.",
            category = ProductCategory.SKINCARE,
            price = 36.00,
            imageRes = R.drawable.img_moisturizer,
        ),
        Product(
            id = 3,
            title = "Rose Gold Lipstick",
            description = "A luxurious long-lasting matte lipstick in the season's most coveted rose gold shade. Enriched with vitamin E for comfortable, all-day wear. Buildable colour payoff.",
            category = ProductCategory.MAKEUP,
            price = 24.00,
            imageRes = R.drawable.img_lipstick,
        ),
        Product(
            id = 4,
            title = "Silk Foundation",
            description = "A lightweight liquid foundation with SPF 30 that blurs imperfections and evens skin tone. Buildable coverage with a natural satin finish. 24-hour wear formula.",
            category = ProductCategory.MAKEUP,
            price = 42.00,
            imageRes = R.drawable.img_foundation,
        ),
        Product(
            id = 5,
            title = "Noir Eau de Parfum",
            description = "A sophisticated evening fragrance crafted with rare oud wood, warm vanilla and dark rose. An intoxicating blend that lingers beautifully on skin and fabric.",
            category = ProductCategory.FRAGRANCE,
            price = 68.00,
            imageRes = R.drawable.img_perfume,
        ),
        Product(
            id = 6,
            title = "Botanical Shampoo",
            description = "A gentle sulfate-free shampoo infused with argan oil, rosemary and green tea extracts. Cleanses deeply while preserving natural moisture balance for healthier, shinier hair.",
            category = ProductCategory.HAIRCARE,
            price = 18.00,
            imageRes = R.drawable.img_shampoo,
        ),
        Product(
            id = 7,
            title = "Cashmere Body Lotion",
            description = "An ultra-nourishing body lotion enriched with vitamin E, cashmere proteins and sweet almond oil. Absorbs instantly without greasiness, leaving skin irresistibly soft and smooth.",
            category = ProductCategory.BODY_CARE,
            price = 28.00,
            imageRes = R.drawable.img_body_lotion,
        ),
        Product(
            id = 8,
            title = "Eye Renewal Cream",
            description = "A targeted anti-aging eye cream with encapsulated retinol, peptides and caffeine. Visibly reduces fine lines, puffiness and dark circles for a wide-awake, youthful look.",
            category = ProductCategory.SKINCARE,
            price = 52.00,
            imageRes = R.drawable.img_eye_cream,
        ),
        Product(
            id = 9,
            title = "Bronzing Powder",
            description = "A silky, finely-milled bronzing powder that gives skin a natural sun-kissed glow. Buildable colour with subtle golden shimmer. Flatters every skin tone.",
            category = ProductCategory.MAKEUP,
            price = 30.00,
            imageRes = R.drawable.img_bronzer,
        ),
        Product(
            id = 10,
            title = "Jasmine Body Oil",
            description = "A luxurious dry body oil infused with pure jasmine extract, jojoba and rosehip oils. Sinks in immediately, leaving skin satin-smooth with an exquisite floral scent.",
            category = ProductCategory.BODY_CARE,
            price = 34.00,
            imageRes = R.drawable.img_body_oil,
        ),
        Product(
            id = 11,
            title = "Repair Hair Mask",
            description = "A deep conditioning treatment mask for damaged and chemically treated hair. Keratin proteins, argan oil and panthenol work together to restore strength, shine and softness.",
            category = ProductCategory.HAIRCARE,
            price = 22.00,
            imageRes = R.drawable.img_hair_mask,
        ),
        Product(
            id = 12,
            title = "Petal Blush",
            description = "A buildable powder blush in a delicate soft rose shade. The ultra-fine pigments blend seamlessly for a fresh, natural flush that lasts all day. Fragrance-free formula.",
            category = ProductCategory.MAKEUP,
            price = 26.00,
            imageRes = R.drawable.img_blush,
        ),
    )

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }
}
