package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    var isEditMode by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("Bavly Safwat") }
    var jobTitle by remember { mutableStateOf("Android Developer") }
    var bio by remember { mutableStateOf("Passionate about clean code.") }

    var nameError by remember { mutableStateOf(false) }
    var jobTitleError by remember { mutableStateOf(false) }
    var bioError by remember { mutableStateOf(false) }

    val phone = "+20 1280999227"
    val email = "bavlysafwatt@gmail.com"

    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "Profile",
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (isEditMode) {
                EditableField(
                    label = "Name",
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = false
                    },
                    isError = nameError
                )
                EditableField(
                    label = "Job Title",
                    value = jobTitle,
                    onValueChange = {
                        jobTitle = it
                        jobTitleError = false
                    },
                    isError = jobTitleError
                )
                EditableField(
                    label = "Bio",
                    value = bio,
                    onValueChange = {
                        bio = it
                        bioError = false
                    },
                    isError = bioError
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    val validName = name.isNotBlank()
                    val validJob = jobTitle.isNotBlank()
                    val validBio = bio.isNotBlank()

                    nameError = !validName
                    jobTitleError = !validJob
                    bioError = !validBio

                    if (validName && validJob && validBio) {
                        isEditMode = false
                        Toast.makeText(context, "Profile saved successfully", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text("Save")
                }
            } else {
                Text("Name: $name", style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold), color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Job: $jobTitle", style = TextStyle(fontSize = 20.sp), color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Bio: $bio", style = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center), color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Phone: $phone", style = TextStyle(fontSize = 20.sp), color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Email: $email", style = TextStyle(fontSize = 20.sp), color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { isEditMode = true }) {
                    Text("Edit Profile")
                }
            }
        }
    }
}

@Composable
fun EditableField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.Black) },
        textStyle = TextStyle(color = Color.Black),
        singleLine = true,
        isError = isError,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        ProfileCard()
    }
}