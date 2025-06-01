package com.sukisu.ultra.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sukisu.ultra.ui.theme.CardConfig.cardAlpha

@Composable
fun SwitchItem(
    icon: ImageVector? = null,
    title: String,
    summary: String? = null,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    // 颜色动画
    val iconTint by animateColorAsState(
        targetValue = if (checked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        animationSpec = tween(300),
        label = "iconTint"
    )

    // 开关颜色
    val switchColors = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colorScheme.primary,
        checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
        checkedBorderColor = MaterialTheme.colorScheme.primary,
        checkedIconColor = MaterialTheme.colorScheme.onPrimary,
        uncheckedThumbColor = MaterialTheme.colorScheme.outline,
        uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
        uncheckedBorderColor = MaterialTheme.colorScheme.outline,
        uncheckedIconColor = MaterialTheme.colorScheme.surfaceVariant,
        disabledCheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        disabledCheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        disabledCheckedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        disabledCheckedIconColor = MaterialTheme.colorScheme.surfaceVariant,
        disabledUncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        disabledUncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        disabledUncheckedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        disabledUncheckedIconColor = MaterialTheme.colorScheme.surfaceVariant
    )

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = cardAlpha)
        )
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = Int.MAX_VALUE,
                    overflow = TextOverflow.Ellipsis
                )
            },
            supportingContent = summary?.let {
                {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            },
            leadingContent = icon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = iconTint
                    )
                }
            },
            trailingContent = {
                Switch(
                    checked = checked,
                    onCheckedChange = null,
                    enabled = enabled,
                    colors = switchColors
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = enabled) {
                    onCheckedChange(!checked)
                }
                .padding(vertical = 4.dp)
        )
    }
}