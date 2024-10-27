package com.example.movies.presentation.screens.home.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.presentation.theme.MoviesTheme
import com.example.movies.presentation.theme.onSurface


@Composable
fun MySearchBar(
    content: String = "",
    onContentChange: (String) -> Unit = {},
    onSearch: (String) -> Unit = {},
    onFocusClear: () -> Unit = {},
    hideKeyboard: Boolean = false
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val containerColor = Color(0xFFE8E8E8)
    OutlinedTextField(
        value = content,
        onValueChange = onContentChange,
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,

            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            if (content.isNotEmpty()) {
                keyboardController?.hide()
                focusManager.clearFocus()
                onSearch(content)
            }
        }),
        singleLine = true,

        placeholder = {
            Text(
                text = stringResource(R.string.search_for_a_content),
                color = onSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        },

        shape = MaterialTheme.shapes.large,

        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Preview(showSystemUi = true)
@Composable
fun MySearchBarPreview() {
    MoviesTheme {
        MySearchBar()
    }
}