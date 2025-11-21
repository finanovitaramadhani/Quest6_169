package com.example.navigasidata.view

import com.example.navigasidata.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.DividerDefaults.Thickness
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormIsian(
    pilihanJK: List<String>,
    onSubmitButtonClicked: (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
){
    var txtNama by rememberSaveable { mutableStateOf("")}
    var txtAlamat by remember { mutableStateOf("") }
    var txtGender by remember { mutableStateOf("")}

    val listData: MutableList<String> = mutableListOf(txtNama,txtGender,txtAlamat)

    Scaffold (modifier=Modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.form_pendaftaran)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = colorResource(id = R.color.teal_700))
            )}
    ) { isiRuang ->
        Column(
            modifier = Modifier.padding(paddingValues = isiRuang),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = txtNama,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 20.dp),
                label = { Text(text = "Nama Lengkap") },
                onValueChange = {
                    txtNama = it
                },
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(all = 20.dp)
                    .width(width = 250.dp),
                thickness = dimensionResource(id = R.dimen.thickness_Divider),
                color = Color.Blue
            )
            Row {
                pilihanJK.forEach { item ->
                    Row(
                        modifier = Modifier.selectable(
                            selected = txtGender == item,
                            onClick = {
                                txtGender = item
                            }
                        ),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = txtGender == item,
                            onClick = {
                                txtGender = item
                            }
                        )
                    }

                    Text(text = item)
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(all = 20.dp)
                    .width(width = 250.dp),
                thickness = 1.dp,
                color = Color.Red
            )
            OutlinedTextField(
                value = txtAlamat,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.width(250.dp),
                label = { Text(text = "Alamat Lengkap") },
                onValueChange = {
                    txtAlamat = it
                }
            )
            Spacer(modifier = Modifier.height(height = 20.dp))


        }
    }

}
