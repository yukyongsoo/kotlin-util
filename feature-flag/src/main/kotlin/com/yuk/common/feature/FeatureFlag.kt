package com.yuk.common.feature

inline fun featureFlag(
    strategy: FeatureFlagStrategy,
    onFlag: () -> Unit,
    offFlag: () -> Unit = { },
) {
    if (strategy.isEnabled()) {
        onFlag()
    } else {
        offFlag()
    }
}

inline fun <T> featureFlagWithResult(
    strategy: FeatureFlagStrategy,
    onFlag: () -> T,
    offFlag: () -> T,
): T =
    if (strategy.isEnabled()) {
        onFlag()
    } else {
        offFlag()
    }

inline fun <T> String.featureFlagWithResult(
    onFlag: () -> T,
    offFlag: () -> T,
): T =
    featureFlagWithResult(
        FeatureFlagStrategyFactory.get(this),
        onFlag,
        offFlag,
    )

inline fun String.featureFlag(
    onFlag: () -> Unit,
    offFlag: () -> Unit = {},
) = featureFlag(
    FeatureFlagStrategyFactory.get(this),
    onFlag,
    offFlag,
)

fun String.featureEnabled(): Boolean = FeatureFlagStrategyFactory.get(this).isEnabled()
