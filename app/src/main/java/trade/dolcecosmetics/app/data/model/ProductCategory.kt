package trade.dolcecosmetics.app.data.model

import androidx.annotation.StringRes
import trade.dolcecosmetics.app.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    SKINCARE(R.string.category_skincare),
    MAKEUP(R.string.category_makeup),
    FRAGRANCE(R.string.category_fragrance),
    HAIRCARE(R.string.category_haircare),
    BODY_CARE(R.string.category_body_care),
}
