@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.footballmanager.ui.goat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballmanager.ui.components.FootballPage
import com.example.footballmanager.ui.components.FootballScene
import com.example.footballmanager.ui.components.footballTopBarColors

@Composable
fun GoatScreen() {
    FootballPage(FootballScene.GOAT) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🐐", fontSize = 24.sp)
                            Spacer(Modifier.width(8.dp))
                            Text("The GOAT", fontWeight = FontWeight.Bold)
                        }
                    },
                    colors = footballTopBarColors()
                )
            }
        ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                HeroGoatCard()
            }

            item {
                Text(
                    text = "Career World Records & Stats",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }

            item {
                StatsGrid()
            }

            item {
                Text(
                    text = "Why Cristiano Ronaldo is The GOAT",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
            }

            item {
                ReasonCard(
                    icon = Icons.Filled.EmojiEvents,
                    title = "Dominance Across 4 Major Leagues",
                    description = "Cristiano Ronaldo is the only player in history to win domestic league titles, national cups, player of the year, and golden boots in the Premier League (England), La Liga (Spain), Serie A (Italy), and Saudi Pro League."
                )
            }

            item {
                ReasonCard(
                    icon = Icons.Filled.Star,
                    title = "The Champions League King",
                    description = "All-time top scorer in UEFA Champions League history with 140 goals. 5x UCL winner, 7x UCL top scorer, and the only player to score in 3 separate Champions League finals."
                )
            }

            item {
                ReasonCard(
                    icon = Icons.Filled.SportsSoccer,
                    title = "International Legend & Leader",
                    description = "All-time top international goalscorer in football history with 130+ goals for Portugal. Led his country to their first major trophies in history at UEFA Euro 2016 and the 2019 UEFA Nations League."
                )
            }

            item {
                ReasonCard(
                    icon = Icons.Filled.Star,
                    title = "The Ultimate Football Athlete",
                    description = "Possesses the complete attacking Arsenal: 900+ official career goals, world-record vertical jump height, unmatched header power, precise free-kicks, and relentless elite work ethic."
                )
            }
        }
        }
    }
}

@Composable
private fun HeroGoatCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🐐",
                    fontSize = 36.sp
                )
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Cristiano Ronaldo",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = "The Greatest Of All Time 🐐",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(Modifier.height(12.dp))

            Surface(
                color = Color.Black.copy(alpha = 0.25f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "\"Your love makes me strong, your hate makes me unstoppable.\"",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Composable
private fun StatsGrid() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            StatBox("900+", "Official Career Goals (#1 All-Time)", Modifier.weight(1f))
            StatBox("140", "Champions League Goals (#1 All-Time)", Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            StatBox("130+", "International Goals for Portugal (#1)", Modifier.weight(1f))
            StatBox("5", "Ballon d'Or Trophies", Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            StatBox("5x", "UEFA Champions League Titles", Modifier.weight(1f))
            StatBox("4x", "European Golden Shoes", Modifier.weight(1f))
        }
    }
}

@Composable
private fun StatBox(value: String, label: String, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReasonCard(icon: ImageVector, title: String, description: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                shape = CircleShape,
                modifier = Modifier.size(44.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
