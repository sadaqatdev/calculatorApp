package com.example.calculatorapp.widgets


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val IconButtonSizeModifire = Modifier.size(40.dp)

@Composable
fun RoundedButton(
    modifier: Modifier,
    color: Color,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color,
    backgroundColor: Color,
    elevation: Dp = 4.dp
) {
    Card(modifier = modifier
        .padding(all = 4.dp)
        .clickable { onClick.invoke() }
        .then(IconButtonSizeModifire),
        shape = CircleShape,
        backgroundColor =backgroundColor,
        elevation = elevation,

    ) {

    }
}