package trade.dolcecosmetics.app.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trade.dolcecosmetics.app.R
import trade.dolcecosmetics.app.data.model.Product
import trade.dolcecosmetics.app.data.model.ProductCategory
import trade.dolcecosmetics.app.ui.composable.shared.LCDSMContentWrapper
import trade.dolcecosmetics.app.ui.composable.shared.LCDSMEmptyView
import trade.dolcecosmetics.app.ui.state.DataUiState
import trade.dolcecosmetics.app.ui.theme.DolceAccent
import trade.dolcecosmetics.app.ui.theme.DolceBorder
import trade.dolcecosmetics.app.ui.theme.DolcePrimary
import trade.dolcecosmetics.app.ui.theme.DolceSurface
import trade.dolcecosmetics.app.ui.theme.DolceTextPrimary
import trade.dolcecosmetics.app.ui.theme.DolceTextSecondary
import trade.dolcecosmetics.app.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

private data class HeroSlide(
    val imageRes: Int,
    val label: String,
    val title: String,
)

private val heroSlides = listOf(
    HeroSlide(R.drawable.img_hero_1, "NEW COLLECTION", "Radiant Skincare Essentials"),
    HeroSlide(R.drawable.img_hero_2, "BESTSELLER", "Signature Fragrance Collection"),
    HeroSlide(R.drawable.img_hero_3, "FEATURED", "Luxury Makeup Essentials"),
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@Composable
private fun HomeContent(
    productsState: DataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }

    Column(modifier = modifier.fillMaxSize()) {
        LCDSMContentWrapper(
            dataState = productsState,
            modifier = Modifier.fillMaxSize(),

            dataPopulated = {
                val products = (productsState as DataUiState.Populated).data
                val filtered = if (selectedCategory == null) products
                else products.filter { it.category == selectedCategory }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp),
                ) {
                    item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                        HeroCarousel()
                    }

                    item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                        CategoryFilterRow(
                            selectedCategory = selectedCategory,
                            onCategorySelected = { selectedCategory = it },
                        )
                    }

                    items(filtered, key = { it.id }) { product ->
                        ProductCard(
                            product = product,
                            onClick = { onNavigateToProductDetails(product.id) },
                        )
                    }
                }
            },

            dataEmpty = {
                LCDSMEmptyView(
                    primaryText = stringResource(R.string.products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun HeroCarousel() {
    val pagerState = rememberPagerState(pageCount = { heroSlides.size })

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
        ) { page ->
            val slide = heroSlides[page]
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(slide.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC1A1A1A)),
                                startY = 80f,
                            )
                        ),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp),
                ) {
                    Text(
                        text = slide.label,
                        color = DolceAccent,
                        fontSize = 10.sp,
                        letterSpacing = 3.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = slide.title,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(heroSlides.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(
                            width = if (pagerState.currentPage == index) 20.dp else 6.dp,
                            height = 6.dp,
                        )
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index) DolcePrimary
                            else Color(0xFFDDDDDD)
                        ),
                )
            }
        }
    }
}

@Composable
private fun CategoryFilterRow(
    selectedCategory: ProductCategory?,
    onCategorySelected: (ProductCategory?) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp),
    ) {
        item {
            CategoryChip(
                label = stringResource(R.string.category_all),
                isSelected = selectedCategory == null,
                onClick = { onCategorySelected(null) },
            )
        }
        items(ProductCategory.entries) { category ->
            CategoryChip(
                label = stringResource(category.titleRes),
                isSelected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
            )
        }
    }
}

@Composable
private fun CategoryChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(if (isSelected) DolcePrimary else DolceSurface)
            .border(
                width = 1.dp,
                color = if (isSelected) DolcePrimary else DolceBorder,
                shape = RoundedCornerShape(4.dp),
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            color = if (isSelected) Color.White else DolceTextPrimary,
            style = MaterialTheme.typography.labelMedium,
            letterSpacing = 0.5.sp,
        )
    }
}

@Composable
private fun ProductCard(
    product: Product,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() },
        color = DolceSurface,
        shadowElevation = 1.dp,
        shape = RoundedCornerShape(4.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, DolceBorder),
    ) {
        Column {
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
            )
            Column(
                modifier = Modifier.padding(12.dp),
            ) {
                Text(
                    text = stringResource(product.category.titleRes),
                    style = MaterialTheme.typography.labelSmall,
                    color = DolceTextSecondary,
                    letterSpacing = 1.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = DolcePrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "£%.2f".format(product.price),
                    style = MaterialTheme.typography.bodyMedium,
                    color = DolceAccent,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}
