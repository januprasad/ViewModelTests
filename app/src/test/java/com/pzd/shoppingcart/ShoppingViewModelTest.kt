package com.pzd.shoppingcart

import ShoppingViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingViewModelTest {

    private lateinit var viewModel: ShoppingViewModel

    @get:Rule
    var instantTaskExecuterRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field return error`() {
        viewModel.insertShoppingItem("name", "", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with max length name value return error`() {
        val string = buildString {
            for (i in 1..MAX_NAME_LENGTH + 1)
                append(1)
        }
        viewModel.insertShoppingItem(string, "", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with max length amount value return error`() {
        val string = buildString {
            for (i in 1..MAX_AMOUNT_LENGTH + 1)
                append(1)
        }
        viewModel.insertShoppingItem(string, "", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with correct name, amount return success`() {
        viewModel.insertShoppingItem("name", "23.0", "3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }
}
