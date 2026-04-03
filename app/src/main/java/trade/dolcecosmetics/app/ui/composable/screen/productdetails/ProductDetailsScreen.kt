package trade.dolcecosmetics.app.ui.composable.screen.productdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import trade.dolcecosmetics.app.R
import trade.dolcecosmetics.app.data.model.Product
import trade.dolcecosmetics.app.ui.composable.shared.LCDSMContentWrapper
import trade.dolcecosmetics.app.ui.composable.shared.LCDSMEmptyView
import trade.dolcecosmetics.app.ui.state.DataUiState
import trade.dolcecosmetics.app.ui.theme.DolceAccent
import trade.dolcecosmetics.app.ui.theme.DolceBorder
import trade.dolcecosmetics.app.ui.theme.DolcePrimary
import trade.dolcecosmetics.app.ui.theme.DolceTextPrimary
import trade.dolcecosmetics.app.ui.theme.DolceTextSecondary
import trade.dolcecosmetics.app.ui.viewmodel.ProductDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart,
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: DataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        LCDSMContentWrapper(
            dataState = productState,
            modifier = Modifier.fillMaxSize(),

            dataPopulated = {
                val product = (productState as DataUiState.Populated).data
                ProductDetailsLayout(product = product, onAddToCart = onAddToCart)
            },

            dataEmpty = {
                LCDSMEmptyView(
                    primaryText = stringResource(R.string.product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun ProductDetailsLayout(
    product: Product,
    onAddToCart: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        Image(
            painter = painterResource(product.imageRes),
            contentDescription = stringResource(R.string.product_image_description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(24.dp),
        ) {
            Text(
                text = stringResource(product.category.titleRes).uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = DolceTextSecondary,
                letterSpacing = 2.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.headlineSmall,
                color = DolcePrimary,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Text(
                    text = "£%.2f".format(product.price),
                    style = MaterialTheme.typography.titleLarge,
                    color = DolceAccent,
                    fontWeight = FontWeight.Normal,
                    fontSize = 26.sp,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalDivider(color = DolceBorder, thickness = 1.dp)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Description",
                style = MaterialTheme.typography.labelMedium,
                color = DolceTextSecondary,
                letterSpacing = 1.5.sp,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge,
                color = DolceTextPrimary,
                lineHeight = 28.sp,
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onAddToCart() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DolcePrimary,
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = stringResource(R.string.button_add_to_cart_label).uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    letterSpacing = 2.sp,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
